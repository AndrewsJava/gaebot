package com.harlequinmettle.gaetool;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;

import com.google.appengine.api.ThreadManager;
import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskOptions;

public class DataAnalyzer implements DBAnalysisPriorities {

	public static final CloudDatabase CD = new CloudDatabase();

	// apply limits to data indicators to contsruct bundles - then calculate
	// value changes
	public static AtomicInteger counter = new AtomicInteger();
	public static volatile float sumation = 0;

	private final TreeMap<Integer, Pair> LIMITS = new TreeMap<Integer, Pair>();
	private final TreeMap<Float, ArrayList<Integer>> BUNDLES = new TreeMap<Float, ArrayList<Integer>>();
	private final static ArrayList<Float> scores = new ArrayList<Float>();
	private static float best = 0.0f;
	private static float worst = Float.MAX_VALUE;
	private TreeMap<Float, TreeMap<Integer, Pair>> results = new TreeMap<Float, TreeMap<Integer, Pair>>();
	private static int WEEKS;
	private final float[] TOTAL_PROB = CD.concat(PROBABILITIES,
			PROBABILITIES_comp);

	private static final int SAVE_SIZE = 200;

	static void doDBAnalysis(final String threadID) {
		DSBlobs.deleteTempBlobs();// /really don't want them hanging around

		if (false)
			DSUtil.addToDatastoreAsString(DSUtil.THREAD, threadID
					+ "<ANALYSIS>", "startTime",
					DSBlobs.DATE.format(new Date()));
		// LOAD UP ALL OLD RESULTS
		// EMAIL SELF A TEXT REPORT
		Thread thread = ThreadManager.createBackgroundThread(new Runnable() {

			public void run() {
				final DataAnalyzer DA = new DataAnalyzer();

				int i = 0;
				int j = 0;
				long timing = System.currentTimeMillis();
				while (true) {
					int numberOfLimits = 1;
					DA.establishSomeLimits(numberOfLimits);
					while (!DA.testLimitsOnDataSets()) {
						// no zero sized bundles
						DA.establishSomeLimits(numberOfLimits);
					}
					// DA.addResultsToMap();

					if (i++ == SAVE_SIZE) {
						i = 0;

						String savingName = "(>" + DA.best + " - (" + DA.worst
								+ ")<)";

						TreeMap<Integer, Pair> bestParams = DA.results
								.get(-DA.best);
						for (Entry<Integer, Pair> entr : bestParams.entrySet()) {
							savingName += ">" + DBLabels.labels[entr.getKey()]
									+ "<";
							savingName += "|" + entr.getValue() + "|";
						}

						savingName += DSBlobs.DATE.format(new Date());
						// LOOP TO GET INDEX AND CONVERT TO TITLE

						DSBlobs.object_asBlob_toDatastore_forDB(savingName,
								DSBlobs.P_RESULTS, DA.results);
						DA.resetBest();
						DA.results.clear(); 
							// DSBlobs.postMemoryData("DA RUN set complete " +
							// j);
						 
							if (false) {
								Queue queue = QueueFactory
										.getQueue("emaildata-queue");
								queue.add(TaskOptions.Builder.withUrl(
										"/emailtask").param(
										EmailTask.PARAMETER,
										"" + EmailTask.ANALYSIS_RESULTS));//
							}

							DSBlobs.postToDebug("(" + best + "->"
									+ (sumation / counter.get()) + "<-" + worst
									+ ")");
							StatInfo STAT = new StatInfo(scores, 0);
							DSBlobs.postToDebug(STAT.toString());
					 
					}

					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}
		});
		thread.start();

	}

	public void resetBest() {
		best = 0;
		worst = Float.MAX_VALUE;
	}

	private static Pair compareSets(ArrayList<Integer> lastSet,
			ArrayList<Integer> valids) {
		int sell = 0;
		int buy = 0;

		for (Integer I : lastSet) {
			if (!valids.contains(I))
				sell++;
		}
		for (Integer I : valids) {
			if (!lastSet.contains(I))
				buy++;
		}

		return new Pair(buy, sell);
	}

	public void establishSomeLimits(int nLims) {
		LIMITS.clear();
		int i = 0;
		while (LIMITS.size() < nLims) {
			int categorie = (int) (Math.random() * TOTAL_PROB.length);

			if (Math.random() > TOTAL_PROB[categorie])
				continue;
			float min = CD.statistics.get(categorie).emin;
			float max = CD.statistics.get(categorie).emax;
			float range = max - min;

			float myMin = (float) (min + Math.random() * 0.98 * range);
			float myRange = max - myMin;
			float myMax = (float) (myMin + Math.random() * myRange);
			LIMITS.put(categorie, new Pair(myMin, myMax));
			if (i++ > 100000)
				break;
		}

	}

	// evaluate current (randomly selected limits) - compare value to market
	// consider:
	// 1.does each bundle beat overall market
	// 2.do 'buys' and 'sells' pay off
	// 3.how much do bundles beat market
	public boolean testLimitsOnDataSets() {
		BUNDLES.clear();
		// BUNDLES_CHANGES.clear();
		// BUNDLES_SIZES.clear();
		// SET_CHANGES.clear();
		for (Entry<Float, float[][]> ent : CD.DB_FUND.entrySet()) {
			final float date = ent.getKey();
			final float[][] data = ent.getValue();
			ArrayList<Integer> bundle = new ArrayList<Integer>();
			for (int i = 0; i < data.length; i++) {
				// include this symbol unless its values don't meet the
				// requirements
				boolean isIn = true;

				for (Entry<Integer, Pair> lims : LIMITS.entrySet()) {
					int id = lims.getKey();// the id of the financial data
											// parameter
					float lowlimit = lims.getValue().low;// lower limit
					float highlimit = lims.getValue().high;// upper limit
					// if data is invalid , or outside the limits exclude i
					// for each id restriction limit bundle to valid data (not
					// nan) and withing range

					if (data[i][id] != data[i][id] || data[i][id] < lowlimit
							|| data[i][id] > highlimit) {
						isIn = false;
						// doesn't meet criteria nan or out of range
						// therefore
						// dont store it
						// in fact no need to continue for this i
						break;
					}

				}
				// if all data points are satisfied add to bundle
				if (isIn)
					bundle.add(i);
			}

			if (bundle.size() == 0)
				return false;

			BUNDLES.put(date, bundle);
		}

		// .....................................
		// out of loop we should have bundles for each date which meet the
		// requirements
		// compute the value of the bundles using prices

		float score = 0.0f;
		float beatScore = 0.0f;
		float percentScore = 0.0f;

		ArrayList<Integer> lastSet = new ArrayList<Integer>();

		for (Entry<Float, ArrayList<Integer>> entr : BUNDLES.entrySet()) {

			float date = entr.getKey();
			ArrayList<Integer> valids = entr.getValue();
			// if(valids.size()==0)continue;
			// CD.postMemoryData("bundle loop "+date);
			// final float[] prcs = CD.PRICES.get(date);
			if (!CD.UNFORESEEN.containsKey(date))
				break;

			final float[] future = CD.UNFORESEEN.get(date);

			final float marketChange = CD.MARKETCHANGE.get(date);

			int sellCount = 0;
			int buyCount = 0;
			float changeSum = 0;
			float buySum = 0;
			float sellSum = 0;

			for (int include : valids) {
				if (future[include] == future[include]) {
					changeSum += future[include];
				}
			}

			for (int include : valids) {
				if (future[include] == future[include]) {
					if (!lastSet.contains(include)) {
						buySum += future[include];
						buyCount++;
					}
				}
			}

			for (int lastIncluded : lastSet) {
				if (future[lastIncluded] == future[lastIncluded]) {
					if (!valids.contains(lastIncluded)){
						sellSum += future[lastIncluded];
					sellCount++;
					}
				}
			}

			float compareBundle = changeSum - marketChange;
			float compareBuy = buySum - marketChange;
			float compareSell = sellSum - marketChange;

			beatScore += compareBundle * 1000000000;

			beatScore += compareBuy * 1000000;

			beatScore += compareSell * 1000;

			percentScore += (changeSum / valids.size());
			if (buyCount > 0)
				percentScore += compareBuy / buyCount;
			if (sellCount > 0)
				percentScore -= compareSell / sellCount;
			lastSet = valids;

		}
		beatScore /= WEEKS;
		// drop decimal
		beatScore = (int) beatScore;

		percentScore /= WEEKS;

		score = beatScore + percentScore;
		// SAVE STATICSTICS ON INDIVIDUAL LOW/HIGH LIMITS AND
		scores.add(score);
		counter.getAndIncrement();
		sumation += score;
		if (score > best)
			best = score;
		if (score < worst)
			worst = score;
		// either put negative or use navagablemap.reverse
		results.put(-score, new TreeMap<Integer, Pair>(LIMITS));
		return true;
	}

	public static void setWeeks(int size) {
		WEEKS = size;
	}

}
