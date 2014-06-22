package com.harlequinmettle.gaetool;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TreeMap;

import com.google.appengine.api.datastore.Blob;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Query;

public class DSBlobs {

	static final String P_BLOB_OB = "Object Bytes";
	static final String E_TEMP_BLOBS = "TEMP_BLOBS";
	static final String E_DB_BLOBS = "DB_BLOBS";
	protected static final String E_DS_RESULTS = "ANALYSIS";
	protected static final String P_RESULTS = "Results (TreeMap)";

	public static final SimpleDateFormat DATE = new SimpleDateFormat(
			"MMM-dd' at 'HH:mm:ss(SSS)");
	static DatastoreService ds = DatastoreServiceFactory.getDatastoreService();

	static void deleteTempBlobs() {

		Query query = new Query(E_TEMP_BLOBS);
		for (Entity e : ds.prepare(query).asIterable()) {
			ds.delete(e.getKey());
		}

	}

	// overwrites any previously saved data
	public static boolean object_asBlob_toDatastore_temp(
			String megaMaxBlobName, Object saveData) {

		byte[] d = convertToSerialBytes(saveData);
		// over 1Megabyte limit? dont try to save
		if (d.length > 1000000)
			return false;

		Entity one = new Entity(E_TEMP_BLOBS, megaMaxBlobName);

		Key getter = one.getKey();// KeyFactory.createKey(limited, uniqueKey);

		Blob serial = new Blob(d);
		one.setProperty(P_BLOB_OB, serial);

		ds.put(one);
		return true;
	}

	static void postMemoryData() {

		float totalMemory = ((float) Runtime.getRuntime().totalMemory()) / 1000000.0f;
		float freeMemory = ((float) Runtime.getRuntime().freeMemory()) / 1000000.0f;
		float maxMemory = ((float) Runtime.getRuntime().maxMemory()) / 1000000.0f;
		DSUtil.addToDatastoreAsText(DSUtil.DEBUG,
				"<-MEMORY-> " + DSBlobs.DATE.format(new Date()), "data", ""
						+ "<max>(" + maxMemory + "M) " + "<total>("
						+ totalMemory + "M) " + "<free>(" + freeMemory + "M)");// /////////////////////////////////////////

	}

	static void postMemoryData(String desc) {

		float totalMemory = ((float) Runtime.getRuntime().totalMemory()) / 1000000.0f;
		float freeMemory = ((float) Runtime.getRuntime().freeMemory()) / 1000000.0f;
		float maxMemory = ((float) Runtime.getRuntime().maxMemory()) / 1000000.0f;
		DSUtil.addToDatastoreAsText(DSUtil.DEBUG,
				DSBlobs.DATE.format(new Date()) + "<-MEMORY-> " + desc + "  ",
				"data", "" + "<max>(" + maxMemory + "M) " + "<total>("
						+ totalMemory + "M) " + "<free>(" + freeMemory + "M)");// /////////////////////////////////////////

	}

	// overwrites any previously saved data
	public static boolean object_asBlob_toDatastore_forDB(
			String megaMaxBlobName, Object saveData) {

		byte[] d = convertToSerialBytes(saveData);
		if (d.length > 1000000)
			return false;

		Entity one = new Entity(E_DB_BLOBS, megaMaxBlobName);

		Key getter = one.getKey();// KeyFactory.createKey(limited, uniqueKey);

		Blob serial = new Blob(d);
		one.setProperty(P_BLOB_OB, serial);

		ds.put(one);
		return true;
	}

	// overwrites any previously saved data
	// used for analysis treemap results
	public static boolean object_asBlob_toDatastore_forDB(
			String megaMaxBlobName, String propID,
			TreeMap<Float, TreeMap<Integer, Pair>> saveData) {

		byte[] d = convertToSerialBytes(saveData);
		if (d.length > 1000000) {
			// break saveData in 2 save both recursively
			object_asBlob_toDatastore_forDB(megaMaxBlobName + "+", propID,
					saveData);

			return false;
		}
		Entity one = new Entity(E_DB_BLOBS, megaMaxBlobName);

		Key getter = one.getKey();// KeyFactory.createKey(limited, uniqueKey);

		Blob serial = new Blob(d);
		one.setProperty(propID, serial);

		ds.put(one);
		return true;
	}

	public static byte[] convertToSerialBytes(Object yourObject) {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		byte[] yourBytes = null;

		ObjectOutput out = null;
		try {
			out = new ObjectOutputStream(bos);
			out.writeObject(yourObject);
			yourBytes = bos.toByteArray();

		} catch (IOException ioe) {
			DSBlobs.postToDebug("unable to save object");
		} finally {
			try {
				out.close();
				bos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return yourBytes;
	}

	public static float[][][] get3DFloatFromBytes(byte[] yourBytes) {
		float[][][] data = null;
		ByteArrayInputStream bis = new ByteArrayInputStream(yourBytes);
		ObjectInput in = null;
		try {
			in = new ObjectInputStream(bis);
			try {
				data = (float[][][]) in.readObject();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} finally {
			try {
				bis.close();
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return data;
	}

	public static float[][] get2DFloatFromBytes(byte[] yourBytes) {
		float[][] data = null;
		ByteArrayInputStream bis = new ByteArrayInputStream(yourBytes);
		ObjectInput in = null;
		try {
			in = new ObjectInputStream(bis);
			try {
				data = (float[][]) in.readObject();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} finally {
			try {
				bis.close();
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return data;
	}

	public static TreeMap<String, String> restoreProgress() {
		TreeMap<String, String> restorData = new TreeMap<String, String>();
		Query query = new Query(E_TEMP_BLOBS);
		for (Entity e : DSUtil.ds.prepare(query).asIterable()) {

			byte[] myBytes = ((Blob) e.getProperty(P_BLOB_OB)).getBytes();
			restorData.putAll(formTreeMapFromBytes(myBytes));

		}

		return restorData;
	}

	public static TreeMap<Float, TreeMap<Integer, Pair>> restoreAnalysisResults(
			String entity, String property) {
		TreeMap<Float, TreeMap<Integer, Pair>> restorData = new TreeMap<Float, TreeMap<Integer, Pair>>();
		Query query = new Query(entity);
		for (Entity e : DSUtil.ds.prepare(query).asIterable()) {

			byte[] myBytes = ((Blob) e.getProperty(property)).getBytes();
			restorData.putAll(composeResultsFromBytes(myBytes));

		}

		return restorData;
	}

	private static TreeMap<String, String> formTreeMapFromBytes(byte[] myBytes) {
		TreeMap<String, String> data = null;
		ByteArrayInputStream bis = new ByteArrayInputStream(myBytes);
		ObjectInput in = null;
		try {
			in = new ObjectInputStream(bis);
			try {
				data = (TreeMap<String, String>) in.readObject();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} finally {
			try {
				bis.close();
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return data;
	}

	private static TreeMap<Float, TreeMap<Integer, Pair>> composeResultsFromBytes(
			byte[] myBytes) {
		TreeMap<Float, TreeMap<Integer, Pair>> data = null;
		ByteArrayInputStream bis = new ByteArrayInputStream(myBytes);
		ObjectInput in = null;
		try {
			in = new ObjectInputStream(bis);
			try {
				data = (TreeMap<Float, TreeMap<Integer, Pair>>) in.readObject();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} finally {
			try {
				bis.close();
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return data;
	}

	public static TreeMap<Float, float[][]> loadFundamentals(String index) {
		TreeMap<Float, float[][]> fundies = new TreeMap<Float, float[][]>();
		Query query = new Query(index);
		for (Entity e : ds.prepare(query).asIterable()) {

			Float date = Float.parseFloat(e.getKey().getName().split("_")[2]);

			Blob data = (Blob) e.getProperty(DSUtil.P_FLOAT_ARRAY);
			float[][] fundamental = DSBlobs
					.get2DFloatFromBytes(data.getBytes());
			fundies.put(date, fundamental);

		}
		return fundies;
	}

	public static TreeMap<Float, float[][][]> loadTechnicals(String index) {
		TreeMap<Float, float[][][]> techies = new TreeMap<Float, float[][][]>();
		Query query = new Query(index);
		for (Entity e : ds.prepare(query).asIterable()) {

			Float date = Float.parseFloat(e.getKey().getName().split("_")[2]);

			Blob data = (Blob) e.getProperty(DSUtil.P_FLOAT_ARRAY);
			float[][][] technical = DSBlobs
					.get3DFloatFromBytes(data.getBytes());
			techies.put(date, technical);

		}
		return techies;
	}

	static void postToDebug(String desc) {

		DSUtil.addToDatastoreAsText(DSUtil.DEBUG,
				DSBlobs.DATE.format(new Date()), "data", "" + desc);// /////////////////////////////////////////

	}

	public static void saveNewToDatabase(String[] index, String title) {
		// build arrays of data from TheBackend.completeResults and Yi.YY Qi.QQ
		StringBuilder bodyText = new StringBuilder();
		for (String s : index) {

			bodyText.append(TheBackend.completeResults.get(s));
		}

		AppInbox.storeData(title, bodyText.toString());
	}
}
