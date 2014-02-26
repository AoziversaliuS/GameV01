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

	public static final float VALUE_MOVE    = 5;                //玩家水平移动速度
	public static final float VALUE_GRAVITY = 4;                //重力
	public static final float limitUp = 200;
	public static final float limitDown = 520;
	
	public  static final float VALUE_JUMP    = 4;  //跳跃的速度
	public  static PointF L = new PointF(0,0);
	public  static final int JumpTimeMAX = 50;
	private static int JumpTimeCount = 0;  //跳跃的时间
	
	//planeTouch
	private static PlaneE     planeT    =  PlaneE.ELSE;
	//verticalTouch
	private static VerticalE  verticalT =  VerticalE.ELSE;
	private  float push_X = 0;  //碰撞后将玩家位置推回到穿墙前
	private  float push_Y = 0;  //碰撞后将玩家位置推回到穿墙前
	private  float dY = 2;      //不出现穿墙状况的最低值 
	private  float dX = 2;      //不出现穿墙状况的最低值 
	
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
	    if( Player.isJump()==true && l.y>Player.limitUp){
			l.y = l.y - Player.VALUE_JUMP;
			Log.v("status","玩家跳跃");
		}
		else if(Player.isJump()==false && (Player.getVerticalT()==VerticalE.ELSE || Player.getVerticalT()==VerticalE.BOTTOM) && l.y<Player.limitDown){
			l.y = l.y + Player.VALUE_GRAVITY;
			Log.v("status","玩家下坠");
		}
		else if( Player.getVerticalT()==VerticalE.TOP ){
			//停止下坠,坐标不改变就是停止下坠的状态
		}
	}

	@Override
	public void show(Canvas canvas) {
		P.pictureDraw(P.Game_Player, l, canvas);
	}


	@Override
	public void reset() {
	}
	
	public void resetOnGameLogic(){
		//状态更新不能写在重设变量这里
		push_X = 0; 
		push_Y = 0;
		planeT = PlaneE.ELSE;
		verticalT = VerticalE.ELSE;
		L = l; //对外传输玩家坐标
	}
	
	//玩家状态更新
	public void updateAction(){
		jumpAction();
	}
	public void jumpAction(){
		//当玩家站在陆地上且按下跳跃按键之后才可以跳跃。verticalT
			Log.v("player","玩家状态："+jump+"   跳跃按键："+GameButton.get_S());
			if(GameButton.get_S() == GameButton.S_JUMP && verticalT == VerticalE.TOP){
				jump = true;
			}
			
			
			if(verticalT == VerticalE.BOTTOM){  //碰到元素顶部则设跳跃状态为false
				jump = false;
			}
			else if( jump == true && JumpTimeCount < JumpTimeMAX){
				JumpTimeCount++;
			}
			else{
				 jump = false;
				JumpTimeCount = 0;  //如果玩家当前状态不是跳跃状态，则重置跳跃时间计数，为下次跳跃做准备。
			}
	}
	
	public void set_VerticalT_and_PlaneT( ArrayList<OzElement>  gateAtlas){
		for(int i=0;i<gateAtlas.size();i++){
			//只能碰到某元素的4条边的其中1条
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

	public  float getPush_Y() {
		return push_Y;
	}

	public  void setPush_X(float push_X) {
		if(push_X - dX > 0){
			this.push_X = push_X - dX;
		}
	}
	public  void setPush_Y(float push_Y) {
		if(push_Y - this.dY > 0){
			this.push_Y = push_Y - this.dY;
		}
	}
	
	public  static PointF getL(){
		return L;
	}
	

}
