package shootgame;


/**
 * 
 * @author LIAN
 * ����һ��JingDongLogo��ͨ��
 * ����logo������Ҫ�е�4�βŻᱻɾ��
 * ������logo��������Ӣ�ۻ���ֵ-3
 *
 */
public class JingDongLogo extends FlyObject{
	
	
	//����һ��������������logo�������ֵ
	private int jdLogoLife=4;
	
	
	//��������set/get����
	public int getJdLogoLife() {
		return jdLogoLife;
	}
	public void setJdLogoLife(int jdLogoLife) {
		this.jdLogoLife = jdLogoLife;
	}

	
	//��дfly���󷽷�
	@Override
	public void fly() {
		setY(getY()+5);
	}


	//дһ���޲ι��췽��
	public JingDongLogo(){
		setImage(MainClass.jd_logo);
	}
}
