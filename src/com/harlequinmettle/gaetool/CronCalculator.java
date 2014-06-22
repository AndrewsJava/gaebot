package com.harlequinmettle.gaetool;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 

@SuppressWarnings("serial")
public class CronCalculator  extends HttpServlet {
	static final String PARAMETER = "THREAD_COUNT";
	static final String PARAMETER2 = "THREAD_TYPE";

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		doGet(req,resp);
	}
		@Override
		public void doGet(HttpServletRequest req, HttpServletResponse resp)
				throws IOException {
	///START BACKEND
			//POST THREAD RUN TO DATASTORE 0 OR 2 FOR THREAD PARSER
		HttpURLConnection conn;
		String url = "http://b4." +POSTUtil.ApplicationID
		+ ".appspot.com/" + "backend";
	 	
		try {
			conn = (HttpURLConnection) new URL(url).openConnection();
		 	 conn.setReadTimeout(60*1000);
			 conn.setConnectTimeout(60*1000);
	 
			InputStream response = conn.getInputStream();
	 } catch (MalformedURLException e) {
	 } catch (IOException e) {	
 
		}
		
	}

}
