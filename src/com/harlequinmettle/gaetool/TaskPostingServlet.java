package com.harlequinmettle.gaetool;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskOptions;

@SuppressWarnings("serial")
public class TaskPostingServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		PrintWriter w = resp.getWriter();

		w.println("<html>");
		w.println("  <body>");
		POSTUtil.addDSOutline(w);
		w.println(" enter info for task:");
		POSTUtil.addForm(req, w, "Start Task");

		w.println("  </body>");
		w.println("</html>");

	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		String[] code = req.getParameter("codeword").split(" ");

		// IF CODE[0] = DELETE
		if (code[0].equalsIgnoreCase("delete")) {
			// queue defined in queue.xml
			Queue queue = QueueFactory.getQueue("deletedata-queue");
			// url-pattern tag in web.xml linking other task executing code
			queue.add(TaskOptions.Builder.withUrl("/deletetask").param(DeleteTask.PARAM_ACCESS,
					code[1]));
		} 
		// IF CODE[0] = email
		if (code[0].equalsIgnoreCase("email")) {
			// queue defined in queue.xml
			Queue queue = QueueFactory.getQueue("emaildata-queue");
			// url-pattern tag in web.xml linking other task executing code
			queue.add(TaskOptions.Builder.withUrl("/emailtask").param(EmailTask.PARAMETER, code[1]));//
			//.param(EmailTask.PARAM_ACCESS,c	code[1]).param(EmailTask.PARAM_ACCESS, 	code[1]));
		} 
		/////////////////////////////
		////////////ONLY USE DELETE TASK -- loading memcashe now with backend
		if (code[0].equalsIgnoreCase("loadmemcache")) {
	 		// queue defined in queue.xml
			Queue queue = QueueFactory.getQueue("loaddata-queue");
			// url-pattern tag in web.xml linking other task executing code
			queue.add(TaskOptions.Builder.withUrl("/loadtask").param(MemqueueLoadTask.PARAM_ONE,
					code[1]).param(MemqueueLoadTask.PARAM_TWO,
							code[2]));
		}
	 
	}
}
