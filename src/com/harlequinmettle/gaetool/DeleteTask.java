package com.harlequinmettle.gaetool;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 

public class DeleteTask  extends HttpServlet {
public static final String PARAM_ACCESS = "PARAMETER";
	// private static final Logger _logger =
	// Logger.getLogger(TaskExcecutionServlet.class.getName());
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
 
		//resp.setContentType("text/plain"); 

		String deleter = req.getParameter(PARAM_ACCESS);
		//
		// PUT YOUR TASK CODE HERE
		//
		boolean doDelete = false;
		for(String s: DSUtil.DS_KINDS){
			if(deleter.equals(s))
				doDelete = true;
		}
		if(doDelete){//NO OUTPUT FROM TASK 
		DSUtil.deleteDatastore(deleter);
		}else{
 //CAN NOT PRINT TO WRITER FOR VIEWING ON PAGE
		}
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String deleter = req.getParameter(PARAM_ACCESS);
		//
		// PUT YOUR TASK CODE HERE
		//
		boolean doDelete = false;
		for(String s: DSUtil.DS_KINDS){
			if(deleter.equals(s))
				doDelete = true;
		}
		if(doDelete){//NO OUTPUT FROM TASK 
		DSUtil.deleteDatastore(deleter);
		}else{
 //CAN NOT PRINT TO WRITER FOR VIEWING ON PAGE
		}
		}
}
