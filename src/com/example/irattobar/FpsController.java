package com.example.irattobar;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class FpsController extends Task{
	
	private long _startTime = 0;
	private int _cnt = 0;
	private Paint _paint = new Paint();
	private float _fps;
	private final static int N = 60;
	private final static int FONT_SIZE = 20;
	
	public FpsController(){
		_paint.setColor(Color.BLUE);
		_paint.setTextSize(FONT_SIZE);
	}
	
	
	
	public boolean onUpdate(){
		if(_cnt == 0){
			_startTime = System.currentTimeMillis();
		}
		if(_cnt == N){
			long t = System.currentTimeMillis();
			_fps = 1000.f/((t-_startTime)/(float)N);
			_cnt = 0;
			_startTime = t;
		}
		_cnt++;
		return true;
	}
	
	
	public void onDraw(Canvas c){
		c.drawText(String.format("%.1f", _fps),0,FONT_SIZE-2,_paint);
		
	}
}
