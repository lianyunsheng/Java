package shootgame;


/**
 * 
 * @author LIAN
 * 这是一个TaoBaoLogo普通类
 * 淘宝logo对象需要中弹4次才会被删除
 * 被淘宝logo对象砸中英雄机命值-3
 *
 */
public class TaoBaoLogo extends FlyObject{

	
	//声明一个变量，代表该logo对象的命值
	private int tbLogoLife=4;
	
	
	//给命设置get/set方法
	public int getTbLogoLife() {
		return tbLogoLife;
	}
	public void setTbLogoLife(int tbLogoLife) {
		this.tbLogoLife = tbLogoLife;
	}
	
	
	//重写fly抽象方法
	@Override
	public void fly() {
		setY(getY()+5);
	}


	//写一个构造方法
	public TaoBaoLogo(){
		setImage(MainClass.taobao_logo);
	}
}
