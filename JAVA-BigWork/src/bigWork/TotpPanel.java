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
	//�ȸ������֤���Ĵ���
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
		jPanel.add(new JLabel("�����붯̬����:"));
		jTextField=new JTextField(10);
		jButton=new JButton("ȷ��");jButton.addActionListener(new ButtonActionListener());
		jPanel.add(jTextField);
		jPanel.add(jButton);
	}
	//��ť�ļ���
	public class ButtonActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String secretKey=Global.getUser().getSecretkey();
			try {
				//���ùȸ��ṩ���㷨������Կ����6λ��̬����
				String res = String.format("%06d",GoogleValidate.calculateCode(Base32.decode(secretKey), new Date().getTime()/30000));
				System.out.println(res);
				if(jTextField.getText().equals(res)){//�۲����˶�̬�����Ƿ���APP��ƥ��
					dispose();
					new MainFrame();
				}
				else{
					JOptionPane.showMessageDialog(jTextField, "��̬�������!");
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
