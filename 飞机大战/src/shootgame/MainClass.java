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
 * 我现在还不清楚为什么要继承JPanel
 * 但是感觉继承了会在后面编程中比较简便
 * 这是一个程序运行的入口类
 * main方法放在这个类里
 */
public class MainClass extends JPanel{
	
	
	//虽然我不知道这句话是什么意思
	//但是不加这句话会有警告
	//所以我顺便加了
	private static final long serialVersionUID = 1L;
	
	
	//成员变量：作为这个项目所需要的图片的引用
	//由于这些变量的类型是BufferedImage
	//它继承于Image抽象类
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
	
	
	//写一个静态块，使这个类在JVM加载时就加载图片等静态资源
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
			System.out.println("加载静态图片时出现异常！");
			e.printStackTrace();
		}
	}
	
	
	//定义两个静态变量，分别表示JFrame窗口的宽和高
	public static int jframeWidth=450;
	public static int jframeHeight=730;
	
	
	//JPanel对象(容器)里有若干架敌机、若干个蜜蜂、若干个子弹、一架英雄机
	//因此把那些对象列入到JPanel对象的成员中
	protected HeroPlane myPlane=new HeroPlane();
	protected EnemyBee[] bees={};
	protected EnemyAirplane[] eaPlanes={};
	protected Bullet[] bullets={};
	protected RedStar[] rstar={};
	protected GreyStar[] gstar={};
	protected JingDongLogo[] jdlogo={};
	protected TaoBaoLogo[] tblogo={};
	
	
	//声明一个变量，代表游戏的状态
	//规定值1为游戏开始，值2为游戏正在进行
	//值3为暂停，值4为游戏结束,值5为游戏退出
	private int gameState=1;
	
	
	//实例化一个Random1对象，使它随机生成敌机蜜蜂红灰色星星等出现的X坐标值
	//实例化一个Random2对象，使他随机产生蜜蜂、红灰色星星等
	//同时声明一个number1变量，控制产生的敌机的频率
	//由于timer定时器每25ms刷新一次，规定每1s窗口产生一架敌机
	//当number1=40时，new一个敌机对象
	//当number2=20且满足其他要求时，new一个蜜蜂对象
	//当number3=20且满足其他要求时，new一个红星对象
	//当number4=20且满足其他要求时，new一个灰星对象
	//当number5=20且满足其他要求时，new一个京东logo对象
	//当number6=20且满足其他要求时，new一个淘宝logo对象
	Random random1=new Random();
	Random random2=new Random();
	private int number1=0;
	private int number2=0;
	private int number3=0;
	private int number4=0;
	private int number5=0;
	private int number6=0;
	
	
	//写一个newEaplane方法，用来产生敌机
	public void newEaPlane(){
		number1++;
		if(number1==40){
			EnemyAirplane eaPlane=new EnemyAirplane();
			//产生了敌机对象后，初始化对象里的XY坐标，使敌机在窗口顶部随机位置出现
			eaPlane.setX(random1.nextInt(jframeWidth-64));
			eaPlane.setY(0);
			//初始化完后，把新产生的对象放入eaPlanes数组里统一管理
			eaPlanes=Arrays.copyOf(eaPlanes, eaPlanes.length+1);
			eaPlanes[eaPlanes.length-1]=eaPlane;
			//把number1清零，进入下次产生对象的计数
			number1=0;
		}else{
			return;
		}
	}
	
	
	//写一个newBee方法，用来产生蜜蜂对象
	public void newBee(){
		number2++;
		//每1s执行一次while语句
		//生成蜜蜂的概率控制在8%
		while(number2==40){
			number2=0;
			int i1=random2.nextInt(100);
			if(i1<=8){
				EnemyBee bee=new EnemyBee();
				//初始化蜜蜂坐标
				bee.setX(random1.nextInt(jframeWidth-75));
				bee.setY(0);
				//初始化完后把蜜蜂对象放入数组统一管理
				bees=Arrays.copyOf(bees, bees.length+1);
				bees[bees.length-1]=bee;
			}
		}
	}
	
	
	//写一个newRedStar方法，用来产生红星对象
	public void newRedStar(){
		number3++;
		//每1s执行一次while语句
		//生成红星的概率控制在1%
		while(number3==40){
			number3=0;
			int i1=random2.nextInt(100);
			if(i1<=1){
				RedStar rs=new RedStar();
				//初始化红星坐标
				rs.setX(random1.nextInt(jframeWidth-75)+30);
				rs.setY(0);
				//初始化完后把红星对象放入数组统一管理
				rstar=Arrays.copyOf(rstar, rstar.length+1);
				rstar[rstar.length-1]=rs;
			}
		}
	}
	
	
	//写一个newGreyStar方法，用来产生灰星对象
	public void newGreyStar(){
		number4++;
		//每1s执行一次while语句
		//生成灰星的概率控制在1%
		while(number4==40){
			number4=0;
			int i1=random2.nextInt(100);
			if(i1<=1){
				GreyStar gs=new GreyStar();
				//初始化灰星坐标
				gs.setX(random1.nextInt(jframeWidth-75)+30);
				gs.setY(0);
				//初始化完后把灰星对象放入数组统一管理
				gstar=Arrays.copyOf(gstar, gstar.length+1);
				gstar[gstar.length-1]=gs;
			}
		}
	}
	
	
	//写一个newJDLogo方法，用来产生京东logo
	public void newJDLogo(){
		number5++;
		//每1s执行一次while语句
		//生成京东logo的概率控制在2%
		while(number5==40){
			number5=0;
			int i1=random2.nextInt(100);
			if(i1<=2){
				JingDongLogo jdl=new JingDongLogo();
				//初始化logo坐标
				jdl.setX(random1.nextInt(jframeWidth-185));
				jdl.setY(0);
				//初始化完后放入数组以便统一管理
				jdlogo=Arrays.copyOf(jdlogo, jdlogo.length+1);
				jdlogo[jdlogo.length-1]=jdl;
			}
		}
	}
	
	
	////写一个newTBLogo方法，用来产生淘宝logo
	public void newTBLogo(){
		number6++;
		//每1s执行一次while语句
		//生成淘宝logo的概率控制在2%
		while(number6==40){
			number6=0;
			int i1=random2.nextInt(100);
			if(i1<=2){
				TaoBaoLogo tbl=new TaoBaoLogo();
				//初始化logo坐标
				tbl.setX(random1.nextInt(jframeWidth-121));
				tbl.setY(0);
				//初始化完后放入数组以便统一管理
				tblogo=Arrays.copyOf(tblogo, tblogo.length+1);
				tblogo[tblogo.length-1]=tbl;
			}
		}
	}
	
	
	//为了能让窗口里的各个对象产生动态效果
	//我们都知道，用定时器能解决这个问题
	Timer timer;
	
	
	//写一个paintHeroPlane方法
	//每次调用paint()方法时，英雄机如何变化就封装在这个方法里
	public void paintHeroPlane(Graphics g){
		myPlane.fly();
		g.drawImage(myPlane.getImage(),myPlane.getX(),myPlane.getY(), null);
	}
	
	
	//写一个paintBullet方法
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
	
	
	//写一个paintEaPlane方法
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
	
	
	//写一个paintBee方法
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
	
	
	//写一个paintRstar方法
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
	
	
	//写一个paintGstar方法
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
	
	
	//写一个paintJDLogo方法
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
	
	
	//写一个paintTBLogo方法
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
	
	
	//把得分和命值画到左上角
	public void paintScoreAndLife(Graphics g) {
		int x = 5; // x坐标
		int y = 25; // y坐标
		Font font = new Font(Font.SANS_SERIF, Font.BOLD, 24); // 字体
		g.setColor(new Color(0x00FF00));
		g.setFont(font); // 设置字体
		g.drawString("得分: "+myPlane.getScore(), x, y); // 画分数
		y += 25; // y坐标增20
		g.drawString("命值: " +myPlane.getHeroLife(), x, y); // 画命
	}
	
	
	//写一个方法，画游戏结束状态
	public void paintGameover(Graphics g){
		paintScoreAndLife(g);
		int x = jframeWidth/2; // x坐标
		int y = jframeHeight/2; // y坐标
		Font font = new Font(Font.SANS_SERIF, Font.BOLD, 50); // 字体
		g.setColor(new Color(0xFF0000));
		g.setFont(font); // 设置字体
		g.drawString("Game Over", x-140, y-100);
		g.drawString("Score:"+myPlane.getScore(), x-140, y-30);
	}
	
	
	//写一个方法，画游戏开始状态
	public void paintGameStart(Graphics g){
		int x = jframeWidth/2; // x坐标
		int y = jframeHeight/2; // y坐标
		Font font = new Font(Font.SANS_SERIF, Font.BOLD, 50); // 字体
		g.setColor(new Color(0x666666));
		g.setFont(font); // 设置字体
		g.drawImage(background, 0, 0, null);
		g.drawString("Game Start", x-140, y-100);
		g.drawImage(hero0,
				(MainClass.jframeWidth/2)-(MainClass.hero0.getWidth()/2),
				(MainClass.jframeHeight/2)-(MainClass.hero0.getHeight()/2),
				null);
	}

	
	
	//重写JPanel里的paint方法
		public void paint(Graphics g){
			//在这里检测gameState的值，如果为1，则画游戏开始状态
			if(gameState==1){
				paintGameStart(g);
			}
			//如果gameState值为2，则游戏进行状态，画background背景图
			//如果值为3，画pause背景图
			//如果值为4，画gameover背景图
			//还有在这些状态下画其他要显示的东西
			if(gameState==2){
				g.drawImage(background, 0, 0, null);
				//调用paintScoreAndLife方法，画英雄机的命值和得分
				paintScoreAndLife(g);
				//调用paintHeroPlane()方法，画英雄机
				paintHeroPlane(g);
				//调用paintBullets方法，画子弹
				paintBullets(g);
				//调用paintEaPlane方法，画敌机
				paintEaPlane(g);
				//调用paintBee方法，画蜜蜂
				paintBee(g);
				//调用paintRstar方法，画红星
				paintRstar(g);
				//调用paintGstar方法，画灰星
				paintGstar(g);
				//调用paintJDLogo方法，画京东logo
				paintJDLogo(g);
				//调用paintTBLogo方法，画淘宝logo
				paintTBLogo(g);
			}
			if(gameState==4){
				paintGameover(g);
			}
		}
		
		
	//写一个方法，用于删除没有用的子弹对象、飞机对象
	//哪些子弹对象和飞机对象需要及时删除掉呢？
	//飞出窗口外的子弹、飞出窗口外的敌机
	//打中敌机的子弹、被子弹打中的敌机
	public void deleteBulletsAndEaPlanes(){
		//当飞机飞出窗口外时，删掉飞机对象
		for(int i0=0;i0<eaPlanes.length;i0++){
			if(eaPlanes[i0].getY()>jframeHeight){
				eaPlanes[i0]=eaPlanes[eaPlanes.length-1];
				eaPlanes=Arrays.copyOf(eaPlanes, eaPlanes.length-1);
			}
		}
		//当子弹飞出窗口外时，删掉子弹对象
		for(int i0=0;i0<bullets.length;i0++){
			if(bullets[i0].getY()<0){
				bullets[i0]=bullets[bullets.length-1];
				bullets=Arrays.copyOf(bullets, bullets.length-1);
			}
		}
		//下面是飞机和子弹互碰互删的算法
		//打掉一架敌机英雄机得10分
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
	
	
	//写一个方法，用于删除没有用的子弹对象、蜜蜂对象
	//哪些子弹对象和蜜蜂对象需要及时删除掉呢？
	//飞出窗口外的蜜蜂
	//打中蜜蜂的子弹、被子弹打中的蜜蜂
	//蜜蜂对象需要中弹2发才会被删除
	public void deleteBees(){
		//当蜜蜂飞出窗口外时，删掉蜜蜂对象
		for(int i0=0;i0<bees.length;i0++){
			if(bees[i0].getY()>jframeHeight){
				bees[i0]=bees[bees.length-1];
				bees=Arrays.copyOf(bees, bees.length-1);
			}
		}	
		//下面是蜜蜂和子弹互碰互删的算法
		//打掉一个蜜蜂英雄机得40分
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
	
	
	//写一个方法，用于删除没有用的京东logo对象
	//哪些京东logo对象需要及时删除掉呢？
	//飞出窗口外的logo
	//打中logo的子弹、被子弹打中的logo
	//京东logo对象需要中弹4发才会被删除
	public void deleteJDLogo(){
		//当logo飞出窗口外时，删掉logo对象
		for(int i0=0;i0<jdlogo.length;i0++){
			if(jdlogo[i0].getY()>jframeHeight){
				jdlogo[i0]=jdlogo[jdlogo.length-1];
				jdlogo=Arrays.copyOf(jdlogo, jdlogo.length-1);
			}
		}	
		//下面是logo和子弹互碰互删的算法
		//打掉一个京东logo英雄机得80分
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
	
	
	//写一个方法，用于删除没有用的淘宝logo对象
	//哪些淘宝logo对象需要及时删除掉呢？
	//飞出窗口外的logo
	//打中logo的子弹、被子弹打中的logo
	//淘宝logo对象需要中弹4发才会被删除
	public void deleteTBLogo(){
		//当logo飞出窗口外时，删掉logo对象
		for(int i0=0;i0<tblogo.length;i0++){
			if(tblogo[i0].getY()>jframeHeight){
				tblogo[i0]=tblogo[tblogo.length-1];
				tblogo=Arrays.copyOf(tblogo, tblogo.length-1);
			}
		}	
		//下面是logo和子弹互碰互删的算法
		//打掉一个淘宝logo英雄机得80分
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
	
	
	//写一个方法，检测各个对象是否撞到英雄机，如果撞到，就减命或奖励
	public void hitHeroPlane(){
		//检测敌机是否与英雄机相撞
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
		//检测英雄机是否与蜜蜂相撞
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
		//检测京东logo是否与英雄机相撞
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
		//检测淘宝logo是否与英雄机相撞
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
		//检测红星是否与英雄机相撞
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
		//检测灰星是否与英雄机相撞
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
	
	
	//写一个方法，用来检测英雄机的命值，采取相应措施
	public void checkHeroLife(){
		if(myPlane.getHeroLife()<=0){
			myPlane.setHeroLife(0);
			gameState=4;
		}
	}
	
	
	//写一个本类的构造方法
	public MainClass(){	
		//初始化定时器
		timer=new Timer();
		//给定时器添加任务
		timer.schedule(new TimerTask(){
			//重写TimerTask里面的run()方法 
			@Override
			public void run() {
				if(gameState==1){
					repaint();
				}
				if(gameState==2){
					//调用newEaPlane方法，产生敌机
					newEaPlane();
					//调用newBee方法，产生蜜蜂
					newBee();
					//调用newRedStar方法，产生红星
					newRedStar();
					//调用newGreyStar方法，产生灰星
					newGreyStar();
					//调用newJDLogo方法，产生京东logo
					newJDLogo();
					//调用newTBLogo方法，产生淘宝logo
					newTBLogo();
					//调用deleteBulletsAndEaPlanes方法，及时删掉没用的子弹和敌机
					deleteBulletsAndEaPlanes();
					//调用deleteBees方法，删除没用的蜜蜂对象
					deleteBees();
					//调用deleteJDLogo方法，删除没用的京东logo对象
					deleteJDLogo();
					//调用deleteTBLogo方法，删除没用的淘宝logo对象
					deleteTBLogo();
					//写一个方法，检测各个对象与敌机是否相撞
					hitHeroPlane();
					//检测英雄机的命值是否小于等于0
					checkHeroLife();
					//通过JPanel的repaint()方法重新调用Paint()方法，刷新页面
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
		//在MainClass对象一创建时就启动定时器
		//因为英雄机在JFrame窗口里需要随着鼠标的移动而运动
		//因此呢，需要在JPanel里注册鼠标移动事件监听器MouseMotionListener
		//用这个监听器来获取事件源产生的事件对象
		//再通过事件对象获取需要的鼠标信息
		//再通过信息去控制游戏的某些实现
		//如果你基础好，那就能看得明白下面的代码
		this.addMouseMotionListener(new MouseMotionAdapter(){
			public void mouseMoved(MouseEvent e) {
				//当游戏不是正在进行状态时，英雄机不随鼠标的移动而移动
				//当游戏正在进行状态时，英雄机随鼠标移动
				if(gameState==2){
					//因为英雄机要随着鼠标的移动而移动
					//所以通过鼠标事件对象获得的鼠标坐标值赋给英雄机坐标
					//而且还得调整为使英雄机的中点随鼠标移动
					myPlane.setX(e.getX()-myPlane.getImage().getWidth()/2);
					myPlane.setY(e.getY()-myPlane.getImage().getHeight()/2);
				}else{
					return;
				}
			}
		});
		//除了加鼠标移动事件监听器，还要加鼠标事件监听器
		//比如我一点击鼠标左键，飞机就打出一个子弹！
		//鼠标移出JFrame窗口时暂停
		//鼠标移入JFrame窗口时取消暂停
		this.addMouseListener(new MouseAdapter(){
			//当鼠标左键单击时产生一个子弹
			//当鼠标右键单击时结束游戏
			@Override
			public void mouseClicked(MouseEvent e) {
				//单击鼠标左键
				if(e.getButton()==MouseEvent.BUTTON1){
					//判断gameState值，如果值为1，则变为2（进入游戏状态）
					if(gameState==1){
						gameState=2;
					}
					if(gameState==2){
						Bullet bullet=new Bullet();
						//初始化子弹的坐标，使子弹开始从英雄机的头部(发射)
						bullet.setX(myPlane.getX()+myPlane.getImage().getWidth()/2-2);
						bullet.setY(myPlane.getY());
						//把 bullets数组扩容，装入新产生的子弹
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
				//单击鼠标右键
				if(e.getButton()==MouseEvent.BUTTON3){
					System.out.println("本次得分"+myPlane.getScore()+"分");
					System.exit(0);
				}
			}
			//当鼠标进入JFrame窗口时取消暂停
			@Override
			public void mouseEntered(MouseEvent e) {
				if(gameState==3){
					gameState=2;
				}
			}
			//当鼠标移出JFrame窗口时游戏暂停
			@Override
			public void mouseExited(MouseEvent e) {
				if(gameState==2){
					gameState=3;
				}
			}
		});
	}
	
	
	//写一个main方法，作为程序执行的入口
	public static void main(String[] args){
		JFrame frame=new JFrame();
		frame.setTitle("老练设计的飞机大战");
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