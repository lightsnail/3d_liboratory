package com.lightsnail.threedd_liboratory;

import java.util.ArrayList;

import javax.security.auth.PrivateCredentialPermission;

import com.lightsnail.threedd_world.LiPolygon;
import com.lightsnail.threedd_world.QC_PointF;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.View;

public class ThreeD_View  extends View{

    private float[] mMatrix4Projection = new float[]{

    		1,0,0,0,
    		0,1,0,0,
    		0,0,1,0,
    		0,0,0,1,
    };
    private float[] mMatrix4XoY = new float[]{

    		1,0,0,0,
    		0,1,0,0,
    		0,0,0,0,
    		0,0,0,1,
    };
    private float[] mMatrix4Tranlat= new float[]{
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
    
    private float[] mMatrixFinal = new float[]{

    		1,0,0,0,
    		0,1,0,0,
    		0,0,1,0,
    		0,0,0,1,
    };
	private boolean mInit = false;
	private LiPolygon mLipolygon;

	private Context mContext;

	private ArrayList<IDrawCall>	mDrawObserverList;
	public ThreeD_View(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		this.mContext =context;
		init();
	}


	public ThreeD_View(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.mContext =context;
		init();
	}

	public ThreeD_View(Context context) {
		super(context);
		this.mContext =context;
		init();
	}
	private void init() {
		
		float radian = (float) (-90/360f * Math.PI * 2);
		mMatrix4RotateX[5] = (float) Math.cos(radian);
		mMatrix4RotateX[6] = (float) Math.sin(radian);
		mMatrix4RotateX[9] = -mMatrix4RotateX[6];
		mMatrix4RotateX[10] = mMatrix4RotateX[5];

		
//		mMatrix4Tranlat[12] = 0;
//		mMatrix4Tranlat[13] = 0;
//		mMatrix4Tranlat[14] = -1200;
		
		mMatrixFinal = MatrixUtil.mul4(  mMatrix4Projection,mMatrix4XoY);
	}

	@Override
	protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
		super.onLayout(changed, left, top, right, bottom);
		AppConstant.SCREEN_WIDTH = getWidth();
		AppConstant.SCREEN_HEIGHT = getHeight();
		if(!mInit){
			
			mInit  = true;
			AppConstant.UNIT = AppConstant.SCREEN_WIDTH > AppConstant.SCREEN_HEIGHT ?  AppConstant.SCREEN_HEIGHT : AppConstant.SCREEN_WIDTH;
			ArrayList<QC_PointF> arrayList = new ArrayList<QC_PointF>(); 
			arrayList.add(new QC_PointF(-0.3f * AppConstant.UNIT, -0.3f * AppConstant.UNIT,0.3f * AppConstant.UNIT));//0
			arrayList.add(new QC_PointF(0.3f * AppConstant.UNIT, -0.3f * AppConstant.UNIT,0.3f * AppConstant.UNIT));//1
			arrayList.add(new QC_PointF(0.3f * AppConstant.UNIT,0.3f * AppConstant.UNIT,0.3f * AppConstant.UNIT));//2
			arrayList.add(new QC_PointF(-0.3f * AppConstant.UNIT,0.3f * AppConstant.UNIT,0.3f * AppConstant.UNIT));//3
			arrayList.add(new QC_PointF(-0.3f * AppConstant.UNIT, -0.3f * AppConstant.UNIT,-0.3f * AppConstant.UNIT));//4
			arrayList.add(new QC_PointF(0.3f * AppConstant.UNIT, -0.3f * AppConstant.UNIT,-0.3f * AppConstant.UNIT));//5
			arrayList.add(new QC_PointF(0.3f * AppConstant.UNIT,0.3f * AppConstant.UNIT,-0.3f * AppConstant.UNIT));//6
			arrayList.add(new QC_PointF(-0.3f * AppConstant.UNIT,0.3f * AppConstant.UNIT,-0.3f * AppConstant.UNIT));//7

			ArrayList<Integer> indexList = new ArrayList<Integer>();
			indexList.add(0);indexList.add(1);
			indexList.add(1);indexList.add(2);
			indexList.add(2);indexList.add(3);
			indexList.add(3);indexList.add(0);
			
			indexList.add(4+0);indexList.add(4+1);
			indexList.add(4+1);indexList.add(4+2);
			indexList.add(4+2);indexList.add(4+3);
			indexList.add(4+3);indexList.add(4+0);

			indexList.add(0);indexList.add(4+0);
			indexList.add(1);indexList.add(4+1);
			indexList.add(2);indexList.add(4+2);
			indexList.add(3);indexList.add(4+3);
			
			mLipolygon = new LiPolygon(mContext);
			mLipolygon.init(arrayList,indexList);
		}
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		mMatrixFinal = new float[]{

	    		1,0,0,0,
	    		0,1,0,0,
	    		0,0,0,0,
	    		0,0,0,1,
	    };
		mLipolygon.draw(getWidth(),getHeight(),canvas,mMatrixFinal,true);
//		mLipolygon.draw(canvas,mMatrix4YoZProjection);
//		mLipolygon.draw(canvas,mMatrix4XoZProjection);
		
		if(mDrawObserverList == null){
			return;
		}
		for (int i = 0; i < mDrawObserverList.size(); i++) {
			mDrawObserverList.get(i).notifyToDraw(mLipolygon);
		}
		
	}

	@Override
	public void computeScroll() {
		super.computeScroll();
		if(mLipolygon.computeScroll()){
			postInvalidate();
		}
	}
	public void goTranX() {
		float x = AppConstant.UNIT/2;
		float y = AppConstant.UNIT/2;
		float z = AppConstant.SCREEN_HEIGHT ;
		float xr  = (float) (Math.random() *2- 1);
		float yr  = (float) (Math.random() *2- 1);
		float zr  = (float) (-Math.random() *4 );
//		mLipolygon.translation(x*xr,y*yr,z*zr);
		mLipolygon.translationX(x*xr );
		
		postInvalidate();
	}
	public void goTranY() {
		float x = AppConstant.UNIT/2;
		float y = AppConstant.UNIT/2;
		float z = AppConstant.SCREEN_HEIGHT ;
		float xr  = (float) (Math.random() *2- 1);
		float yr  = (float) (Math.random() *2- 1);
		float zr  = (float) (-Math.random() *4 );
//		mLipolygon.translation(x*xr,y*yr,z*zr);
		mLipolygon.translationY( y*yr);
		
		postInvalidate();
	}
	public void goTranZ() {
		float x = AppConstant.UNIT/2;
		float y = AppConstant.UNIT/2;
		float z = AppConstant.SCREEN_HEIGHT ;
		float xr  = (float) (Math.random() *2- 1);
		float yr  = (float) (Math.random() *2- 1);
		float zr  = (float) (-Math.random() *4 );
//		mLipolygon.translation(x*xr,y*yr,z*zr);
		mLipolygon.translationZ( -AppConstant.UNIT + AppConstant.UNIT *zr);
		
		postInvalidate();
		
		
	}

	public void goScaleX() {

//		float x = AppConstant.SCREEN_WIDTH/200;
//		float y = AppConstant.SCREEN_HEIGHT/200;
//		float z = AppConstant.SCREEN_WIDTH/200;
		float xr  = (float) (Math.random() *4 );
		float yr  = (float) (Math.random() *4 );
		float zr  = (float) (Math.random() *4 );
		mLipolygon.scaleX( xr );
		postInvalidate();
	}
	public void goScaleY() {

//		float x = AppConstant.SCREEN_WIDTH/200;
//		float y = AppConstant.SCREEN_HEIGHT/200;
//		float z = AppConstant.SCREEN_WIDTH/200;
		float yr  = (float) (Math.random() *4 );
		mLipolygon.scaleY( yr);
		postInvalidate();
	}
	public void goScaleZ() {

//		float x = AppConstant.SCREEN_WIDTH/200;
//		float y = AppConstant.SCREEN_HEIGHT/200;
//		float z = AppConstant.SCREEN_WIDTH/200;
		float xr  = (float) (Math.random() *4 );
		float yr  = (float) (Math.random() *4 );
		float zr  = (float) (Math.random() *4 );
		mLipolygon.scaleZ(  zr);
		postInvalidate();
	}
	public void goRotateZ() {

		float xr  = (float) (Math.random() *2- 1);
		mLipolygon.rotateZ(360*xr);
		postInvalidate();
	}

	public void goRotateY() {

		float xr  = (float) (Math.random() *2- 1);
		mLipolygon.rotateY(360*xr);
		postInvalidate();
	}
	public void goRotateX() {

		float xr  = (float) (Math.random() *2- 1);
		mLipolygon.rotateX(360*xr);
		postInvalidate();
	}

	public void registViews(ArrayList<IDrawCall> list) {
		this.mDrawObserverList = list;
	}
	
}
