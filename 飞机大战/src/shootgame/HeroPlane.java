package shootgame;


/**
 * 
 * @author LIAN
 * 这是一个英雄机类
 * 英雄机对象的命值为6
 * 当命值小于等于0时，游戏结束
 *
 */
public class HeroPlane extends FlyObject{
	
	
	//声明一个变量，代表英雄机的命值
	private int heroLife=6;
	
	
	//给命设置get/set方法
	public int getHeroLife() {
		return heroLife;
	}
	public void setHeroLife(int heroLife) {
		this.heroLife = heroLife;
	}
	
	
	//声明一个变量，用来存放英雄机获得的分数
	private int score=0;
	
	
	//给score设置get/set方法
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	
	
	//在类里面定义一个changeImage变量
	//当changeImage=0时，图片为hero0,；当=1时，图片为hero1
	int changeImage=0;


	//重写Fly接口里的fly抽象方法
	public void fly(){
		//控制changeImage值只能取0和2，使图片只能在hero0和her01之间切换
		changeImage++;
		if(changeImage==4){
			changeImage=0;
		}
		if(changeImage==0){
			setImage(MainClass.hero0);
		}
		if(changeImage==2){
			setImage(MainClass.hero1);
		}
	}
	
	
	//写一个该类的无参构造方法
	public HeroPlane(){
		//第一步，初始化英雄机的XY坐标，使英雄机在游戏开始前定位到游戏窗口中间
		this.setX((MainClass.jframeWidth/2)-(MainClass.hero0.getWidth()/2));
		this.setY((MainClass.jframeHeight/2)-(MainClass.hero0.getHeight()/2));
		// 第二步，初始化图片
		this.setImage(MainClass.hero0);
	}
}
