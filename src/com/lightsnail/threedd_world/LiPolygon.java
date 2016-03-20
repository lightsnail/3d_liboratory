package com.lightsnail.threedd_world;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.lightsnail.threedd_liboratory.AppConstant;
import com.lightsnail.threedd_liboratory.LiScroller;
import com.lightsnail.threedd_liboratory.LogUtil;
import com.lightsnail.threedd_liboratory.MatrixUtil;

public class LiPolygon extends LiObject {


	private ArrayList<QC_PointF> mSourcePointList;
	private ArrayList<QC_PointF> mTargetPointList = new ArrayList<QC_PointF>();
	private Paint mPaint;
	private Paint mTextPaint;
	private LiScroller mLiScrollerX  ;
	private LiScroller mLiScrollerY  ;
	private LiScroller mLiScrollerScaleX  ;
	private LiScroller mLiScrollerScaleY  ;
	private LiScroller mLiScrollerRotate0  ;
    private float[] mMatrixTran = new float[]{
    		1,0,0,
    		0,1,0,
    		0,0,1,
    };

    private float[] mMatrixScale= new float[]{
    		1,0,0,
    		0,1,0,
    		0,0,1,
    };
    private float[] mMatrixRotate= new float[]{
    		1,0,0,
    		0,1,0,
    		0,0,1,
    };
    
	public LiPolygon(Context mContext){
		mPaint = new Paint();
		mPaint.setColor(Color.WHITE);
		mTextPaint = new Paint();
		mTextPaint.setColor(Color.GREEN);
		mTextPaint.setTextSize(25*3);
		
		mLiScrollerX = new LiScroller(mContext,0,100);
		mLiScrollerY = new LiScroller(mContext,0,100);
		mLiScrollerScaleX = new LiScroller(mContext,1,100);
		mLiScrollerScaleY = new LiScroller(mContext,1,100);
		mLiScrollerRotate0 = new LiScroller(mContext,0,100);
	}
	public void draw(Canvas canvas) {
		if(mTargetPointList != null && mTargetPointList.size() > 0 ){
			for (int i = 0; i < mTargetPointList.size()-1; i++) {
				QC_PointF pointF_s =mTargetPointList.get(i);
				QC_PointF pointF_e =mTargetPointList.get(i+1);
				super.drawLine(canvas,pointF_s,pointF_e,mPaint);
				super.drawText(canvas,pointF_s,i+"",mTextPaint);
			}

			QC_PointF pointF_s =mTargetPointList.get( mTargetPointList.size()-1);
			QC_PointF pointF_e =mTargetPointList.get(0);
			super.drawLine(canvas,pointF_s,pointF_e,mPaint);
			super.drawText(canvas,pointF_s,( mTargetPointList.size()-1)+"",mTextPaint);
				
		}
	}

	public void init(ArrayList<QC_PointF> arrayList) {
		this.mSourcePointList = arrayList;
		for (int i = 0; i < mSourcePointList.size(); i++) {
			mTargetPointList.add(mSourcePointList.get(i));
		}
	}
	public void translation(float x, float y) {
		LogUtil.d("tra x = "+x +",y = "+y);
		mLiScrollerX.scrollToTargetIndex(x, AppConstant.ANIMATION_DURATION);
		mLiScrollerY.scrollToTargetIndex(y, AppConstant.ANIMATION_DURATION);
	}
	public void scale(float x, float y) {
		LogUtil.d("scale x = "+x +",y = "+y);
		mLiScrollerScaleX.scrollToTargetIndex(x, AppConstant.ANIMATION_DURATION);
		mLiScrollerScaleY.scrollToTargetIndex(y, AppConstant.ANIMATION_DURATION);
	}
	public void rotate(float rotate) {
		LogUtil.d("rotate  = "+  rotate);
		float radian = (float) (rotate/360f * Math.PI * 2);
		mLiScrollerRotate0.scrollToTargetIndex(radian, AppConstant.ANIMATION_DURATION);
	}
	
	public boolean computeScroll() {
		boolean a = mLiScrollerX.computeOffset();
		boolean b =  mLiScrollerY.computeOffset();
		boolean c = mLiScrollerScaleX.computeOffset();
		boolean d =  mLiScrollerScaleY.computeOffset();
		boolean e = mLiScrollerRotate0.computeOffset();
		
		boolean finalBoo = a || b|| c|| d|| e ;
		
		mMatrixTran[6] = mLiScrollerX.getCurrentIndex();
		mMatrixTran[7] = mLiScrollerY.getCurrentIndex();
		
		mMatrixScale[0] = mLiScrollerScaleX.getCurrentIndex();
		mMatrixScale[4] = mLiScrollerScaleY.getCurrentIndex();

		mMatrixRotate[0] = (float) Math.cos(mLiScrollerRotate0.getCurrentIndex());
		mMatrixRotate[1] = (float) Math.sin(mLiScrollerRotate0.getCurrentIndex());
		mMatrixRotate[3] = -mMatrixRotate[1];
		mMatrixRotate[4] = mMatrixRotate[0];
		
		
		mTargetPointList.clear();
		for (int i = 0; i < mSourcePointList.size(); i++) {
			float[]	 targetMatrix =  MatrixUtil.mul3(mMatrixRotate,mMatrixTran);
			targetMatrix =  MatrixUtil.mul3(mMatrixScale,targetMatrix);
			QC_PointF  qc_PointF = MatrixUtil.mul3(mSourcePointList.get(i),targetMatrix);
			mTargetPointList.add(qc_PointF);
		}
		return finalBoo;
	}

}
