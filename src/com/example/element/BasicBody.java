package com.example.element;

import android.graphics.PointF;
import android.graphics.RectF;

import com.example.basicenum.OzElementType;
import com.example.basicenum.PlaneE;
import com.example.basicenum.VerticalE;
import com.example.element.button.GameButton;

public abstract class BasicBody extends OzElement {


	public BasicBody(String Tag, int Rank, OzElementType type, PointF l,
			RectF entityOffset) {
		super(Tag, Rank, type, l, entityOffset);
	}

	
	

	
	
	//在这里进行水平移动运算  【玩家向左移动，向右移动】            时相对运动
	@Override
	public void planeLogic() {
		
		if(GameButton.get_O() == GameButton.O_LEFT && Player.getPlaneT() != PlaneE.Right){
			l.x = l.x + Player.VALUE_MOVE;
		}
		else if(GameButton.get_O() == GameButton.O_RIGHT && Player.getPlaneT() != PlaneE.Left){
			l.x = l.x - Player.VALUE_MOVE;
		}
	}
	//垂直移动运算 玩家【跳跃，下坠】时的相对运动
	@Override
	public void verticalLogic() {
		
	    if( Player.isJump()==true ){
			l.y = l.y + Player.VALUE_JUMP;
		}
		else if( Player.getVerticalT()==VerticalE.ELSE || Player.getVerticalT()==VerticalE.BOTTOM ){
			l.y = l.y - Player.VALUE_GRAVITY;
		}
		else if( Player.getVerticalT()==VerticalE.TOP ){
			//停止下坠,坐标不改变就是停止下坠的状态
		}
	    
	}

	@Override
	public void reset() {

	}

}
