package com.example.irattobar;

import java.util.List;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class AcSensor implements SensorEventListener{

	private SensorManager _sensorManager = null;
	private float _x,_y,_z;
	
	public void onCreate(Context c){
		_sensorManager = (SensorManager) c.getSystemService(Context.SENSOR_SERVICE);
		onResume();
	}
	
	@Override
	public void onAccuracyChanged(Sensor sensor,int accuracy){
		//今回は使用しません
	}

	//アクティビティが動き出したらリスナーを登録する
	public void onResume(){
		List<Sensor> sensorList = _sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER);
		if(sensorList != null && !sensorList.isEmpty()){
			Sensor sensor = sensorList.get(0);
			_sensorManager.registerListener(this, sensor,SensorManager.SENSOR_DELAY_FASTEST);		
		}
	}

	//アクティビティがポーズになったらリスナーを止める
	public void onPause(){
		if(_sensorManager == null){
			return;
		}
		_sensorManager.unregisterListener(this);
	}
	
	//センサーの値に変化があったら呼ばれる。
	public void onSensorChanged(SensorEvent event){
		if(event.sensor.getType()==Sensor.TYPE_ACCELEROMETER){
			_x = event.values[SensorManager.DATA_X];
			_y = event.values[SensorManager.DATA_Y];
			_z = event.values[SensorManager.DATA_Z];
		}
	}
	
	public float getX(){
		return _x;
	}

	public float getY(){
		return _y;
	}

	public float getZ(){
		return _z;
	}
	
	//シングルトン
	private static AcSensor _instance = new AcSensor();
	private AcSensor(){
		_x = _y = _z = 0;
	}
	public static AcSensor Inst(){
		return _instance;
	}
	
}
