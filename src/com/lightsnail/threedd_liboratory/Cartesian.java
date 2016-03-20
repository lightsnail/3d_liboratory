package com.lightsnail.threedd_liboratory;


public class Cartesian {


	public static float getX(float x) {
		return AppConstant.SCREEN_WIDTH/2 + x;
	}

	public static float getY(float y) {
		return AppConstant.SCREEN_HEIGHT/2 - y;
	}

}
