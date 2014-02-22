package com.example.element.button;

import java.util.ArrayList;

import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.RectF;

import com.example.basicenum.OzElementType;
import com.example.element.OzElement;
import com.example.element.Player;
import com.example.toolclass.P;
import com.example.toolclass.Rank;

public class GameButton extends OzElement{

	private RectF buttonLeft;
	private RectF buttonRight;
	private RectF buttonJump;
	
	/**ö��ֵ��*/
	//orientation  ö��ֵ������ͬ��
	public static final int O_LEFT=1, O_RIGHT=2,O_ELSE=3;
	//Skill
	public static final int S_JUMP=4,S_ELSE=5;
	/**ö��ֵ��*/
	
	public  static int   pressO = GameButton.O_ELSE;   //�����,������Ϣ
	public  static int   pressS = GameButton.S_ELSE;   //���ܼ�,������Ϣ
	
	private static boolean pressO_changed = false;
	private static boolean pressS_changed = false;
	
	public GameButton() {
		super("GameButton",Rank._9, OzElementType.GameButton,null,null);
		
		this.buttonLeft  = new RectF(0, 600, P.Game_LarrowA.basicWidth, 600+P.Game_LarrowA.basicHeight);
		this.buttonRight = new RectF(250, 600, 250+P.Game_RarrowA.basicWidth, 600+P.Game_RarrowA.basicHeight);
		this.buttonJump = new RectF(1050, 580, 1050+P.Game_JumpA.basicWidth, 580+P.Game_JumpA.basicHeight);
		
		GameButton.pressO = GameButton.O_ELSE;
		GameButton.pressS = GameButton.S_ELSE;
	}

	@Override
	public void reset() {
		GameButton.pressO = GameButton.O_ELSE;   //�������һ֡�İ�����Ϣ
		GameButton.pressS = GameButton.S_ELSE;
		 pressO_changed = false;
		 pressS_changed = false;
	}
	public void logic(ArrayList<PointF> pressPoint) {
		
//		Log.v("TouchScreen", "������������"+pressPoint.size());
		
		//�д�����Ļ�������
		for(int i=0;i<pressPoint.size();i++){
			//���Ұ���
			if(this.Inside(pressPoint.get(i),buttonLeft) && GameButton.pressO == GameButton.O_ELSE){
				GameButton.pressO = GameButton.O_LEFT;
				pressO_changed = true;
			}
			else if(this.Inside(pressPoint.get(i),buttonRight) && GameButton.pressO == GameButton.O_ELSE){
				GameButton.pressO = GameButton.O_RIGHT;
				pressO_changed = true;
			}
			else if(pressO_changed == false){
				GameButton.pressO = GameButton.O_ELSE;
			}
			//��Ծ����
			if(this.Inside(pressPoint.get(i),buttonJump)){
				GameButton.pressS = GameButton.S_JUMP;
				pressS_changed = true;
			}
			else if(pressS_changed == false){
				GameButton.pressS = GameButton.S_ELSE;
			}
		}
	}
	@Override
	public void show(Canvas canvas) {
		//���Ұ���
		if(pressO == GameButton.O_ELSE){
			P.pictureDraw(P.Game_LarrowA, this.buttonLeft.left,this.buttonLeft.top, canvas);
			P.pictureDraw(P.Game_RarrowA, this.buttonRight.left,this.buttonRight.top, canvas);
		}
		else if(pressO == GameButton.O_LEFT){
			P.pictureDraw(P.Game_LarrowB, this.buttonLeft.left,this.buttonLeft.top, canvas);
			P.pictureDraw(P.Game_RarrowA, this.buttonRight.left,this.buttonRight.top, canvas);
		}
		else if(pressO == GameButton.O_RIGHT){
			P.pictureDraw(P.Game_LarrowA,  this.buttonLeft.left,this.buttonLeft.top, canvas);
			P.pictureDraw(P.Game_RarrowB, this.buttonRight.left,this.buttonRight.top, canvas);
		}
		//��Ծ����
		if(pressS == GameButton.S_ELSE){
			P.pictureDraw(P.Game_JumpA, this.buttonJump.left,this.buttonJump.top, canvas);
		}
		else if(pressS == GameButton.S_JUMP){
			P.pictureDraw(P.Game_JumpB,  this.buttonJump.left,this.buttonJump.top, canvas);
		}
	}

	
	public static int get_O(){
		return GameButton.pressO;     //��pressO��ֵ���ݸ����
	}
	public static int get_S(){
		return GameButton.pressS;     //��pressS��ֵ���ݸ����
	}
	
	
	@Override
	public void logic() {
	}

	@Override
	public void impact(Player player) {
	}

	


}
