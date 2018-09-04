package shootgame;


/**
 * 
 * @author LIAN
 * 这是一个JingDongLogo普通类
 * 京东logo对象需要中弹4次才会被删除
 * 被京东logo对象砸中英雄机命值-3
 *
 */
public class JingDongLogo extends FlyObject{
	
	
	//声明一个变量，代表京东logo对象的命值
	private int jdLogoLife=4;
	
	
	//给命设置set/get方法
	public int getJdLogoLife() {
		return jdLogoLife;
	}
	public void setJdLogoLife(int jdLogoLife) {
		this.jdLogoLife = jdLogoLife;
	}

	
	//重写fly抽象方法
	@Override
	public void fly() {
		setY(getY()+5);
	}


	//写一个无参构造方法
	public JingDongLogo(){
		setImage(MainClass.jd_logo);
	}
}
