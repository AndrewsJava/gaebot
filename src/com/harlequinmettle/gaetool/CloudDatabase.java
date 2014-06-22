package com.harlequinmettle.gaetool;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.TreeSet;

import com.google.appengine.api.datastore.Blob;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Query;

public class CloudDatabase implements Qi, Yi, DBLabels {

	private long time = System.currentTimeMillis();
	final TreeMap<Float, float[][]> DB_FUND = new TreeMap<Float, float[][]>();
	final TreeMap<Float, float[][][]> DB_TECH = new TreeMap<Float, float[][][]>();
	// public final TreeMap<Float, float[][]> DB_COMP = new TreeMap<Float,
	// float[][]>();

	final TreeMap<Float, Float> MARKETCHANGE = new TreeMap<Float, Float>();
	private final TreeMap<Float, Float> MARKET = new TreeMap<Float, Float>();

	final TreeMap<Float, float[]> PRICES = new TreeMap<Float, float[]>();
	final TreeMap<Float, float[]> UNFORESEEN = new TreeMap<Float, float[]>();

	public ArrayList<StatInfo> statistics = new ArrayList<StatInfo>();
	private static final String[] dbSet = concat(QQ, YY);
	static final String[] LABELS = concat(labels, COMPUTED);
	private int oks = 0;

	public int errors = 0;
	public int priceArraySize = 0;

	CloudDatabase() {
 
	//	DSBlobs.postMemoryData("preload memory");
		TreeMap<Float, float[][]> nasF = new TreeMap<Float, float[][]>(
				DSBlobs.loadFundamentals(DSUtil.NASDAQ_FUNDAMENTALS));
		TreeMap<Float, float[][]> nyF = new TreeMap<Float, float[][]>(
				DSBlobs.loadFundamentals(DSUtil.NYSE_FUNDAMENTALS));
		TreeMap<Float, float[][][]> nasT = new TreeMap<Float, float[][][]>(
				DSBlobs.loadTechnicals(DSUtil.NASDAQ_TECHNICALS));
		TreeMap<Float, float[][][]> nyT = new TreeMap<Float, float[][][]>(
				DSBlobs.loadTechnicals(DSUtil.NYSE_TECHNICALS));

		joinAllArraysInFundamentalsTreeMaps(nasF, nyF);
		joinAllArraysInTechnicalsTreeMaps(nasT, nyT);
		appendComputedFactors();
		loadPriceData();
		computePriceChanges();

		for (int i = 0; i < LABELS.length; i++) {
			statistics.add(generateStatistics(i));
		}
		DataAnalyzer.setWeeks(DB_FUND.size()); 
		long elaps = System.currentTimeMillis() - time;
		elaps /= 1000;

		DSUtil.addToDatastoreAsText(DSUtil.DEBUG, "DatabaseCreated "
				+ DSBlobs.DATE.format(new Date()), "data", "" + elaps
				+ " seconds " + MARKETCHANGE.values().toString());// /////////////////////////////////////////

		//DSBlobs.postMemoryData("Data Loaded");
		if (false) {
			for (int i = 0; i < DBLabels.labels.length; i++) {
				StatInfo stat = statistics.get(i);
				DSUtil.addToDatastoreAsText(DSUtil.DEBUG, DBLabels.labels[i]
						+ "  (" + stat.dataQuality + ")", "data", "" + "<"
						+ stat.emin + "," + stat.emax + ">  " + "["
						+ stat.median + "]");// /////////////////////////////////////////

			}

			DSUtil.addToDatastoreAsText(DSUtil.DEBUG,
					"computed market changes", "data", "" + elaps + " seconds "
							+ MARKETCHANGE.values().toString());// /////////////////////////////////////////
			DSUtil.addToDatastoreAsText(DSUtil.DEBUG,
					"price read:  error / ok", "data", "" + errors + "  /  "
							+ oks);// /////////////////////////////////////////
			int size = dbSet.length;

			DSUtil.addToDatastoreAsText(DSUtil.DEBUG, "sizes: ", "data",
					"set: " + size + " Price set: " + priceArraySize);// /////////////////////////////////////////

			for (int i = 0; i < 10; i++) {
				int random = (int) (Math.random() * size);
				String prices = "";
				for (float[] priceData : PRICES.values()) {
					prices += "" + priceData[random] + "  ";
				}
				DSUtil.addToDatastoreAsText(DSUtil.DEBUG, "z-->"
						+ dbSet[random] + "  " + random, "data", prices);// ///////////////////////////////////
			}
		}
	}

	private void appendComputedFactors() {
		for (Entry<Float, float[][]> ent : DB_FUND.entrySet()) {
			float key = ent.getKey();
			float[][] data = ent.getValue();

			for (int i = 0; i < data.length; i++) {
				float[] newData = new float[DBLabels.COMPUTED.length];
				float[] oldData = data[i];
				newData[0] = oldData[40] / oldData[41];//
				newData[1] = oldData[69] / oldData[70];//

				//"Market Cap", // 0 --   38
				//"Shares Outstanding",// 35 --  73
				// must have one for each label in computed
				// //
				// /
				// /WORK WORK WORK
				// //
				// /
				// /WORK WORK WORK
				// //
				// /
				// /WORK WORK WORK
				// //
				// /
				// /WORK WORK WORK
				// //
				// /
				// /WORK WORK WORK
				// //
				// /
				// /WORK WORK WORK
				data[i] = concat(oldData, newData);
			}

		}
	}

	public StatInfo generateStatistics(int id) {
		ArrayList<Float> stats = new ArrayList<Float>();
		for (Entry<Float, float[][]> ent : DB_FUND.entrySet()) {
			float[][] dats = ent.getValue();
			for (float[] d : dats) {
				stats.add(d[id]);
			}
		}
		return new StatInfo(stats, id);
	}

	private void computePriceChanges() {
		// Collection pcs = prices.values();
		TreeSet<Float> dates = new TreeSet<Float>(PRICES.keySet());
		// float[][] asArray = new float[dates.size()][dbSet.length];
		// allocate variables
		float[] startPrices = null;
		float[] endPrices;
		Iterator<Float> datadays = dates.iterator();
		float startDate = 0;
		// get initial prices array and timekey
		if (datadays.hasNext()) {
			startDate = datadays.next();
			startPrices = PRICES.get(startDate);
		}
		// cycle through the rest calculating changes
		while (datadays.hasNext()) {
			float endDate = datadays.next();
			endPrices = PRICES.get(endDate);
			// calculates price change array and adds it to UNFORESEEN
			// System.out.println("start: " + Arrays.toString(startPrices));
			// System.out.println("end  : " + Arrays.toString(endPrices));
			calculateAllChangesForInterval(startDate, startPrices, endPrices);
			startDate = endDate;
			startPrices = endPrices;
		}
		priceArraySize = startPrices.length;

	}

	private void calculateAllChangesForInterval(float startDate,
			float[] startPrices, float[] endPrices) {
		float[] changes = new float[dbSet.length];
		for (int i = 0; i < dbSet.length; i++) {
			if (endPrices[i] != endPrices[i]
					|| startPrices[i] != startPrices[i])
				errors++;
			else
				oks++;
			// can be NaN for now
			if (startPrices[i] > 0)
				changes[i] = (endPrices[i] - startPrices[i]) / startPrices[i];
			else
				changes[i] = 0.0f / 0.0f;// NaN
			// changes[i] = (endPrices[i]/startPrices[i] ) -1 ;//alternate
			// method
		}

		// System.out.println("changes: 	" + Arrays.toString(changes));
		MARKETCHANGE.put(startDate, averageMarketChange(changes));
		UNFORESEEN.put(startDate, changes);
	}

	private Float averageMarketChange(float[] changes) {
		float sum = 0;
		float valid = 0;
		for (float f : changes) {
			if (f == f) {
				sum += f;
				valid++;
			}
		}
		System.out.println("SUMMING GIVES :   " + sum);
		return sum / valid;
	}

	private void joinAllArraysInTechnicalsTreeMaps(
			TreeMap<Float, float[][][]> nasT, TreeMap<Float, float[][][]> nyT) {

		Iterator<Entry<Float, float[][][]>> nq = nasT.entrySet().iterator();
		Iterator<Entry<Float, float[][][]>> ny = nyT.entrySet().iterator();

		while (nq.hasNext() && ny.hasNext()) {

			Entry<Float, float[][][]> nnq = nq.next();
			float qdate = nnq.getKey();// using nasdaq week number; ignore nyse
										// number

			float[][][] completeData = concat3d(nnq.getValue(), ny.next()
					.getValue());

			DB_TECH.put(qdate, completeData);
		}

	}

	private void joinAllArraysInFundamentalsTreeMaps(
			TreeMap<Float, float[][]> nasF, TreeMap<Float, float[][]> nyF) {

		Iterator<Entry<Float, float[][]>> nq = nasF.entrySet().iterator();
		Iterator<Entry<Float, float[][]>> ny = nyF.entrySet().iterator();
		while (nq.hasNext() && ny.hasNext()) {

			Entry<Float, float[][]> nnq = nq.next();
			float qdate = nnq.getKey();// using nasdaq week number; ignore nyse
										// number
			// possibly check nyse date with nasdaq date for <3 difference
			float[][] completeData = concat2d(nnq.getValue(), ny.next()
					.getValue());

			DB_FUND.put(qdate, completeData);
		}

	}

	private void loadPriceData() {
		for (Entry<Float, float[][][]> priceVolData : DB_TECH.entrySet()) {
			float[] weeksPrices = new float[dbSet.length];
			// int j, float days, float[] weeksPrices, float[][] dailyData
			float days = priceVolData.getKey();
			float[][][] setOfPriceData = priceVolData.getValue();
			for (int j = 0; j < setOfPriceData.length; j++) {
				float[][] dailyData = setOfPriceData[j];
				// days if float rep of this weeks date of collection -
				// dailyData[0][0]
				// is most recent dayNumber representation of historic prices
				// data
				// dailyData[0][6] is adjClose of most recent day
				if (days - dailyData[0][0] < 4 && days - dailyData[0][0] >= 0) {
					weeksPrices[j] = dailyData[0][6];
				} else {
					weeksPrices[j] = Float.NaN;
				}
			}
			PRICES.put(days, weeksPrices);
			float marketSum = sum(weeksPrices);
			MARKET.put(days, marketSum);
		}

	}

	private Float sum(float[] weeksPrices) {
		float sum = 0;
		for (float f : weeksPrices) {
			if (f == f) {
				sum += f;
			}
		}
		return sum;
	}

	static float[][][] concat3d(float[][][] A, float[][][] B) {
		int aLen = A.length;
		int bLen = B.length;
		float[][][] C = new float[aLen + bLen][][];
		System.arraycopy(A, 0, C, 0, aLen);
		System.arraycopy(B, 0, C, aLen, bLen);
		return C;
	}

	static float[][] concat2d(float[][] A, float[][] B) {
		int aLen = A.length;
		int bLen = B.length;
		float[][] C = new float[aLen + bLen][];
		System.arraycopy(A, 0, C, 0, aLen);
		System.arraycopy(B, 0, C, aLen, bLen);
		return C;
	}

	float[] concat(float[] A, float[] B) {
		int aLen = A.length;
		int bLen = B.length;
		float[] C = new float[aLen + bLen];
		System.arraycopy(A, 0, C, 0, aLen);
		System.arraycopy(B, 0, C, aLen, bLen);
		return C;
	}

	static String[] concat(String[] A, String[] B) {
		int aLen = A.length;
		int bLen = B.length;
		String[] C = new String[aLen + bLen];
		System.arraycopy(A, 0, C, 0, aLen);
		System.arraycopy(B, 0, C, aLen, bLen);
		return C;
	}

}
