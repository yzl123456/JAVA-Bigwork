import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


//@SuppressWarnings("serial")
public class FirstPanel extends JPanel{
	private JLabel title;
	private JPanel titlePanel;
	
	public FirstPanel(){
		// �����������
		titlePanel = new JPanel();
		
		// ���ñ������Ĵ�С
		titlePanel.setPreferredSize(new Dimension(600, 140));
		
		// ���ñ�������������ҵı߾�
		titlePanel.setBorder(BorderFactory.createEmptyBorder(40, 0, 0, 0));
		
		// ���ñ�������弰��С
		title = new JLabel("First Panel", SwingConstants.CENTER);
		title.setFont(new Font("����", Font.BOLD, 28));
		
		// �ѱ������������
		titlePanel.add(title);
		
	    // �ѱ���������first panel���
		this.add(titlePanel, BorderLayout.NORTH);
	}	
}
