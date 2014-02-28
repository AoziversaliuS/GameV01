package com.example.toolclass;


import java.io.IOException;
import java.io.InputStream;

import android.R.color;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.Log;

public class P {
	
	
	public static OzPicture Game_RarrowA;
	public static OzPicture Game_RarrowB;
	public static OzPicture Game_LarrowA;
	public static OzPicture Game_LarrowB;
	public static OzPicture Game_JumpA;
	public static OzPicture Game_JumpB;
	public static OzPicture Game_BackGround;
	public static OzPicture Game_Player;
	public static OzPicture Game_BasicStone;
	
	
	
	
	
	/**载入全部图片*/
	public static void pictureLoad(Resources resources){
		if(pictureLoaded == false){
			varibleDefine();//变量初始化
			//载入图片  
			Game_BackGround		 = pictureMake(resources,"Game/BackGround/backg.png", 1280, 720);
			Game_LarrowA   		 = pictureMake(resources,"Game/Button/Larrow_A.png",      200, 109);
			Game_LarrowB   		 = pictureMake(resources,"Game/Button/Larrow_B.png",      200, 109);
			Game_RarrowA   	 	 = pictureMake(resources,"Game/Button/Rarrow_A.png",      200, 109);
		    Game_RarrowB    	 = pictureMake(resources,"Game/Button/Rarrow_B.png",      200, 109);
		    Game_JumpA  	     = pictureMake(resources,"Game/Button/Jump_A.png",        130, 130);
			Game_JumpB   		 = pictureMake(resources,"Game/Button/Jump_B.png",        130, 130);
		    Game_Player    		 = pictureMake(resources,"Game/Player/ppp.png",            70,  70);
			Game_BasicStone      = pictureMake(resources,"Game/Build/st.png",            459,  201);
			
			
			
			
			pictureLoaded = true;
		}
	}
	
	
	 public static final PointF zero = new PointF(0,0);  //为了消除DrawAllocation而设，以后忘了是什么可以删掉
	private static String DpiFolder = "xh/";
	
	private static void varibleDefine(){
		
		if(Screen.dpi>=320){
			DpiFolder = "xh/";  //在xh文件夹下找图片
		}
		else if(Screen.dpi>=240){
			DpiFolder = "h/";   //在h文件夹下找图片
		}
		else if(Screen.dpi>=160){
			DpiFolder = "m/";   //在m文件夹下找图片
		}
		else if(Screen.dpi>=120){
			DpiFolder = "l/";   //在l文件夹下找图片
		}
		
	}
	private static boolean pictureLoaded = false; //检测图片是否已载入;
	/**根据比例来制造适合屏幕的图片*/
	private static OzPicture pictureMake(Resources resources,String filePath,int basicWidth,int basicHeight){
		Bitmap picBuffer; //图片暂时存放区
		Bitmap picZoom;   //缩放后的图片
//		picBuffer = BitmapFactory.decodeResource(resources, picId);
		picBuffer = pictureSummon(filePath, resources);
		picZoom = Bitmap.createScaledBitmap(picBuffer, (int)(basicWidth*Screen.ratioX), (int)(basicHeight*Screen.ratioY), false);
		OzPicture picture = new OzPicture(picZoom, basicWidth, basicHeight);
		return picture;
	}
	/**将图片从asset文件夹下召唤出来                                                                                  fileName:assest目录下图片的相对路径*/
	private static Bitmap pictureSummon(String filePath,Resources resources){
		Bitmap bitmap = null;
		AssetManager am = resources.getAssets();
		
		try {
			InputStream is = am.open("Image/"+DpiFolder+filePath);  // 图片召唤默认以assets/Image/对应dpi文件夹/ 为起始地址
			bitmap = BitmapFactory.decodeStream(is);
			is.close();
		} catch (IOException e) {
			Log.e("picture", "图片加载失败！");
		}
		
		return bitmap;
	}
	/**将图片画在屏幕的合适位置A*/
	public static void pictureDraw(OzPicture ozPicture,float angle,PointF l,Canvas canvasBuffer,boolean isCenter){
		Matrix mx = new Matrix();
		mx.postRotate(angle, ozPicture.bitMap.getWidth()/2, ozPicture.bitMap.getHeight()/2); 
		if(isCenter){//若获得的坐标是图片的中心点的话则。。
			mx.postTranslate(l.x*Screen.ratioX-ozPicture.bitMap.getWidth()/2,  l.y*Screen.ratioY-ozPicture.bitMap.getHeight()/2);
		}
		else{//若获得的坐标是图片左上角坐标的话
			mx.postTranslate(l.x*Screen.ratioX,  l.y*Screen.ratioY);
		}
		
		canvasBuffer.drawBitmap(ozPicture.bitMap, mx, null);
	}
	/**将图片画在屏幕的合适位置B*/
	public static void pictureDraw(OzPicture ozPicture,PointF l,Canvas canvasBuffer){
		float x =  l.x*Screen.ratioX;
		float y = l.y*Screen.ratioY;
		
		canvasBuffer.drawBitmap(ozPicture.bitMap, x, y, null);
//		if(ozPicture.basicWidth > 1000){
//			paint.setColor(color.white);
//			canvasBuffer.drawRect(l.x,l.y, l.x+ozPicture.basicWidth, l.y+ozPicture.basicHeight, paint);
//		}
//		else{
//			paint.setColor(Color.BLUE);
//			canvasBuffer.drawRect(l.x,l.y, l.x+ozPicture.basicWidth, l.y+ozPicture.basicHeight, paint);
//		}
		
	}
	/**将图片画在屏幕的合适位置C*/
	public static void pictureDraw(OzPicture ozPicture,float x,float y,Canvas canvasBuffer){
		canvasBuffer.drawBitmap(ozPicture.bitMap, x*Screen.ratioX, y*Screen.ratioY, null);
	}

	
	
}
