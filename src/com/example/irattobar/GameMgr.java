package com.example.irattobar;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.irattobar.Barricade.eType;


public class GameMgr {
	Random r = new Random();
	
	private enum eStatus{
		NORMAL,
		GAMEOVER,
		GAMECLEAR
	};
	
	private static final float PI =(float)Math.PI;
	private ArrayList<Barricade> _barrList = new ArrayList<Barricade>();
	private	LinkedList<Task> _taskList = new LinkedList<Task>();
	private eStatus _status = eStatus.NORMAL;
	private Player _player;


	GameMgr(){
		int stagenumber = r.nextInt(2);
		_barrList.add(new BarricadeSquare(  0,  0,480, 20, null));// 画面4隅に四角形を配置
		_barrList.add(new BarricadeSquare(  0,820,480, 40, null));
		
		if(stagenumber == 0){
			_barrList.add(new BarricadeSquare2( -100,  0, 120,820, new BConf(+PI/300000)));
			_barrList.add(new BarricadeSquare2(460,  0, 200,820, new BConf(-PI/300000)));
						
			_barrList.add(new BarricadeSquare(0,650,380,30,null));
			_barrList.add(new BarricadeSquare(350,250,30,400,null));
			_barrList.add(new BarricadeSquare(0,30,380,30,null));
			_barrList.add(new BarricadeSquare(100,150,650,30,null));
			_barrList.add(new BarricadeSquare(-10,450,400,30,new BConf(+PI/800)));
			_barrList.add(new BarricadeSquare(175,265,30,400,new BConf(+PI/800)));
			_barrList.add(new BarricadeSquare(180,180,30,300,null));
			
			_barrList.add(new BarricadeSquare3(230, 600, 260, 80, new BConf(-PI/2000),230,0));
			//_barrList.add(new BarricadeStar(240,400,80,220,new BConf(+PI/180)));
			
			_barrList.add(new BarricadeShootingStar(280,300,20,30,new BConf(-PI/600),2000,300));
			_barrList.add(new BarricadeShootingStar(280,300,20,30,new BConf(-PI/800),2000,300));
			_barrList.add(new BarricadeShootingStar(280,300,20,30,new BConf(-PI/1000),2000,300));
			_barrList.add(new BarricadeShootingStar(280,300,20,30,new BConf(-PI/1200),2000,300));
			_barrList.add(new BarricadeShootingStar(280,300,20,30,new BConf(-PI/1400),2000,300));
			_barrList.add(new BarricadeShootingStar(280,300,20,30,new BConf(-PI/1600),2000,300));
			
			_barrList.add(new BarricadeShootingStar(200,100,20,30,new BConf(-PI/600),2000,400));
			_barrList.add(new BarricadeShootingStar(200,100,20,30,new BConf(-PI/800),2000,400));
			_barrList.add(new BarricadeShootingStar(200,100,20,30,new BConf(-PI/1000),2000,400));
			_barrList.add(new BarricadeShootingStar(200,100,20,30,new BConf(-PI/1200),2000,400));
			_barrList.add(new BarricadeShootingStar(200,100,20,30,new BConf(-PI/1400),2000,400));
			_barrList.add(new BarricadeShootingStar(200,100,20,30,new BConf(-PI/1600),2000,400));
			
			_barrList.add(new BarricadeShootingStar(360,200,20,30,new BConf(-PI/600),2000,200));
			_barrList.add(new BarricadeShootingStar(360,200,20,30,new BConf(-PI/800),2000,200));
			_barrList.add(new BarricadeShootingStar(360,200,20,30,new BConf(-PI/1000),2000,200));
			_barrList.add(new BarricadeShootingStar(360,200,20,30,new BConf(-PI/1200),2000,200));
			_barrList.add(new BarricadeShootingStar(360,200,20,30,new BConf(-PI/1400),2000,200));
			_barrList.add(new BarricadeShootingStar(360,200,20,30,new BConf(-PI/1600),2000,200));
		}else if(stagenumber == 1){
			_barrList.add(new BarricadeSquare2( -1000,  0, 1020,820, new BConf(+PI/100000)));
			_barrList.add(new BarricadeSquare2(460,  0, 1200,820, new BConf(-PI/100000)));
			_barrList.add(new BarricadeSquare2( 0,  30, 480,20, new BConf(+PI/80000)));
						
			_barrList.add(new BarricadeShootingStar(280,400,20,40,new BConf(-PI/600),260,640));
			_barrList.add(new BarricadeShootingStar(280,300,20,30,new BConf(-PI/800),260,440));
			_barrList.add(new BarricadeShootingStar(280,200,20,40,new BConf(-PI/400),260,460));
			_barrList.add(new BarricadeShootingStar(280,100,20,30,new BConf(-PI/200),240,440));
			_barrList.add(new BarricadeShootingStar(280,50,20,50,new BConf(-PI/400),240,140));
			_barrList.add(new BarricadeShootingStar(280,10,20,30,new BConf(-PI/600),240,140));
			
			_barrList.add(new BarricadeShootingStar(100,500,20,30,new BConf(-PI/600),260,640));
			_barrList.add(new BarricadeShootingStar(300,550,20,40,new BConf(-PI/800),246,440));
			_barrList.add(new BarricadeShootingStar(100,360,20,30,new BConf(-PI/1000),260,440));
			_barrList.add(new BarricadeShootingStar(300,450,20,30,new BConf(-PI/1200),240,440));
			_barrList.add(new BarricadeShootingStar(100,300,20,30,new BConf(-PI/400),260,640));
			_barrList.add(new BarricadeShootingStar(300,750,20,40,new BConf(-PI/600),240,440));
			
			_barrList.add(new BarricadeShootingStar(160,100,20,30,new BConf(-PI/600),240,440));
			_barrList.add(new BarricadeShootingStar(160,200,20,30,new BConf(-PI/800),260,440));
			_barrList.add(new BarricadeShootingStar(160,300,20,30,new BConf(-PI/1000),240,440));
			_barrList.add(new BarricadeShootingStar(160,200,20,40,new BConf(-PI/200),260,440));
			_barrList.add(new BarricadeShootingStar(260,500,20,30,new BConf(-PI/400),240,640));
			_barrList.add(new BarricadeShootingStar(360,600,20,30,new BConf(-PI/600),240,440));
			
			_barrList.add(new BarricadeShootingStar(180,250,20,30,new BConf(-PI/600),260,440));
			_barrList.add(new BarricadeShootingStar(180,300,20,40,new BConf(-PI/800),240,640));
			_barrList.add(new BarricadeShootingStar(180,200,20,30,new BConf(-PI/180),260,440));
			_barrList.add(new BarricadeShootingStar(180,100,20,40,new BConf(-PI/280),240,440));
			_barrList.add(new BarricadeShootingStar(180,50,20,30,new BConf(-PI/400),260,460));
			_barrList.add(new BarricadeShootingStar(180,10,20,40,new BConf(-PI/600),240,440));
			
			_barrList.add(new BarricadeShootingStar(300,500,20,30,new BConf(-PI/600),260,460));
			_barrList.add(new BarricadeShootingStar(300,550,20,50,new BConf(-PI/800),240,440));
			_barrList.add(new BarricadeShootingStar(300,600,20,30,new BConf(-PI/240),260,640));
			_barrList.add(new BarricadeShootingStar(300,650,20,30,new BConf(-PI/200),240,440));
			_barrList.add(new BarricadeShootingStar(100,200,20,50,new BConf(-PI/400),260,140));
			_barrList.add(new BarricadeShootingStar(100,400,20,30,new BConf(-PI/600),240,440));
			
			_barrList.add(new BarricadeShootingStar(160,100,20,30,new BConf(-PI/600),240,140));
			_barrList.add(new BarricadeShootingStar(160,200,20,40,new BConf(-PI/800),260,440));
			_barrList.add(new BarricadeShootingStar(360,400,20,30,new BConf(-PI/1000),260,440));
			_barrList.add(new BarricadeShootingStar(360,400,20,30,new BConf(-PI/200),240,440));
			_barrList.add(new BarricadeShootingStar(360,500,20,40,new BConf(-PI/400),260,440));
			_barrList.add(new BarricadeShootingStar(360,600,20,30,new BConf(-PI/600),240,440));
		
		}
       
        
        _barrList.add(new BarricadeSquare(0,0,800,30,new BConf(eType.GOAL)));

        for (Barricade bar : _barrList) {
                _taskList.add(bar);     //タスクリストに障害物を追加
        }
        
        _player = new Player();
		_taskList.add(_player);
		_taskList.add(new FpsController());
	}
	
	private boolean Collision(){
		Vec vec = new Vec();
		final Circle cir = _player.getPt();
		for(Barricade barr : _barrList){
			Def.eHitCode code = barr.isHit(cir, vec);
			switch(code){
			case OUT:
				_status = eStatus.GAMEOVER;
				return true;
			case GOAL:
				_status = eStatus.GAMECLEAR;
				return true;
			}
		}
		return false;
	}
	
	public boolean onUpdate(){
		if(_status != eStatus.NORMAL){
			return true;
		}
		if(Collision()){
			return true;
		}
		for(int i=0;i < _taskList.size();i++){
			if(_taskList.get(i).onUpdate() == false){
				_taskList.remove(i);
				i--;
			}
		}
		return true;
	}
	private void drawStatus(Canvas c){
		switch (_status){
		case GAMEOVER:
			{
				Paint paint = new Paint();
				paint.setTextSize(80);
				paint.setColor(Color.BLACK);
				c.drawText("GameOVER", 40, 100, paint);
			}
			break;
		case GAMECLEAR:
			{
				Paint paint = new Paint();
				paint.setTextSize(80);
				paint.setColor(Color.BLACK);
				c.drawText("GameClear",40,100,paint);
			}
			break;
		}
	}
	
	public void onDraw(Canvas c){
		c.drawColor(Color.WHITE);
		for(Task task : _taskList){
			task.onDraw(c);
		}
		drawStatus(c);
	}
}
