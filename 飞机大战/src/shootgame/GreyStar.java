package shootgame;


/**
 * 
 * @author LIAN
 * ����һ��GreyStar��
 * ����Ϸ�е��ɻ�ײ����������ʱ��������е��˶������飬ͬʱӢ�ۻ��÷�+100
 *
 */
public class GreyStar extends FlyObject{

	
	//��дfly���󷽷�
	@Override
	public void fly() {
		setY(getY()+3);
	}
	
	
	//����һ���޲ι��췽��
	public GreyStar(){
		setImage(MainClass.greyStar);
	}
}
