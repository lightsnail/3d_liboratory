package com.lightsnail.threedd_liboratory;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Path.Direction;
import android.util.Log;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.Scroller;

public class LiScroller {
	
	private Scroller mScroller;
	private float 	mCurrentIndex;
	private float 	mSpace;
	private float	mCurrentPara;
	private float	mTargetIndex;
	private float	mTargetPara;
	private IRoll	mRoll; 
	public interface IRoll{
		void onRoll(float f);
	}
	private Interpolator mInterpolator = new Interpolator() {

//		float a = 1f/((float) Math.sin(Math.PI * 2/2.8f ));
		@Override
		public float getInterpolation(float input) {
			
//			float t = a*(float) Math.sin(Math.PI * 2/2.8f * input);
//			Log.d("debug", "input = "+input+",,"+ "t = "+t);
//			return t  ;
			return input;
		}
	};
	private Context mContext;
	
	public  LiScroller(Context context){
		this.mContext = context;
		mScroller = new Scroller(context,mInterpolator);
	}
	public  LiScroller(Context context,Interpolator i){
		this.mInterpolator = i;
		this.mContext = context;
		mScroller = new Scroller(context,mInterpolator);
	}
	public  LiScroller(Context context,float index,float space){
		this.mContext = context;
		mScroller = new Scroller(context,mInterpolator);
		init(index,space);
	}
	public  LiScroller(Context context,float index,float space,Interpolator i){

		this.mContext = context;
		this.mInterpolator = i;
		mScroller = new Scroller(context,mInterpolator);
		init(index,space);
	}
	public void setOnRollListener(IRoll l) {
		this.mRoll = l;
	} 
	public void init(float index, float space){
		this.mCurrentIndex = index;
		this.mSpace = space;
		this.mCurrentPara = this.mCurrentIndex * this.mSpace;
		mScroller.setFinalX((int)mCurrentPara);
//		mScroller.abortAnimation(); 
	}
	public void setCurrentIndex(float index){
		this.mCurrentIndex = index;
		this.mCurrentPara = this.mCurrentIndex * this.mSpace;
		mScroller.setFinalX((int)mCurrentPara);
//		mScroller.abortAnimation(); 
	}
	public void setCurrentPara(float para){
		this.mCurrentPara = para;
		mScroller.setFinalX((int)mCurrentPara);
//		mScroller.abortAnimation(); 
	}
	public float getCurrentPara(){
		this.mCurrentPara = mScroller.getCurrX();
//		Log.d("debug", "mCurrentPara  = "+mCurrentPara + ",,mSpace = "+mSpace);
		return this.mCurrentPara;
	}
	public float getCurrentIndex(){
		this.mCurrentPara = mScroller.getCurrX();
		this.mCurrentIndex = mCurrentPara/mSpace;
		return mCurrentIndex;
	}
	public void scrollToTargetIndex(float index,int duration){
		this.mTargetIndex = index;
		this.mTargetPara = this.mTargetIndex * this.mSpace;
		
		int dx = (int) (mTargetPara - mCurrentPara) ;
//		Log.d("debug", "dx  = "+dx + ",,index = "+index);
		mScroller.forceFinished(true);
//		mScroller.extendDuration(duration);
		mScroller.startScroll((int)mCurrentPara, 0,  dx, 0,duration);
   }
	 
	public void scrollToTargetIndex(float index,int duration, Interpolator i){
		if(mInterpolator != i){
			mInterpolator = i;
			mScroller = new Scroller(mContext,mInterpolator);
		}
		scrollToTargetIndex(index,duration);
	}
	public void scrollToTargetPara(float targetPara,int duration){
			this.mTargetIndex = targetPara/this.mSpace;
			this.mTargetPara = targetPara;
			
			int dx = (int) (mTargetPara - mCurrentPara) ;
//			Log.d("debug", "dx  = "+dx + ",,index = "+index);
			mScroller.forceFinished(true);
//			mScroller.extendDuration(duration);
			mScroller.startScroll((int)mCurrentPara, 0,  dx, 0,duration);
	}
	
	public boolean computeOffset() {
//		Log.d("debug", "mCurrentPara  = "+mCurrentPara );
		if(mRoll != null){
			mRoll.onRoll(getCurrentPara());
		}
		return mScroller.computeScrollOffset();
	}
	public void forceFinish(){
		mScroller.forceFinished(true);
	}
	public float getTargetPara() {
		return mTargetPara;
	}
	public float getTargetIndex() {
		return mTargetIndex;
	}
	public float getCurrVelocity(){
		return mScroller.getCurrVelocity();
	}
	public void dragBy( int dd) {

		mCurrentPara = mScroller.getCurrX() + dd;
		mScroller.setFinalX((int)mCurrentPara);
	}
	@SuppressLint("NewApi") public void flingTo(int velocity, int minX,int maxX,int minY,int maxY) {
//		mScroller.setFinalX((int)mCurrentPara);
//		mScroller.forceFinished(true);
		mScroller.abortAnimation();
		mScroller.fling(mScroller.getCurrX(), 0, velocity, 0, minX, maxX, minY, maxY);
		
	}
	
}
