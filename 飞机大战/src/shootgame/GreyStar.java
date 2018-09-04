package shootgame;


/**
 * 
 * @author LIAN
 * 这是一个GreyStar类
 * 在游戏中当飞机撞到这个类对象时，清除所有敌人对象数组，同时英雄机得分+100
 *
 */
public class GreyStar extends FlyObject{

	
	//重写fly抽象方法
	@Override
	public void fly() {
		setY(getY()+3);
	}
	
	
	//定义一个无参构造方法
	public GreyStar(){
		setImage(MainClass.greyStar);
	}
}
