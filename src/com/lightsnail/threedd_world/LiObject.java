package com.lightsnail.threedd_world;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.lightsnail.threedd_liboratory.Cartesian;

public class LiObject {

	public void drawLine(Canvas canvas, QC_PointF pointF_s, QC_PointF pointF_e, Paint mPaint) {
		canvas.drawLine(Cartesian.getX(pointF_s.x), Cartesian.getY(pointF_s.y), Cartesian.getX(pointF_e.x), Cartesian.getY(pointF_e.y), mPaint);
	}

	public void drawText(Canvas canvas, QC_PointF pointF_s,  String string, Paint mTextPaint) {
		canvas.drawText(string,Cartesian.getX(pointF_s.x), Cartesian.getY(pointF_s.y),   mTextPaint);
	}

}
