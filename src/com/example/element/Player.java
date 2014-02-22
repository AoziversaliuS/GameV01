package com.example.element;

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
	
	
	public static final float VALUE_JUMP    = 7;  //��Ծ���ٶ�
	public static final int JumpTimeMAX = 100;
	public static int JumpTimeCount = 0;  //��Ծ��ʱ��
	
	//planeTouch
	public static PlaneE     planeT    =  PlaneE.ELSE;
	//verticalTouch
	public static VerticalE  verticalT =  VerticalE.ELSE;
	
	public Player() {
		super(
				"Player",
				Rank.SELF_CUSTOM, 
				OzElementType.Player,
				new PointF(400, 400),
				new RectF(0, 0,P.Game_Player.basicWidth,P.Game_Player.basicHeight)
		);
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
		Player.planeT = PlaneE.ELSE;
		Player.verticalT = VerticalE.ELSE;
	}
	
	//���״̬����
	public void updateAction(){
		jumpAction();
	}
	public void jumpAction(){
		//�����վ��½�����Ұ�����Ծ����֮��ſ�����Ծ��verticalT
//			Log.v("player","���״̬��"+Player.verticalT+"   ��Ծ������"+GameButton.get_S());
//			if(GameButton.get_S() == GameButton.S_JUMP && Player.verticalT == VerticalE.TOP){
//				Player.verticalT = VerticalE.JUMP;
//			}
//			if(Player.verticalT == VerticalE.JUMP && JumpTimeCount < JumpTimeMAX){
//				Player.JumpTimeCount++;
//			}
//			else if(Player.verticalT == VerticalE.JUMP && JumpTimeCount > JumpTimeMAX){
//				Player.verticalT = VerticalE.FALL;
//				JumpTimeCount = 0;
//			}
//			else{
//				JumpTimeCount = 0;  //�����ҵ�ǰ״̬������Ծ״̬����������Ծʱ�������Ϊ�´���Ծ��׼����
//			}
	}
	@Override
	public void impact(Player player) {
	}

}
