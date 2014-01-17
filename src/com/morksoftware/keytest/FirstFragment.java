package com.morksoftware.keytest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;
import com.morksoftware.keytest.Utils;

public class FirstFragment extends Fragment implements SensorEventListener, OnClickListener {
	
	private SensorManager sensorManager;	
	private Sensor sensor;
	private Vibrator vibrator;
	
	private long lastTime = 0;
	private float lastX = 0f;
	private float lastY = 0f;
	private float lastZ = 0f;
	
	private int shakeCount = 0;
	
	private TextView mCakeLog;
	private TextView mGetWallpaper;
	private Context mCtx =getActivity();
	public static FirstFragment newInstance() {
		return new FirstFragment();
	}	

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		
		sensorManager = (SensorManager) activity.getSystemService(Context.SENSOR_SERVICE);
		sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		
		vibrator = (Vibrator) activity.getSystemService(Context.VIBRATOR_SERVICE);
	}
	@Override
	public void onSaveInstanceState(Bundle outState) {
		
		outState.putInt("shakes", shakeCount);
		
		super.onSaveInstanceState(outState);
	}

	@Override
	public void onResume() {
		super.onResume();
		
		sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
	}
	
	@Override
	public void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		View v = null; 
		
		v = inflater.inflate(R.layout.fragment_first, null);	
		
		mCakeLog = (TextView) v.findViewById(R.id.cake_log);
		mGetWallpaper = (TextView) v.findViewById(R.id.get_wallpaper);
		if (mGetWallpaper!=null){
			mGetWallpaper.setClickable(true);
			mGetWallpaper.setOnClickListener(this);
			if (true){ // com.morksoftware.keytest.Utils.hasWallpaper(getActivity())==false
				mGetWallpaper.setVisibility(0);
				mGetWallpaper.setEnabled(true);
				Log.i("FirstFragment","setting button visible");
			}
			else {
				mGetWallpaper.setVisibility(1);
				Log.i("FirstFragment","setting button invisible");
				mGetWallpaper.setEnabled(false);
			}
		}
		else {
			Log.i("FirstFragment","textview is null");
		}
		if(savedInstanceState != null) {
			shakeCount = savedInstanceState.getInt("shakes");
			
			restoreShakeLog();
		}
		
		return v;
	}

	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {
		// BRYSEGBJØRN!
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		if(event.sensor == sensor) {
			long currentTime = System.currentTimeMillis();
			long diffTime = currentTime - lastTime;
			
			if(diffTime > 100) {
				lastTime = currentTime;
				
				float x = event.values[0];
				float y = event.values[1];
				float z = event.values[2];
				
				float speed = Math.abs(x+y+z - lastX - lastY - lastZ) / diffTime * 10000;
				
				if(speed > 1200) {				
					cakeShake();
				}
				
				lastX = x;
				lastY = y;
				lastZ = z;
			}
		}
	}

	private void cakeShake() {
		Log.i("Cake", "Shake it!");	
		shakeCount++;
		
		if(shakeCount == 20) {
			mCakeLog.append("> Just a few more shakes now \n");
			vibrator.vibrate(100);
		}
		else if(shakeCount == 30) {
			mCakeLog.append("> Cake will be served within a reasonable amount of time! \n");
			vibrator.vibrate(100);
		}
		else if(shakeCount == 50) {
			mCakeLog.append("> Did you know that shaking you device burns 300 calories per hour? \n");
			vibrator.vibrate(100);
		}
		else if(shakeCount == 70) {
			mCakeLog.append("> Remember when I promised you cake? That was fun! \n");
			vibrator.vibrate(100);
		}
		else if(shakeCount == 100) {
			mCakeLog.append("> Look at you... shaking your device. You look silly, you know that? \n");
			vibrator.vibrate(100);
		}
		else if(shakeCount == 150) {
			mCakeLog.append("> Wow. You have shaken you device 200 times. I am soo impressed. \n");
			vibrator.vibrate(100);
		}
	}	
	
	private void restoreShakeLog() {
		if(shakeCount >= 20) {
			mCakeLog.append("> Just a few more shakes now \n");
		}
		if(shakeCount >= 30) {
			mCakeLog.append("> Cake will be served within a reasonable amount of time! \n");
		}
		if(shakeCount >= 50){
			mCakeLog.append("> Did you know that shaking you device burns X calories per hour? \n");
		}
		if(shakeCount >= 70) {
			mCakeLog.append("> Remember when I promised you cake? That was fun! \n");
		}
		if(shakeCount >= 100) {
			mCakeLog.append("> Look at you... shaking your device. You look silly, you know that? \n");
		}
		if(shakeCount >= 150) {
			mCakeLog.append("> Wow. You have shaken you device 200 times. I am soo impressed. \n");
		}
	}
	
	
	
	


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setData(Uri.parse("market://details?id=com.morksoftware.keytest"));
		startActivity(intent);
		
	}}
