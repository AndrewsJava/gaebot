package com.harlequinmettle.gaetool;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetRawDataServlet extends HttpServlet {
	public static final String PARAM_ONE = "PARAMETERONE";
	public static final String PARAM_TWO = "PARAMETERTWO";

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String genFile = req.getParameter(PARAM_ONE);
		String specProp = req.getParameter(PARAM_TWO);
		PrintWriter w = resp.getWriter();
		long items = DSUtil.syncCache.getStatistics().getItemCount();
		// //shortcircuit data with parameter keywords
		if (genFile.equals("CACHESTATE")) {
			POSTUtil.addCacheState(w);
			return;
		}

		// for normal collection of 2-3K items test for memcache load
		while (items < 0) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			items = DSUtil.syncCache.getStatistics().getItemCount();
		}
		POSTUtil.printDatastore(w, genFile, specProp);

	}

}
