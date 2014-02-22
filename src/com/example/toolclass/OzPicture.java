package com.example.toolclass;


import android.graphics.Bitmap;

public class OzPicture {
	
	public Bitmap bitMap;
	public float  basicWidth=0;  //图片在基准1280*720p屏幕下的长宽
	public float  basicHeight=0;
	
	public OzPicture(Bitmap bitMap,float  basicWidth,float  basicHeight){
		this.bitMap = bitMap;
		this.basicHeight = basicHeight;
		this.basicWidth =  basicWidth;
	}
}
