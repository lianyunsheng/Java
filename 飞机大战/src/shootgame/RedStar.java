package shootgame;


/**
 * 
 * @author LIAN
 * ����һ��RedStar��
 * ����Ϸ�е��ɻ�ײ��RedStar����ʱ�����ֵ+1�Ľ���
 *
 */
public class RedStar extends FlyObject{

	
	//��дFlyObject�е�fly���󷽷�
	@Override
	public void fly() {
		setY(getY()+3);
	}
	
	
	//дһ���޲ι��췽��
	public RedStar(){
		setImage(MainClass.redStar);
	}
}
