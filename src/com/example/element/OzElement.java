package com.example.element;

import android.graphics.PointF;
import android.graphics.RectF;

import com.example.basicenum.OzElementType;
import com.example.basicenum.PlaneE;
import com.example.basicenum.VerticalE;


public abstract class OzElement implements Origin{
	
	public int rankNum = -1;  //��Ԫ�����ڵ�ͼ��
	public float angle = 0;
	OzElementType type = OzElementType.Element;
	public PointF l;   //Ԫ�ص�����
	public RectF  entityOffset; //entifyOffset ʵ�������l��ƫ����
	public RectF  entity = new RectF();//��Ԫ��ʵ��������ײ����ʵ�巽��    Ҫ��ͨ��OzElement.makeEntity(this); ����ʹ��
	public boolean planeLogicUsed = true;  //ʹ��ˮƽ�������
	public boolean verticalLogicUsed = true;  //ʹ��ˮƽ�������
	public String  Tag = "0-0-0";
	
	public PlaneE    planeT = PlaneE.ELSE;        //����ʶ���������������Լ����ĸ�λ��
	public VerticalE verticalT = VerticalE.FALL;
	//���캯��
	public OzElement(String Tag,int Rank,OzElementType type,PointF l,RectF entityOffset){
		this.Tag = Tag;
		this.rankNum = Rank;
		this.type = type;
		this.l = l;
		this.entityOffset = entityOffset;
	}
	
	//��Ԫ�ص��߼�����
	//��Ԫ�ص�ͼ����ʾ����
	//��Ԫ�ص���ײ��⺯��
	
	
	
	//���������ˮƽ�ƶ�����  ����������ƶ��������ƶ���            ʱ����˶�
	public void planeLogic(){
	}
	//��ֱ�ƶ����� ��ҡ���Ծ����׹��ʱ������˶�
	public void verticalLogic(){
	}
	
	public void engine(){
		
		if(planeLogicUsed){
			planeLogic();     //��������ƶ��������ƶ�ʱ����˶�
		}
		if(verticalLogicUsed){
			verticalLogic();  // �����Ծ����׹ʱ������˶�
		}
		
		logic();              //Ԫ���������е��߼�����
	}
	
	
	public boolean hit(OzElement other){
		
		OzElement.makeEntity(this);   //Ϊ��������������ײ����ʵ��
		OzElement.makeEntity(other);  //Ϊ����һ����ײ�Ķ�������������ײ����ʵ��
		
		if(this.entity.intersect(other.entity)){
			//���������������򷵻�true
			return true;
			
		}
		else{
			//û��������false;
			return false;
			
		}
		
	}
	public static void makeEntity(OzElement element){
		element.entity.left   = element.l.x + element.entityOffset.left;
		element.entity.top    = element.l.y + element.entityOffset.top;
		element.entity.right  = element.l.x + element.entityOffset.left + element.entityOffset.right;
		element.entity.bottom = element.l.y + element.entityOffset.top + element.entityOffset.bottom;
//		element.entity.centerX()
	}
	/**�����ж�һ����pP�Ƿ���һ��������*/
	public boolean Inside(PointF pP,RectF rect){ 
		if(rect.contains(pP.x, pP.y)){
			return true;
		}
		return false;
	}
}
