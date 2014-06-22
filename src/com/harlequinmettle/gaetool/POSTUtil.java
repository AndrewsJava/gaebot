package com.harlequinmettle.gaetool;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.PriorityBlockingQueue;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.Blob;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Text;
import com.google.appengine.api.memcache.Stats;

public class POSTUtil {

	// Project->Properties->Google->App Engine->|Application ID|
	public static final String ApplicationID = "cloudapp22x";

	// public static final String ApplicationID = "backend-test-app";
	// public static final String ApplicationID = "financialdatacollector";
	// public static final String ApplicationID = "cloudcomputer99";
	public static final String BACKEND_SERVLET_JSP_CODE = "backend";

	public static void printDatastore(PrintWriter w, String kind, String field)
			throws IOException {

		Query query = new Query(kind);
		FetchOptions fetch_options = FetchOptions.Builder.withPrefetchSize(900)
				.chunkSize(900);

		for (Entity e : DSUtil.ds.prepare(query).asQueryResultList(
				fetch_options)) {
			String memData = (String) (DSUtil.syncCache.get(e.getKey()));
			if (memData == null) {
				String propertyData = "";
				try {
					propertyData = ((Text) e.getProperty(field.trim()))
							.getValue().toString();
				} catch (Exception ex) {
				}
				memData = e.getKey().getName() + "^" + propertyData + "<br/>";
			}
			w.println(memData);
		}

	}

	public static void printMemcacheState(PrintWriter w) throws IOException {
		Stats st = DSUtil.syncCache.getStatistics();
		w.println(st.getItemCount());

	}

	public static void preloadData(final String kind, final String field) {
		// FIX - CREATE A THREAD AND RETURN

		PriorityBlockingQueue<String> preloader = new PriorityBlockingQueue<String>();
		Query query = new Query(kind);

		FetchOptions fetch_options = FetchOptions.Builder.withPrefetchSize(900)
				.chunkSize(900);

		for (Entity e : DSUtil.ds.prepare(query).asQueryResultList(
				fetch_options)) {
			try {
				DSUtil.ds.get(e.getKey());
			} catch (EntityNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			String output = e.getKey().getName()
					+ "^"
					+ ((Text) e.getProperty(field.trim())).getValue()
							.toString() + "<br/>";
			preloader.offer(output);
			try {
				Thread.sleep(10);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	public static HttpServletResponse printRecentQ(HttpServletResponse resp)
			throws IOException {

		for (String output : Qi.QQ) {
			resp.getWriter().println(TheBackend.completeResults.get(output));
		}

		return resp;
	}

	public static HttpServletResponse printRecentY(HttpServletResponse resp)
			throws IOException {

		for (String output : Yi.YY) {
			resp.getWriter().println(TheBackend.completeResults.get(output));

		}

		return resp;
	}

	public static HttpServletResponse printDatastore2(HttpServletResponse resp,
			String kind, String field) throws IOException {

		String labels = "<h5><p>   key/name   </h5></p>";
		resp.getWriter().println(labels);
		Query query = new Query(kind);
		for (Entity e : DSUtil.ds.prepare(query).asIterable()) {
			try {
				DSUtil.ds.get(e.getKey());
			} catch (EntityNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			String output = e.getKey().getName()
					+ "^"
					+ ((Text) e.getProperty(field.trim())).getValue()
							.toString() + "<br/>";
			resp.getWriter().println(output);
		}

		return resp;
	}

	public static void addForm(HttpServletRequest req, PrintWriter w,
			String buttonTitle) throws IOException {

		String urlfraction = req.getRequestURI();
		w.println("<form action='" + urlfraction + "' method='post'>");
		w.println("<input type='text' autocomplete='off' id='codeword' name='codeword'>");
		w.println("  <div><input id = 'submit' type='submit' value='"
				+ buttonTitle + "' /></div>");
		w.println("</form>");

	}

	public static HttpServletResponse displayProtocolInfo(
			HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String[] labels = { "getAttributeNames().toString()-----:::>",
				"                 getAuthType()-----:::>",
				"        getCharacterEncoding()-----:::>",
				"            getContentLength()-----:::>",
				"              getContentType()-----:::>",
				"              getContextPath()-----:::>",
				"   getHeaderNames().toString()-----:::>",
				"                getLocalAddr()-----:::>",
				"        getLocale().toString()-----:::>",
				"       getLocales().toString()-----:::>",
				"                getLocalName()-----:::>",
				"                getLocalPort()-----:::>",
				"                   getMethod()-----:::>",
				"  getParameterMap().toString()-----:::>",
				"getParameterNames().toString()-----:::>",
				"                 getPathInfo()-----:::>",
				"           getPathTranslated()-----:::>",
				"                 getProtocol()-----:::>",
				"              getQueryString()-----:::>",
				"               getRemoteAddr()-----:::>",
				"               getRemoteHost()-----:::>",
				"               getRemotePort()-----:::>",
				"               getRemoteUser()-----:::>",
				"       getRequestedSessionId()-----:::>",
				"               getRequestURI()-----:::>",
				"    getRequestURL().toString()-----:::>",
				"                   getScheme()-----:::>",
				"               getServerName()-----:::>",
				"               getServerPort()-----:::>",
				"              getServletPath()-----:::>" };

		String[] definitions = { req.getAttributeNames().toString(),
				req.getAuthType(), req.getCharacterEncoding(),
				"" + req.getContentLength(), req.getContentType(),
				req.getContextPath(), req.getHeaderNames().toString(),
				req.getLocalAddr(), req.getLocale().toString(),
				req.getLocales().toString(), req.getLocalName(),
				"" + req.getLocalPort(), req.getMethod(),
				req.getParameterMap().toString(),
				req.getParameterNames().toString(), req.getPathInfo(),
				req.getPathTranslated(), req.getProtocol(),
				req.getQueryString(), req.getRemoteAddr(), req.getRemoteHost(),
				"" + req.getRemotePort(), req.getRemoteUser(),
				req.getRequestedSessionId(), req.getRequestURI(),
				req.getRequestURL().toString(), req.getScheme(),
				req.getServerName(), "" + req.getServerPort(),
				req.getServletPath()

		};

		resp.getWriter().println("<h3>");
		resp.getWriter().println("HttpServletRequest");

		resp.getWriter().println("</h3>");

		resp.getWriter().println("<h4>");
		for (int i = 0; i < labels.length; i++) {
			String result = "";

			result += labels[i];
			result += definitions[i];
			resp.getWriter().println("<p>" + result + "</p>");
		}

		resp.getWriter().println("</h4>");

		resp.getWriter().println(" <p>  </p>");
		resp.getWriter().println("<h5><p>attributeNames:</p>");

		Enumeration e = req.getAttributeNames();
		while (e.hasMoreElements()) {
			String element = e.nextElement().toString();
			resp.getWriter().println(
					"<p>" + element + " : " + req.getAttribute(element)
							+ "</p>");
		}

		resp.getWriter().println(" <p>  </p>");
		resp.getWriter().println(" <p>headerNames:</p>");

		e = req.getHeaderNames();
		while (e.hasMoreElements()) {

			String element = e.nextElement().toString();
			resp.getWriter().println(
					"<p>" + element + " : " + req.getHeader(element) + "</p>");
		}

		resp.getWriter().println(" <p>  </p>");
		resp.getWriter().println(" <p>localeNames:</p>");

		e = req.getLocales();
		while (e.hasMoreElements()) {
			resp.getWriter().println("<p>" + e.nextElement() + "</p>");
		}

		resp.getWriter().println(" <p>  </p>");
		resp.getWriter().println(" <p>parameterNames:</p>");

		e = req.getParameterNames();
		while (e.hasMoreElements()) {
			String element = e.nextElement().toString();
			resp.getWriter().println(
					"<p>" + element + " : " + req.getParameter(element)
							+ "</p>");
		}

		resp.getWriter().println("</h5>");

		return resp;
	}

	public static void addBackendLinks(HttpServletResponse resp)
			throws IOException {

		resp.getWriter().println(
				"<p><a href=\"http://b1." + ApplicationID + ".appspot.com/"
						+ BACKEND_SERVLET_JSP_CODE + "\">launch   B1</a></p>");
		resp.getWriter().println(
				"<p><a href=\"http://b2." + ApplicationID + ".appspot.com/"
						+ BACKEND_SERVLET_JSP_CODE + "\">launch   B2</a></p>");
		resp.getWriter().println(
				"<p><a href=\"http://b4." + ApplicationID + ".appspot.com/"
						+ BACKEND_SERVLET_JSP_CODE + "\">launch   B4</a></p>");
		resp.getWriter().println(
				"<p><a href=\"http://b8." + ApplicationID + ".appspot.com/"
						+ BACKEND_SERVLET_JSP_CODE + "\">launch   B8</a></p>");

	}

	public static void addPropertyLists(PrintWriter w, int dsAccess) {

		w.println("<h4><p>" + DSUtil.DS_KINDS[dsAccess] + "</p></h4>");

		Query query = new Query(DSUtil.DS_KINDS[dsAccess]);
		try {
			Entity e = DSUtil.ds.prepare(query).asIterable().iterator().next();
			// Key k = KeyFactory.createKey(entityTitle, someEntry);

			Set<String> props = e.getProperties().keySet();
			for (String p : props)
				w.println("<p>" + p + "</p>");
		} catch (NoSuchElementException e) {

		}
	}

	public static void addDSOutline(PrintWriter w) {
		for (int i = 0; i < DSUtil.DS_KINDS.length; i++) {
			addPropertyLists(w, i);
		}

	}

	public static void addDatabaseOutline(PrintWriter w) {
		for (int i = 0; i < DSUtil.Database_KINDS.length; i++) {
			addDatabaseItems(w, i);
		}

	}

	public static void addDatabaseItems(PrintWriter w, int dsAccess) {

		w.println("<h4><p>" + DSUtil.Database_KINDS[dsAccess] + "</p></h4>");

		Query query = new Query(DSUtil.Database_KINDS[dsAccess]);
		try {
			for (Entity e : DSUtil.ds.prepare(query).asIterable()) {

				String output = e.getKey().getName();

				output = "<a href=\"http://" + POSTUtil.ApplicationID
						+ ".appspot.com/datadata?" + DataArraysServlet.PARAM1
						+ "=" + DSUtil.Database_KINDS[dsAccess] + "&"
						+ DataArraysServlet.PARAM2 + "=" + output + "\">"
						+ output + "</a><br>";

				w.println(output);

			}
		} catch (NoSuchElementException e) {

		}
	}

	public static void addCacheState(PrintWriter w) {
		w.println("items in memcache: "
				+ DSUtil.syncCache.getStatistics().getItemCount());
		w.println("oldest item acces: "
				+ DSUtil.syncCache.getStatistics().getMaxTimeWithoutAccess()
				/ 1000 + " seconds");
	}

	public static void addBlobs(PrintWriter writer) {

		Query query = new Query(DSUtil.BLOBS);

		for (Entity e : DSUtil.ds.prepare(query).asIterable()) {
			writer.println(e.getKey().getName() + "!");
		}
	}

	public static void showSavedData(PrintWriter w, String entity, String file) {
		Blob saved = DSUtil.getBlobByName(entity, file);

		int DBSize = Qi.QQ.length;
		String[] DBTickers = Qi.QQ;

		if (file.contains("nas")) {

		} else if (file.contains("ny")) {
			DBSize = Yi.YY.length;
			DBTickers = Yi.YY;

		}
		if (file.contains("TECHNICAL")) {

			float[][][] data = DSBlobs.get3DFloatFromBytes(saved.getBytes());
			for (int i = 0; i < DBSize; i++) {
				for (int j = 0; j < data[i].length; j++) {
					w.println(DBTickers[i] + " -->"
							+ Arrays.toString(data[i][j]));
					w.println("<br>");
				}
				w.println("<br>");
			}

		} else if (file.contains("FUNDAMENTAL")) {

			float[][] data = DSBlobs.get2DFloatFromBytes(saved.getBytes());

			w.println("<table border=\"1\"><tr>");
			w.println("<th>" + "Ticker" + "</th> ");

			for (int i = 0; i < DBLabels.labels.length; i++)
				w.println("<th>" + DBLabels.labels[i] + "</th>");
			w.println("</tr>");

			for (int i = 0; i < DBSize; i++) {
				w.println("<tr>");
				w.println("<td>" + DBTickers[i] + "</td>");
				for (int j = 0; j < data[i].length; j++) {
					w.println("<td>" + data[i][j] + "</td>");
				}
				w.println("</tr>");
				if (i % 80 == 0) {
					w.println("</table><table border=\"1\"><tr>");
					w.println("<th>" + "Ticker" + "</th>");
					for (int j = 0; j < DBLabels.labels.length; j++)
						w.println("<th>" + DBLabels.labels[j] + "</th>");
					w.println("</tr>");
				}

			}

			w.println("</table>");
		}

	}

}
