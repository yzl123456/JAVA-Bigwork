package bigWork.stu.entity;



import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
@Entity
@Table(name="studentinfo")
//此为实体类，利用了spring data 加JPA框架
public class Student {
	private int Id;
	
	private String stuId;
	private String name;
	private String gender;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date bornDate;
	private int chinese;
	private int math;
	private int english;
	
	
	public Student(String stuId, String name, String gender, Date bornDate, int chinese, int math, int english) {
		super();
		this.stuId = stuId;
		this.name = name;
		this.gender = gender;
		this.bornDate = bornDate;
		this.chinese = chinese;
		this.math = math;
		this.english = english;
	}
	public Student() {
		// TODO Auto-generated constructor stub
	}
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	public int getId() {
		return Id;
	}
	public void setId(int iD) {
		Id = iD;
	}
	@Column(name="stuid")
	public String getStuId() {
		return stuId;
	}
	public void setStuId(String stuId) {
		this.stuId = stuId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	@Temporal(TemporalType.DATE)
	@Column(name="borndate")
	public Date getBornDate() {
		return bornDate;
	}
	public void setBornDate(Date bornDate) {
		this.bornDate = bornDate;
	}
	public int getChinese() {
		return chinese;
	}
	public void setChinese(int chinese) {
		this.chinese = chinese;
	}
	public int getMath() {
		return math;
	}
	public void setMath(int math) {
		this.math = math;
	}
	public int getEnglish() {
		return english;
	}
	public void setEnglish(int english) {
		this.english = english;
	}
	@Override
	public String toString() {
		return "Student [ID=" + Id + ", stuId=" + stuId + ", name=" + name + ", gender=" + gender + ", bornDate="
				+ bornDate + ", chinese=" + chinese + ", math=" + math + ", english=" + english + "]";
	}
	
	
}
