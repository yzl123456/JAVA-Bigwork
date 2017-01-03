package bigWork;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import bigWork.global.Global;
import bigWork.stu.entity.Student;
import bigWork.stu.service.StudentService;
import bigWork.utils.CtxUtils;

//@SuppressWarnings("serial")
public class FirstPanel extends JPanel{
	private JLabel title;
	private JPanel titlePanel;
	JPanel buttonPanel;
	JPanel contentPanel;
	//��Ӧ�����
	JLabel stuIdLabel;
	JTextField stuIdField;
	JLabel nameLabel;
	JTextField nameField;
	JLabel sexLabel;
	JTextField sexField;
	JLabel birthLabel;
	JTextField birthField;
	JLabel chineseLabel;
	JTextField chineseField;
	JLabel mathLabel;
	JTextField mathField;
	JLabel englishLabel;
	JTextField englishField;
	
	
	JButton addButton;
	JButton updateButton;
	JButton deleteButton;
	
    String[] n = { "ѧ��", "����","�Ա�", "��������", "����","��ѧ","Ӣ��" };
    JTable table = null;
    DefaultTableModel defaultModel = null;
    //service��Ķ���
    StudentService service=CtxUtils.getStudentService();
    //ѧ������
    List<Student>students;
    //������һ��ҳ��
	public FirstPanel(){
		this.setLayout(new BorderLayout());
		
		buildTitlePanel();
		bulidContentPanel();
		bulidButtonPanel();
		
	    // �ѱ���������first panel���
		this.add(titlePanel, BorderLayout.NORTH);
		this.add(contentPanel, BorderLayout.CENTER);
		this.add(buttonPanel, BorderLayout.SOUTH);
	}	
	public void buildTitlePanel(){
		// �����������
		titlePanel = new JPanel();
		
		// ���ñ������Ĵ�С
		titlePanel.setPreferredSize(new Dimension(600, 70));
		
		// ���ñ�������������ҵı߾�
		titlePanel.setBorder(BorderFactory.createEmptyBorder(40, 0, 0, 0));
		
		// ���ñ�������弰��С
		title = new JLabel("ѧ����Ϣ", SwingConstants.CENTER);
		title.setFont(new Font("����", Font.BOLD, 14));
		// �ѱ������������
		titlePanel.add(title);	
	}
	//�����������
	public void bulidContentPanel(){
		
		contentPanel=new JPanel();
		defaultModel = new DefaultTableModel();
		Object[][] res=getFromDB();
	    defaultModel.setDataVector(res, n);
	    table = new JTable(defaultModel);
	    //��ѡ
	    table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    table.getSelectionModel().addListSelectionListener(new SelectionListener());
	    table.setPreferredScrollableViewportSize(new Dimension(600, 200));  
	    JScrollPane s = new JScrollPane(table);
	    contentPanel.add(s,BorderLayout.CENTER);
		 
	        
	}
	//������ť���
	public void bulidButtonPanel(){
		buttonPanel=new JPanel(new GridLayout(2, 1));
		JPanel jPanel1=new JPanel();
		JPanel jPanel2=new JPanel();
		//�½���ť���
		addButton=new JButton("���");
		addButton.addActionListener(new ButtonActionListener());
		updateButton=new JButton("�޸�");
		updateButton.addActionListener(new ButtonActionListener());
		deleteButton=new JButton("ɾ��");
		deleteButton.addActionListener(new ButtonActionListener());
		
		stuIdLabel=new JLabel("ѧ��:");
		stuIdField=new JTextField(5);
		nameLabel=new JLabel("����:");
		nameField=new JTextField(5);
		sexLabel=new JLabel("�Ա�:");
		sexField=new JTextField(5);
		birthLabel=new JLabel("��������:");
		birthField=new JTextField(5);
		chineseLabel=new JLabel("����:");
		chineseField=new JTextField(3);
		mathLabel=new JLabel("��ѧ:");
		mathField=new JTextField(3);
		englishLabel=new JLabel("Ӣ��:");
		englishField=new JTextField(3);
		
		//�������ӵ�Jpanel����
		jPanel1.add(stuIdLabel);
		jPanel1.add(stuIdField);
		jPanel1.add(nameLabel);
		jPanel1.add(nameField);
		jPanel1.add(sexLabel);
		jPanel1.add(sexField);
		jPanel1.add(birthLabel);
		jPanel1.add(birthField);
		jPanel1.add(chineseLabel);
		jPanel1.add(chineseField);
		jPanel1.add(mathLabel);
		jPanel1.add(mathField);
		jPanel1.add(englishLabel);
		jPanel1.add(englishField);
		
		jPanel2.add(addButton);
		jPanel2.add(updateButton);
		jPanel2.add(deleteButton);
		
		buttonPanel.add(jPanel1);
		buttonPanel.add(jPanel2);
	}
	//��ť���¼�����
	public class ButtonActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==addButton){//ͨ����Դ�ж϶�Ӧ�ĸ���ť
				
				Student student=getNewStudent();
				if(student==null){
					JOptionPane.showMessageDialog(addButton, Global.getError());//����������Ϣ
				}
				else{
					service.saveAndFlush(student);//�����ݿⱣ��������ʵ����
					JOptionPane.showMessageDialog(addButton, "��ӳɹ�");
					defaultModel.setDataVector(getFromDB(), n);//������ʾ��Ϣ
				}
				
			}
			//�����ж϶�ͬ��һ����ťһ��
			else if(e.getSource()==updateButton){
				int index=table.getSelectedRow();
				if(index==-1){
					JOptionPane.showMessageDialog(deleteButton, "��ѡ����");
					return ;
				}
				int id = students.get(index).getId();
				Student cur = service.getById(id);
				Student updatedStu=getUpdateStudent(cur);
				if(updatedStu==null){
					JOptionPane.showMessageDialog(addButton, Global.getError());
				}
				else{
					service.saveAndFlush(updatedStu);
					JOptionPane.showMessageDialog(updateButton, "�޸ĳɹ�");
					defaultModel.setDataVector(getFromDB(), n);
				}
			}
			else if(e.getSource()==deleteButton){
				int index=table.getSelectedRow();
				if(index==-1){
					JOptionPane.showMessageDialog(deleteButton, "��ѡ����");
					return ;
				}
				int id = students.get(index).getId();
				service.delete(id);
				JOptionPane.showMessageDialog(deleteButton, "ɾ���ɹ�");
				defaultModel.setDataVector(getFromDB(), n);
			}
		}
	}
	//�����ݿ��еõ�����
	Object[][] getFromDB(){
		students=service.getAll();Global.setRowNumber(students.size());
		Object[][] res=new Object[students.size()][10];
		for(int i=0;i<students.size();i++){
			res[i][0]=students.get(i).getStuId();
			res[i][1]=students.get(i).getName();
			res[i][2]=students.get(i).getGender();
			res[i][3]=students.get(i).getBornDate();
			res[i][4]=students.get(i).getChinese();
			res[i][5]=students.get(i).getMath();
			res[i][6]=students.get(i).getEnglish();
		}
		return res;//���ؽ��
	}
	
	//���ѧ��
	public Student getNewStudent(){
		String stuIdStr=stuIdField.getText().trim();//ȡ�ı������ݲ�ȥ��ǰ��ո�
		String nameStr=nameField.getText().trim();
		String sexStr=sexField.getText().trim();
		String birthStr=birthField.getText().trim();
		String chineseStr=chineseField.getText().trim();
		String mathStr=mathField.getText().trim();
		String englishStr=englishField.getText().trim();
		if(stuIdStr==null||stuIdStr=="") return null;//��һ��Ϊ�շ���null��ʾ���ʧ��
		if(nameStr==null||nameStr=="") return null;
		if(sexStr==null||sexStr=="") return null;
		if(birthStr==null||birthStr=="") return null;
		if(chineseStr==null||chineseStr=="") return null;
		if(mathStr==null||mathStr=="") return null;
		if(englishStr==null||englishStr=="") return null;
		
		Date birth;
		int chinese,math,english;
		try {//try catch������ӵ��쳣
			SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");dateFormat.setLenient(false);
			birth=dateFormat.parse(birthStr);
			//������ǰʱ��
			if(birth.getTime()>new Date().getTime()) throw new Exception("�������ڳ�����ǰʱ��");
			//����100��
			if(new Date().getTime()-birth.getTime()>1000L*60L*60L*24L*365L*100L) 
				throw new Exception("����100��");
			Global.setSubject("����");//���浱ǰ�Ŀ�Ŀ��Ϣ
			chinese=Integer.parseInt(chineseStr);
			Global.setSubject("��ѧ");
			math=Integer.parseInt(mathStr);
			Global.setSubject("Ӣ��");
			english=Integer.parseInt(englishStr);
			if(chinese>100||chinese<0) 
				throw new Exception("���ĳɼ����벻�Ϸ�");//���Ϸ��׳���Ӧ�쳣
			if(math>100||math<0)
				throw new Exception("��ѧ�ɼ����벻�Ϸ�");
			if(english>100||english<0)
				throw new Exception("Ӣ��ɼ����벻�Ϸ�");
		}catch(ParseException e){//���ڸ�ʽ������
			Global.setError("���ڸ�ʽ�������");
			return null;
		}
		catch (NumberFormatException e) {//int��������
			Global.setError(Global.getSubject()+"�ɼ���ʽ����!");
			return null;
		}
		catch (Exception e) {//�ɼ����벻�Ϸ�
			Global.setError(e.getMessage());
			return null;
		}
		
		Student student=new Student(stuIdStr, nameStr, sexStr, birth, chinese, math, english);
		return student;//����ʵ���ಢ����
	}
	
	//����ѧ����Ϣ
	public Student getUpdateStudent(Student stu){
		String stuIdStr=stuIdField.getText().trim();//���ı����еõ�����ȥ��ǰ��ո�
		String nameStr=nameField.getText().trim();
		String sexStr=sexField.getText().trim();
		String birthStr=birthField.getText().trim();
		String chineseStr=chineseField.getText().trim();
		String mathStr=mathField.getText().trim();
		String englishStr=englishField.getText().trim();
		if(stuIdStr==null||stuIdStr=="") return null;//����һ��Ϊ�շ���NULL
		if(nameStr==null||nameStr=="") return null;
		if(sexStr==null||sexStr=="") return null;
		if(birthStr==null||birthStr=="") return null;
		if(chineseStr==null||chineseStr=="") return null;
		if(mathStr==null||mathStr=="") return null;
		if(englishStr==null||englishStr=="") return null;
		Date birth;
		int chinese,math,english;
		try {//�쳣����ͬ���ѧ��һ��
			SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");dateFormat.setLenient(false);
			birth=dateFormat.parse(birthStr);
			//������ǰʱ��
			if(birth.getTime()>new Date().getTime()) throw new Exception("�������ڳ�����ǰʱ��");
			//����100��
			if(new Date().getTime()-birth.getTime()>1000L*60L*60L*24L*365L*100L) 
				throw new Exception("����100��");
			Global.setSubject("����");
			chinese=Integer.parseInt(chineseStr);
			Global.setSubject("��ѧ");
			math=Integer.parseInt(mathStr);
			Global.setSubject("Ӣ��");
			english=Integer.parseInt(englishStr);
			if(chinese>100||chinese<0) 
				throw new Exception("���ĳɼ����벻�Ϸ�");
			if(math>100||math<0)
				throw new Exception("��ѧ�ɼ����벻�Ϸ�");
			if(english>100||english<0)
				throw new Exception("Ӣ��ɼ����벻�Ϸ�");
		}catch(ParseException e){
			Global.setError("���ڸ�ʽ�������");
			return null;
		}
		catch (NumberFormatException e) {
			Global.setError(Global.getSubject()+"�ɼ���ʽ����!");
			return null;
		}
		catch (Exception e) {
			Global.setError(e.getMessage());
			return null;
		}
		
		stu.setStuId(stuIdStr);
		stu.setName(nameStr);
		stu.setGender(sexStr);
		stu.setBornDate(birth);
		stu.setChinese(chinese);
		stu.setMath(math);
		stu.setEnglish(english);
		return stu;//����ѧ����Ϣ�󷵻�
	}
	//JTable��ѡ������һ�е���Ӧ
	public class SelectionListener implements ListSelectionListener{

		@Override
		public void valueChanged(ListSelectionEvent e) {
			Object[] data=new Object[7];
			System.out.println("---->"+table.getRowCount());
			System.out.println("???"+Global.getRowNumber());
			System.out.println("select "+table.getSelectedRow());
			if(table.getSelectedRow()==-1){//��δѡ���� ���� null���ı������
				stuIdField.setText("");;
				nameField.setText("");
				sexField.setText("");
				birthField.setText("");
				chineseField.setText("");
				mathField.setText("");
				englishField.setText("");
				return ;
			}
			if(table.getSelectedRow()>=Global.getRowNumber()){//����������
				stuIdField.setText("");;
				nameField.setText("");
				sexField.setText("");
				birthField.setText("");
				chineseField.setText("");
				mathField.setText("");
				englishField.setText("");
				return ;
			}
			for(int i=0;i<7;i++){//��jtextfield�����ö�Ӧ�е�����
				data[i]=table.getModel().getValueAt(table.getSelectedRow(), i);			
			}
			stuIdField.setText(data[0]+"");;
			nameField.setText(data[1]+"");
			sexField.setText(data[2]+"");
			birthField.setText(data[3]+"");
			chineseField.setText(data[4]+"");
			mathField.setText(data[5]+"");
			englishField.setText(data[6]+"");
			
		}
		
	}
}
