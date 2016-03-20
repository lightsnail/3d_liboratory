package com.lightsnail.threedd_liboratory;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

import com.example.threedd_liboratory.R;

public class MainActivity extends ActionBarActivity {

	private ThreeD_View mThreeD;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mThreeD = (ThreeD_View) findViewById(R.id.threeDView);
		findViewById(R.id.trans).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mThreeD.goTran();
			}
		});
		findViewById(R.id.scale).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mThreeD.goScale();
			}
		});
		findViewById(R.id.rotate).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mThreeD.goRotate();	
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
