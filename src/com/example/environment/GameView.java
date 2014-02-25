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

	long timeStart = 0;  //ÿһ֡�Ŀ�ʼ�ͽ���ʱ��
	long timeCost = 0;
	long timeSleep = 4;  //�߳�˯�ߵ�ʱ�䣬ÿһ֡
	Canvas canvasBuffer;
	Bitmap bitmapBuffer;
	Paint paint;
	
	/**��Ϸ״̬����*/
		private static   ArrayList<OzElement>        gateAtlas;  //ÿһ���ؿ��ĵ�ͼ������
		private  ArrayList<OzInt> rankNum;
		private Player player;
		private GameButton game_Button;     //��Ϸ��ť
		private ArrayList<PointF> game_PressPoint;
	
	private StatusType status;  //��ǰ����״̬
	
	public boolean threadFlag   = true;
	public  int     threadnum = 0;
	
	/**
	 * ��Activity��Destory����ã�����GameView
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
	 * ������ʼ��
	 */
	public void variableDefine(){
		canvasBuffer = new Canvas();
		//Ϊ���廭�����ÿ����
		canvasBuffer.setDrawFilter(new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG|Paint.FILTER_BITMAP_FLAG));
		//������Ļ��С�Ļ���ͼƬ
		bitmapBuffer = Bitmap.createBitmap((int)Screen.width, (int)Screen.height, Bitmap.Config.ARGB_8888);
		canvasBuffer.setBitmap(bitmapBuffer);//���û���ͼƬ
		
		paint           = new Paint();
		paint.setTextSize(50);
		paint.setColor(Color.BLUE);
		paint.setAntiAlias(true);//�������ÿ����
		paint.setFilterBitmap(true); //�������ù��ˣ���֪��ʲô�á���
		
		game_Button     = new GameButton();
		gateAtlas       = new ArrayList<OzElement>();
		rankNum         = new ArrayList<OzInt>();
		game_PressPoint = new ArrayList<PointF>();
		player = new Player();
		
	}
	/**
	 * ��ϷͼƬ���뼰�����ʼ��
	 */
	public void gameInitialize(){
		
		P.pictureLoad(getResources());//����ͼƬһ��Ҫд�ڵ�һ��
		this.variableDefine();
		GameChapter.chapterLoad(gateAtlas, rankNum,1);  //��ͼ���룬�ݶ�
	}
	/**
	 *�����ֻ���Ļ�������ת��Ϊ1280*720������Ļ�������� 
	 */
	private PointF change_XY_toBasicScreen(float x,float y){
		
		PointF ozPoint = new PointF( x/Screen.ratioX , y/Screen.ratioY);
		return ozPoint;
	}
	//���캯��
	public GameView(Context context) {
		super(context);
		this.setFocusableInTouchMode(true);
		status = StatusType.Game;
		this.gameInitialize();//��ϷͼƬ���뼰�����ʼ��
		threadnum++;
		Log.v("ccc", "threadnum: "+threadnum);
	}
	/**
	 * ������Ӧ
	 */
	public boolean onTouchEvent(MotionEvent e) {
		
		this.game_PressPoint = new ArrayList<PointF>();//�����һ֡�����ݣ����¼��㴥��λ��
		for(int i=0; i<e.getPointerCount();i++){
			this.game_PressPoint.add( this.change_XY_toBasicScreen(e.getX(i), e.getY(i)) );
		}
		
		switch (e.getActionMasked() ) {
		
		case MotionEvent.ACTION_MOVE:           //��ָ����Ļ���϶�ʱ
		case MotionEvent.ACTION_DOWN:           //��һ��������Ļʱ
		case MotionEvent.ACTION_POINTER_DOWN:   //��N��������Ļʱ
			game_Button.reset();
			game_Button.logic(game_PressPoint);
			Log.v("TouchScreen", "----����||�϶�----:"+game_PressPoint.size());
			break;
		case MotionEvent.ACTION_UP:            //���һ���뿪��Ļʱ
		case MotionEvent.ACTION_POINTER_UP:    //�뿪��Ļʱ����������ָ�ڴ�����Ļ
//			Log.v("TouchScreen", "----getActionIndex----:"+e.getActionIndex()+" ���꣺ "+e.getX(e.getActionIndex()));
			game_Button.reset();
			game_PressPoint.remove(e.getActionIndex());
			game_Button.logic(game_PressPoint);
			Log.v("TouchScreen", "+++++|����|+++++:"+game_PressPoint.size());
		    break;
		default:    //���������ж�
			Log.e("TouchScreen", "��*****����*****��:"+game_PressPoint.size());
			break;
		}
		
		return true;
	}
	/**
	 * ��Ϸ��ÿһ֡���¼��߼�����
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
    * ÿһ֡��ͼ
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
		
		
		canvas.drawBitmap(bitmapBuffer, 0, 0, null); //˫����
		canvas.drawText("˯�ߺ�ʱ : "+w, 50, 50, paint);
		
		super.onDraw(canvas);
	}
	/**
	 * ��ÿһ֡�������л�
	 */
	private void frameSwitching(){
		
		this.eventLogics();     //�߼���
		this.postInvalidate(); //�滭��
		
	}
	/**
	 * ��Ϸģ����߳�
	 */
	float w;
	public void run() {
		try {
			while(threadFlag){
//				timeStart = System.currentTimeMillis();
				//��ÿһ֡�����߼������л�
				this.frameSwitching();
//				timeCost   = System.currentTimeMillis() - timeStart;
				//�������ĵ���ʱ��
				if( timeCost< timeSleep ){
					Thread.sleep(timeSleep - timeCost);
				}
				else{
				}
				w= timeSleep-timeCost;
				Log.v("GameView", "˯��ʱ�䣺"+( timeSleep-timeCost ));
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * ���ֻ���Menu��Back�����������δ���
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK){
			//����true��ʾ�˰����Ѵ������ٽ���ϵͳ������P261
			Log.v("PRESSKEY", "������Back��");
			return true;
		}
		else if(keyCode == KeyEvent.KEYCODE_MENU){
			Log.v("PRESSKEY", "������Menu��");
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
//				Log.v("test", "BasicBody����: "+s);
				
				//���������Ϣ��
				player.resetOnGameLogic();
				
				//��ײ��
				this.impactEngine();
				
				//���״̬�ı��
				player.updateAction();
				
				//Ԫ���ƶ����߼���
				for(int i=0;i<gateAtlas.size();i++){
					gateAtlas.get(i).engine();
				}
				
				player.engine();
	}
	public void gameDraw(Canvas canvas ){
		//���ü�canvas��������Ϊ�����õ���canvasBuffer��������
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
		player.set_VerticalT_and_PlaneT(gateAtlas); //������ҵĴ�ֱ״̬��ˮƽ״ֵ̬
		
		for(OzElement g:gateAtlas){
			if(g instanceof BasicBody){
				//����һص���ǽǰ��һ˲�������˵��Ҵ�ǽʵ������ǽ����ң���ȷ�������ǰ�ǽ��������������
				g.l.x = g.l.x + player.getPush_X();
				g.l.y = g.l.y + player.getPush_Y();
			}
		}
		//��ʱ��δ��ͼ
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
