package shootgame;


/**
 * 
 * @author LIAN
 * ����һ��TaoBaoLogo��ͨ��
 * �Ա�logo������Ҫ�е�4�βŻᱻɾ��
 * ���Ա�logo��������Ӣ�ۻ���ֵ-3
 *
 */
public class TaoBaoLogo extends FlyObject{

	
	//����һ�������������logo�������ֵ
	private int tbLogoLife=4;
	
	
	//��������get/set����
	public int getTbLogoLife() {
		return tbLogoLife;
	}
	public void setTbLogoLife(int tbLogoLife) {
		this.tbLogoLife = tbLogoLife;
	}
	
	
	//��дfly���󷽷�
	@Override
	public void fly() {
		setY(getY()+5);
	}


	//дһ�����췽��
	public TaoBaoLogo(){
		setImage(MainClass.taobao_logo);
	}
}
