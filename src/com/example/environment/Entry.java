package com.example.environment;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;

import com.example.gamev01.R;
import com.example.toolclass.Screen;

public class Entry extends Activity {

	private static GameView gameView;
	private static Thread  gameThread;
	
	
	public void entryInitialize(){
		//全屏显示。
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		//横屏显示。
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		//获得屏幕长宽，和相对于1280*720基准屏幕的比率，并存入Screen类中。
		 DisplayMetrics dm=new DisplayMetrics(); 
		 super.getWindowManager().getDefaultDisplay().getMetrics(dm); 
		 Screen.width  = dm.widthPixels;
		 Screen.height = dm.heightPixels;
		 Screen.ratioX = Screen.width/1280f;
		 Screen.ratioY = Screen.height/720f;
		 Screen.dpi    = dm.densityDpi;
		 Log.v("environment", "屏幕宽度："+Screen.width+" 屏幕高度："+Screen.height
				 +" ratioX："+Screen.ratioX+" ratioY："+Screen.ratioY+" DPI: "+Screen.dpi);
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//初始化环境
		Log.i("ccc", "进入onCreate ");
		this.entryInitialize();
		//定义游戏视图和游戏主线程，并启动线程
		gameView = new GameView(this);
		gameThread = new Thread(gameView);
		gameView.threadFlag = true;
		gameThread.start();
		
		
		setContentView(gameView);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.entry, menu);
		return true;
	}
	@Override
	protected void onStop() {
		//使游戏主线程死亡，防止游戏出现多个线程
		gameView.threadFlag = false;
		gameView.resetOnStop();
		Log.i("ccc", "进入OnStop "+" gameView.threadFlag:  "+gameView.threadFlag);
		super.onStop();
	}

	@Override
	protected void onRestart() {
		Log.i("ccc", "进入onRestart ");
		//按下游戏图标返回游戏时重新启动游戏主线程，此时GameView里的资料不变
		if(gameView.threadFlag==false){
			gameView.threadFlag = true;
			gameThread = new Thread(gameView);
			gameThread.start();
		}
		super.onRestart();
	}
	
	@Override
	protected void onDestroy() {
		//在这里将会GameView杀掉
		gameView.resetAll();
		Log.i("ccc", "进入onDestroy ");
		super.onDestroy();
	}
	
	
	@Override
	protected void onResume() {
		Log.i("ccc", "进入onResume ");
		super.onResume();
	}
	@Override
	protected void onStart() {
		Log.i("ccc", "进入onStart ");
		super.onStart();
	}
	@Override
	protected void onPause() {
		Log.i("ccc", "进入onPause ");
		super.onPause();
	}
}
