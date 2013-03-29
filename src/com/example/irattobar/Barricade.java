package com.example.irattobar;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;

public class Barricade extends Task{
	public enum eType{//障害物の種類
		OUT,//あたるとアウト
		GOAL,//当たるとゴール
		NORMAL
	}
	
	protected PointF _center = new PointF(0,0);//図形の中心点
	protected PointF _pt[];//図形の頂点
	protected Paint _paint = new Paint();//ペイント
	protected eType _type = eType.OUT;;//種類
	protected float _rotaSpeed = 0;//回転スピード
	
	//コンストラクタ
	public Barricade(int n,BConf conf){
		if(conf != null){
			_rotaSpeed = conf.speed;
			_type = conf.type;
		}
		switch (_type){
		case OUT:
			_paint.setColor(Color.RED);
			break;
		case GOAL:
			_paint.setColor(Color.GREEN);
			break;
		}
		_pt = new PointF[n];
		for(int i = 0;i < n;i++){
		_pt[i] = new PointF();
		}
		_paint.setAntiAlias(true);
	}
	
	//更新する
	public boolean onUpdate(){
		if(_rotaSpeed != 0){
			DiagramCalcr.RotateDiagram(_pt, _center, _rotaSpeed);
		}
		return true;
	}
	
	//接触しているかを問う。
	public Def.eHitCode isHit(final Circle cir, Vec vec){
		if(DiagramCalcr.isHit(_pt,cir,vec) == true){
			switch(_type){
			case OUT:
				return Def.eHitCode.OUT;
			case GOAL:
				return Def.eHitCode.GOAL;
			}
		}
		return Def.eHitCode.NO;
	}
	
	//描画する
	public void onDraw(Canvas c){
		if(_pt.length < 1){
			return;
		}
		Path path = new Path();
		path.moveTo(_pt[0].x,_pt[0].y);
		for(int i = 0;i<_pt.length;i++){
			path.lineTo(_pt[i].x, _pt[i].y);
		}
		c.drawPath(path, _paint);
	}
}
