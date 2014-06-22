package com.harlequinmettle.gaetool;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.google.appengine.api.datastore.Entity;

public class DebugUtil {

	public static void storeRequestInfo(HttpServletRequest req) {
		Entity one = new Entity("REQUESTS");
		String date = new Date().toString();
		String city = req.getHeader("X-AppEngine-City");
		String address = req.getRemoteAddr();
		if (!address.equals("69.14.184.212") && !address.equals("0.1.0.40")) {
			DSUtil.addToDatastoreAsString(4, date, "city", city);
			DSUtil.addToDatastoreAsString(4, date, "address", address);
		}
	}
}
