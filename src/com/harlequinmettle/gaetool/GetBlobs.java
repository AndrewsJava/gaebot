package com.harlequinmettle.gaetool;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class GetBlobs extends HttpServlet {
	public static final String PARAM = "parameter";

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String blobsend = req.getParameter(PARAM);
		if (blobsend == null) {
			POSTUtil.addBlobs(resp.getWriter());
		} else {
			DSUtil.emailDataFromBlobstore(blobsend);
		}
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		POSTUtil.addBlobs(resp.getWriter());
	}

}
