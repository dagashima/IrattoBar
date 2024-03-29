package com.example.irattobar;

import android.content.Context;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameSurfaceView extends SurfaceView implements SurfaceHolder.Callback,Runnable{
	private Thread _thread;
	private GameMgr _gameMgr = new GameMgr();
	
	public GameSurfaceView(Context context){
		super(context);
		getHolder().addCallback(this);
	}
	
	@Override
	public void surfaceChanged(SurfaceHolder holder,int format,int width, int height){
		//解像度情報変更通知
	}
	
	@Override
	public void surfaceCreated(SurfaceHolder holder){
		_thread = new Thread(this);
		_thread.start();
	}
	
	@Override
	public void surfaceDestroyed(SurfaceHolder holder){
		_thread = null;
	}
	
	@Override
	public void run(){
		while (_thread!=null){//メインループ
			_gameMgr.onUpdate();
			onDraw(getHolder());
		}
	}
	private void onDraw(SurfaceHolder holder){
		Canvas c = holder.lockCanvas();
		if(c == null){
			return;
		}
		//ここにゲームの描画処理
		_gameMgr.onDraw(c);
		holder.unlockCanvasAndPost(c);
	}
}
