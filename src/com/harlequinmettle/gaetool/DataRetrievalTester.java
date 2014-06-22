package com.harlequinmettle.gaetool;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DataRetrievalTester extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		//resp.setContentType("text/plain");
		PrintWriter w = resp.getWriter();
		w.println("<html><body>"); 
		POSTUtil.addDatabaseOutline(w);
		w.println("</body></html>");
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		doGet(req, resp);
	}
}
