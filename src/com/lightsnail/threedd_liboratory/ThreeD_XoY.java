package com.lightsnail.threedd_liboratory;

import java.security.Policy;

import com.lightsnail.threedd_world.LiObject;
import com.lightsnail.threedd_world.LiPolygon;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

public class ThreeD_XoY extends View implements IDrawCall{

	private LiPolygon	mLiPolygon;


    private float[] mMatrix4XoYProjection = new float[]{

    		1,0,0,0,
    		0,1,0,0,
    		0,0,0,0,
    		0,0,0,1,
    };
	public ThreeD_XoY(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	public ThreeD_XoY(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();

	}

	public ThreeD_XoY(Context context) {
		super(context);
		init();
	}

	private void init() {
//		float radian = (float) (-90/360f * Math.PI * 2);
//		mMatrix4RotateX[5] = (float) Math.cos(radian);
//		mMatrix4RotateX[6] = (float) Math.sin(radian);
//		mMatrix4RotateX[9] = -mMatrix4RotateX[6];
//		mMatrix4RotateX[10] = mMatrix4RotateX[5];
//		mFinalTransform = MatrixUtil.mul4(mMatrix4XoZProjection, mMatrix4RotateX);
	}
	@Override
	public void notifyToDraw(LiObject liObject) {
		LiPolygon liPolygon = (LiPolygon) liObject;
		this.mLiPolygon = liPolygon;
		postInvalidate();
	}
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		if(mLiPolygon != null){
			mLiPolygon.draw(getWidth(),getHeight(),canvas,mMatrix4XoYProjection,false);
		}
			
	}

}
