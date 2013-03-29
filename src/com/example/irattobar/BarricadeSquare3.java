package com.example.irattobar;


public class BarricadeSquare3 extends Barricade{
	
	public BarricadeSquare3(float x,float y,float w,float h,BConf conf,float shaft_x,float shaft_y){
		super(4,conf);
		_pt[0].x = x;			_pt[0].y = y;
		_pt[1].x = x+w;			_pt[1].y = y;
		_pt[2].x = x+w;			_pt[2].y = y+h;
		_pt[3].x = x;			_pt[3].y = y+h;
		_center.x = shaft_x;
		_center.y = shaft_y;
	}
	
}
