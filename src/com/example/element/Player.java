package com.example.element;

import java.util.ArrayList;

import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.Log;

import com.example.basicenum.OzElementType;
import com.example.basicenum.PlaneE;
import com.example.basicenum.VerticalE;
import com.example.element.button.GameButton;
import com.example.toolclass.P;
import com.example.toolclass.Rank;

public class Player extends OzElement{

	public static final float VALUE_MOVE    = 5;                //���ˮƽ�ƶ��ٶ�
	public static final float VALUE_GRAVITY = 8;                //����
	
	
	public  static final float VALUE_JUMP    = 7;  //��Ծ���ٶ�
	
	public  static final int JumpTimeMAX = 50;
	private static int JumpTimeCount = 0;  //��Ծ��ʱ��
	
	//planeTouch
	private static PlaneE     planeT    =  PlaneE.ELSE;
	//verticalTouch
	private static VerticalE  verticalT =  VerticalE.ELSE;
	private  float push_X = 0;  //��ײ�����λ���ƻص���ǽǰ
	private  float push_Y = 0;  //��ײ�����λ���ƻص���ǽǰ
	
	private static boolean jump = false;
	
	public Player() {
		super(
				"Player",
				Rank.SELF_CUSTOM, 
				OzElementType.Player,
				new PointF(400, 400),
				new RectF(0, 0,P.Game_Player.basicWidth,P.Game_Player.basicHeight)
		);
		jump = false;
	}

	@Override
	public void logic() {
		
	}
	

	@Override
	public void planeLogic() {
//		if(GameButton.getPressO() == GamePressO.Left){
//			l.x = l.x - MOVE_SPEED;
//		}
//		else if(GameButton.getPressO() == GamePressO.Right){
//			l.x = l.x + MOVE_SPEED;
//		}
	}

	@Override
	public void verticalLogic() {
		
	}

	@Override
	public void show(Canvas canvas) {
		P.pictureDraw(P.Game_Player, l, canvas);
	}


	@Override
	public void reset() {
	}
	
	public void resetOnGameLogic(){
		//״̬���²���д�������������
		planeT = PlaneE.ELSE;
		verticalT = VerticalE.ELSE;
	}
	
	//���״̬����
	public void updateAction(){
		jumpAction();
	}
	public void jumpAction(){
		//�����վ��½�����Ұ�����Ծ����֮��ſ�����Ծ��verticalT
			Log.v("player","���״̬��"+jump+"   ��Ծ������"+GameButton.get_S());
			if(GameButton.get_S() == GameButton.S_JUMP && verticalT == VerticalE.TOP){
				jump = true;
			}
			
			
			if(verticalT == VerticalE.BOTTOM){  //����Ԫ�ض���������Ծ״̬Ϊfalse
				jump = false;
			}
			else if( jump == true && JumpTimeCount < JumpTimeMAX){
				JumpTimeCount++;
			}
			else{
				 jump = false;
				JumpTimeCount = 0;  //�����ҵ�ǰ״̬������Ծ״̬����������Ծʱ�������Ϊ�´���Ծ��׼����
			}
	}
	
	public void set_VerticalT_and_PlaneT( ArrayList<OzElement>  gateAtlas){
		for(int i=0;i<gateAtlas.size();i++){
			//ֻ������ĳԪ�ص�4���ߵ�����1��
			if(gateAtlas.get(i).planeT == PlaneE.Left){
				
				planeT = PlaneE.Left;
				
			}
			else if(gateAtlas.get(i).planeT == PlaneE.Right){
				
				planeT = PlaneE.Right;
				
			}
			else if(gateAtlas.get(i).verticalT == VerticalE.TOP){
				
				verticalT = VerticalE.TOP;
				
			}
			else if(gateAtlas.get(i).verticalT == VerticalE.BOTTOM){
				
				verticalT = VerticalE.BOTTOM;
			}
		}
	}
	@Override
	public void impact(Player player) {
	}

	public static boolean isJump() {
		return jump;
	}
	public static PlaneE getPlaneT() {
		return planeT;
	}
	public static VerticalE getVerticalT() {
		return verticalT;
	}

	public  float getPush_X() {
		return push_X;
	}

	public  void setPush_X(float push_X) {
		this.push_X = push_X;
	}

	public  float getPush_Y() {
		return push_Y;
	}

	public  void setPush_Y(float push_Y) {
		this.push_Y = push_Y;
	}
	

	
	
	

}
