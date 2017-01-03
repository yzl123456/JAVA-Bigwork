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
	//������
	public LoginWindow() {
		this.setTitle("ϵͳ��¼");
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
	//��ʾJLabel
	public void buildHintPanel(){
		hintPanel=new JPanel();
		hintLabel=new JLabel("");
		hintPanel.add(hintLabel);
	}
	//�����jpanel
	public void buildInputPanel() {
		inputUserPanel=new JPanel();
		inputPasswordPanel=new JPanel();
		
		userLabel=new JLabel("�û���",SwingConstants.CENTER);
		passwordLabel=new JLabel("��   ��",SwingConstants.CENTER);
	
		userText=new JTextField(15);
		passwordText=new JPasswordField(15);
		inputUserPanel.add(userLabel);
		inputUserPanel.add(userText);
		inputPasswordPanel.add(passwordLabel);
		inputPasswordPanel.add(passwordText);
	
	}
	//�ύ��Ӧ��jpanel
	public void buildSubmitPanel(){
		submitPanel=new JPanel();
		okButton=new JButton("ȷ��");
		cancelButton=new JButton("ע��");
		okButton.addActionListener(new ButtonAction());
		cancelButton.addActionListener(new ButtonAction());		
		submitPanel.add(okButton);
		submitPanel.add(cancelButton);
		
	}
	//��ť����Ӧ
	public class ButtonAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==okButton){
				String name=userText.getText();
				String password=passwordText.getText();
				User user=userService.getUserByName(name);//�����ݿ�õ���Ӧѧ��
				int loop=user.getId();//�õ�IDȷ�����ι�ϣ�Ĵ���
				while(loop--!=0){//����hash
					password=MD5.stringMD5(password);
				}
			
				//ƥ������MD5
				if(password.equals(user.getMD5String())){
					Global.setUser(user);
					new TotpPanel();
					dispose();
//					new MainFrame();
				}
				else{//��ʾ������Ϣ
					passwordText.setText("");
					hintLabel.setText("�û������������!");
					hintLabel.setFont(new Font("����",Font.BOLD,14));
					hintLabel.setForeground(Color.RED);
				}
			}
			else{
				new NewUserWindow();
			}
				
		}
		
	}
	
}
