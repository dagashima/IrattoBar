package com.example.irattobar;

import android.app.Activity;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity{
	
	private SensorManager sensorManager;
	
	GameSurfaceView _view;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		
		_view = new GameSurfaceView(this);
		setContentView(new GameSurfaceView(this));
		AcSensor.Inst().onCreate(this);
		
		
	}
	
	@Override
	protected void onResume(){
		super.onResume();
		AcSensor.Inst().onResume();
	}
	
	@Override
	protected void onPause(){
		super.onPause();
		AcSensor.Inst().onPause();
	}
	
	@Override
	public boolean onKeyDown(int keyCode,KeyEvent event){
		if(keyCode == KeyEvent.KEYCODE_BACK){
			_view = new GameSurfaceView(this);
			setContentView(_view);
			return false;
		}else{
			return super.onKeyDown(keyCode, event);
		}
	}
}
