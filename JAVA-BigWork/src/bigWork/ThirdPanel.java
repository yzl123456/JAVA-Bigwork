package bigWork;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
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
import bigWork.utils.MD5;


//@SuppressWarnings("serial")
public class ThirdPanel extends JPanel{
	//��Ӧ���
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
	//������
	public ThirdPanel(){
		buildNorthPanel();
		buildCenterPanel();
		buildSouthPanel();
		
	    // �ѱ���������first panel���
		this.add(northPanel, BorderLayout.NORTH);
//		this.add(southPanel, BorderLayout.SOUTH);
		this.add(centerPanel, BorderLayout.CENTER);
	}	
	public void buildNorthPanel(){
		// �����������
		northPanel = new JPanel();
		
		// ���ñ������Ĵ�С
		northPanel.setPreferredSize(new Dimension(600, 140));
		
		// ���ñ�������������ҵı߾�
		northPanel.setBorder(BorderFactory.createEmptyBorder(40, 0, 0, 0));
		
		// ���ñ�������弰��С
		title = new JLabel("�޸�����", SwingConstants.CENTER);
		title.setFont(new Font("����", Font.BOLD, 28));
		
		// �ѱ������������
		northPanel.add(title);
	}
	//�����������
	public void buildCenterPanel(){
		centerPanel=new JPanel(new GridLayout(4, 2,10,50));
		jLabel1=new JLabel("�����������:  ");
		jLabel2=new JLabel("������������:  ");
		jLabel3=new JLabel("�ٴ�����������:");
		jTextField1=new JPasswordField(10);
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
	public void buildSouthPanel(){
//		southPanel=new JPanel();
//		jButton=new JButton("ȷ��");
//		southPanel.add(jButton);
	}
	//��ť��Ӧ
	public class ButtonActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			User user=Global.getUser();//�õ�ȫ�ֵ�User����
			String oldPassword=jTextField1.getText();
			int loop=user.getId();
			while(loop--!=0){
				oldPassword=MD5.stringMD5(oldPassword);
			}
			//����MD5 ȷ���Ƿ������������ȷ
			if(!oldPassword.equals(user.getMD5String())){
				JOptionPane.showMessageDialog(jButton, "�������������");
				jTextField1.setText("");
				return ;
			}
			//������2��ƥ��
			if(jTextField2.getText().equals(jTextField3.getText())){
				String newPassword=jTextField2.getText();
				int cycle=user.getId();
				while(cycle--!=0){
					newPassword=MD5.stringMD5(newPassword);
				}
				
				user.setMD5String(newPassword);
				userService.saveAndFlush(user);
				JOptionPane.showMessageDialog(jButton, "�����޸ĳɹ�");
				jTextField1.setText("");
				jTextField2.setText("");
				jTextField3.setText("");
			}
			else{
				JOptionPane.showMessageDialog(jButton, "�����������벻һ��");
			}
			
			
		}
	}
	
}
