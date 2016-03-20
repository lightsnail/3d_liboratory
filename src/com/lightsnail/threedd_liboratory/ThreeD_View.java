package com.lightsnail.threedd_liboratory;

import java.util.ArrayList;

import com.lightsnail.threedd_world.LiPolygon;
import com.lightsnail.threedd_world.QC_PointF;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.View;

public class ThreeD_View  extends View{

	private boolean mInit = false;
	
	private LiPolygon mLipolygon;

	private Context mContext;
	public ThreeD_View(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		this.mContext =context;
	}

	public ThreeD_View(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.mContext =context;
	}

	public ThreeD_View(Context context) {
		super(context);
		this.mContext =context;
	}

	@Override
	protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
		super.onLayout(changed, left, top, right, bottom);
		AppConstant.SCREEN_WIDTH = getWidth();
		AppConstant.SCREEN_HEIGHT = getHeight();
		if(!mInit){
			mInit  = true;
			ArrayList<QC_PointF> arrayList = new ArrayList<QC_PointF>();
			arrayList.add(new QC_PointF(-100, -100));
			arrayList.add(new QC_PointF(100, -100));
			arrayList.add(new QC_PointF(100,100));
			arrayList.add(new QC_PointF(-100f,100f));
			mLipolygon = new LiPolygon(mContext);
			mLipolygon.init(arrayList);
		}
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		mLipolygon.draw(canvas);
	}

	@Override
	public void computeScroll() {
		super.computeScroll();
		if(mLipolygon.computeScroll()){
			postInvalidate();
		}
	}
	public void goTran() {
		float x = AppConstant.SCREEN_WIDTH/2;
		float y = AppConstant.SCREEN_HEIGHT/2;
		float xr  = (float) (Math.random() *2- 1);
		float yr  = (float) (Math.random() *2- 1);
		mLipolygon.translation(x*xr,y*yr);
		
		postInvalidate();
	}

	public void goScale() {

		float x = AppConstant.SCREEN_WIDTH/200;
		float y = AppConstant.SCREEN_HEIGHT/200;
		float xr  = (float) (Math.random() *2- 1);
		float yr  = (float) (Math.random() *2- 1);
		mLipolygon.scale(x*xr,y*yr);
		postInvalidate();
	}
	public void goRotate() {

		float xr  = (float) (Math.random() *2- 1);
		mLipolygon.rotate(360*xr);
		postInvalidate();
	}
	
}
