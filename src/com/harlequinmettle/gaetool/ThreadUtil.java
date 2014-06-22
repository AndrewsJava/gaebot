package com.harlequinmettle.gaetool;

import java.util.Arrays;
import java.util.Date;
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

public class ThreadUtil implements   Qi, Yi //NASDAQ,NYSE  //
{
	 //NOT USING THIS LEGACY CLASS - SEE TheBackend
	public static String fileTitleQ = "date not setx";
	public static String fileTitleY = "date not setxx";
 
	public static boolean complete = false;
	public static AtomicBoolean sendEmail = new AtomicBoolean(true);
	 
	public static String currentTicker = "";
	// sum of all tickers to eval
	//public static int totalSize;
	// later read from datastore to alter which thread to run
	public static int threadAction = 10000000;
	
	
	//// REPLACE DBSETTING IN VAROIS SERVLETS AND IN ANDROID APP GAECONNECT - 0 2 -> 2000 10000
	public static final int ACTION_SMALL = 2000;// 2mb  * 2 //replace all instances of 0
	public static final int ACTION_BIG = 10000; // 10mb * 2 // replace all instances of 2
	
	
	
	
	
	// used to identify individual thread progress
	static ConcurrentHashMap<String, AtomicInteger> vars = new ConcurrentHashMap<String, AtomicInteger>();
	
	static ConcurrentHashMap<String, TreeMap<String,String>> saveProgress = new ConcurrentHashMap<String, TreeMap<String,String>>();
	 
	static ConcurrentHashMap<String, String> preloader = new ConcurrentHashMap<String, String>();
 
	private static ConcurrentLinkedQueue<String> tickers = new ConcurrentLinkedQueue<String>();
	
	
	static {
		tickers.addAll(Arrays.asList(QQ));
		tickers.addAll(Arrays.asList(YY));
		//totalSize = tickers.size();
		try {
			threadAction = Integer.parseInt((String) (DSUtil.ds
					.get(DSUtil.THREAD_STATE.getKey()).getProperty("data")));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EntityNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

	}

	static void allocateThreadAction(String threadID) {

		switch (threadAction) {
		case 0:
			runCollectionInBackend(threadID);
			break;
		case 1:
			loadMemcacheInBackend();
			break;
		case 2:
			runCollectionInBackend(threadID);
			break;
		case 3:
			break;
		default:
			break;

		}

	}

	static void runCollectionInBackend(String ID) {

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
		// id used to track individual thread in ds
		final String id = IDID;

		int size = preloader.size();
		if(size>0){
			try {
				Thread.sleep((1+threadAction/2)*400);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(preloader.size()>size){
				DSUtil.addToDatastoreAsString(DSUtil.THREAD, id,
						"shutdownTime",
						"UNNECESSARY:" + new Date().toString()
								+ "  remaining:" + tickers.size());
				return;
			
			}
			
		}
		
		LifecycleManager.getInstance().setShutdownHook(
				new LifecycleManager.ShutdownHook() {
					public void shutdown() {

						if (!complete) {
							DSUtil.savePt.setProperty("name", currentTicker);
							DSUtil.ds.put(DSUtil.savePt);
						}

						DSUtil.addToDatastoreAsString(DSUtil.THREAD, id,
								"shutdownTime",
								"KILLED:" + new Date().toString()
										+ "  remaining:" + tickers.size());

						// TAKE SOME ACTION - DUMP PRELOADER INTO A BLOBFILE
						LifecycleManager.getInstance().interruptAllRequests();

					}
				});
		// DEFINE A THREAD
		Thread thread = ThreadManager.createBackgroundThread(new Runnable() {

			public void run() {
saveProgress.put(id, new TreeMap<String,String>());
				vars.put(id, new AtomicInteger(1));
				
				DSUtil.addToDatastoreAsString(DSUtil.THREAD, id, "startTime",
						"s:" + new Date().toString() + " n: "
								+ (QQ.length+YY.length));

				final long time = System.currentTimeMillis();

				while (!tickers.isEmpty()) {
					String ticker = tickers.poll();

					currentTicker = ticker;

					// ///////////////////////////////////////
					// /PRIMARY ACTIVITY
					NetUtil.addToLocalData(ticker,id);
					// UPON COMPLETION PRELOAD HAS 1 MORE DATA
					// ///////////////////////////////////////
					int progress = vars.get(id).getAndIncrement() ;
					if (progress % 100 == 99) {
						//bundle away current treemap
						TreeMap<String ,String> saving = saveProgress.get(id);
					//	DSBlobs.object_asBlob_toDatastore_temp(id+progress, saving);
						saving.clear();
					}
					// save record of each threads progress
					if(false)
					if (vars.get(id).getAndIncrement() % 10 == 9) {
						DSUtil.addToDatastoreAsString(DSUtil.THREAD, id,
								"data",   vars.get(id).get()+" last saved: " + ticker + " remain:-"
										+ tickers.size() + "-");
						
						
						DSUtil.addToDatastoreAsString(DSUtil.THREAD, id,
								"time",  new Date().toString());

						DSUtil.savePt.setProperty("name", currentTicker);
						DSUtil.savePt.setProperty("remaining",
								(int) (tickers.size()));
						DSUtil.ds.put(DSUtil.savePt);
					}

					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				long delta = System.currentTimeMillis() - time;

				// DELETE DSUtil.SAVEPOINT FROM DS
				DSUtil.ds.delete(DSUtil.savePt.getKey());

				DSUtil.addToDatastoreAsString(DSUtil.THREAD, id,
						"shutdownTime", "complete:" + new Date().toString());
				DSUtil.addToDatastoreAsString(DSUtil.THREAD, id, "elapsedTime",
						delta / 60000 + " minutes");

				DSUtil.ds.delete(DSUtil.THREAD_STATE.getKey());
				//DSUtil.ds.delete(DSUtil.THREAD_KIND.getKey());
				//DSUtil.ds.delete(DSUtil.THREAD_PROPERTY.getKey());

				if (preloader.size() == (QQ.length+YY.length) && sendEmail.getAndSet(false)) {
				 
					if(true){
						//send as backup - also convert small thread directly to DS blobs
					Queue queue = QueueFactory.getQueue("emaildata-queue");
					queue.add(TaskOptions.Builder.withUrl("/emailtask").param(
							EmailTask.PARAMETER, EmailTask.SETQ));//
					queue.add(TaskOptions.Builder.withUrl("/emailtask").param(
							EmailTask.PARAMETER, EmailTask.SETY));//
					//convert to DS Arrays
					}
					//writing to blobstore DEPRECATED!!!use email
					DSUtil.writeToBlobStore(YY,fileTitleY);
					DSUtil.writeToBlobStore(QQ,fileTitleQ);
					
					switch (threadAction) {
					case 0:
						//process data into arrays
						//save arrays to datastore for quick loading
						break;
					default:
						break;
			 
					}
					complete = true;
					// AUTOMATIC TEST - REMOVE LATER
					//DSUtil.emailDataFromBlobstore(fileTitleY);
					//DSUtil.emailDataFromBlobstore(fileTitleQ);
					

				}

			}

		});
		thread.start();

	}

	static void loadMemcacheInBackend() {

		Thread thread = ThreadManager.createBackgroundThread(new Runnable() {

			public void run() {
				try {
				//	String index = (String) DSUtil.ds.get( 			DSUtil.THREAD_KIND.getKey()).getProperty("data");
				//	String property = (String) DSUtil.ds.get( DSUtil.THREAD_PROPERTY.getKey()) 	.getProperty("data");
				//	DSUtil.loadMemcacheFromDatastore(index, property);
				} catch ( Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		thread.start();

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
