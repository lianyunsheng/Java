package shootgame;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


/**
 * @author LIAN
 * �����ڻ������ΪʲôҪ�̳�JPanel
 * ���Ǹо��̳��˻��ں������бȽϼ��
 * ����һ���������е������
 * main���������������
 */
public class MainClass extends JPanel{
	
	
	//��Ȼ�Ҳ�֪����仰��ʲô��˼
	//���ǲ�����仰���о���
	//������˳�����
	private static final long serialVersionUID = 1L;
	
	
	//��Ա��������Ϊ�����Ŀ����Ҫ��ͼƬ������
	//������Щ������������BufferedImage
	//���̳���Image������
	public static BufferedImage airplane;
	public static BufferedImage background;
	public static BufferedImage bee;
	public static BufferedImage bullet;
	public static BufferedImage gameover;
	public static BufferedImage hero0;
	public static BufferedImage hero1;
	public static BufferedImage pause;
	public static BufferedImage start;
	public static BufferedImage redStar;
	public static BufferedImage greyStar;
	public static BufferedImage jd_logo;
	public static BufferedImage taobao_logo;
	
	
	//дһ����̬�飬ʹ�������JVM����ʱ�ͼ���ͼƬ�Ⱦ�̬��Դ
	static{
		try {
			airplane=ImageIO.read(MainClass.class.getResource("airplane.png"));
			background=ImageIO.read(MainClass.class.getResource("background.png"));
			bee=ImageIO.read(MainClass.class.getResource("bee.png"));
			bullet=ImageIO.read(MainClass.class.getResource("bullet.png"));
			gameover=ImageIO.read(MainClass.class.getResource("gameover.png"));
			hero0=ImageIO.read(MainClass.class.getResource("hero0.png"));
			hero1=ImageIO.read(MainClass.class.getResource("hero1.png"));
			pause=ImageIO.read(MainClass.class.getResource("pause.png"));
			start=ImageIO.read(MainClass.class.getResource("start.png"));
			redStar=ImageIO.read(MainClass.class.getResource("star_focus.png"));
			greyStar=ImageIO.read(MainClass.class.getResource("star.png"));
			jd_logo=ImageIO.read(MainClass.class.getResource("jd_logo.png"));
			taobao_logo=ImageIO.read(MainClass.class.getResource("taobao_logo.png"));
		} catch (IOException e) {
			System.out.println("���ؾ�̬ͼƬʱ�����쳣��");
			e.printStackTrace();
		}
	}
	
	
	//����������̬�������ֱ��ʾJFrame���ڵĿ�͸�
	public static int jframeWidth=450;
	public static int jframeHeight=730;
	
	
	//JPanel����(����)�������ɼܵл������ɸ��۷䡢���ɸ��ӵ���һ��Ӣ�ۻ�
	//��˰���Щ�������뵽JPanel����ĳ�Ա��
	protected HeroPlane myPlane=new HeroPlane();
	protected EnemyBee[] bees={};
	protected EnemyAirplane[] eaPlanes={};
	protected Bullet[] bullets={};
	protected RedStar[] rstar={};
	protected GreyStar[] gstar={};
	protected JingDongLogo[] jdlogo={};
	protected TaoBaoLogo[] tblogo={};
	
	
	//����һ��������������Ϸ��״̬
	//�涨ֵ1Ϊ��Ϸ��ʼ��ֵ2Ϊ��Ϸ���ڽ���
	//ֵ3Ϊ��ͣ��ֵ4Ϊ��Ϸ����,ֵ5Ϊ��Ϸ�˳�
	private int gameState=1;
	
	
	//ʵ����һ��Random1����ʹ��������ɵл��۷���ɫ���ǵȳ��ֵ�X����ֵ
	//ʵ����һ��Random2����ʹ����������۷䡢���ɫ���ǵ�
	//ͬʱ����һ��number1���������Ʋ����ĵл���Ƶ��
	//����timer��ʱ��ÿ25msˢ��һ�Σ��涨ÿ1s���ڲ���һ�ܵл�
	//��number1=40ʱ��newһ���л�����
	//��number2=20����������Ҫ��ʱ��newһ���۷����
	//��number3=20����������Ҫ��ʱ��newһ�����Ƕ���
	//��number4=20����������Ҫ��ʱ��newһ�����Ƕ���
	//��number5=20����������Ҫ��ʱ��newһ������logo����
	//��number6=20����������Ҫ��ʱ��newһ���Ա�logo����
	Random random1=new Random();
	Random random2=new Random();
	private int number1=0;
	private int number2=0;
	private int number3=0;
	private int number4=0;
	private int number5=0;
	private int number6=0;
	
	
	//дһ��newEaplane���������������л�
	public void newEaPlane(){
		number1++;
		if(number1==40){
			EnemyAirplane eaPlane=new EnemyAirplane();
			//�����˵л�����󣬳�ʼ���������XY���꣬ʹ�л��ڴ��ڶ������λ�ó���
			eaPlane.setX(random1.nextInt(jframeWidth-64));
			eaPlane.setY(0);
			//��ʼ����󣬰��²����Ķ������eaPlanes������ͳһ����
			eaPlanes=Arrays.copyOf(eaPlanes, eaPlanes.length+1);
			eaPlanes[eaPlanes.length-1]=eaPlane;
			//��number1���㣬�����´β�������ļ���
			number1=0;
		}else{
			return;
		}
	}
	
	
	//дһ��newBee���������������۷����
	public void newBee(){
		number2++;
		//ÿ1sִ��һ��while���
		//�����۷�ĸ��ʿ�����8%
		while(number2==40){
			number2=0;
			int i1=random2.nextInt(100);
			if(i1<=8){
				EnemyBee bee=new EnemyBee();
				//��ʼ���۷�����
				bee.setX(random1.nextInt(jframeWidth-75));
				bee.setY(0);
				//��ʼ�������۷�����������ͳһ����
				bees=Arrays.copyOf(bees, bees.length+1);
				bees[bees.length-1]=bee;
			}
		}
	}
	
	
	//дһ��newRedStar�����������������Ƕ���
	public void newRedStar(){
		number3++;
		//ÿ1sִ��һ��while���
		//���ɺ��ǵĸ��ʿ�����1%
		while(number3==40){
			number3=0;
			int i1=random2.nextInt(100);
			if(i1<=1){
				RedStar rs=new RedStar();
				//��ʼ����������
				rs.setX(random1.nextInt(jframeWidth-75)+30);
				rs.setY(0);
				//��ʼ�����Ѻ��Ƕ����������ͳһ����
				rstar=Arrays.copyOf(rstar, rstar.length+1);
				rstar[rstar.length-1]=rs;
			}
		}
	}
	
	
	//дһ��newGreyStar�����������������Ƕ���
	public void newGreyStar(){
		number4++;
		//ÿ1sִ��һ��while���
		//���ɻ��ǵĸ��ʿ�����1%
		while(number4==40){
			number4=0;
			int i1=random2.nextInt(100);
			if(i1<=1){
				GreyStar gs=new GreyStar();
				//��ʼ����������
				gs.setX(random1.nextInt(jframeWidth-75)+30);
				gs.setY(0);
				//��ʼ�����ѻ��Ƕ����������ͳһ����
				gstar=Arrays.copyOf(gstar, gstar.length+1);
				gstar[gstar.length-1]=gs;
			}
		}
	}
	
	
	//дһ��newJDLogo������������������logo
	public void newJDLogo(){
		number5++;
		//ÿ1sִ��һ��while���
		//���ɾ���logo�ĸ��ʿ�����2%
		while(number5==40){
			number5=0;
			int i1=random2.nextInt(100);
			if(i1<=2){
				JingDongLogo jdl=new JingDongLogo();
				//��ʼ��logo����
				jdl.setX(random1.nextInt(jframeWidth-185));
				jdl.setY(0);
				//��ʼ�������������Ա�ͳһ����
				jdlogo=Arrays.copyOf(jdlogo, jdlogo.length+1);
				jdlogo[jdlogo.length-1]=jdl;
			}
		}
	}
	
	
	////дһ��newTBLogo���������������Ա�logo
	public void newTBLogo(){
		number6++;
		//ÿ1sִ��һ��while���
		//�����Ա�logo�ĸ��ʿ�����2%
		while(number6==40){
			number6=0;
			int i1=random2.nextInt(100);
			if(i1<=2){
				TaoBaoLogo tbl=new TaoBaoLogo();
				//��ʼ��logo����
				tbl.setX(random1.nextInt(jframeWidth-121));
				tbl.setY(0);
				//��ʼ�������������Ա�ͳһ����
				tblogo=Arrays.copyOf(tblogo, tblogo.length+1);
				tblogo[tblogo.length-1]=tbl;
			}
		}
	}
	
	
	//Ϊ�����ô�����ĸ������������̬Ч��
	//���Ƕ�֪�����ö�ʱ���ܽ���������
	Timer timer;
	
	
	//дһ��paintHeroPlane����
	//ÿ�ε���paint()����ʱ��Ӣ�ۻ���α仯�ͷ�װ�����������
	public void paintHeroPlane(Graphics g){
		myPlane.fly();
		g.drawImage(myPlane.getImage(),myPlane.getX(),myPlane.getY(), null);
	}
	
	
	//дһ��paintBullet����
	public void paintBullets(Graphics g){
		if(bullets.length!=0){
			for(Bullet bulle:bullets){
				bulle.fly();
				g.drawImage(bulle.image, bulle.getX(), bulle.getY(), null);
			}
		}else{
			return;
		}
	}
	
	
	//дһ��paintEaPlane����
	public void paintEaPlane(Graphics g){
		if(eaPlanes.length!=0){
			for(EnemyAirplane eap:eaPlanes){
				eap.fly();
				g.drawImage(eap.getImage(), eap.getX(), eap.getY(), null);
			}
		}else{
			return;
		}
	}
	
	
	//дһ��paintBee����
	public void paintBee(Graphics g){
		if(bees.length!=0){
			for(EnemyBee eb:bees){
				eb.fly();
				g.drawImage(eb.getImage(), eb.getX(), eb.getY(), null);
			}
		}else{
			return;
		}
	}
	
	
	//дһ��paintRstar����
	public void paintRstar(Graphics g){
		if(rstar.length!=0){
			for(RedStar redS:rstar){
				redS.fly();
				g.drawImage(redS.getImage(), redS.getX(), redS.getY(), null);
			}
		}else{
			return;
		}
	}
	
	
	//дһ��paintGstar����
	public void paintGstar(Graphics g){
		if(gstar.length!=0){
			for(GreyStar greyS:gstar){
				greyS.fly();
				g.drawImage(greyS.getImage(), greyS.getX(), greyS.getY(), null);
			}
		}else{
			return;
		}
	}
	
	
	//дһ��paintJDLogo����
	public void paintJDLogo(Graphics g){
		if(jdlogo.length!=0){
			for(JingDongLogo jd:jdlogo){
				jd.fly();
				g.drawImage(jd.getImage(), jd.getX(), jd.getY(), null);
			}
		}else{
			return;
		}
	}
	
	
	//дһ��paintTBLogo����
	public void paintTBLogo(Graphics g){
		if(tblogo.length!=0){
			for(TaoBaoLogo tb:tblogo){
				tb.fly();
				g.drawImage(tb.getImage(), tb.getX(), tb.getY(), null);
			}
		}else{
			return;
		}
	}
	
	
	//�ѵ÷ֺ���ֵ�������Ͻ�
	public void paintScoreAndLife(Graphics g) {
		int x = 5; // x����
		int y = 25; // y����
		Font font = new Font(Font.SANS_SERIF, Font.BOLD, 24); // ����
		g.setColor(new Color(0x00FF00));
		g.setFont(font); // ��������
		g.drawString("�÷�: "+myPlane.getScore(), x, y); // ������
		y += 25; // y������20
		g.drawString("��ֵ: " +myPlane.getHeroLife(), x, y); // ����
	}
	
	
	//дһ������������Ϸ����״̬
	public void paintGameover(Graphics g){
		paintScoreAndLife(g);
		int x = jframeWidth/2; // x����
		int y = jframeHeight/2; // y����
		Font font = new Font(Font.SANS_SERIF, Font.BOLD, 50); // ����
		g.setColor(new Color(0xFF0000));
		g.setFont(font); // ��������
		g.drawString("Game Over", x-140, y-100);
		g.drawString("Score:"+myPlane.getScore(), x-140, y-30);
	}
	
	
	//дһ������������Ϸ��ʼ״̬
	public void paintGameStart(Graphics g){
		int x = jframeWidth/2; // x����
		int y = jframeHeight/2; // y����
		Font font = new Font(Font.SANS_SERIF, Font.BOLD, 50); // ����
		g.setColor(new Color(0x666666));
		g.setFont(font); // ��������
		g.drawImage(background, 0, 0, null);
		g.drawString("Game Start", x-140, y-100);
		g.drawImage(hero0,
				(MainClass.jframeWidth/2)-(MainClass.hero0.getWidth()/2),
				(MainClass.jframeHeight/2)-(MainClass.hero0.getHeight()/2),
				null);
	}

	
	
	//��дJPanel���paint����
		public void paint(Graphics g){
			//��������gameState��ֵ�����Ϊ1������Ϸ��ʼ״̬
			if(gameState==1){
				paintGameStart(g);
			}
			//���gameStateֵΪ2������Ϸ����״̬����background����ͼ
			//���ֵΪ3����pause����ͼ
			//���ֵΪ4����gameover����ͼ
			//��������Щ״̬�»�����Ҫ��ʾ�Ķ���
			if(gameState==2){
				g.drawImage(background, 0, 0, null);
				//����paintScoreAndLife��������Ӣ�ۻ�����ֵ�͵÷�
				paintScoreAndLife(g);
				//����paintHeroPlane()��������Ӣ�ۻ�
				paintHeroPlane(g);
				//����paintBullets���������ӵ�
				paintBullets(g);
				//����paintEaPlane���������л�
				paintEaPlane(g);
				//����paintBee���������۷�
				paintBee(g);
				//����paintRstar������������
				paintRstar(g);
				//����paintGstar������������
				paintGstar(g);
				//����paintJDLogo������������logo
				paintJDLogo(g);
				//����paintTBLogo���������Ա�logo
				paintTBLogo(g);
			}
			if(gameState==4){
				paintGameover(g);
			}
		}
		
		
	//дһ������������ɾ��û���õ��ӵ����󡢷ɻ�����
	//��Щ�ӵ�����ͷɻ�������Ҫ��ʱɾ�����أ�
	//�ɳ���������ӵ����ɳ�������ĵл�
	//���ел����ӵ������ӵ����еĵл�
	public void deleteBulletsAndEaPlanes(){
		//���ɻ��ɳ�������ʱ��ɾ���ɻ�����
		for(int i0=0;i0<eaPlanes.length;i0++){
			if(eaPlanes[i0].getY()>jframeHeight){
				eaPlanes[i0]=eaPlanes[eaPlanes.length-1];
				eaPlanes=Arrays.copyOf(eaPlanes, eaPlanes.length-1);
			}
		}
		//���ӵ��ɳ�������ʱ��ɾ���ӵ�����
		for(int i0=0;i0<bullets.length;i0++){
			if(bullets[i0].getY()<0){
				bullets[i0]=bullets[bullets.length-1];
				bullets=Arrays.copyOf(bullets, bullets.length-1);
			}
		}
		//�����Ƿɻ����ӵ�������ɾ���㷨
		//���һ�ܵл�Ӣ�ۻ���10��
		if(eaPlanes.length!=0 && bullets.length!=0){
			int i1,i2,i3,i4,i5,i6,i7,i8;
			for(i1=0;i1<bullets.length;i1++){
				i3=bullets[i1].getX();
				i4=bullets[i1].getY();
				for(i2=0;i2<eaPlanes.length;i2++){
					i5=eaPlanes[i2].getX()-bullets[0].getImage().getWidth();
					i6=eaPlanes[i2].getY()-bullets[0].getImage().getHeight();
					i7=eaPlanes[i2].getX()+eaPlanes[i2].getImage().getWidth();
					i8=eaPlanes[i2].getY()+eaPlanes[i2].getImage().getHeight();
					if(i3>i5 && i3<i7 && i4>i6 && i4<i8){
						bullets[i1]=bullets[bullets.length-1];
						bullets=Arrays.copyOf(bullets, bullets.length-1);
						eaPlanes[i2]=eaPlanes[eaPlanes.length-1];
						eaPlanes=Arrays.copyOf(eaPlanes, eaPlanes.length-1);
						myPlane.setScore(myPlane.getScore()+10);
						if(bullets.length==0){
							return;
						}
						break;
					}
				}
			}
		}
	}
	
	
	//дһ������������ɾ��û���õ��ӵ������۷����
	//��Щ�ӵ�������۷������Ҫ��ʱɾ�����أ�
	//�ɳ���������۷�
	//�����۷���ӵ������ӵ����е��۷�
	//�۷������Ҫ�е�2���Żᱻɾ��
	public void deleteBees(){
		//���۷�ɳ�������ʱ��ɾ���۷����
		for(int i0=0;i0<bees.length;i0++){
			if(bees[i0].getY()>jframeHeight){
				bees[i0]=bees[bees.length-1];
				bees=Arrays.copyOf(bees, bees.length-1);
			}
		}	
		//�������۷���ӵ�������ɾ���㷨
		//���һ���۷�Ӣ�ۻ���40��
		if(bees.length!=0 && bullets.length!=0){
			int i1,i2,i3,i4,i5,i6,i7,i8;
			for(i1=0;i1<bullets.length;i1++){
				i3=bullets[i1].getX();
				i4=bullets[i1].getY();
				for(i2=0;i2<bees.length;i2++){
					i5=bees[i2].getX()-bullets[0].getImage().getWidth();
					i6=bees[i2].getY()-bullets[0].getImage().getHeight();
					i7=bees[i2].getX()+bees[i2].getImage().getWidth();
					i8=bees[i2].getY()+bees[i2].getImage().getHeight();
					if(i3>i5 && i3<i7 && i4>i6 && i4<i8){
						bullets[i1]=bullets[bullets.length-1];
						bullets=Arrays.copyOf(bullets, bullets.length-1);
						bees[i2].setBeeLife(bees[i2].getBeeLife()-1);
						if(bees[i2].getBeeLife()<=0){
							bees[i2]=bees[bees.length-1];
							bees=Arrays.copyOf(bees, bees.length-1);
							myPlane.setScore(myPlane.getScore()+40);
						}
						if(bullets.length==0){
							return;
						}
						break;
					}
				}
			}
		}
	}
	
	
	//дһ������������ɾ��û���õľ���logo����
	//��Щ����logo������Ҫ��ʱɾ�����أ�
	//�ɳ��������logo
	//����logo���ӵ������ӵ����е�logo
	//����logo������Ҫ�е�4���Żᱻɾ��
	public void deleteJDLogo(){
		//��logo�ɳ�������ʱ��ɾ��logo����
		for(int i0=0;i0<jdlogo.length;i0++){
			if(jdlogo[i0].getY()>jframeHeight){
				jdlogo[i0]=jdlogo[jdlogo.length-1];
				jdlogo=Arrays.copyOf(jdlogo, jdlogo.length-1);
			}
		}	
		//������logo���ӵ�������ɾ���㷨
		//���һ������logoӢ�ۻ���80��
		if(jdlogo.length!=0 && bullets.length!=0){
			int i1,i2,i3,i4,i5,i6,i7,i8;
			for(i1=0;i1<bullets.length;i1++){
				i3=bullets[i1].getX();
				i4=bullets[i1].getY();
				for(i2=0;i2<jdlogo.length;i2++){
					i5=jdlogo[i2].getX()-jdlogo[0].getImage().getWidth();
					i6=jdlogo[i2].getY()-jdlogo[0].getImage().getHeight();
					i7=jdlogo[i2].getX()+jdlogo[i2].getImage().getWidth();
					i8=jdlogo[i2].getY()+jdlogo[i2].getImage().getHeight();
					if(i3>i5 && i3<i7 && i4>i6 && i4<i8){
						bullets[i1]=bullets[bullets.length-1];
						bullets=Arrays.copyOf(bullets, bullets.length-1);
						jdlogo[i2].setJdLogoLife(jdlogo[i2].getJdLogoLife()-1);
						if(jdlogo[i2].getJdLogoLife()<=0){
							jdlogo[i2]=jdlogo[jdlogo.length-1];
							jdlogo=Arrays.copyOf(jdlogo, jdlogo.length-1);
							myPlane.setScore(myPlane.getScore()+80);
						}
						if(bullets.length==0){
							return;
						}
						break;
					}
				}
			}
		}
	}
	
	
	//дһ������������ɾ��û���õ��Ա�logo����
	//��Щ�Ա�logo������Ҫ��ʱɾ�����أ�
	//�ɳ��������logo
	//����logo���ӵ������ӵ����е�logo
	//�Ա�logo������Ҫ�е�4���Żᱻɾ��
	public void deleteTBLogo(){
		//��logo�ɳ�������ʱ��ɾ��logo����
		for(int i0=0;i0<tblogo.length;i0++){
			if(tblogo[i0].getY()>jframeHeight){
				tblogo[i0]=tblogo[tblogo.length-1];
				tblogo=Arrays.copyOf(tblogo, tblogo.length-1);
			}
		}	
		//������logo���ӵ�������ɾ���㷨
		//���һ���Ա�logoӢ�ۻ���80��
		if(tblogo.length!=0 && bullets.length!=0){
			int i1,i2,i3,i4,i5,i6,i7,i8;
			for(i1=0;i1<bullets.length;i1++){
				i3=bullets[i1].getX();
				i4=bullets[i1].getY();
				for(i2=0;i2<tblogo.length;i2++){
					i5=tblogo[i2].getX()-tblogo[0].getImage().getWidth();
					i6=tblogo[i2].getY()-tblogo[0].getImage().getHeight();
					i7=tblogo[i2].getX()+tblogo[i2].getImage().getWidth();
					i8=tblogo[i2].getY()+tblogo[i2].getImage().getHeight();
					if(i3>i5 && i3<i7 && i4>i6 && i4<i8){
						bullets[i1]=bullets[bullets.length-1];
						bullets=Arrays.copyOf(bullets, bullets.length-1);
						tblogo[i2].setTbLogoLife(tblogo[i2].getTbLogoLife()-1);
						if(tblogo[i2].getTbLogoLife()<=0){
							tblogo[i2]=tblogo[tblogo.length-1];
							tblogo=Arrays.copyOf(tblogo, tblogo.length-1);
							myPlane.setScore(myPlane.getScore()+80);
						}
						if(bullets.length==0){
							return;
						}
						break;
					}
				}
			}
		}
	}
	
	
	//дһ�������������������Ƿ�ײ��Ӣ�ۻ������ײ�����ͼ�������
	public void hitHeroPlane(){
		//���л��Ƿ���Ӣ�ۻ���ײ
		if(eaPlanes.length!=0){
			int i1,i2,i3,i4,i5,i6,i7;
			i1=myPlane.getX();
			i2=myPlane.getY();
			i3=myPlane.getX()+97;
			i4=myPlane.getY()+82;
			for(i5=0;i5<eaPlanes.length;i5++){
				i6=eaPlanes[i5].getX();
				i7=eaPlanes[i5].getY();
				if(i6>i1-eaPlanes[i5].getImage().getWidth() && i6<i3
						&& i7>i2-eaPlanes[i5].getImage().getHeight() && i7<i4){
					eaPlanes[i5]=eaPlanes[eaPlanes.length-1];
					eaPlanes=Arrays.copyOf(eaPlanes, eaPlanes.length-1);
					myPlane.setHeroLife(myPlane.getHeroLife()-1);
				}
			}
		}
		//���Ӣ�ۻ��Ƿ����۷���ײ
		if(bees.length!=0){
			int i1,i2,i3,i4,i5,i6,i7;
			i1=myPlane.getX();
			i2=myPlane.getY();
			i3=myPlane.getX()+97;
			i4=myPlane.getY()+82;
			for(i5=0;i5<bees.length;i5++){
				i6=bees[i5].getX();
				i7=bees[i5].getY();
				if(i6>i1-bees[i5].getImage().getWidth() && i6<i3
						&& i7>i2-bees[i5].getImage().getHeight() && i7<i4){
					bees[i5]=bees[bees.length-1];
					bees=Arrays.copyOf(bees, bees.length-1);
					myPlane.setHeroLife(myPlane.getHeroLife()-1);
				}
			}
		}
		//��⾩��logo�Ƿ���Ӣ�ۻ���ײ
		if(jdlogo.length!=0){
			int i1,i2,i3,i4,i5,i6,i7;
			i1=myPlane.getX();
			i2=myPlane.getY();
			i3=myPlane.getX()+97;
			i4=myPlane.getY()+82;
			for(i5=0;i5<jdlogo.length;i5++){
				i6=jdlogo[i5].getX();
				i7=jdlogo[i5].getY();
				if(i6>i1-jdlogo[i5].getImage().getWidth() && i6<i3
						&& i7>i2-jdlogo[i5].getImage().getHeight() && i7<i4){
					jdlogo[i5]=jdlogo[jdlogo.length-1];
					jdlogo=Arrays.copyOf(jdlogo, jdlogo.length-1);
					myPlane.setHeroLife(myPlane.getHeroLife()-3);
				}
			}
		}
		//����Ա�logo�Ƿ���Ӣ�ۻ���ײ
		if(tblogo.length!=0){
			int i1,i2,i3,i4,i5,i6,i7;
			i1=myPlane.getX();
			i2=myPlane.getY();
			i3=myPlane.getX()+97;
			i4=myPlane.getY()+82;
			for(i5=0;i5<tblogo.length;i5++){
				i6=tblogo[i5].getX();
				i7=tblogo[i5].getY();
				if(i6>i1-tblogo[i5].getImage().getWidth() && i6<i3
						&& i7>i2-tblogo[i5].getImage().getHeight() && i7<i4){
					tblogo[i5]=tblogo[tblogo.length-1];
					tblogo=Arrays.copyOf(tblogo,tblogo.length-1);
					myPlane.setHeroLife(myPlane.getHeroLife()-3);
				}
			}
		}
		//�������Ƿ���Ӣ�ۻ���ײ
		if(rstar.length!=0){
			int i1,i2,i3,i4,i5,i6,i7;
			i1=myPlane.getX();
			i2=myPlane.getY();
			i3=myPlane.getX()+97;
			i4=myPlane.getY()+82;
			for(i5=0;i5<rstar.length;i5++){
				i6=rstar[i5].getX();
				i7=rstar[i5].getY();
				if(i6>i1-rstar[i5].getImage().getWidth() && i6<i3
						&& i7>i2-rstar[i5].getImage().getHeight() && i7<i4){
					rstar[i5]=rstar[rstar.length-1];
					rstar=Arrays.copyOf(rstar, rstar.length-1);
					myPlane.setHeroLife(myPlane.getHeroLife()+1);
				}
			}
		}
		//�������Ƿ���Ӣ�ۻ���ײ
		if(gstar.length!=0){
			int i1,i2,i3,i4,i5,i6,i7;
			i1=myPlane.getX();
			i2=myPlane.getY();
			i3=myPlane.getX()+97;
			i4=myPlane.getY()+82;
			for(i5=0;i5<gstar.length;i5++){
				i6=gstar[i5].getX();
				i7=gstar[i5].getY();
				if(i6>i1-gstar[i5].getImage().getWidth() && i6<i3
						&& i7>i2-gstar[i5].getImage().getHeight() && i7<i4){
					gstar[i5]=gstar[gstar.length-1];
					gstar=Arrays.copyOf(gstar, gstar.length-1);
					eaPlanes=Arrays.copyOf(eaPlanes, 0);
					bees=Arrays.copyOf(bees, 0);
					jdlogo=Arrays.copyOf(jdlogo, 0);
					tblogo=Arrays.copyOf(tblogo, 0);
					myPlane.setScore(myPlane.getScore()+100);
				}
			}
		}
	}
	
	
	//дһ���������������Ӣ�ۻ�����ֵ����ȡ��Ӧ��ʩ
	public void checkHeroLife(){
		if(myPlane.getHeroLife()<=0){
			myPlane.setHeroLife(0);
			gameState=4;
		}
	}
	
	
	//дһ������Ĺ��췽��
	public MainClass(){	
		//��ʼ����ʱ��
		timer=new Timer();
		//����ʱ���������
		timer.schedule(new TimerTask(){
			//��дTimerTask�����run()���� 
			@Override
			public void run() {
				if(gameState==1){
					repaint();
				}
				if(gameState==2){
					//����newEaPlane�����������л�
					newEaPlane();
					//����newBee�����������۷�
					newBee();
					//����newRedStar��������������
					newRedStar();
					//����newGreyStar��������������
					newGreyStar();
					//����newJDLogo��������������logo
					newJDLogo();
					//����newTBLogo�����������Ա�logo
					newTBLogo();
					//����deleteBulletsAndEaPlanes��������ʱɾ��û�õ��ӵ��͵л�
					deleteBulletsAndEaPlanes();
					//����deleteBees������ɾ��û�õ��۷����
					deleteBees();
					//����deleteJDLogo������ɾ��û�õľ���logo����
					deleteJDLogo();
					//����deleteTBLogo������ɾ��û�õ��Ա�logo����
					deleteTBLogo();
					//дһ��������������������л��Ƿ���ײ
					hitHeroPlane();
					//���Ӣ�ۻ�����ֵ�Ƿ�С�ڵ���0
					checkHeroLife();
					//ͨ��JPanel��repaint()�������µ���Paint()������ˢ��ҳ��
					repaint();
					if(gameState==3){
						return;
					}
					if(gameState==4){
						repaint();
					}
				}
			}
		},25,25);
		//��MainClass����һ����ʱ��������ʱ��
		//��ΪӢ�ۻ���JFrame��������Ҫ���������ƶ����˶�
		//����أ���Ҫ��JPanel��ע������ƶ��¼�������MouseMotionListener
		//���������������ȡ�¼�Դ�������¼�����
		//��ͨ���¼������ȡ��Ҫ�������Ϣ
		//��ͨ����Ϣȥ������Ϸ��ĳЩʵ��
		//���������ã��Ǿ��ܿ�����������Ĵ���
		this.addMouseMotionListener(new MouseMotionAdapter(){
			public void mouseMoved(MouseEvent e) {
				//����Ϸ�������ڽ���״̬ʱ��Ӣ�ۻ����������ƶ����ƶ�
				//����Ϸ���ڽ���״̬ʱ��Ӣ�ۻ�������ƶ�
				if(gameState==2){
					//��ΪӢ�ۻ�Ҫ���������ƶ����ƶ�
					//����ͨ������¼������õ��������ֵ����Ӣ�ۻ�����
					//���һ��õ���ΪʹӢ�ۻ����е�������ƶ�
					myPlane.setX(e.getX()-myPlane.getImage().getWidth()/2);
					myPlane.setY(e.getY()-myPlane.getImage().getHeight()/2);
				}else{
					return;
				}
			}
		});
		//���˼�����ƶ��¼�����������Ҫ������¼�������
		//������һ������������ɻ��ʹ��һ���ӵ���
		//����Ƴ�JFrame����ʱ��ͣ
		//�������JFrame����ʱȡ����ͣ
		this.addMouseListener(new MouseAdapter(){
			//������������ʱ����һ���ӵ�
			//������Ҽ�����ʱ������Ϸ
			@Override
			public void mouseClicked(MouseEvent e) {
				//����������
				if(e.getButton()==MouseEvent.BUTTON1){
					//�ж�gameStateֵ�����ֵΪ1�����Ϊ2��������Ϸ״̬��
					if(gameState==1){
						gameState=2;
					}
					if(gameState==2){
						Bullet bullet=new Bullet();
						//��ʼ���ӵ������꣬ʹ�ӵ���ʼ��Ӣ�ۻ���ͷ��(����)
						bullet.setX(myPlane.getX()+myPlane.getImage().getWidth()/2-2);
						bullet.setY(myPlane.getY());
						//�� bullets�������ݣ�װ���²������ӵ�
						bullets=Arrays.copyOf(bullets, bullets.length+1);
						bullets[bullets.length-1]=bullet;
					}
					if(gameState==4){
						myPlane.setHeroLife(6);
						myPlane.setScore(0);
						eaPlanes=Arrays.copyOf(eaPlanes, 0);
						bees=Arrays.copyOf(bees, 0);
						jdlogo=Arrays.copyOf(jdlogo, 0);
						tblogo=Arrays.copyOf(tblogo, 0);
						rstar=Arrays.copyOf(rstar, 0);
						gstar=Arrays.copyOf(gstar, 0);
						gameState=1;
					}
				}
				//��������Ҽ�
				if(e.getButton()==MouseEvent.BUTTON3){
					System.out.println("���ε÷�"+myPlane.getScore()+"��");
					System.exit(0);
				}
			}
			//��������JFrame����ʱȡ����ͣ
			@Override
			public void mouseEntered(MouseEvent e) {
				if(gameState==3){
					gameState=2;
				}
			}
			//������Ƴ�JFrame����ʱ��Ϸ��ͣ
			@Override
			public void mouseExited(MouseEvent e) {
				if(gameState==2){
					gameState=3;
				}
			}
		});
	}
	
	
	//дһ��main��������Ϊ����ִ�е����
	public static void main(String[] args){
		JFrame frame=new JFrame();
		frame.setTitle("������Ƶķɻ���ս");
		frame.setSize(jframeWidth,jframeHeight);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setAlwaysOnTop(true);
		frame.setIconImage(hero1);
		frame.setLocationRelativeTo(null);
		MainClass myPen=new MainClass();
		frame.add(myPen);
		frame.setVisible(true);
	}
}