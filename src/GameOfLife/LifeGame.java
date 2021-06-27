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
		JMenu options =new JMenu("ѡ��");
		menu.add(options);
		JMenu changeSpeed=new JMenu("�غ��ٶ�");
		menu.add(changeSpeed);
		JMenu other = new JMenu("����");
		menu.add(other);
		JMenuItem start=options.add("��ʼ");
		start.addActionListener(frame.new StartActionListener());
		JMenuItem random=options.add("���");
		random.addActionListener(frame.new RandomActionListener());
		JMenuItem stop=options.add("ֹͣ");
		stop.addActionListener(frame.new StopActionListener());
		JMenuItem pause=options.add("��ͣ");
		pause.addActionListener(frame.new PauseActionListener());
		JMenuItem doityourself=options.add("���");
		doityourself.addActionListener(frame.new DIYActionListener());
		JMenuItem clean=options.add("����");
		clean.addActionListener(frame.new CleanActionListener());
		JMenuItem slow=changeSpeed.add("��");
		slow.addActionListener(frame.new SlowActionListener());
		JMenuItem fast=changeSpeed.add("��");
		fast.addActionListener(frame.new FastActionListener());
		JMenuItem hyper=changeSpeed.add("��");
		hyper.addActionListener(frame.new HyperActionListener());
		JMenuItem help=other.add("����");
		help.addActionListener(frame.new HelpActionListener());
		JMenuItem about=other.add("����");
		about.addActionListener(frame.new AboutActionListener());
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);int cx,cy;
		cx=Variable.xnum*Variable.length+14;
		cy=Variable.ynum*Variable.length+50+12;
		frame.setSize(cx,cy);
		frame.setLocationRelativeTo(null);
		frame.setTitle("������Ϸ");
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
			JOptionPane.showMessageDialog(null, "����������Ϸ!!!\n������Ϸ��Ӣ����ѧ��Լ�����ζ١�������1970�귢����ϸ���Զ���\n "+"1�����һ��ϸ����Χ��3��ϸ��Ϊ�������ϸ��Ϊ��;\n"
												+"2�� ���һ��ϸ����Χ��2��ϸ��Ϊ�������ϸ��������״̬���ֲ���;\n"
												+"3�� ����������£���ϸ��Ϊ����");
		}
	}
	class AboutActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			JOptionPane.showMessageDialog(null, "��Ϸ���ߣ�����ʴ�����");
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

