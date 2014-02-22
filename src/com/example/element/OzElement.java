package com.example.element;

import android.graphics.PointF;
import android.graphics.RectF;

import com.example.basicenum.OzElementType;
import com.example.basicenum.PlaneE;
import com.example.basicenum.VerticalE;


public abstract class OzElement implements Origin{
	
	public int rankNum = -1;  //该元素所在的图层
	public float angle = 0;
	OzElementType type = OzElementType.Element;
	public PointF l;   //元素的坐标
	public RectF  entityOffset; //entifyOffset 实体相对于l的偏移量
	public RectF  entity = new RectF();//此元素实际用于碰撞检测的实体方块    要先通过OzElement.makeEntity(this); 才能使用
	public boolean planeLogicUsed = true;  //使用水平相关运算
	public boolean verticalLogicUsed = true;  //使用水平相关运算
	public String  Tag = "0-0-0";
	
	public PlaneE    planeT = PlaneE.ELSE;        //用来识别其它物体碰到自己的哪个位置
	public VerticalE verticalT = VerticalE.FALL;
	//构造函数
	public OzElement(String Tag,int Rank,OzElementType type,PointF l,RectF entityOffset){
		this.Tag = Tag;
		this.rankNum = Rank;
		this.type = type;
		this.l = l;
		this.entityOffset = entityOffset;
	}
	
	//该元素的逻辑运算
	//该元素的图形显示函数
	//该元素的碰撞检测函数
	
	
	
	//在这里进行水平移动运算  【玩家向左移动，向右移动】            时相对运动
	public void planeLogic(){
	}
	//垂直移动运算 玩家【跳跃，下坠】时的相对运动
	public void verticalLogic(){
	}
	
	public void engine(){
		
		if(planeLogicUsed){
			planeLogic();     //玩家向左移动，向右移动时相对运动
		}
		if(verticalLogicUsed){
			verticalLogic();  // 玩家跳跃，下坠时的相对运动
		}
		
		logic();              //元素自身特有的逻辑运算
	}
	
	
	public boolean hit(OzElement other){
		
		OzElement.makeEntity(this);   //为自身制造用于碰撞检测的实体
		OzElement.makeEntity(other);  //为另外一个碰撞的对象制造用于碰撞检测的实体
		
		if(this.entity.intersect(other.entity)){
			//与其它物体相碰则返回true
			return true;
			
		}
		else{
			//没碰到返回false;
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
	/**用来判定一个点pP是否在一个矩形内*/
	public boolean Inside(PointF pP,RectF rect){ 
		if(rect.contains(pP.x, pP.y)){
			return true;
		}
		return false;
	}
}
