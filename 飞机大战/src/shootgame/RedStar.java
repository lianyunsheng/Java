package shootgame;


/**
 * 
 * @author LIAN
 * 这是一个RedStar类
 * 在游戏中当飞机撞到RedStar对象时获得命值+1的奖励
 *
 */
public class RedStar extends FlyObject{

	
	//重写FlyObject中的fly抽象方法
	@Override
	public void fly() {
		setY(getY()+3);
	}
	
	
	//写一个无参构造方法
	public RedStar(){
		setImage(MainClass.redStar);
	}
}
