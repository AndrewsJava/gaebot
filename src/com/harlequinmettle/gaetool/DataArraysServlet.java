package com.harlequinmettle.gaetool;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DataArraysServlet extends HttpServlet {

	public static final String PARAM1  = "PARAMETER";
	public static final String PARAM2  = "PARAMETER2";
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		

		String DSLOCATION = req.getParameter(PARAM1);
		String DSITEM = req.getParameter(PARAM2);
		//resp.setContentType("text/plain");
		PrintWriter w = resp.getWriter();
		w.println("<html><body>");
		POSTUtil.showSavedData(w, DSLOCATION, DSITEM); 
		w.println("</body></html>");
	 
	}

}
