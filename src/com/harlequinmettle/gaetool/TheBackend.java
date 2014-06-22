package com.harlequinmettle.gaetool;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import com.google.appengine.api.LifecycleManager;
import com.google.appengine.api.ThreadManager;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskOptions;

public class TheBackend implements Qi, Yi // NASDAQ, NYSE //
{
	public static final int THREADMAX = 4;
	public static volatile int threadStarts = 0;
	public static long time;
	public static String fileTitleQ = "date not setx";
	public static String fileTitleY = "date not setxx";

	public static volatile boolean complete = false;
	public static AtomicBoolean emailSent = new AtomicBoolean(false);

	public static final SimpleDateFormat DATE = new SimpleDateFormat(
			"MMM-dd' at 'HH:mm:ss");
	public static String currentTicker = "";
	// sum of all tickers to eval
	// public static int totalSize;
	// later read from datastore to alter which thread to run
	public static int threadAction = 10000000;
	public static float officialStartTime;

	// // REPLACE DBSETTING IN VAROIS SERVLETS AND IN ANDROID APP GAECONNECT - 0
	// 2 -> 2000 10000
	public static final int ACTION_SMALL = 2000;// 2mb * 2 //replace all
												// instances of 0
	public static final int ACTION_BIG = 10000; // 10mb * 2 // replace all
												// instances of 2

	// used to identify individual thread progress
	static ConcurrentHashMap<String, AtomicInteger> vars = new ConcurrentHashMap<String, AtomicInteger>();

	static ConcurrentHashMap<String, TreeMap<String, String>> saveProgress = new ConcurrentHashMap<String, TreeMap<String, String>>();

	static ConcurrentHashMap<String, String> completeResults = new ConcurrentHashMap<String, String>();

	static ConcurrentLinkedQueue<String> tickers = new ConcurrentLinkedQueue<String>();
	// private static ConcurrentSkipListSet<String> tickers = new
	// ConcurrentSkipListSet<String>();
	private static float lastCompletionDateSM, lastCompletionDateLG;
	public static int BATCH = 21;
	public static boolean stopURLFetch = false;
	public static boolean restart = true;
	public volatile static double big_Count = 0;

	// public static AtomicLong atomic_counter = new AtomicLong();

	static {

		// System.exit(0);
		if (restart) {
			int i = 1;
			// threadAction 0,2,10000
			// lastDateLG 15000 and up
			// lastDateSM 15000 and up
			// if (tickers.size() == 0)
			// resets those 3 vars from datastore values
			initFromDS();
			if (false)
				DSUtil.addToDatastoreAsString(DSUtil.THREAD,
						DATE.format(new Date()) + "<>" + i++, "STATICBLOCK",
						"" + threadAction + "  " + lastCompletionDateSM + "  "
								+ lastCompletionDateLG + "  <" + tickers.size()
								+ ">");

			chooseInitialStateFromDate();
			if (false)
				DSUtil.addToDatastoreAsString(DSUtil.THREAD,
						DATE.format(new Date()) + "<STATIC>" + i++,
						"startTime", "" + threadAction + "  "
								+ lastCompletionDateSM + "  "
								+ lastCompletionDateLG + "  <" + tickers.size()
								+ ">");

		}
	}

	// called each time (apparently) a new backend is launched by static block
	// runInBackend will be called immediately after
	private static void chooseInitialStateFromDate() {
		//DSBlobs.postMemoryData("cron_A: " + threadAction);
		// if this was not called from cron then its operated manually

		if (threadAction == 10000) {// 10000 called from cron (or generalthread
			// from phone)
			int currently = (int) (System.currentTimeMillis() / 1000 / 3600 / 24);
			Calendar cal = Calendar.getInstance();
			cal.setTimeZone(TimeZone.getTimeZone("GMT-5"));
			int day = cal.get(Calendar.DAY_OF_WEEK);
			// last completion just set from DS
			boolean timeToCollectSM = currently > 4 + lastCompletionDateSM;
			boolean timeToCollectLG = currently > 4 + lastCompletionDateLG;
			// friday EST is over it is the weekend

			if (true || day == Calendar.SATURDAY || day == Calendar.SUNDAY) {
				// how recent was last completion if more than 4 days ago get
				// data
				// for this week
				if ((timeToCollectSM || timeToCollectLG) && tickers.size() == 0)
					resetTickersLessSavedData();
				// first set action to small collection
				if (timeToCollectSM) {
					threadAction = 0;
					// BATCH = 101;
				} else if (timeToCollectLG) {
					threadAction = 2;
					// BATCH = 31;
				} else if (day != Calendar.FRIDAY) {
					// doDBAnalysis("not friday either");
				}

			} else if (day != Calendar.FRIDAY) {
				// doDBAnalysis("not friday");

			}
		}// 10000 threadAction called from cron
		//DSBlobs.postMemoryData("cron_B: " + threadAction);
	}

	public static void initFromDS() {
		try {
			// overridding if 10000 only from cron weekly
			threadAction = Integer.parseInt((String) (DSUtil.ds
					.get(DSUtil.THREAD_STATE.getKey()).getProperty("data")));

		} catch (NumberFormatException e) {
		} catch (EntityNotFoundException e) {
		}
		try {
			lastCompletionDateLG = Float.parseFloat((String) (DSUtil.ds
					.get(DSUtil.LAST_DATE_lg.getKey()).getProperty("data")));

		} catch (NumberFormatException e) {

		} catch (EntityNotFoundException e) {
			lastCompletionDateLG = 15000;
		}
		try {
			lastCompletionDateSM = Float.parseFloat((String) (DSUtil.ds
					.get(DSUtil.LAST_DATE_sm.getKey()).getProperty("data")));

		} catch (NumberFormatException e) {
		} catch (EntityNotFoundException e) {
			lastCompletionDateSM = 15000; // a few years ago
		}
	}

	// PUT IN MEMORY LOG BEFORE AND AFTER STORE TEMPBLOBS

	// called by BackendsAccessory to start backend after setting threadAction
	// in Datastore
	static void allocateThreadAction(String threadID) {

		switch (threadAction) {
		case 0:
			runCollectionInBackend(threadID);
			break;
		case 1:
			// loadMemcacheInBackend();
			break;
		case 2:
			runCollectionInBackend(threadID);
			break;
		case 3:
			break;
		case 100:
			// launch DB analysis
			break;
		default:
			// doDBAnalysis( threadID);
			break;

		}

	}

	public static void fillTickers() {
		// fill er' up
		tickers.addAll(Arrays.asList(QQ));
		tickers.addAll(Arrays.asList(YY));
	}

	private static void resetTickersLessSavedData() {
		// only refill if tickers has no more
		if (!tickers.isEmpty())
			return;
		fillTickers();
		// restores all TreeMap Blobs under E_TEMP_BLOBS regardless of
		// threadAction
		TreeMap<String, String> dataRestore = DSBlobs.restoreProgress();

		completeResults.putAll(dataRestore);

		for (TreeMap<String, String> local : saveProgress.values()) {
			completeResults.putAll(local);
		}

		tickers.removeAll(completeResults.keySet());
	}

	static void runCollectionInBackend(String ID) {
		if (stopURLFetch)
			return;
		// acquire a title
		switch (threadAction) {
		case 0:
			fileTitleQ = GenUtil.fileTitle("nas");
			fileTitleY = GenUtil.fileTitle("ny");
			break;
		case 2:
			fileTitleQ = GenUtil.fileTitle("BIG_nas");
			fileTitleY = GenUtil.fileTitle("BIG_ny");
			break;
		default:
			break;
		}
		// refine thread ID
		String IDID = ID;
		switch (threadAction) {
		case 0:
			break;
		case 1:
			break;
		case 2:
			IDID = "BIG_" + ID;
			break;
		case 3:
			break;
		case 4:
			break;
		case 5:
			break;
		}
		// id permanently IDID
		// id used to track individual thread in ds
		final String id = IDID;

		// if we are making sufficient progress dont bother to start antother
		// thread
		if (makingProgress(id))
			return;

		// DEFINE A THREAD
		Thread thread = ThreadManager.createBackgroundThread(new Runnable() {

			public void run() {

				time = System.currentTimeMillis();
				// add a treemap for this thread
				saveProgress.put(id, new TreeMap<String, String>());
				// add an atomic counter for this thread
				vars.put(id, new AtomicInteger(1));
				// record starting time
				if(true)
				DSUtil.addToDatastoreAsString(DSUtil.THREAD, id, "startTime",
						DATE.format(new Date()) + "  (" + tickers.size() + ")");
				// completion test: preloader.size==QQ.length+YY.length (first
				// thread to finish perform blobstore and email files)
				while (!testCompletion(id)) {

					// completion: preloader

					// if tickers is empty and preloader is not full then
					// restore
					// from memory
					checkState();

					// String ticker = tickers.pollFirst();
					String ticker = tickers.poll();

					// if incomplete (ie preloader size insufficient)
					// and checkstate has not added necessary tickers back in
					// and tickers == null then something went wrong
					if (ticker == null)
						break;

					// ///////////////////////////////////////
					// /PRIMARY ACTIVITY
					NetUtil.addToLocalData(ticker, id);
					// UPON COMPLETION PRELOAD HAS 1 MORE DATA
					// saveProgress has 1 more
					//
					// ///////////////////////////////////////

					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {

					}
				}
			}

		});
		thread.start();

	}

	// completion: all data gathered and email has not been set
	protected static boolean testCompletion(String id) {
		// every X times (for each thread) save progress as blob
		considerSavingProgress(id);
		if (completeResults.size() == (QQ.length + YY.length)) {
			// just to make sure there are no outstanding tickers
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
			}

			// send as backup - also convert small thread
			// directly
			// to DS blobs
			if (!emailSent.getAndSet(true)) {
				// these only need to be done once
				// the first time that size is sufficeint
				// email txt data
				// save to blobstore - deprecated
				// (process into arrays for next backend)
				// delete tempblobs
				// save completion date
				if(POSTUtil.ApplicationID.equals("cloudapp22x")){
				Queue queue = QueueFactory.getQueue("emaildata-queue");
				queue.add(TaskOptions.Builder.withUrl("/emailtask").param(
						EmailTask.PARAMETER, EmailTask.SETQ));//
				queue.add(TaskOptions.Builder.withUrl("/emailtask").param(
						EmailTask.PARAMETER, EmailTask.SETY));//
				}
				// convert to DS Arrays
				if (threadAction == 0) {
					DSBlobs.saveNewToDatabase(QQ, fileTitleQ);
					DSBlobs.saveNewToDatabase(YY, fileTitleY);
				}
				// writing to blobstore DEPRECATED!!!use email
				DSUtil.writeToBlobStore(YY, fileTitleY);
				DSUtil.writeToBlobStore(QQ, fileTitleQ);

				switch (threadAction) {
				case 0:
					DSUtil.addToDatastoreAsString(DSUtil.LAST_DATE_sm, "data",
							"" + GenUtil.daysSince());
					 
					break;
				case 2:
					DSUtil.addToDatastoreAsString(DSUtil.LAST_DATE_lg, "data",
							"" + GenUtil.daysSince());
					// lastCompletionDateLG = GenUtil.daysSince();

					// process data into arrays
					// save arrays to datastore for quick loading
					break;
				default:
					break;

				}

				if(false)
				DSUtil.addToDatastoreAsString(DSUtil.THREAD, "overall: "
						+ officialStartTime, "shutdownTime",
						"complete:" + DATE.format(new Date()));
				if(false)
				DSUtil.addToDatastoreAsString(DSUtil.THREAD, "overall: "
						+ officialStartTime, "elapsedTime",
						(System.currentTimeMillis() - officialStartTime)
								/ 60000 + " minutes");
				// lastCompletionDate = Integer.parseInt((String)
				// (DSUtil.ds.get(DSUtil.LAST_DATE.getKey()).getProperty("data")));
				// complete = true;

			}
			long delta = System.currentTimeMillis() - time;
			if(false)
			DSUtil.addToDatastoreAsString(DSUtil.THREAD, id, "shutdownTime",
					"complete:" + DATE.format(new Date()));
			if(false)
			DSUtil.addToDatastoreAsString(DSUtil.THREAD, id, "elapsedTime",
					delta / 60000 + " minutes , completed: "
							+ vars.get(id).get());
			// put outside so its more likely all old DSBlobs are removed
			DSBlobs.deleteTempBlobs(); // we have data for all tickers so even
										// if there are

			try {
				// sleep before clearing results just in case still needed for
				// tasks
				Thread.sleep(2 * 60 * 1000);
			} catch (InterruptedException e) {
				tickers.clear();
				completeResults.clear();
			}
			// tickers left remove them so subsequent backends can start over
			tickers.clear();
			completeResults.clear();
			return true;// stop thread loop

		}
		return false;// continue thread loop
	}

	static volatile int counter = 0;

	protected static void considerSavingProgress(String id) {
		int progress = vars.get(id).getAndIncrement();

		if (counter++ % 10 == 2) {
			DSUtil.addToDatastoreAsString(DSUtil.DEBUG, "REMAINING", "COUNT",
					"" + tickers.size());
		}
		if (progress % BATCH == (BATCH - 1)
				|| (completeResults.size() == (QQ.length + YY.length))) {
			// CloudDatabase.postMemoryData(" progress: "+progress+" ");//////////////////////////////////
			// bundle away current treemap
			TreeMap<String, String> saving = saveProgress.get(id);
			TreeMap<String, String> backup = new TreeMap<String, String>();
			while (!DSBlobs.object_asBlob_toDatastore_temp(id + progress,
					saving)) {
				String lastKey = (String) saving.lastKey();
				String lastData = (String) saving.get(lastKey);
				saving.remove(lastKey);
				backup.put(lastKey, lastData);
			}
			if (backup.size() > 0) {
				DSBlobs.object_asBlob_toDatastore_temp(id + "-SUB-" + progress,
						backup);
			}
			saving.clear();
		}

	}

	protected static void checkState() {

		if (tickers.isEmpty()) {
			// tickers is empty wait a few seconds for any outstanding tickers
			// about to be added to results
			try {
				Thread.sleep(4 * 1000);
			} catch (InterruptedException e) {
			}
			// for whatever reason tickers is depleted but
			// collected data is incomplete
			// reset tickers with the symbols with no data
			if (completeResults.size() != (QQ.length + YY.length)) {

				// reconstruct data from datastore
				// refill tickers for uncollected data
				resetTickersLessSavedData();

				DSUtil.addToDatastoreAsString(DSUtil.DEBUG, "TICKERSTATE",
						"REFILLED", "YES: " + tickers.size());// //////////////////////////////////////////////////////////////////////

			}
		} else if (tickers.size() > QQ.length + YY.length) {
			tickers.clear();
			resetTickersLessSavedData();
		}
	}

	private static boolean makingProgress(String id) {
		// go ahead and start a few threads
		if (threadStarts <= THREADMAX) {
			threadStarts++;
			return false;
		}
		int size = completeResults.size();

		try {
			Thread.sleep((1 + threadAction / 2) * 900);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (completeResults.size() > size) {
			if(false)
			DSUtil.addToDatastoreAsString(DSUtil.THREAD, id, "shutdownTime",
					"NO:" + DATE.format(new Date()) + "  (" + tickers.size()
							+ ")");
			return true;

		}
		threadStarts = 0;
		return false;
	}

	private static void genericBackendOutline() {

		LifecycleManager.getInstance().setShutdownHook(
				new LifecycleManager.ShutdownHook() {
					public void shutdown() {

						LifecycleManager.getInstance().interruptAllRequests();

					}
				});

		Thread thread = ThreadManager.createBackgroundThread(new Runnable() {

			public void run() {

			}

		});
		thread.start();

	}

}
