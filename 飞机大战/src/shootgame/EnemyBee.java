package shootgame;


/**
 * 
 * @author LIAN
 * 这是一个EnemyBee普通类,用来实例化蜜蜂对象
 * 蜜蜂对象需要中弹2发才会被删除
 *
 */
public class EnemyBee extends FlyObject{
	
	
	//声明一个代表蜜蜂命值的变量，蜜蜂中弹一次命值减1
	//当命值为0时，蜜蜂被删除
	private int beeLife=2;
	
	
	//给命设置get/set方法
	public int getBeeLife() {
		return beeLife;
	}
	public void setBeeLife(int beeLife) {
		this.beeLife = beeLife;
	}
	
	
	//声明2个变量，在fly方法里用
	private int number1=0;
	private int number2=21;
	
	
	//重写Fly接口里的fly抽象方法
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
	
	
	//本类无参构造方法
	public EnemyBee(){
		setImage(MainClass.bee);
	}
}
