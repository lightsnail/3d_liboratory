package com.lightsnail.threedd_liboratory;

import android.util.Log;

public class LogUtil {

	private static boolean debug = true;
	public static void d(String string) {
		if(debug){
			Log.d("debug",string);
		}
	}

}
