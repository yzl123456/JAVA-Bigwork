package bigWork.user.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="user")
public class User {//Userʵ���࣬����Spring data + JPA ��ܣ�ע��˴�Userʵ��û����������
	int id;
	String username;
//	String password;
	//���ü��ι�ϣ��hash���� MD5�����ε���ֵΪ��Ӧ�û���ID
	String MD5String;
	String secretkey;
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	
	public String getMD5String() {
		return MD5String;
	}
	public void setMD5String(String mD5String) {
		MD5String = mD5String;
	}
	public String getSecretkey() {
		return secretkey;
	}
	public void setSecretkey(String secretkey) {
		this.secretkey = secretkey;
	}
	
}
