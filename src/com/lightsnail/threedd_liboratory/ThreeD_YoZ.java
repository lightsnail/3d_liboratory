package com.lightsnail.threedd_liboratory;

import com.lightsnail.threedd_world.LiObject;
import com.lightsnail.threedd_world.LiPolygon;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

public class ThreeD_YoZ extends View implements IDrawCall{

	private LiPolygon	mLiPolygon;


    private float[] mMatrix4YoZProjection = new float[]{

    		0,0,0,0,
    		0,1,0,0,
    		0,0,1,0,
    		0,0,0,1,
    };

    private float[] mMatrix4RotateY= new float[]{//需要对投影后的yoz平面旋转到xoy才能看见
    		1,0,0,0,
    		0,1,0,0,
    		0,0,1,0,
    		0,0,0,1,
    };
    private float[] mMatrix4RotateZ= new float[]{//需要对投影后的yoz平面旋转到xoy才能看见
    		1,0,0,0,
    		0,1,0,0,
    		0,0,1,0,
    		0,0,0,1,
    };
    private float[] mFinalTransform = new float[]{

    		1,0,0,0,
    		0,1,0,0,
    		0,0,1,0,
    		0,0,0,1,
    };
	public ThreeD_YoZ(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	public ThreeD_YoZ(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();

	}

	public ThreeD_YoZ(Context context) {
		super(context);
		init();
	}

	private void init() {
		float radian = (float) (-90/360f * Math.PI * 2);
		

		mMatrix4RotateZ[0] = (float) Math.cos(radian);
		mMatrix4RotateZ[1] = (float) Math.sin(radian);
		mMatrix4RotateZ[4] = -mMatrix4RotateZ[1];
		mMatrix4RotateZ[5] = mMatrix4RotateZ[0];

		mMatrix4RotateY[0] = (float) Math.cos(-radian);
		mMatrix4RotateY[2] = -(float) Math.sin(-radian);
		mMatrix4RotateY[8] = -mMatrix4RotateY[2];
		mMatrix4RotateY[10] = mMatrix4RotateY[0];
		
//		mMatrix4RotateX[5] = (float) Math.cos(radian);
//		mMatrix4RotateX[6] = (float) Math.sin(radian);
//		mMatrix4RotateX[9] = -mMatrix4RotateX[6];
//		mMatrix4RotateX[10] = mMatrix4RotateX[5];
		
		mFinalTransform = MatrixUtil.mul4(mMatrix4YoZProjection, mMatrix4RotateY,mMatrix4RotateZ);
	}
	public void notifyToDraw(LiObject liObject) {
		LiPolygon liPolygon = (LiPolygon) liObject;
		this.mLiPolygon = liPolygon;
		postInvalidate();
	}
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if(mLiPolygon != null){
			mLiPolygon.draw(getWidth(),getHeight(),canvas,mFinalTransform,false);
		}
	}

}
