package com.example.irattobar;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Player extends Task{
	private final static float MAX_SREED = 20;
	private final static float SIZE = 20;
	private Circle _cir = null;
	private Paint _paint = new Paint();
	private Vec _vec = new Vec();
	private Vec _sensorVec = new Vec();
	
	public Player(){
		_cir = new Circle(100,750,SIZE);
		_paint.setColor(Color.BLUE);
		_paint.setAntiAlias(true);
	}
	public final Circle getPt(){
		return _cir;
	}
	
	//ベクトルをセットする
	private void setVec(){
		float x = -AcSensor.Inst().getX()*2;
		float y = AcSensor.Inst().getY()*2;
		_sensorVec._x = x < 0 ? -x*x : x*x;
		_sensorVec._y = y < 0 ? -y*y : y*y;
		_sensorVec.setLengthCap(MAX_SREED);
		_vec.blend(_sensorVec, 0.05f);
	}
	
	private void Move(){
		_cir._x += _vec._x;
		_cir._y += _vec._y;
	}
	
	@Override
	public boolean onUpdate(){
		
		setVec();
		Move();
		return true;
	}
	
	@Override
	public void onDraw(Canvas c){
		c.drawCircle(_cir._x,_cir._y,_cir._r,_paint);
	}
}
