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
	
	/**枚举值↓*/
	//orientation  枚举值不能相同！
	public static final int O_LEFT=1, O_RIGHT=2,O_ELSE=3;
	//Skill
	public static final int S_JUMP=4,S_ELSE=5;
	/**枚举值↑*/
	
	public  static int   pressO = GameButton.O_ELSE;   //方向键,触碰信息
	public  static int   pressS = GameButton.S_ELSE;   //技能键,触碰信息
	
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
		GameButton.pressO = GameButton.O_ELSE;   //清除掉上一帧的按键信息
		GameButton.pressS = GameButton.S_ELSE;
		 pressO_changed = false;
		 pressS_changed = false;
	}
	public void logic(ArrayList<PointF> pressPoint) {
		
//		Log.v("TouchScreen", "触碰点数量："+pressPoint.size());
		
		//有触碰屏幕的情况下
		for(int i=0;i<pressPoint.size();i++){
			//左右按键
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
			//跳跃按键
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
		//左右按键
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
		//跳跃按键
		if(pressS == GameButton.S_ELSE){
			P.pictureDraw(P.Game_JumpA, this.buttonJump.left,this.buttonJump.top, canvas);
		}
		else if(pressS == GameButton.S_JUMP){
			P.pictureDraw(P.Game_JumpB,  this.buttonJump.left,this.buttonJump.top, canvas);
		}
	}

	
	public static int get_O(){
		return GameButton.pressO;     //将pressO的值传递给外界
	}
	public static int get_S(){
		return GameButton.pressS;     //将pressS的值传递给外界
	}
	
	
	@Override
	public void logic() {
	}

	@Override
	public void impact(Player player) {
	}

	


}
