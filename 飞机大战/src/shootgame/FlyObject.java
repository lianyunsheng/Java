package shootgame;

import java.awt.image.BufferedImage;

/**
 * @author LIAN
 * 这是一个飞行物的抽象类
 * 飞行物有敌机、蜜蜂、英雄机、子弹
 * 飞行物的共同特征都抽象在这个类里
 */
public abstract class FlyObject {
	
	//首先每个飞行物都有一张属于它自己的图片
	protected BufferedImage image;
	
	//每个飞行物在JFrame窗口上都有自己的XY坐标
	protected int x;
	protected int y;
	
	
	/**
	 *光有坐标不行，还得定义一个方法来描述飞的行为
	 *怎么体现飞行物“飞”一般的感觉？就是让坐标随着时间的变化而变化
	 *但是不同的飞行物有不同的飞法，因此这里定义一个fly()抽象方法
	 *方法的具体实现交给各个子类
	 */
	public abstract void fly();


	/**
	 * 下面是一系列get/set方法
	 */
	public BufferedImage getImage() {
		return image;
	}
	public void setImage(BufferedImage image) {
		this.image = image;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}	
}
