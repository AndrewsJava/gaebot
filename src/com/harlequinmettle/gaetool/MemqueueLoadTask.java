package com.harlequinmettle.gaetool;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class MemqueueLoadTask  extends HttpServlet {
	public static final String PARAM_ONE = "PARAMETERONE"; 
	public static final String PARAM_TWO = "PARAMETERTWO"; 
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException { 
//load database PARAM_ONE into Memqueue
		//datastore kind eg nasdaq or nyse
		String param1 =  req.getParameter(PARAM_ONE);
		//datastore property eg 15899.4.txt
		String param2 =  req.getParameter(PARAM_TWO);
		DSUtil.loadMemcacheFromDatastore(param1, param2);
		
		
	}
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
	throws IOException {
		
	}
	
}
