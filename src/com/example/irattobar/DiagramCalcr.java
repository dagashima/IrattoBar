package com.example.irattobar;

import android.graphics.PointF;

public class DiagramCalcr {
	
	//centerを中心に角度ang、頂点郡ptを回転する。
	public static void RotateDiagram(PointF pt[],final PointF center,final float ang){
		for(int i = 0;i<pt.length; i++){
			RotatePt(pt[i],center,ang);
		}
	}
	
	//rotaPtを中心に角度ang、origPtを回転する。
	public static void RotatePt(PointF rotaPt,final PointF origPt,final float ang){
		float cx = origPt.x;
		float cy = origPt.y;
		float x = rotaPt.x;
		float y = rotaPt.y;
		rotaPt.x = (float)(cx + Math.cos(ang) * (x-cx) - Math.sin(ang) * (y-cy));
		rotaPt.y = (float)(cy + Math.sin(ang) * (x-cx) + Math .cos(ang) * (y-cy));
	}
	
	//頂点郡ptの線分とcirが接触していたらその接触しているベクトルをverに格納して返す。
	public static boolean isHit(PointF pt[],final Circle cir,Vec vec){
		if(pt.length < 2){
			return false;
		}
		int len = pt.length;
		for(int i = 1;i <= len;i++){
			Line line = new Line(pt[i-1].x,pt[i-1].y,pt[i % len].x,pt[i % len].y);
			if(isHitLC(line,cir)==true){
				vec._x=pt[i % len].x - pt[i-1].x;
				vec._y=pt[i % len].y - pt[i-1].y;
				return true;
			}
		}
		return false;
	}
	
	//線分lineと円cirが当たっていればtureを返す。
	public static boolean isHitLC(Line line,Circle cir){
		if((line._sx*(cir._x-line._x) + line._sy*(cir._y-line._y)) <= 0){
			return (cir._r*cir._r >= (cir._x-line._x)*(cir._x-line._x)+(cir._y-line._y)*(cir._y-line._y)); 
		}else if(((-line._sx)*(cir._x-(line._x+line._sx)) + (-line._sy)*(cir._y-(line._y+line._sy))) <= 0){
			
			return (cir._r*cir._r >= (cir._x-(line._x+line._sx))*(cir._x-(line._x+line._sx))+(cir._y-(line._y+line._sy))*(cir._y-(line._y+line._sy)));
		}else {
			float e = (float) Math.sqrt(line._sx*line._sx + line._sy*line._sy);
			float c2 = (cir._x-line._x)*(cir._x-line._x)+(cir._y-line._y)*(cir._y-line._y);
			float b = (cir._x-line._x)*(line._sx/e)+(cir._y-line._y)*(line._sy/e);
			return (cir._r*cir._r >= c2 - b*b);
		}
	}
}
