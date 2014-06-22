package com.harlequinmettle.gaetool;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class BackendAccessory extends HttpServlet {
	static final String PARAMETER = "THREAD_COUNT";
	static final String PARAMETER2 = "THREAD_TYPE";

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
	// DSBlobs.postMemoryData("backendAccess");
		DebugUtil.storeRequestInfo(req);
		int threads = 1; 
		String threadCount = req.getParameter(PARAMETER);
		if (threadCount != null) {
			threads = Integer.parseInt(threadCount);
		}
		String threadType = req.getParameter(PARAMETER2);
		if (threadType != null) {
			int threadAction = Integer.parseInt(threadType);
			DSUtil.ds.delete(DSUtil.THREAD_STATE.getKey());
			DSUtil.addToDatastoreAsString(DSUtil.THREAD_STATE, "data", ""
					+ threadAction);
		}
		 
		PrintWriter w = resp.getWriter();
		w.println("<html>");
		w.println("  <body>");

		POSTUtil.addDSOutline(w);

		POSTUtil.addForm(req, w, "What's your pleasure?");

		w.println("  </body>");
		w.println("</html>");
		// short circut call to backend on friday - save some resources for
		// datacollections
		// cron every hr from 0 est to 23
		if (false) {
			Calendar cal = Calendar.getInstance();
			cal.setTimeZone(TimeZone.getTimeZone("GMT-5"));
			int day = cal.get(Calendar.DAY_OF_WEEK);
			boolean isItFridayPST = day == Calendar.FRIDAY;

			cal.setTimeZone(TimeZone.getTimeZone("GMT-5"));
			day = cal.get(Calendar.DAY_OF_WEEK);

			boolean isItSaturdayEST = day == Calendar.SATURDAY;
			if (isItFridayPST && !isItSaturdayEST) {
				return;
			}
		}
		if (req.getRequestURL().toString()
				.indexOf("b1." + POSTUtil.ApplicationID) > 0) {
			for (int i = 0; i < threads; i++) {
				TheBackend.allocateThreadAction("b1-" + GenUtil.secondsSince());
				long sleepfor = 2000;
				if (i == 0)
					sleepfor = 10000;
				try {
					Thread.sleep(sleepfor);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
		if (req.getRequestURL().toString()
				.indexOf("b2." + POSTUtil.ApplicationID) > 0) {
			for (int i = 0; i < threads; i++) {
				TheBackend.allocateThreadAction("b2-" + GenUtil.secondsSince());
				long sleepfor = 2000;
				if (i == 0)
					sleepfor = 8 * 1000;
				try {
					Thread.sleep(sleepfor);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		if (req.getRequestURL().toString()
				.indexOf("b4." + POSTUtil.ApplicationID) > 0) {
			
			for (int i = 0; i < threads; i++) {
				//final DataAnalyzer DA = new DataAnalyzer();
				DataAnalyzer.doDBAnalysis("b4-" + GenUtil.secondsSince());
				//TheBackend.allocateThreadAction("b4-" + GenUtil.secondsSince());
				long sleepfor = 2000;
				if (i == 0)
					sleepfor = 10000;
				try {
					Thread.sleep(sleepfor);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		if (req.getRequestURL().toString()
				.indexOf("b8." + POSTUtil.ApplicationID) > 0) {
			for (int i = 0; i < threads; i++) {
				TheBackend.allocateThreadAction("b8-" + GenUtil.secondsSince());
				long sleepfor = 2000;
				if (i == 0)
					sleepfor = 10000;
				try {
					Thread.sleep(sleepfor);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		PrintWriter w = resp.getWriter();
		String[] params = req.getParameter("codeword").split(" ");
		// POSSIBLE TO ADJUST STATIC VARIABLES BASED ON INPUT TO CONTROL THREAD
		// SETTINGS
		if (params[0].equals("backends")) {
			// add links which will launch a backend
			w.println("<h6><p>click to launch backend:</h6></p>");
			POSTUtil.addBackendLinks(resp);
			// add settings to datastore to determine which backend thread to
			// launch
			if (params.length > 2 && params[1].equals("loadmemcache")) {
				// setting to determine which thread action to take
				DSUtil.addToDatastoreAsString(DSUtil.THREAD_STATE, "data",
						"" + 1);
				// which indicies to use for loadmemcache
				// DSUtil.addToDatastoreAsString(DSUtil.THREAD_KIND, "data",
				// params[2]);
				// which property of said indecies
				// DSUtil.addToDatastoreAsString(DSUtil.THREAD_PROPERTY, "data",
				// params[3]);

			} else if (params.length > 1 && params[1].equals("big")) {
				// use alternate thread
				DSUtil.addToDatastoreAsString(DSUtil.THREAD_STATE, "data",
						"" + 2);
			} else {
				DSUtil.ds.delete(DSUtil.THREAD_STATE.getKey());
				DSUtil.addToDatastoreAsString(DSUtil.THREAD_STATE, "data",
						"" + 0);
			}
		}

		// /NOT A BACKEND -- ACCESSED IN BACKENDS PAGE THOUGH
		else if (params[0].equals("datastore") && params.length > 2) {
			DSUtil.printDatastore(w, params[1], params[2]);
		}

		doGet(req, resp);
	}

}
