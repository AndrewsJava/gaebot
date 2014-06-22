package com.harlequinmettle.gaetool;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 

@SuppressWarnings("serial")
public class PostingServlet  extends HttpServlet { 
//STATE_INFO = "THREAD_STATE"
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		 String limited = req.getParameter("p1");//STATE_INFO = "THREAD_STATE"
		 String uniqueKey = req.getParameter("p2");//"threadAction","fromIndex","propertyID"
		 String propertyName = req.getParameter("p3");//"data"
		 String resultData = req.getParameter("p4");//1
 DSUtil.addToDatastoreAsString(limited, uniqueKey, propertyName, resultData);
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String[] params = req.getParameter("codeword").split(" ");

		if (params[0].equals("datastore")
				&& params.length > 2) {
			
			
		}
	}
}
