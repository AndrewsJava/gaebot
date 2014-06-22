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
public class CronSmall extends HttpServlet {
	static final String PARAMETER = "THREAD_COUNT";
	static final String PARAMETER2 = "THREAD_TYPE";

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		doGet(req, resp);
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		HttpURLConnection conn;
		String url = "http://b2." + POSTUtil.ApplicationID + ".appspot.com/"
				+ "backend?THREAD_COUNT=" + 3 + "&" + PARAMETER2 + "=" + 0;

		try {
			conn = (HttpURLConnection) new URL(url).openConnection();
			conn.setReadTimeout(60 * 1000);
			conn.setConnectTimeout(60 * 1000);

			InputStream response = conn.getInputStream();
	 

		} catch (MalformedURLException e) {

		} catch (IOException e) {

		}

	}

}
