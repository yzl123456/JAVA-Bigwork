package bigWork;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import bigWork.global.Global;
import bigWork.utils.Base32;
import bigWork.utils.GoogleValidate;

public class TotpPanel extends JFrame{
	JLabel jLabel;
	JTextField jTextField;
	JButton jButton;
	JPanel jPanel;
	//谷歌身份验证器的窗口
	public TotpPanel() {
		setSize(300, 200);
		build();
		add(jPanel);
		setVisible(true);
	}
	public void build(){
		jPanel=new JPanel();
		jLabel=new JLabel("");
		jPanel.add(jLabel);
		jPanel.add(new JLabel("请输入动态密码:"));
		jTextField=new JTextField(10);
		jButton=new JButton("确定");jButton.addActionListener(new ButtonActionListener());
		jPanel.add(jTextField);
		jPanel.add(jButton);
	}
	//按钮的监听
	public class ButtonActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String secretKey=Global.getUser().getSecretkey();
			try {
				//利用谷歌提供的算法传入密钥生成6位动态密码
				String res = String.format("%06d",GoogleValidate.calculateCode(Base32.decode(secretKey), new Date().getTime()/30000));
				System.out.println(res);
				if(jTextField.getText().equals(res)){//观察服务端动态密码是否与APP端匹配
					dispose();
					new MainFrame();
				}
				else{
					JOptionPane.showMessageDialog(jTextField, "动态密码错误!");
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
				
			
		}
		
	}
	public static void main(String[] args) {
		new TotpPanel();
	}
}
