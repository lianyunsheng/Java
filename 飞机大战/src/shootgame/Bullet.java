package shootgame;


/**
 * 
 * @author LIAN
 * 这是一个Bullet普通类
 * 这个类是用来实例化子弹对象的
 *
 */
public class Bullet extends FlyObject{
	
	
	//写一个无参构造方法
	public Bullet(){
		setImage(MainClass.bullet);
	}
	
	
	/**
	 * 重写Fly接口里的fly抽象方法
	 */
	public void fly(){
		setY(getY()-40);
	}
}
