package GameOfLife;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class LifeGame extends JFrame implements MouseMotionListener{
	private final World world;
	public int length;
	static JMenu location=new JMenu();
	
	public LifeGame(int rows,int columns)
	{
		world=new World(rows, columns);
		world.setBackground(Color.WHITE);
		new Thread(world).start();
		add(world);
	}
	public static void main(String[]args)
	{
		LifeGame frame=new LifeGame(Variable.ynum,Variable.xnum);
		frame.addMouseMotionListener(frame);
		JMenuBar menu=new JMenuBar();
		frame.setJMenuBar(menu);
		JMenu options =new JMenu("选择");
		menu.add(options);
		JMenu changeSpeed=new JMenu("回合速度");
		menu.add(changeSpeed);
		JMenu other = new JMenu("其它");
		menu.add(other);
		JMenuItem start=options.add("开始");
		start.addActionListener(frame.new StartActionListener());
		JMenuItem random=options.add("随机");
		random.addActionListener(frame.new RandomActionListener());
		JMenuItem stop=options.add("停止");
		stop.addActionListener(frame.new StopActionListener());
		JMenuItem pause=options.add("暂停");
		pause.addActionListener(frame.new PauseActionListener());
		JMenuItem doityourself=options.add("添加");
		doityourself.addActionListener(frame.new DIYActionListener());
		JMenuItem clean=options.add("减少");
		clean.addActionListener(frame.new CleanActionListener());
		JMenuItem slow=changeSpeed.add("慢");
		slow.addActionListener(frame.new SlowActionListener());
		JMenuItem fast=changeSpeed.add("中");
		fast.addActionListener(frame.new FastActionListener());
		JMenuItem hyper=changeSpeed.add("快");
		hyper.addActionListener(frame.new HyperActionListener());
		JMenuItem help=other.add("帮助");
		help.addActionListener(frame.new HelpActionListener());
		JMenuItem about=other.add("关于");
		about.addActionListener(frame.new AboutActionListener());
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);int cx,cy;
		cx=Variable.xnum*Variable.length+14;
		cy=Variable.ynum*Variable.length+50+12;
		frame.setSize(cx,cy);
		frame.setLocationRelativeTo(null);
		frame.setTitle("生命游戏");
		frame.setVisible(true);
		frame.setResizable(false);
	}
	class RandomActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			world.diy=false;
			world.clean=false;
			world.setBackground(Color.WHITE);
			world.setRandom();
		}
	}
	class StartActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			world.setBackground(Color.WHITE);
			world.diy=false;
			world.clean=false;
			world.setShape();
		}
	}
	class StopActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			world.setBackground(Color.WHITE);
			world.diy=false;
			world.clean=false;
			world.setStop();
		}
	}
	class PauseActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			world.setBackground(Color.WHITE);
			world.diy=false;
			world.clean=false;
			world.setPause();
		}
	}
	class SlowActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			world.changeSpeedSlow();
		}
	}
	class FastActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			world.changeSpeedFast();
		}
	}
	class HyperActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			world.changeSpeedHyper();
		}
	}
	class HelpActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			JOptionPane.showMessageDialog(null, "这是生命游戏!!!\n生命游戏是英国数学家约翰·何顿·康威在1970年发明的细胞自动机\n "+"1．如果一个细胞周围有3个细胞为生，则该细胞为生;\n"
												+"2． 如果一个细胞周围有2个细胞为生，则该细胞的生死状态保持不变;\n"
												+"3． 在其它情况下，该细胞为死。");
		}
	}
	class AboutActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			JOptionPane.showMessageDialog(null, "游戏作者：杨帆、甘春根。");
		}
	}
	class CleanActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			world.setPause();
			world.clean=true;
			world.diy=false;
			world.setBackground(Color.orange);
		}
	}
	class DIYActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			world.setPause();
			world.diy=true;
			world.clean=false;
			world.setBackground(Color.cyan);
		}
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		if(world.diy){
		int x=e.getX();
		int y=e.getY();
		World.pauseshape[(y-50)/Variable.length][x/Variable.length]=1;
		world.setDiy();
		}
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		if(world.clean){
		int x=e.getX();
		int y=e.getY();
		World.pauseshape[(y-50)/Variable.length][x/Variable.length]=0;
		world.setDiy();
		}
	}
}

