package shootgame;


/**
 * 
 * @author LIAN
 * ����һ��EnemyBee��ͨ��,����ʵ�����۷����
 * �۷������Ҫ�е�2���Żᱻɾ��
 *
 */
public class EnemyBee extends FlyObject{
	
	
	//����һ�������۷���ֵ�ı������۷��е�һ����ֵ��1
	//����ֵΪ0ʱ���۷䱻ɾ��
	private int beeLife=2;
	
	
	//��������get/set����
	public int getBeeLife() {
		return beeLife;
	}
	public void setBeeLife(int beeLife) {
		this.beeLife = beeLife;
	}
	
	
	//����2����������fly��������
	private int number1=0;
	private int number2=21;
	
	
	//��дFly�ӿ����fly���󷽷�
	public void fly(){
		number1++;
		setY(getY()+5);
		while(number1==10){
			number1=0;
			number2=(int)(Math.random()*20);
		}
		if(number2<5){
			setX(getX()+5);
		}else{
			if(number2>=15 && number2<20){
				setX(getX()-5);
			}else{
				if(number2>=5 && number2<10){
					setY(getY()-5);
				}else{
					if(number2>=10 && number2<15){
						setY(getY()+5);
					}else{
						return;
					}
				}
			}
		}
		if(getX()<0){
			setX(0);
		}
		if(getX()>MainClass.jframeWidth-75){
			setX(MainClass.jframeWidth-75);
		}
	}
	
	
	//�����޲ι��췽��
	public EnemyBee(){
		setImage(MainClass.bee);
	}
}
