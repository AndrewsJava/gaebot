package com.harlequinmettle.gaetool;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskOptions;

public class MobileDeleteServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		PrintWriter w = resp.getWriter();
		String code = req.getParameter("DELETE");

		// IF CODE[0] = DELETE
		// queue defined in queue.xml
		Queue queue = QueueFactory.getQueue("deletedata-queue");
		// url-pattern tag in web.xml linking other task executing code
		queue.add(TaskOptions.Builder.withUrl("/deletetask").param(
				DeleteTask.PARAM_ACCESS, code));
	}

}
