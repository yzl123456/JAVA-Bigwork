package bigWork.global;

import bigWork.user.entity.User;

public class Global {
	//此类存放全局变量
	static User user;
	static int Id;
	static String secretKey;
	static int rowNumber;
	static String error;
	static String subject;
	public static void setSubject(String subject) {
		Global.subject = subject;
	}
	public static String getSubject() {
		return subject;
	}
	public static void setError(String error) {
		Global.error = error;
	}
	public static String getError() {
		return error;
	}
	public static void setRowNumber(int rowNumber) {
		Global.rowNumber = rowNumber;
	}
	public static int getRowNumber() {
		return rowNumber;
	}
	public static void setSecretKey(String secretKey) {
		Global.secretKey = secretKey;
	}
	public static String getSecretKey() {
		return secretKey;
	}
	public static void setId(int id) {
		Id = id;
	}
	public static int getId() {
		return Id;
	}
	public static void setUser(User user) {
		Global.user = user;
	}
	public static User getUser() {
		return user;
	}
}
