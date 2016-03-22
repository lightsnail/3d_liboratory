package com.lightsnail.threedd_world;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import com.lightsnail.threedd_liboratory.AppConstant;
import com.lightsnail.threedd_liboratory.LiScroller;
import com.lightsnail.threedd_liboratory.LogUtil;
import com.lightsnail.threedd_liboratory.MatrixUtil;

public class LiPolygon extends LiObject {


	private ArrayList<QC_PointF> mSourcePointList;
	private Paint mPaint;
	private Paint mTextPaint;
	private LiScroller mLiScrollerX  ;
	private LiScroller mLiScrollerY  ;
	private LiScroller mLiScrollerZ  ;
	private LiScroller mLiScrollerScaleX  ;
	private LiScroller mLiScrollerScaleY  ;
	private LiScroller mLiScrollerRotateZ  ;
	private LiScroller mLiScrollerRotateY  ;
	private LiScroller mLiScrollerRotateX  ;
	
	

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
    private float[] mMatrix4Tran = new float[]{
    		1,0,0,0,
    		0,1,0,0,
    		0,0,1,0,
    		0,0,0,1,
    };

    private float[] mMatrix4Scale= new float[]{
    		1,0,0,
    		0,1,0,
    		0,0,1,
    };
    private float[] mMatrix4RotateZ= new float[]{
    		1,0,0,0,
    		0,1,0,0,
    		0,0,1,0,
    		0,0,0,1,
    };
    private float[] mMatrix4RotateY= new float[]{
    		1,0,0,0,
    		0,1,0,0,
    		0,0,1,0,
    		0,0,0,1,
    };
    private float[] mMatrix4RotateX= new float[]{
    		1,0,0,0,
    		0,1,0,0,
    		0,0,1,0,
    		0,0,0,1,
    };
	private ArrayList<Integer>	mIndexList;
    
	public LiPolygon(Context mContext){
		mPaint = new Paint();
		mPaint.setColor(Color.WHITE);
		mTextPaint = new Paint();
		mTextPaint.setColor(Color.GREEN);
		mTextPaint.setTextSize(25*3);
		
		mLiScrollerX = new LiScroller(mContext,0,100);
		mLiScrollerY = new LiScroller(mContext,0,100);
		mLiScrollerZ = new LiScroller(mContext,0,100);
		mLiScrollerScaleX = new LiScroller(mContext,1,100);
		mLiScrollerScaleY = new LiScroller(mContext,1,100);
		mLiScrollerRotateZ = new LiScroller(mContext,0,100);
		mLiScrollerRotateY = new LiScroller(mContext,0,100);
		mLiScrollerRotateX = new LiScroller(mContext,0,100);
	}
	public void draw( int width, int height, Canvas canvas,float[] projection, boolean joinZ) {
		

//		mMatrix4Tran[12] = mLiScrollerX.getCurrentIndex();
//		mMatrix4Tran[13] = mLiScrollerY.getCurrentIndex();
//		mMatrix4Tran[14] = mLiScrollerZ.getCurrentIndex();
		
		mMatrix4Scale[0] = mLiScrollerScaleX.getCurrentIndex();
		mMatrix4Scale[4] = mLiScrollerScaleY.getCurrentIndex();

		mMatrix4RotateZ[0] = (float) Math.cos(mLiScrollerRotateZ.getCurrentIndex());
		mMatrix4RotateZ[1] = (float) Math.sin(mLiScrollerRotateZ.getCurrentIndex());
		mMatrix4RotateZ[4] = -mMatrix4RotateZ[1];
		mMatrix4RotateZ[5] = mMatrix4RotateZ[0];

		mMatrix4RotateY[0] = (float) Math.cos(mLiScrollerRotateY.getCurrentIndex());
		mMatrix4RotateY[2] = -(float) Math.sin(mLiScrollerRotateY.getCurrentIndex());
		mMatrix4RotateY[8] = -mMatrix4RotateY[2];
		mMatrix4RotateY[10] = mMatrix4RotateY[0];
		
		mMatrix4RotateX[5] = (float) Math.cos(mLiScrollerRotateX.getCurrentIndex());
		mMatrix4RotateX[6] = (float) Math.sin(mLiScrollerRotateX.getCurrentIndex());
		mMatrix4RotateX[9] = -mMatrix4RotateX[6];
		mMatrix4RotateX[10] = mMatrix4RotateX[5];
		
		mMatrix4Tran[14] = 0;
		if(joinZ){
			mMatrix4Tran[14] = -1000;
		}
		ArrayList<QC_PointF> mTargetPointList = new ArrayList<QC_PointF>();
		for (int i = 0; i < mSourcePointList.size(); i++) {
		
			float[]	 targetMatrix  =  MatrixUtil.mul4( mMatrix4RotateX,mMatrix4RotateY,mMatrix4RotateZ,mMatrix4Tran);
			QC_PointF  qc_PointF = MatrixUtil.mul4(mSourcePointList.get(i),targetMatrix);
			Log.d("debug", "qc_PointF.z = "+qc_PointF.z);
			 if(joinZ){
//					projection[0] *= 0.6f;
//					projection[5] *= 0.6f;
//					projection[9] *= 0.6f;
					 qc_PointF = MatrixUtil.mul4(qc_PointF,new float[]{

							-600/ qc_PointF.z ,0,0,0,
					    		0,-600/ qc_PointF.z,0,0,
					    		0,0,0.5f,0,
					    		0,0,0,1,
					    });

				}
			 qc_PointF = MatrixUtil.mul4(qc_PointF,projection);
			
			mTargetPointList.add(qc_PointF);
		}
		
		if(mTargetPointList != null && mTargetPointList.size() > 0 ){
			for (int i = 0; i < mIndexList.size(); i+=2) {

				QC_PointF pointF_s = mTargetPointList.get(mIndexList.get(i));
				QC_PointF pointF_e = mTargetPointList.get(mIndexList.get(i+1));
				super.drawLine(width,height,canvas,pointF_s,pointF_e,mPaint);
				super.drawText(width,height,canvas,pointF_s,mIndexList.get(i)+"",mTextPaint);
			}
		}
	}

	public void init(ArrayList<QC_PointF> arrayList, ArrayList<Integer> indexList) {
		this.mSourcePointList = arrayList;
		this.mIndexList = indexList;
	}
	public void translation(float x, float y,float z) {
		LogUtil.d("tra x = "+x +",y = "+y);
		mLiScrollerX.scrollToTargetIndex(x, AppConstant.ANIMATION_DURATION);
		mLiScrollerY.scrollToTargetIndex(y, AppConstant.ANIMATION_DURATION);
		mLiScrollerZ.scrollToTargetIndex(z, AppConstant.ANIMATION_DURATION);
	}
	public void scale(float x, float y) {
		LogUtil.d("scale x = "+x +",y = "+y);
		mLiScrollerScaleX.scrollToTargetIndex(x, AppConstant.ANIMATION_DURATION);
		mLiScrollerScaleY.scrollToTargetIndex(y, AppConstant.ANIMATION_DURATION);
	}
	public void rotateZ(float rotate) {
		LogUtil.d("rotate  = "+  rotate);
		float radian = (float) (rotate/360f * Math.PI * 2);
		mLiScrollerRotateZ.scrollToTargetIndex(radian, AppConstant.ANIMATION_DURATION);
	}
	public void rotateY(float rotate) {
		LogUtil.d("rotate  = "+  rotate);
		float radian = (float) (rotate/360f * Math.PI * 2);
		mLiScrollerRotateY.scrollToTargetIndex(radian, AppConstant.ANIMATION_DURATION);
	}
	public void rotateX(float rotate) {
		LogUtil.d("rotate  = "+  rotate);
		float radian = (float) (rotate/360f * Math.PI * 2);
		mLiScrollerRotateX.scrollToTargetIndex(radian, AppConstant.ANIMATION_DURATION);
	}

//	public boolean computeScroll() {
//		boolean a = mLiScrollerX.computeOffset();
//		boolean b =  mLiScrollerY.computeOffset();
//		boolean c = mLiScrollerScaleX.computeOffset();
//		boolean d =  mLiScrollerScaleY.computeOffset();
//		boolean e = mLiScrollerRotate0.computeOffset();
//		
//		boolean finalBoo = a || b|| c|| d|| e ;
//		
//		mMatrixTran[6] = mLiScrollerX.getCurrentIndex();
//		mMatrixTran[7] = mLiScrollerY.getCurrentIndex();
//		
//		mMatrixScale[0] = mLiScrollerScaleX.getCurrentIndex();
//		mMatrixScale[4] = mLiScrollerScaleY.getCurrentIndex();
//
//		mMatrixRotate[0] = (float) Math.cos(mLiScrollerRotate0.getCurrentIndex());
//		mMatrixRotate[1] = (float) Math.sin(mLiScrollerRotate0.getCurrentIndex());
//		mMatrixRotate[3] = -mMatrixRotate[1];
//		mMatrixRotate[4] = mMatrixRotate[0];
//		
//		
//		mTargetPointList.clear();
//		for (int i = 0; i < mSourcePointList.size(); i++) {
//			float[]	 targetMatrix =  MatrixUtil.mul3(mMatrixRotate,mMatrixTran);
//			targetMatrix =  MatrixUtil.mul3(mMatrixScale,targetMatrix);
//			QC_PointF  qc_PointF = MatrixUtil.mul3(mSourcePointList.get(i),targetMatrix);
//			mTargetPointList.add(qc_PointF);
//		}
//		return finalBoo;
//	}

	public boolean computeScroll() {
		boolean a = mLiScrollerX.computeOffset();
		boolean b =  mLiScrollerY.computeOffset();
		boolean c = mLiScrollerScaleX.computeOffset();
		boolean d =  mLiScrollerScaleY.computeOffset();
		boolean e = mLiScrollerRotateZ.computeOffset();
		boolean f = mLiScrollerRotateY.computeOffset();
		boolean g = mLiScrollerRotateX.computeOffset();
		boolean h = mLiScrollerZ.computeOffset();
		
		boolean finalBoo = a || b|| c|| d|| e || f ||g ||h;
		
		return finalBoo;
	}

}
