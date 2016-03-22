package com.lightsnail.threedd_world;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.lightsnail.threedd_liboratory.Cartesian;

public class LiObject {

	public void drawLine(int width, int height, Canvas canvas, QC_PointF pointF_s, QC_PointF pointF_e, Paint mPaint) {
		canvas.drawLine(Cartesian.getX(width,pointF_s.x), Cartesian.getY(height,pointF_s.y), Cartesian.getX(width,pointF_e.x), Cartesian.getY(height,pointF_e.y), mPaint);
	}

	public void drawText(int width, int height, Canvas canvas, QC_PointF pointF_s,  String string, Paint mTextPaint) {
		canvas.drawText(string,Cartesian.getX(width,pointF_s.x), Cartesian.getY(height,pointF_s.y),   mTextPaint);
	}

}
