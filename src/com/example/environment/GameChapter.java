package com.example.environment;

import java.util.ArrayList;

import android.graphics.PointF;
import android.util.Log;

import com.example.element.BackGround;
import com.example.element.OzElement;
import com.example.element.build.Land;
import com.example.toolclass.OzInt;

public class GameChapter {
	
	//��һ�ص�ͼ
	static void A_01(ArrayList<OzElement> gateAtlas){
		
		gateAtlas.add(new BackGround("BG-1"));
		
		
		//���½�Ϊ�̳�BasicBody�Ķ���ֻ��BasicBody��������ܴﵽα��ֹ״̬
		gateAtlas.add(new Land("L-1", new PointF(  0, 1600)));
		gateAtlas.add(new Land("L-2", new PointF(200, 1500)));
		gateAtlas.add(new Land("L-3", new PointF(400, 1400)));
		gateAtlas.add(new Land("L-4", new PointF(400, 1700)));
	}
	
	
	
	
	//�ڶ��ص�ͼ
	private static void A_02(ArrayList<OzElement> gateAtlas) {
		// TODO Auto-generated method stub
	}
	
	
	
	
	
	
	
	
	
	
	
	
	//��ͼ����
	static void chapterLoad(ArrayList<OzElement> gateAtlas,ArrayList<OzInt> rankNum,int gateNum){
		initialise(gateAtlas,rankNum); //һ��Ҫд�ڵ�һ��
		switch (gateNum){
		
		case 1:
			                    A_01(gateAtlas);
			break;
		case 2:
								A_02(gateAtlas);
			break;
			
			
		default:
			break;
		}
		makingRankArray(gateAtlas, rankNum);//һ��Ҫд�����һ��
	}
	/**�л���ͼʱ�����ͼ����ͼ�����*/
	private static void initialise(ArrayList<OzElement> gateAtlas,ArrayList<OzInt> rankNum){
		gateAtlas       = new ArrayList<OzElement>();   //�����ͼ������
		rankNum         = new ArrayList<OzInt>();       //����ͼ����Ŷ��� 
	}
	/**ʹͼ����д�С��������*/
	private static void makingRankArray(ArrayList<OzElement> gateAtlas,ArrayList<OzInt> rankNum){
		
		for(int i=0;i<gateAtlas.size();i++){
			
			boolean rankNumHaved = false;
			for(int j=0;j<rankNum.size();j++){
				if(rankNum.get(j).value == gateAtlas.get(i).rankNum){
					rankNumHaved = true;
					break;
				}
			}
			if( rankNumHaved==false ){
				rankNum.add(new OzInt(gateAtlas.get(i).rankNum));
			}
			
		}
		//�õ������е�ͼ��
//		String s = "ͼ����: ";
//		for(int j=0;j<rankNum.length;j++){
//			s = s + " "+rankNum[j]+" ";
//		}
//		Log.v("RankNum", s);
		//��ͼ����Ž�������
		int buffer = -2;
		for(int i=0;i<rankNum.size();i++){
			for(int j=i+1;j<rankNum.size();j++){
				if(rankNum.get(i).value>rankNum.get(j).value){
					buffer = rankNum.get(i).value;
					rankNum.get(i).value = rankNum.get(j).value;
					rankNum.get(j).value = buffer;
				}
			}
		}
		String s = "ͼ����: ";
		for(int j=0;j<rankNum.size();j++){
			s = s + " "+rankNum.get(j).value+" ";
		}
		Log.v("RankNum", s);
	}
}
