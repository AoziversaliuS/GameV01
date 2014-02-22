package com.example.element;

import android.graphics.Canvas;

public interface Origin {
	
		public abstract void logic();
		
		public abstract void show(Canvas canvasBuffer);
		
		public abstract void  impact(Player player);
		
		public abstract void reset();
}
