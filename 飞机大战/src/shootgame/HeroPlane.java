package shootgame;


/**
 * 
 * @author LIAN
 * ����һ��Ӣ�ۻ���
 * Ӣ�ۻ��������ֵΪ6
 * ����ֵС�ڵ���0ʱ����Ϸ����
 *
 */
public class HeroPlane extends FlyObject{
	
	
	//����һ������������Ӣ�ۻ�����ֵ
	private int heroLife=6;
	
	
	//��������get/set����
	public int getHeroLife() {
		return heroLife;
	}
	public void setHeroLife(int heroLife) {
		this.heroLife = heroLife;
	}
	
	
	//����һ���������������Ӣ�ۻ���õķ���
	private int score=0;
	
	
	//��score����get/set����
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	
	
	//�������涨��һ��changeImage����
	//��changeImage=0ʱ��ͼƬΪhero0,����=1ʱ��ͼƬΪhero1
	int changeImage=0;


	//��дFly�ӿ����fly���󷽷�
	public void fly(){
		//����changeImageֵֻ��ȡ0��2��ʹͼƬֻ����hero0��her01֮���л�
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
	
	
	//дһ��������޲ι��췽��
	public HeroPlane(){
		//��һ������ʼ��Ӣ�ۻ���XY���꣬ʹӢ�ۻ�����Ϸ��ʼǰ��λ����Ϸ�����м�
		this.setX((MainClass.jframeWidth/2)-(MainClass.hero0.getWidth()/2));
		this.setY((MainClass.jframeHeight/2)-(MainClass.hero0.getHeight()/2));
		// �ڶ�������ʼ��ͼƬ
		this.setImage(MainClass.hero0);
	}
}
