package shootgame;


/**
 * 
 * @author LIAN
 * ����һ��Bullet��ͨ��
 * �����������ʵ�����ӵ������
 *
 */
public class Bullet extends FlyObject{
	
	
	//дһ���޲ι��췽��
	public Bullet(){
		setImage(MainClass.bullet);
	}
	
	
	/**
	 * ��дFly�ӿ����fly���󷽷�
	 */
	public void fly(){
		setY(getY()-40);
	}
}
