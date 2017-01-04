import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.Border;



public class MainFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private int WINDOW_WIDTH = 1000;
	private int WINDOW_HEIGHT = 600;
	private JPanel navigatorPanel;
	private SecondPanel secondPanel;
	private FirstPanel firstPanel;
	private JLabel firstLabel, secondLabel;

	public MainFrame() {
		// set title
		this.setTitle("ͬһ������ʾ��ͬ���");

		// set size for the form
		this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);

		// set layout for the frame
		this.setLayout(new BorderLayout(10, 10));

		// specify the operation for the close button
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// build navigator panel
		buildNavigatorPanel();

		// build first panel and second panel 
		// ��Ҫ��ʾ�����������󶼽�������
		buildOtherPanels();

		// add the created panels to the frame
		this.add(navigatorPanel, BorderLayout.WEST);
		
		// ��Ҫ��ʾ�����������󵽷ŵ���ͬ�����ڴ�������ʾ��Ϊ���������塣
		// ��������Ŀ���ǰ��������󶼺͸ô������������ֻ�й���������֮��Ӧ����۷������Motif���Ż�������������������
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
		firstLabel = new JLabel("First Panel", SwingConstants.CENTER);
		firstLabel.setPreferredSize(new Dimension(100, 30));
		firstLabel.setCursor(new Cursor(Cursor.HAND_CURSOR)); // ������Ƶ���ǩ��ʱ��ʾ����ͼ��

		secondLabel = new JLabel("Second Panel", SwingConstants.CENTER);
		secondLabel.setPreferredSize(new Dimension(100, 30));
		secondLabel.setCursor(new Cursor(Cursor.HAND_CURSOR)); // ������Ƶ���ǩ��ʱ��ʾ����ͼ��

		// add the labels to the panel
		navigatorPanel.add(firstLabel);
		navigatorPanel.add(secondLabel);

		// register action listener for the navigator labels
		firstLabel.addMouseListener(new LabelClick());
		secondLabel.addMouseListener(new LabelClick());
	}

	public void buildOtherPanels() {
		// ���洴���������������ǽ�����ʾ��ͬһ�����򣬵�ͬһʱ��ֻ��һ���ɼ�
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
				// show the first panel
				// ��Ҫ��ʾ��������ָ������
				if (!firstPanel.isVisible()) {
					MainFrame.this.add(firstPanel, BorderLayout.CENTER);
					firstPanel.setVisible(true);
				}
				// ԭ����ʾ���������Ϊ���ɼ�
				secondPanel.setVisible(false);
			} else if (e.getSource() == secondLabel) {
				// the label clicked is set to red, other labels are black
				secondLabel.setForeground(Color.red);
				firstLabel.setForeground(Color.black);
				// show the second panel
				if (!secondPanel.isVisible()) {
					MainFrame.this.add(secondPanel, BorderLayout.CENTER);
					secondPanel.setVisible(true);
				}
				firstPanel.setVisible(false);
			}
		}
	}

	public static void main(String[] args) {
		new MainFrame();
	}

}
