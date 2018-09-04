package shootgame;


/**
 * 
 * @author LIAN
 * 这是一个EnemyAirplane普通类
 * 这个类是用来实例化敌机对象的
 *
 */
public class EnemyAirplane extends FlyObject{
	
	
	//声明2个变量，在fly方法里用
	private int number1=0;
	private int number2=10;
	
	
	//重写fly抽象方法
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
	
	
	//写一个该类的无参构造方法
	public EnemyAirplane(){
		setImage(MainClass.airplane);
	}
}
