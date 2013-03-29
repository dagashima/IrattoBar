package com.example.irattobar;

public class BarricadeShootingStar extends Barricade{
	private static final float PI2 = (float)(Math.PI*2);
	
	public BarricadeShootingStar(float x,float y,float inR,float outR,BConf conf,float shaft_x,float shaft_y){
		super(10,conf);
		for(int i=0;i < 5;i++){
			_pt[i*2+0].x = (float)(x+Math.cos(PI2/5*i)*inR);
			_pt[i*2+0].y = (float)(y+Math.sin(PI2/5*i)*inR);
			_pt[i*2+1].x = (float)(x+Math.cos(PI2/5*i+PI2/10)*outR);
			_pt[i*2+1].y = (float)(y+Math.sin(PI2/5*i+PI2/10)*outR);
		}
		_center.x = shaft_x;
		_center.y = shaft_y;
	}
}
