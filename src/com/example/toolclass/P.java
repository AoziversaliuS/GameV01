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
	
	
	
	
	
	/**����ȫ��ͼƬ*/
	public static void pictureLoad(Resources resources){
		if(pictureLoaded == false){
			varibleDefine();//������ʼ��
			//����ͼƬ  
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
	
	
	 public static final PointF zero = new PointF(0,0);  //Ϊ������DrawAllocation���裬�Ժ�������ʲô����ɾ��
	private static String DpiFolder = "xh/";
	
	private static void varibleDefine(){
		
		if(Screen.dpi>=320){
			DpiFolder = "xh/";  //��xh�ļ�������ͼƬ
		}
		else if(Screen.dpi>=240){
			DpiFolder = "h/";   //��h�ļ�������ͼƬ
		}
		else if(Screen.dpi>=160){
			DpiFolder = "m/";   //��m�ļ�������ͼƬ
		}
		else if(Screen.dpi>=120){
			DpiFolder = "l/";   //��l�ļ�������ͼƬ
		}
		
	}
	private static boolean pictureLoaded = false; //���ͼƬ�Ƿ�������;
	/**���ݱ����������ʺ���Ļ��ͼƬ*/
	private static OzPicture pictureMake(Resources resources,String filePath,int basicWidth,int basicHeight){
		Bitmap picBuffer; //ͼƬ��ʱ�����
		Bitmap picZoom;   //���ź��ͼƬ
//		picBuffer = BitmapFactory.decodeResource(resources, picId);
		picBuffer = pictureSummon(filePath, resources);
		picZoom = Bitmap.createScaledBitmap(picBuffer, (int)(basicWidth*Screen.ratioX), (int)(basicHeight*Screen.ratioY), false);
		OzPicture picture = new OzPicture(picZoom, basicWidth, basicHeight);
		return picture;
	}
	/**��ͼƬ��asset�ļ������ٻ�����                                                                                  fileName:assestĿ¼��ͼƬ�����·��*/
	private static Bitmap pictureSummon(String filePath,Resources resources){
		Bitmap bitmap = null;
		AssetManager am = resources.getAssets();
		
		try {
			InputStream is = am.open("Image/"+DpiFolder+filePath);  // ͼƬ�ٻ�Ĭ����assets/Image/��Ӧdpi�ļ���/ Ϊ��ʼ��ַ
			bitmap = BitmapFactory.decodeStream(is);
			is.close();
		} catch (IOException e) {
			Log.e("picture", "ͼƬ����ʧ�ܣ�");
		}
		
		return bitmap;
	}
	/**��ͼƬ������Ļ�ĺ���λ��A*/
	public static void pictureDraw(OzPicture ozPicture,float angle,PointF l,Canvas canvasBuffer,boolean isCenter){
		Matrix mx = new Matrix();
		mx.postRotate(angle, ozPicture.bitMap.getWidth()/2, ozPicture.bitMap.getHeight()/2); 
		if(isCenter){//����õ�������ͼƬ�����ĵ�Ļ��򡣡�
			mx.postTranslate(l.x*Screen.ratioX-ozPicture.bitMap.getWidth()/2,  l.y*Screen.ratioY-ozPicture.bitMap.getHeight()/2);
		}
		else{//����õ�������ͼƬ���Ͻ�����Ļ�
			mx.postTranslate(l.x*Screen.ratioX,  l.y*Screen.ratioY);
		}
		
		canvasBuffer.drawBitmap(ozPicture.bitMap, mx, null);
	}
	/**��ͼƬ������Ļ�ĺ���λ��B*/
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
	/**��ͼƬ������Ļ�ĺ���λ��C*/
	public static void pictureDraw(OzPicture ozPicture,float x,float y,Canvas canvasBuffer){
		canvasBuffer.drawBitmap(ozPicture.bitMap, x*Screen.ratioX, y*Screen.ratioY, null);
	}

	
	
}
