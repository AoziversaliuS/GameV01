package com.example.toolclass;


import android.graphics.Bitmap;

public class OzPicture {
	
	public Bitmap bitMap;
	public float  basicWidth=0;  //ͼƬ�ڻ�׼1280*720p��Ļ�µĳ���
	public float  basicHeight=0;
	
	public OzPicture(Bitmap bitMap,float  basicWidth,float  basicHeight){
		this.bitMap = bitMap;
		this.basicHeight = basicHeight;
		this.basicWidth =  basicWidth;
	}
}
