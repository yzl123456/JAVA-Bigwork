package bigWork;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import bigWork.global.Global;
import bigWork.user.entity.User;
import bigWork.user.service.UserService;
import bigWork.utils.CtxUtils;
import bigWork.utils.MD5;

public class LoginWindow extends JFrame{
	final int WINDOW_WIDTH = 500;
	final int WINDOW_HEIGHT =240;
	JLabel userLabel;
	JTextField userText;
	JLabel passwordLabel;
	JTextField passwordText;
	JButton okButton;
	JButton cancelButton;
	
	JPanel inputUserPanel;
	JPanel inputPasswordPanel;
	JPanel submitPanel;
	JPanel hintPanel;
	
	JLabel hintLabel;
	
	UserService userService=CtxUtils.getUserService();
	//构造器
	public LoginWindow() {
		this.setTitle("系统登录");
		this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		this.setLayout(new GridLayout(4, 1,5,5));
	
		buildHintPanel();
		buildInputPanel();
		buildSubmitPanel();
		
		add(hintPanel);
		add(inputUserPanel);
		add(inputPasswordPanel);
		add(submitPanel);
		this.setVisible(true);
	}
	//提示JLabel
	public void buildHintPanel(){
		hintPanel=new JPanel();
		hintLabel=new JLabel("");
		hintPanel.add(hintLabel);
	}
	//输入的jpanel
	public void buildInputPanel() {
		inputUserPanel=new JPanel();
		inputPasswordPanel=new JPanel();
		
		userLabel=new JLabel("用户名",SwingConstants.CENTER);
		passwordLabel=new JLabel("密   码",SwingConstants.CENTER);
	
		userText=new JTextField(15);
		passwordText=new JPasswordField(15);
		inputUserPanel.add(userLabel);
		inputUserPanel.add(userText);
		inputPasswordPanel.add(passwordLabel);
		inputPasswordPanel.add(passwordText);
	
	}
	//提交对应的jpanel
	public void buildSubmitPanel(){
		submitPanel=new JPanel();
		okButton=new JButton("确定");
		cancelButton=new JButton("注册");
		okButton.addActionListener(new ButtonAction());
		cancelButton.addActionListener(new ButtonAction());		
		submitPanel.add(okButton);
		submitPanel.add(cancelButton);
		
	}
	//按钮的响应
	public class ButtonAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==okButton){
				String name=userText.getText();
				String password=passwordText.getText();
				User user=userService.getUserByName(name);//查数据库得到对应学生
				int loop=user.getId();//得到ID确定加盐哈希的次数
				while(loop--!=0){//加盐hash
					password=MD5.stringMD5(password);
				}
			
				//匹配服务端MD5
				if(password.equals(user.getMD5String())){
					Global.setUser(user);
					new TotpPanel();
					dispose();
//					new MainFrame();
				}
				else{//提示错误消息
					passwordText.setText("");
					hintLabel.setText("用户名或密码错误!");
					hintLabel.setFont(new Font("宋体",Font.BOLD,14));
					hintLabel.setForeground(Color.RED);
				}
			}
			else{
				new NewUserWindow();
			}
				
		}
		
	}
	
}
