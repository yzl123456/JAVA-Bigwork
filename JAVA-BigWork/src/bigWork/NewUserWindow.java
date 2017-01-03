package bigWork;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import bigWork.global.Global;
import bigWork.user.entity.User;
import bigWork.user.service.UserService;
import bigWork.utils.CtxUtils;
import bigWork.utils.GetRandomString;
import bigWork.utils.GoogleValidate;
import bigWork.utils.MD5;
import bigWork.utils.QRCodeUtil;

public class NewUserWindow extends JFrame{
	private JLabel title;
	private JPanel northPanel;
	JPanel centerPanel;
	JPanel southPanel;
	JLabel jLabel1;
	JLabel jLabel2;
	JLabel jLabel3;
	JTextField jTextField1;
	JTextField jTextField2;
	JTextField jTextField3;
	JButton jButton;
	
	UserService userService=CtxUtils.getUserService();
	public NewUserWindow(){
		this.setSize(500, 500);
		buildNorthPanel();
		buildCenterPanel();

		
	    // 把标题面板加入first panel面板
		this.add(northPanel, BorderLayout.NORTH);
//		this.add(southPanel, BorderLayout.SOUTH);
		this.add(centerPanel, BorderLayout.CENTER);
		this.setVisible(true);
	}	
	public void buildNorthPanel(){
		// 创建标题面板
		northPanel = new JPanel();
		
		// 设置标题面板的大小
		northPanel.setPreferredSize(new Dimension(200, 150));
		
		// 设置标题面板上下左右的边距
		northPanel.setBorder(BorderFactory.createEmptyBorder(40, 0, 0, 0));
		
		// 设置标题的字体及大小
		title = new JLabel("注册页面", SwingConstants.CENTER);
		title.setFont(new Font("宋体", Font.BOLD, 28));
		
		// 把标题加入标题面板
		northPanel.add(title);
	}
	public void buildCenterPanel(){
		centerPanel=new JPanel(new GridLayout(4, 2,10,50));
		jLabel1=new JLabel("请输入用户名    :");
		jLabel2=new JLabel("请输入密码       :");
		jLabel3=new JLabel("再次输入新密码:");
		jTextField1=new JTextField(10);
		jTextField2=new JPasswordField(10);
		jTextField3=new JPasswordField(10);
		centerPanel.add(jLabel1);
		centerPanel.add(jTextField1);
		centerPanel.add(jLabel2);
		centerPanel.add(jTextField2);
		centerPanel.add(jLabel3);
		centerPanel.add(jTextField3);
		
		jButton=new JButton("确定");
		jButton.addActionListener(new ButtonActionListener());
//		centerPanel.add(new JLabel(""));
		centerPanel.add(jButton);
		
	}
	//按钮事件监听
	public class ButtonActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(jTextField2.getText().equals(jTextField3.getText())){
				User user=new User();
				user.setUsername(jTextField1.getText());
				userService.saveAndFlush(user);
				
				String password=jTextField2.getText();
				int loop=user.getId();//得到对应ID
				while(loop--!=0){//加盐MD5 根据ID确定次数
					password=MD5.stringMD5(password);
				}
				user.setMD5String(password);
				
				Global.setId(user.getId());//设置对应User对象为全局
				
				String randomNumber=GetRandomString.getRandomString(16);
				String secretKey=GoogleValidate.createCredentials(randomNumber);
				user.setSecretkey(secretKey);//设置谷歌身份验证器密钥
				userService.saveAndFlush(user);
				
				JOptionPane.showMessageDialog(jTextField2, "注册成功");
				new QRCodePanel();//弹出二维码
				String url="otpauth://totp/Google%3A"+user.getUsername()+"@gmail.com?secret="+secretKey+"&issuer=Google";
				try {
					QRCodeUtil.encode(url, "QRCode/qq.png", "QRCode", true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dispose();
			}
			else{
				JOptionPane.showMessageDialog(jButton, "两次密码不匹配");
			}
		}
		
	}
	

	public static void main(String[] args) {
		new NewUserWindow();
	}
	
}
