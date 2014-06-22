package com.harlequinmettle.gaetool;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class GAEBotServlet extends HttpServlet { 

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
		PrintWriter w = resp.getWriter();
	 	POSTUtil.addDSOutline(w);
	}
}
