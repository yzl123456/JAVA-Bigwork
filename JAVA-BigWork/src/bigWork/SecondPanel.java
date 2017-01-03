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

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import bigWork.stu.entity.Student;
import bigWork.stu.service.StudentService;
import bigWork.utils.CtxUtils;
import bigWork.utils.EntityManagerUtil;


//@SuppressWarnings("serial")
public class SecondPanel extends JPanel{
	private JLabel title;
	private JPanel northPanel;
	JPanel southPanel;
	JLabel southLabel;
	//若干组件
	JTable table = null;
    DefaultTableModel defaultModel = null;
	

    StudentService service=CtxUtils.getStudentService();

    String[] n = { "学号", "姓名","性别", "出生日期", "语文","数学","英语" };
    String[] and={"并且","或"};
    String[] eql={"大于","等于","小于"};
    JComboBox[][] jcb=new JComboBox[7][3];  //下拉框  
    JTextField[] textFields=new JTextField[7];
    String[] condition={"学号","姓名","性别","出生日期","语文","数学","英语"};
    JPanel northContent;
    JButton queryButton;
    List<Student>students;
    Object[][] res=new Object[100][10];
    
    //构造器
	public SecondPanel(){
		res=getFromDB();

		buildNorthPanel();
		buildSouthPanel();
		
	    // 把标题面板加入first panel面板
		this.add(northPanel, BorderLayout.NORTH);
		this.add(southPanel, BorderLayout.SOUTH);
	}
	//构造北边面板
	public void buildNorthPanel(){
    	northPanel=new JPanel(new BorderLayout());
    	
    	JLabel titleLabel=new JLabel("输入查询条件",SwingConstants.CENTER);
    	titleLabel.setFont(new Font("宋体", Font.BOLD, 28));
    	northPanel.add(titleLabel,BorderLayout.NORTH);
    	
    	northContent=new JPanel(new GridLayout(4,2,5,5));
    	for(int i=0;i<7;i++){//二维数组构造jcb
    		jcb[i][0]=new JComboBox(and);
    		jcb[i][1]=new JComboBox(n);
    		jcb[i][2]=new JComboBox(eql);    		
    	}
    	for(int i=0;i<7;i++){
    		textFields[i]=new JTextField(10);
    	}
    	
    	
    	JPanel[] panels=new JPanel[8];
    	for(int i=0;i<8;i++)
    		panels[i]=new JPanel();
    	
    	for(int i=0;i<7;i++){
    		for(int j=0;j<3;j++){
    			if(i==0&&j==0) {
    				panels[i].add(new JLabel("                  "));
    				continue;
    			}
    			panels[i].add(jcb[i][j]);
    		}
    		panels[i].add(textFields[i]);
    	}
    	
    	queryButton=new JButton("查询");
    	queryButton.addActionListener(new ButtonActionListener());
    	panels[7].add(new JLabel("                                                                                     "));
    	panels[7].add(queryButton);
    	
    	for(int i=0;i<8;i++){
    		northContent.add(panels[i]);
    	}
    	
    	
		northPanel.add(northContent,BorderLayout.SOUTH);
		
    }
	//构造南部面板
	public void buildSouthPanel(){
		southPanel=new JPanel();
		defaultModel = new DefaultTableModel();
        defaultModel.setDataVector(res, n);
        table = new JTable(defaultModel);
        table.setPreferredScrollableViewportSize(new Dimension(650, 180));
        JScrollPane s = new JScrollPane(table);
        southPanel.add(s,BorderLayout.SOUTH);
	}
	//按钮监听
	public class ButtonActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String sql=getSql();//得到对应拼接的sql语句
			if(sql.equals("select * from studentinfo s where ")){
//				getFromDB();
				updateFromDB();
				return ;
			}
//			System.out.println(sql);
			executeSql(sql);//执行sql
			
			
		}
		
	}
	//从数据库得到数据
	Object[][] getFromDB(){
		students=service.getAll();
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
//		defaultModel.setDataVector(res, n);
		return res;
	}
	//手工拼接sql语句
	String getSql(){
		StringBuilder sql=new StringBuilder("select * from studentinfo s where ");//最初的sql
		boolean flag=false;
		for(int i=0;i<7;i++){
			if(textFields[i].getText()==null||textFields[i].getText().trim().equals("")){
				continue;
			}
			for(int j=0;j<3;j++){
				if(j==0){
					if(flag){
						if(jcb[i][j].getSelectedItem().equals("并且")){
							sql.append(" and");
						}
						else if(jcb[i][j].getSelectedItem().equals("或")){
							sql.append(" or");
						}
					}
					else{
						flag=true;
					}
				}
				else if(j==1){
					if(jcb[i][j].getSelectedItem().equals("学号")){
						sql.append(" s.stuId");
					}
					else if(jcb[i][j].getSelectedItem().equals("姓名")){
						sql.append(" s.name");
					}
					else if(jcb[i][j].getSelectedItem().equals("性别")){
						sql.append(" s.gender");
					}
					else if(jcb[i][j].getSelectedItem().equals("出生日期")){
						sql.append(" s.bornDate");
					}
					else if(jcb[i][j].getSelectedItem().equals("语文")){
						sql.append(" s.chinese");
					}
					else if(jcb[i][j].getSelectedItem().equals("数学")){
						sql.append(" s.math");
					}
					else if(jcb[i][j].getSelectedItem().equals("英语")){
						sql.append(" s.english");
					}
				}
				else if(j==2){
					if(jcb[i][j].getSelectedItem().equals("大于")){
						sql.append(" >");
					}
					else if(jcb[i][j].getSelectedItem().equals("等于")){
						sql.append(" =");
					}
					else if(jcb[i][j].getSelectedItem().equals("小于")){
						sql.append(" <");
					}
				}
			}
			if(textFields[i].getText()!=null&&!textFields[i].getText().trim().equals("")){
				if(jcb[i][1].getSelectedItem().equals("姓名")||jcb[i][1].getSelectedItem().equals("性别")||jcb[i][1].getSelectedItem().equals("出生日期")){
					sql.append("\""+textFields[i].getText()+"\"");continue;
				}
				sql.append(textFields[i].getText());
			}
		}
		
		
		
		return sql.toString();
	}
	//执行sql
	public void executeSql(String sql){
		EntityManager entityManager=EntityManagerUtil.getEntityManager();//得到entityManager
		System.out.println(entityManager);
		Query query=EntityManagerUtil.getEntityManager().createNativeQuery(sql);
		List result = query.getResultList();
//		System.out.println(result);
		res=new Object[result.size()][10];
		for(int i=0;i<result.size();i++){
			Object[] objects=(Object[]) result.get(i);
			//跳过ID j=1
			for(int j=1;j<objects.length;j++){
				if(j==4){
					res[i][j-1]=parseDate(objects[j]);continue;
				}
				res[i][j-1]=objects[j];
			}

		}
		defaultModel.setDataVector(res, n);//更新jtable
	}
	public String parseDate(Object object) {//解析日期
		try {
			SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
			return dateFormat.format(dateFormat.parse(object+""));
		} catch (Exception e) {
			
		}
		return null;
	}
	public void updateFromDB(){//更新jtable
		res=getFromDB();
		defaultModel.setDataVector(res, n);
	}
}
