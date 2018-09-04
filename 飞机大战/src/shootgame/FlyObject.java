package shootgame;

import java.awt.image.BufferedImage;

/**
 * @author LIAN
 * ����һ��������ĳ�����
 * �������ел����۷䡢Ӣ�ۻ����ӵ�
 * ������Ĺ�ͬ�������������������
 */
public abstract class FlyObject {
	
	//����ÿ�������ﶼ��һ���������Լ���ͼƬ
	protected BufferedImage image;
	
	//ÿ����������JFrame�����϶����Լ���XY����
	protected int x;
	protected int y;
	
	
	/**
	 *�������겻�У����ö���һ�������������ɵ���Ϊ
	 *��ô���ַ�����ɡ�һ��ĸо�����������������ʱ��ı仯���仯
	 *���ǲ�ͬ�ķ������в�ͬ�ķɷ���������ﶨ��һ��fly()���󷽷�
	 *�����ľ���ʵ�ֽ�����������
	 */
	public abstract void fly();


	/**
	 * ������һϵ��get/set����
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
