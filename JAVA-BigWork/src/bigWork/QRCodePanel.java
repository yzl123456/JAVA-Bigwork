package bigWork;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import bigWork.global.Global;

public class QRCodePanel extends JFrame{
	JButton jButton;
	JLabel jLabel;
	JPanel jPanel;
	public QRCodePanel() {
		setSize(310,400);
		//给jpanel添加一个二维码
		jPanel=new JPanel() {
			   protected void paintComponent(Graphics g) {
				    ImageIcon icon = new ImageIcon("QRCode\\"+Global.getId()+".jpg");
				    Image img = icon.getImage();
				    g.drawImage(img, 0, 0, icon.getIconWidth(),
				      icon.getIconHeight(), icon.getImageObserver());
				    setSize(icon.getIconWidth(), icon.getIconHeight());
				   }
				  };
		add(jPanel);
		JPanel buttonJpanel=new JPanel();
		jButton=new JButton("確定");
		jButton.addActionListener(new ButtonActionListener());
		add(jButton,BorderLayout.SOUTH);
		setVisible(true);
	}
	public class ButtonActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
		}
		
	}
	public static void main(String[] args) {
		new QRCodePanel();
	}
}
