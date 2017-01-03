package bigWork;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.Border;

import bigWork.utils.EntityManagerUtil;


//������
public class MainFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private int WINDOW_WIDTH = 800;
	private int WINDOW_HEIGHT = 500;
	private JPanel navigatorPanel;
	private SecondPanel secondPanel;
	private FirstPanel firstPanel;
	private ThirdPanel thirdPanel;
	private JLabel firstLabel, secondLabel,thirdLabel;
	
	//�������
	private JMenuBar menuBar;
	private JMenu menu1, menu2,menu3;
	private JMenuItem exitItem;
	private JMenuItem totalStu;
	private JMenuItem ave;
	private JMenuItem max;
	private JMenuItem min;
	
	JMenu eachScore;
	JRadioButton radioButton1;
	JRadioButton radioButton2;
	JRadioButton radioButton3;
	
	public MainFrame() {
		// set title
		this.setTitle("�༶��Ϣ����");

		// set size for the form
		this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);

		// set layout for the frame
		this.setLayout(new BorderLayout(10, 10));

		// specify the operation for the close button
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		buildMenuBar();
		setJMenuBar(menuBar);
		
		// build navigator panel
		buildNavigatorPanel();

		// build first panel and second panel 
		// ��Ҫ��ʾ�����������󶼽�������
		buildOtherPanels();

		// add the created panels to the frame
		this.add(navigatorPanel, BorderLayout.WEST);
		
		// ��Ҫ��ʾ�����������󵽷ŵ���ͬ�����ڴ�������ʾ��Ϊ���������塣
		// ��������Ŀ���ǰ��������󶼺͸ô������������ֻ�й���������֮��Ӧ����۷������Motif���Ż�������������������
		this.add(thirdPanel,BorderLayout.CENTER);
		this.add(secondPanel, BorderLayout.CENTER);
		this.add(firstPanel, BorderLayout.CENTER);

		// show the window
		this.setVisible(true);

	}

	
	public void buildNavigatorPanel() {
		// create a panel for navigator labels
		navigatorPanel = new JPanel();

		// ���ñ߿�����������Լ��������ұ߾�
		Border insideBorder = BorderFactory.createEmptyBorder(20, 0, 0, 0);
		Border outsideBorder = BorderFactory.createLoweredBevelBorder();
		navigatorPanel.setBorder(BorderFactory.createCompoundBorder(
				outsideBorder, insideBorder));

		// set the size for the navigator panel
		navigatorPanel.setPreferredSize(new Dimension(100, 500));

		// create label objects for navigator
		firstLabel = new JLabel("�����¼", SwingConstants.CENTER);
		firstLabel.setForeground(Color.RED);
		firstLabel.setPreferredSize(new Dimension(100, 30));
		firstLabel.setCursor(new Cursor(Cursor.HAND_CURSOR)); // ������Ƶ���ǩ��ʱ��ʾ����ͼ��

		secondLabel = new JLabel("��ѯ��¼", SwingConstants.CENTER);
		secondLabel.setPreferredSize(new Dimension(100, 30));
		secondLabel.setCursor(new Cursor(Cursor.HAND_CURSOR)); // ������Ƶ���ǩ��ʱ��ʾ����ͼ��
		
		thirdLabel = new JLabel("��������", SwingConstants.CENTER);
		thirdLabel.setPreferredSize(new Dimension(100, 30));
		thirdLabel.setCursor(new Cursor(Cursor.HAND_CURSOR)); // ������Ƶ���ǩ��ʱ��ʾ����ͼ��

		// add the labels to the panel
		navigatorPanel.add(firstLabel);
		navigatorPanel.add(secondLabel);
		navigatorPanel.add(thirdLabel);

		// register action listener for the navigator labels
		firstLabel.addMouseListener(new LabelClick());
		secondLabel.addMouseListener(new LabelClick());
		thirdLabel.addMouseListener(new LabelClick());
	}

	public void buildOtherPanels() {
		// ���洴���������������ǽ�����ʾ��ͬһ�����򣬵�ͬһʱ��ֻ��һ���ɼ�
		thirdPanel=new ThirdPanel();
		thirdPanel.setVisible(false);
		
		secondPanel = new SecondPanel();
		secondPanel.setVisible(false);
		
		firstPanel = new FirstPanel();
		firstPanel.setVisible(true);
		
		
		
	}

	public class LabelClick extends MouseAdapter {
		//@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getSource() == firstLabel) {
				// the label clicked is set to red, other labels are black
				firstLabel.setForeground(Color.red);
				secondLabel.setForeground(Color.black);
				thirdLabel.setForeground(Color.black);
				// show the first panel
				// ��Ҫ��ʾ��������ָ������
				if (!firstPanel.isVisible()) {
					MainFrame.this.add(firstPanel, BorderLayout.CENTER);
					firstPanel.setVisible(true);
				}
				// ԭ����ʾ���������Ϊ���ɼ�
				secondPanel.setVisible(false);
				thirdPanel.setVisible(false);
			} else if (e.getSource() == secondLabel) {
				// the label clicked is set to red, other labels are black
				firstLabel.setForeground(Color.black);
				secondLabel.setForeground(Color.red);
				thirdLabel.setForeground(Color.black);
				// show the second panel
				if (!secondPanel.isVisible()) {
					MainFrame.this.add(secondPanel, BorderLayout.CENTER);secondPanel.updateFromDB();
					secondPanel.setVisible(true);
				}
				firstPanel.setVisible(false);
				thirdPanel.setVisible(false);
			}else if (e.getSource() == thirdLabel) {
				// the label clicked is set to red, other labels are black
				firstLabel.setForeground(Color.black);
				secondLabel.setForeground(Color.black);
				thirdLabel.setForeground(Color.red);
				// show the second panel
				if (!thirdPanel.isVisible()) {
					MainFrame.this.add(thirdPanel, BorderLayout.CENTER);
					thirdPanel.setVisible(true);
				}
				firstPanel.setVisible(false);
				secondPanel.setVisible(false);
			}
		}
	}
	
	public void buildMenuBar() {
		// create a menu bar
		menuBar = new JMenuBar();
		
		// create a File menu
		buildMenu1();
		buildMenu2();
		buildMenu3();
		
		
		// add the file menu and text menu to the menu bar
		menuBar.add(menu1);
		menuBar.add(menu2);
		menuBar.add(menu3);
	}

	public void buildMenu1() {
		// create file menu
		menu1 = new JMenu("�ļ�");
		
		// create exit menu item
		exitItem = new JMenuItem("�˳�ϵͳ");
		exitItem.addActionListener(new ExitAction());
		// add the exit menu item to the file menu
		menu1.add(exitItem);	
		
		// add register action listener for exitItem
	}

	public void buildMenu2() {
		// create text menu
		menu2 = new JMenu("ͳ��");
		eachScore=new JMenu("���Ƴɼ�");
		
		// create the black, red, and blue menu items
		totalStu = new JMenuItem("ѧ��������");
		totalStu.addActionListener(new ButtonActionListener());
		ave = new JMenuItem("���ſγ�ƽ����");
		max = new JMenuItem("���ſγ���߷�");
		min = new JMenuItem("���ſγ���ͷ�");
		ave.addActionListener(new ButtonActionListener());
		max.addActionListener(new ButtonActionListener());
		min.addActionListener(new ButtonActionListener());
		
		
		
		eachScore.add(ave);
		eachScore.add(max);
		eachScore.add(min);
		
		// put the menu items in the text menu
		menu2.add(eachScore);
		menu2.addSeparator();//���÷ָ���
		menu2.add(totalStu);
	}

	public void buildMenu3() {
		// create text menu
		menu3 = new JMenu("ϵͳ���");
		//style��radiobutton
		radioButton1=new JRadioButton("Metal���");radioButton1.doClick();
		radioButton2=new JRadioButton("Motif���");
		radioButton3=new JRadioButton("Windows���");
		ButtonGroup buttonGroup=new ButtonGroup();
		buttonGroup.add(radioButton1);
		buttonGroup.add(radioButton2);
		buttonGroup.add(radioButton3);
		
		menu3.add(radioButton1);
		menu3.add(radioButton2);
		menu3.add(radioButton3);
		//��ť����¼�
		radioButton1.addActionListener(new switchStyle());
		radioButton2.addActionListener(new switchStyle());
		radioButton3.addActionListener(new switchStyle());
		
	}
	//����Ӧ���¼�
	public class switchStyle  implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==radioButton1){//�ж���Դ
				try {
					UIManager.setLookAndFeel(
						    "javax.swing.plaf.metal.MetalLookAndFeel");
					updateAll();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			
			}
			else if(e.getSource()==radioButton2){
				try {
					UIManager.setLookAndFeel(
							"com.sun.java.swing.plaf.motif.MotifLookAndFeel");
					updateAll();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			else if(e.getSource()==radioButton3){
				try {
					UIManager.setLookAndFeel(
						    "com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
					updateAll();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			
			
		}

		
		
			
		
		
	}
	
	public void updateAll(){
		SwingUtilities.updateComponentTreeUI(this);
	}
	//�˳����¼�
	public class ExitAction implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}
	//ͳ�Ʋ˵������¼���Ӧ
	public class ButtonActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			EntityManager entityManager=EntityManagerUtil.getEntityManager();
			if(e.getSource()==totalStu){//����ѧ��
				Query query=entityManager.createNativeQuery("select count(*) from studentinfo");
				List result = query.getResultList();
				Object total=(Object)result.get(0);
				String totalNumber=total+"";				
				JOptionPane.showMessageDialog(totalStu, "ѧ������:   "+totalNumber , "ѧ������", JOptionPane.INFORMATION_MESSAGE);
			}
			else if(e.getSource()==max){//����
				Query query=entityManager.createNativeQuery("select max(Chinese),max(math),max(english) from studentinfo");
				List result = query.getResultList();
				Object[] scores=(Object[])result.get(0);
				String chinese=scores[0]+"";
				String math= scores[1]+"";
				String english= scores[2]+"";
				System.out.println(chinese+" "+math+" "+english);
				JOptionPane.showMessageDialog(max, "����:  "+chinese+"\n��ѧ  "+math+"\nӢ��  "+english, "������߷�", JOptionPane.INFORMATION_MESSAGE);
			}
			else if(e.getSource()==min){//��С��
				Query query=entityManager.createNativeQuery("select min(Chinese),min(math),min(english) from studentinfo");
				List result = query.getResultList();
				Object[] scores=(Object[])result.get(0);
				String chinese=scores[0]+"";
				String math= scores[1]+"";
				String english= scores[2]+"";
				System.out.println(chinese+" "+math+" "+english);
				JOptionPane.showMessageDialog(min, "����:  "+chinese+"\n��ѧ  "+math+"\nӢ��  "+english, "������ͷ�", JOptionPane.INFORMATION_MESSAGE);
			}
			else if(e.getSource()==ave){//ƽ��
				Query query=entityManager.createNativeQuery("select avg(Chinese),avg(math),avg(english) from studentinfo");
				List result = query.getResultList();
				Object[] scores=(Object[])result.get(0);
				//2λ
				DecimalFormat df=new DecimalFormat("0.##");
				
				String chinese=df.format(Double.parseDouble(scores[0]+""));
				String math=df.format(Double.parseDouble(scores[1]+""));
				String english=df.format(Double.parseDouble(scores[2]+""));
				System.out.println(chinese+" "+math+" "+english);
				JOptionPane.showMessageDialog(ave, "����:  "+chinese+"\n��ѧ  "+math+"\nӢ��  "+english, "����ƽ����", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		
	}
	
	
	
	public static void main(String[] args) {
		new MainFrame();
	}

}
