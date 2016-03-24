package com.lightsnail.threedd_liboratory;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

import com.example.threedd_liboratory.R;

public class MainActivity extends Activity {

	private ThreeD_View mThreeD;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mThreeD = (ThreeD_View) findViewById(R.id.threeDView);
		IDrawCall viewXoy = (IDrawCall) findViewById(R.id.xoy);
		IDrawCall viewXoz = (IDrawCall) findViewById(R.id.xoz);
		IDrawCall viewYoz = (IDrawCall) findViewById(R.id.yoz);
		
		
		
		ArrayList<IDrawCall>  list = new ArrayList<IDrawCall>();
		list.add(viewXoy);
		list.add(viewXoz);
		list.add(viewYoz);
		mThreeD.registViews(list);

		findViewById(R.id.transX).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mThreeD.goTranX();
			}
		});
		findViewById(R.id.transY).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mThreeD.goTranY();
			}
		});
		findViewById(R.id.transZ).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mThreeD.goTranZ();
			}
		});
		findViewById(R.id.scaleX).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mThreeD.goScaleX();
			}
		});
		findViewById(R.id.scaleY).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mThreeD.goScaleY();
			}
		});
		findViewById(R.id.scaleZ).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mThreeD.goScaleZ();
			}
		});
		findViewById(R.id.rotateZ).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mThreeD.goRotateZ();	
			}
		});
		findViewById(R.id.rotateY).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mThreeD.goRotateY();	
			}
		});
		findViewById(R.id.rotateX).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mThreeD.goRotateX();	
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
