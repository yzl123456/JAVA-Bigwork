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

		
	    // �ѱ���������first panel���
		this.add(northPanel, BorderLayout.NORTH);
//		this.add(southPanel, BorderLayout.SOUTH);
		this.add(centerPanel, BorderLayout.CENTER);
		this.setVisible(true);
	}	
	public void buildNorthPanel(){
		// �����������
		northPanel = new JPanel();
		
		// ���ñ������Ĵ�С
		northPanel.setPreferredSize(new Dimension(200, 150));
		
		// ���ñ�������������ҵı߾�
		northPanel.setBorder(BorderFactory.createEmptyBorder(40, 0, 0, 0));
		
		// ���ñ�������弰��С
		title = new JLabel("ע��ҳ��", SwingConstants.CENTER);
		title.setFont(new Font("����", Font.BOLD, 28));
		
		// �ѱ������������
		northPanel.add(title);
	}
	public void buildCenterPanel(){
		centerPanel=new JPanel(new GridLayout(4, 2,10,50));
		jLabel1=new JLabel("�������û���    :");
		jLabel2=new JLabel("����������       :");
		jLabel3=new JLabel("�ٴ�����������:");
		jTextField1=new JTextField(10);
		jTextField2=new JPasswordField(10);
		jTextField3=new JPasswordField(10);
		centerPanel.add(jLabel1);
		centerPanel.add(jTextField1);
		centerPanel.add(jLabel2);
		centerPanel.add(jTextField2);
		centerPanel.add(jLabel3);
		centerPanel.add(jTextField3);
		
		jButton=new JButton("ȷ��");
		jButton.addActionListener(new ButtonActionListener());
//		centerPanel.add(new JLabel(""));
		centerPanel.add(jButton);
		
	}
	//��ť�¼�����
	public class ButtonActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(jTextField2.getText().equals(jTextField3.getText())){
				User user=new User();
				user.setUsername(jTextField1.getText());
				userService.saveAndFlush(user);
				
				String password=jTextField2.getText();
				int loop=user.getId();//�õ���ӦID
				while(loop--!=0){//����MD5 ����IDȷ������
					password=MD5.stringMD5(password);
				}
				user.setMD5String(password);
				
				Global.setId(user.getId());//���ö�ӦUser����Ϊȫ��
				
				String randomNumber=GetRandomString.getRandomString(16);
				String secretKey=GoogleValidate.createCredentials(randomNumber);
				user.setSecretkey(secretKey);//���ùȸ������֤����Կ
				userService.saveAndFlush(user);
				
				JOptionPane.showMessageDialog(jTextField2, "ע��ɹ�");
				new QRCodePanel();//������ά��
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
				JOptionPane.showMessageDialog(jButton, "�������벻ƥ��");
			}
		}
		
	}
	

	public static void main(String[] args) {
		new NewUserWindow();
	}
	
}
