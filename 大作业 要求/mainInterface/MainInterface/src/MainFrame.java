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
		this.setTitle("同一区域显示不同面板");

		// set size for the form
		this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);

		// set layout for the frame
		this.setLayout(new BorderLayout(10, 10));

		// specify the operation for the close button
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// build navigator panel
		buildNavigatorPanel();

		// build first panel and second panel 
		// 把要显示的两个面板对象都建立起来
		buildOtherPanels();

		// add the created panels to the frame
		this.add(navigatorPanel, BorderLayout.WEST);
		
		// 把要显示的两个面板对象到放到相同区域，在窗体中显示的为最后放入的面板。
		// 这样做的目的是把两个对象都和该窗体关联起来。只有关联起来，之后应用外观风格（例如Motif）才会作用于这两个面板对象。
		this.add(secondPanel, BorderLayout.CENTER);
		this.add(firstPanel, BorderLayout.CENTER);

		// show the window
		this.setVisible(true);

	}

	
	public void buildNavigatorPanel() {
		// create a panel for navigator labels
		navigatorPanel = new JPanel();

		// 设置边框来控制外观以及上下左右边距
		Border insideBorder = BorderFactory.createEmptyBorder(20, 0, 0, 0);
		Border outsideBorder = BorderFactory.createLoweredBevelBorder();
		navigatorPanel.setBorder(BorderFactory.createCompoundBorder(
				outsideBorder, insideBorder));

		// set the size for the navigator panel
		navigatorPanel.setPreferredSize(new Dimension(100, 500));

		// create label objects for navigator
		firstLabel = new JLabel("First Panel", SwingConstants.CENTER);
		firstLabel.setPreferredSize(new Dimension(100, 30));
		firstLabel.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 当鼠标移到标签上时显示手型图标

		secondLabel = new JLabel("Second Panel", SwingConstants.CENTER);
		secondLabel.setPreferredSize(new Dimension(100, 30));
		secondLabel.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 当鼠标移到标签上时显示手型图标

		// add the labels to the panel
		navigatorPanel.add(firstLabel);
		navigatorPanel.add(secondLabel);

		// register action listener for the navigator labels
		firstLabel.addMouseListener(new LabelClick());
		secondLabel.addMouseListener(new LabelClick());
	}

	public void buildOtherPanels() {
		// 下面创建两个面板对象，它们将会显示在同一个区域，但同一时刻只有一个可见
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
				// 把要显示的面板放入指定区域
				if (!firstPanel.isVisible()) {
					MainFrame.this.add(firstPanel, BorderLayout.CENTER);
					firstPanel.setVisible(true);
				}
				// 原先显示的面板设置为不可见
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
