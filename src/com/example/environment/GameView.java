package com.example.environment;


import java.util.ArrayList;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.PointF;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

import com.example.basicenum.PlaneE;
import com.example.basicenum.StatusType;
import com.example.basicenum.VerticalE;
import com.example.element.BasicBody;
import com.example.element.OzElement;
import com.example.element.Player;
import com.example.element.button.GameButton;
import com.example.toolclass.OzInt;
import com.example.toolclass.P;
import com.example.toolclass.Screen;

public class GameView extends View implements Runnable{

	long timeStart = 0;  //每一帧的开始和结束时间
	long timeCost = 0;
	long timeSleep = 4;  //线程睡眠的时间，每一帧
	Canvas canvasBuffer;
	Bitmap bitmapBuffer;
	Paint paint;
	
	/**游戏状态变量*/
		private static   ArrayList<OzElement>        gateAtlas;  //每一个关卡的地图集序列
		private  ArrayList<OzInt> rankNum;
		private Player player;
		private GameButton game_Button;     //游戏按钮
		private ArrayList<PointF> game_PressPoint;
	
	private StatusType status;  //当前界面状态
	
	public boolean threadFlag   = true;
	public  int     threadnum = 0;
	
	/**
	 * 在Activity的Destory里调用，重设GameView
	 */
	public void resetAll(){
		gateAtlas = null;
		status = StatusType.Start;
		this.resetOnStop();
	}
	public void resetOnStop(){
		game_Button.reset();
	}
	/**
	 * 变量初始化
	 */
	public void variableDefine(){
		canvasBuffer = new Canvas();
		//为缓冲画布设置抗锯齿
		canvasBuffer.setDrawFilter(new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG|Paint.FILTER_BITMAP_FLAG));
		//设置屏幕大小的缓冲图片
		bitmapBuffer = Bitmap.createBitmap((int)Screen.width, (int)Screen.height, Bitmap.Config.ARGB_8888);
		canvasBuffer.setBitmap(bitmapBuffer);//设置缓冲图片
		
		paint           = new Paint();
		paint.setTextSize(50);
		paint.setColor(Color.BLUE);
		paint.setAntiAlias(true);//画笔设置抗锯齿
		paint.setFilterBitmap(true); //画笔设置过滤，不知有什么用。。
		
		game_Button     = new GameButton();
		gateAtlas       = new ArrayList<OzElement>();
		rankNum         = new ArrayList<OzInt>();
		game_PressPoint = new ArrayList<PointF>();
		player = new Player();
		
	}
	/**
	 * 游戏图片载入及引擎初始化
	 */
	public void gameInitialize(){
		
		P.pictureLoad(getResources());//载入图片一定要写在第一行
		this.variableDefine();
		GameChapter.chapterLoad(gateAtlas, rankNum,1);  //地图载入，暂定
	}
	/**
	 *将该手机屏幕的坐标点转换为1280*720参照屏幕里的坐标点 
	 */
	private PointF change_XY_toBasicScreen(float x,float y){
		
		PointF ozPoint = new PointF( x/Screen.ratioX , y/Screen.ratioY);
		return ozPoint;
	}
	//构造函数
	public GameView(Context context) {
		super(context);
		this.setFocusableInTouchMode(true);
		status = StatusType.Game;
		this.gameInitialize();//游戏图片载入及引擎初始化
		threadnum++;
		Log.v("ccc", "threadnum: "+threadnum);
	}
	/**
	 * 触屏响应
	 */
	public boolean onTouchEvent(MotionEvent e) {
		
		this.game_PressPoint = new ArrayList<PointF>();//清空上一帧的数据，重新计算触点位置
		for(int i=0; i<e.getPointerCount();i++){
			this.game_PressPoint.add( this.change_XY_toBasicScreen(e.getX(i), e.getY(i)) );
		}
		
		switch (e.getActionMasked() ) {
		
		case MotionEvent.ACTION_MOVE:           //手指在屏幕上拖动时
		case MotionEvent.ACTION_DOWN:           //第一次碰到屏幕时
		case MotionEvent.ACTION_POINTER_DOWN:   //第N次碰到屏幕时
			game_Button.reset();
			game_Button.logic(game_PressPoint);
			Log.v("TouchScreen", "----按下||拖动----:"+game_PressPoint.size());
			break;
		case MotionEvent.ACTION_UP:            //最后一次离开屏幕时
		case MotionEvent.ACTION_POINTER_UP:    //离开屏幕时还有其它手指在触碰屏幕
//			Log.v("TouchScreen", "----getActionIndex----:"+e.getActionIndex()+" 坐标： "+e.getX(e.getActionIndex()));
			game_Button.reset();
			game_PressPoint.remove(e.getActionIndex());
			game_Button.logic(game_PressPoint);
			Log.v("TouchScreen", "+++++|弹起|+++++:"+game_PressPoint.size());
		    break;
		default:    //用作出错判定
			Log.e("TouchScreen", "【*****其它*****】:"+game_PressPoint.size());
			break;
		}
		
		return true;
	}
	/**
	 * 游戏中每一帧的事件逻辑处理
	 */
	private void eventLogics(){
		
		switch (status) {
		
		case Game:{
						this.gameLogic();
		}
		break;
		
		case Start:{
			
		}
		break;
		
		case Credits:
			break;
		case Loading:
			break;
		case Pause:
			break;
		case Select:
			break;
		case StatusInitialize:
			break;
		}
		
	}
   /**
    * 每一帧绘图
    */
	protected void onDraw(Canvas canvas) {
		switch (status) {
		
		case Game:{
						this.gameDraw( canvasBuffer);
		}
		break;
		
		case Loading:
			break;
		case Pause:
			break;
		case Select:
			break;
		case Start:
			break;
		case StatusInitialize:
			break;
		case Credits:
			break;
		}
		
		
		canvas.drawBitmap(bitmapBuffer, 0, 0, null); //双缓冲
		canvas.drawText("睡眠耗时 : "+w, 50, 50, paint);
		
		super.onDraw(canvas);
	}
	/**
	 * 对每一帧进行总切换
	 */
	private void frameSwitching(){
		
		this.eventLogics();     //逻辑。
		this.postInvalidate(); //绘画。
		
	}
	/**
	 * 游戏模拟的线程
	 */
	float w;
	public void run() {
		try {
			while(threadFlag){
//				timeStart = System.currentTimeMillis();
				//对每一帧根据逻辑进行切换
				this.frameSwitching();
//				timeCost   = System.currentTimeMillis() - timeStart;
				//运算消耗的总时间
				if( timeCost< timeSleep ){
					Thread.sleep(timeSleep - timeCost);
				}
				else{
				}
				w= timeSleep-timeCost;
				Log.v("GameView", "睡眠时间："+( timeSleep-timeCost ));
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 对手机的Menu和Back按键进行屏蔽处理
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK){
			//返回true表示此按键已处理，不再交给系统处理，书P261
			Log.v("PRESSKEY", "按下了Back键");
			return true;
		}
		else if(keyCode == KeyEvent.KEYCODE_MENU){
			Log.v("PRESSKEY", "按下了Menu键");
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
	

	
	
	
	
	
	
	public void gameLogic(){
		
//				int s = 0;
//				for(OzElement g:gateAtlas){
//					if(g instanceof BasicBody){
//						s++;
//					}
//				}
//				Log.v("test", "BasicBody个数: "+s);
				
				//重置玩家信息↓
				player.resetOnGameLogic();
				
				//碰撞↓
				this.impactEngine();
				
				//玩家状态改变↓
				player.updateAction();
				
				//元素移动等逻辑↓
				for(int i=0;i<gateAtlas.size();i++){
					gateAtlas.get(i).engine();
				}
				
				player.engine();
	}
	public void gameDraw(Canvas canvas ){
		//不用加canvas参数，因为现在用的是canvasBuffer缓冲来画
		for(int i=0;i<rankNum.size();i++){
			
			for(int i2=0;i2<gateAtlas.size();i2++){
				if(rankNum.get(i).value == gateAtlas.get(i2).rankNum){
					gateAtlas.get(i2).show(canvas);
				}
			}
			
		}
		player.show(canvas);
		game_Button.show(canvas);
	}
	public void impactEngine(){
		
		for(int i=0;i<gateAtlas.size();i++){
			gateAtlas.get(i).impact(player);
		}
		player.set_VerticalT_and_PlaneT(gateAtlas); //设置玩家的垂直状态和水平状态值
		
		for(OzElement g:gateAtlas){
			if(g instanceof BasicBody){
				//让玩家回到穿墙前的一瞬，相对来说玩家穿墙实际上是墙穿玩家，正确的做法是把墙从玩家身边拉开。
				g.l.x = g.l.x + player.getPush_X();
				g.l.y = g.l.y + player.getPush_Y();
			}
		}
		//此时还未绘图
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
