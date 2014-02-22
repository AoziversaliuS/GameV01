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
		//ȫ����ʾ��
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		//������ʾ��
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		//�����Ļ�����������1280*720��׼��Ļ�ı��ʣ�������Screen���С�
		 DisplayMetrics dm=new DisplayMetrics(); 
		 super.getWindowManager().getDefaultDisplay().getMetrics(dm); 
		 Screen.width  = dm.widthPixels;
		 Screen.height = dm.heightPixels;
		 Screen.ratioX = Screen.width/1280f;
		 Screen.ratioY = Screen.height/720f;
		 Screen.dpi    = dm.densityDpi;
		 Log.v("environment", "��Ļ��ȣ�"+Screen.width+" ��Ļ�߶ȣ�"+Screen.height
				 +" ratioX��"+Screen.ratioX+" ratioY��"+Screen.ratioY+" DPI: "+Screen.dpi);
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//��ʼ������
		Log.i("ccc", "����onCreate ");
		this.entryInitialize();
		//������Ϸ��ͼ����Ϸ���̣߳��������߳�
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
		//ʹ��Ϸ���߳���������ֹ��Ϸ���ֶ���߳�
		gameView.threadFlag = false;
		gameView.resetOnStop();
		Log.i("ccc", "����OnStop "+" gameView.threadFlag:  "+gameView.threadFlag);
		super.onStop();
	}

	@Override
	protected void onRestart() {
		Log.i("ccc", "����onRestart ");
		//������Ϸͼ�귵����Ϸʱ����������Ϸ���̣߳���ʱGameView������ϲ���
		if(gameView.threadFlag==false){
			gameView.threadFlag = true;
			gameThread = new Thread(gameView);
			gameThread.start();
		}
		super.onRestart();
	}
	
	@Override
	protected void onDestroy() {
		//�����ｫ��GameViewɱ��
		gameView.resetAll();
		Log.i("ccc", "����onDestroy ");
		super.onDestroy();
	}
	
	
	@Override
	protected void onResume() {
		Log.i("ccc", "����onResume ");
		super.onResume();
	}
	@Override
	protected void onStart() {
		Log.i("ccc", "����onStart ");
		super.onStart();
	}
	@Override
	protected void onPause() {
		Log.i("ccc", "����onPause ");
		super.onPause();
	}
}
