package com.example.irattobar;

import java.util.ArrayList;
import java.util.LinkedList;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.irattobar.Barricade.eType;

public class GameMgr {
	
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
		_barrList.add(new BarricadeSquare(  0,  0,480, 20, null));// 画面4隅に四角形を配置
        _barrList.add(new BarricadeSquare2(  0,  0, 20,820, new BConf(+PI/360000)));// コンフィグを特に設定しない時はnullを渡すとデフォルト設定になる
        _barrList.add(new BarricadeSquare2(460,  0, 20,820, new BConf(-PI/360000)));
        _barrList.add(new BarricadeSquare(  0,820,480, 40, null));
        
        _barrList.add(new BarricadeSquare3(230, -200, 260, 80, new BConf(-PI/180),230,-300));
        _barrList.add(new BarricadeSquare3(230, 250, 280, 80, new BConf(+PI/100),230,-600));
        _barrList.add(new BarricadeSquare3(230, 200, 260, 80, new BConf(-PI/180),230,-300));
        _barrList.add(new BarricadeSquare3(230, -250, 280, 80, new BConf(+PI/100),230,-600));
        _barrList.add(new BarricadeSquare3(230, 600, 260, 80, new BConf(-PI/180),230,0));
        _barrList.add(new BarricadeSquare3(230, -450, 200, 80, new BConf(+PI/100),230,60));
        //_barrList.add(new BarricadeStar(240,400,80,220,new BConf(+PI/180)));
        
        _barrList.add(new BarricadeShootingStar(280,0,20,30,new BConf(-PI/600),2000,300));
        _barrList.add(new BarricadeShootingStar(280,0,20,30,new BConf(-PI/800),2000,300));
        _barrList.add(new BarricadeShootingStar(280,0,20,30,new BConf(-PI/1000),2000,300));
        _barrList.add(new BarricadeShootingStar(280,0,20,30,new BConf(-PI/1200),2000,300));
        _barrList.add(new BarricadeShootingStar(280,0,20,30,new BConf(-PI/1400),2000,300));
        _barrList.add(new BarricadeShootingStar(280,0,20,30,new BConf(-PI/1600),2000,300));
        
        _barrList.add(new BarricadeShootingStar(200,0,20,30,new BConf(-PI/600),2000,400));
        _barrList.add(new BarricadeShootingStar(200,0,20,30,new BConf(-PI/800),2000,400));
        _barrList.add(new BarricadeShootingStar(200,0,20,30,new BConf(-PI/1000),2000,400));
        _barrList.add(new BarricadeShootingStar(200,0,20,30,new BConf(-PI/1200),2000,400));
        _barrList.add(new BarricadeShootingStar(200,0,20,30,new BConf(-PI/1400),2000,400));
        _barrList.add(new BarricadeShootingStar(200,0,20,30,new BConf(-PI/1600),2000,400));
        
        _barrList.add(new BarricadeShootingStar(360,0,20,30,new BConf(-PI/600),2000,200));
        _barrList.add(new BarricadeShootingStar(360,0,20,30,new BConf(-PI/800),2000,200));
        _barrList.add(new BarricadeShootingStar(360,0,20,30,new BConf(-PI/1000),2000,200));
        _barrList.add(new BarricadeShootingStar(360,0,20,30,new BConf(-PI/1200),2000,200));
        _barrList.add(new BarricadeShootingStar(360,0,20,30,new BConf(-PI/1400),2000,200));
        _barrList.add(new BarricadeShootingStar(360,0,20,30,new BConf(-PI/1600),2000,200));
        
        _barrList.add(new BarricadeShootingStar(440,0,20,30,new BConf(-PI/600),2000,100));
        _barrList.add(new BarricadeShootingStar(440,0,20,30,new BConf(-PI/800),2000,100));
        _barrList.add(new BarricadeShootingStar(440,0,20,30,new BConf(-PI/1000),2000,100));
        _barrList.add(new BarricadeShootingStar(440,0,20,30,new BConf(-PI/1200),2000,100));
        _barrList.add(new BarricadeShootingStar(440,0,20,30,new BConf(-PI/1400),2000,100));
        _barrList.add(new BarricadeShootingStar(440,0,20,30,new BConf(-PI/1600),2000,100));
        
        
       
        
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
