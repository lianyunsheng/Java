package shootgame;


/**
 * 
 * @author LIAN
 * ����һ��EnemyAirplane��ͨ��
 * �����������ʵ�����л������
 *
 */
public class EnemyAirplane extends FlyObject{
	
	
	//����2����������fly��������
	private int number1=0;
	private int number2=10;
	
	
	//��дfly���󷽷�
	public void fly(){
		number1++;
		setY(getY()+5);
		while(number1==10){
			number1=0;
			number2=(int)(Math.random()*20);
		}
		if(number2<5){
			setX(getX()-5);
		}else{
			if(number2>=15){
				setX(getX()+5);
			}else{
				return;
			}
		}
		if(getX()<0){
			setX(0);
		}
		if(getX()>MainClass.jframeWidth-64){
			setX(MainClass.jframeWidth-64);
		}
	}
	
	
	//дһ��������޲ι��췽��
	public EnemyAirplane(){
		setImage(MainClass.airplane);
	}
}
