package bigWork.user.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="user")
public class User {//User实体类，利用Spring data + JPA 框架，注意此处User实体没有密码属性
	int id;
	String username;
//	String password;
	//利用加盐哈希，hash函数 MD5，加盐的盐值为对应用户的ID
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
