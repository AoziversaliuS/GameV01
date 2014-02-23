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

	
	

	
	
	//���������ˮƽ�ƶ�����  ����������ƶ��������ƶ���            ʱ����˶�
	@Override
	public void planeLogic() {
		
		if(GameButton.get_O() == GameButton.O_LEFT && Player.getPlaneT() != PlaneE.Right){
			l.x = l.x + Player.VALUE_MOVE;
		}
		else if(GameButton.get_O() == GameButton.O_RIGHT && Player.getPlaneT() != PlaneE.Left){
			l.x = l.x - Player.VALUE_MOVE;
		}
	}
	//��ֱ�ƶ����� ��ҡ���Ծ����׹��ʱ������˶�
	@Override
	public void verticalLogic() {
		
	    if( Player.isJump()==true ){
			l.y = l.y + Player.VALUE_JUMP;
		}
		else if( Player.getVerticalT()==VerticalE.ELSE || Player.getVerticalT()==VerticalE.BOTTOM ){
			l.y = l.y - Player.VALUE_GRAVITY;
		}
		else if( Player.getVerticalT()==VerticalE.TOP ){
			//ֹͣ��׹,���겻�ı����ֹͣ��׹��״̬
		}
	    
	}

	@Override
	public void reset() {

	}

}
