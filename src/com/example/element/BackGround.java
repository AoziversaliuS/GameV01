package com.example.element;

import android.graphics.Canvas;
import android.graphics.PointF;

import com.example.basicenum.OzElementType;
import com.example.toolclass.P;
import com.example.toolclass.Rank;

public class BackGround extends OzElement{

	public BackGround(String Tag) {
		super(Tag,Rank.BACK_GROUND, OzElementType.BackGround,new PointF(0,0),null);
	}

	@Override
	public void logic() {
		
	}

	@Override
	public void show(Canvas canvas) {
		P.pictureDraw(P.Game_BackGround,this.l, canvas);
	}


	@Override
	public void reset() {
	}

	@Override
	public void impact(Player player) {
	}

}
