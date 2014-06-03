package sk.village.office.util;


public class Log {
	private static final String TAG = "OFFICE";
	private static final boolean DEBUG = true;

	public static void i(String message) {
		if (DEBUG)
			android.util.Log.i(TAG, message);
	}
	
	public static void e(String message) {
		if (DEBUG)
			android.util.Log.e(TAG, message);
	}
	
	public static void v(String message) {
		if (DEBUG)
			android.util.Log.v(TAG, message);
	}
	
	public static void d(String message) {
		if (DEBUG)
			android.util.Log.d(TAG, message);
	}
}
