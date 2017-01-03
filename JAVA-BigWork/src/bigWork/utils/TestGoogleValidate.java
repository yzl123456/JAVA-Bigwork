package bigWork.utils;

import java.util.Date;
//这个用来作测试的 忽略。。。
public class TestGoogleValidate {
	public static void main(String[] args) throws Exception {
		/*
		 * 1.服务器端生成一个随机数，利用base32对此随机数编码便得到密钥，同时保存在服务端
		 * 2.用户验证时，服务端通过对该用户的密钥解码得到最初生成该用户密钥的随机数，将此随机数和时间作为参数
		 * 	  通过谷歌的算法得到6位验证码
		 * 3.验证服务端密码是否与APP端一致
		 */
		//由于是demo，我直接把服务端的随机数写死，为了验证服务端生成的动态密码是否与app端一致，可自行修改，唯一的随机数对应唯一的密钥
		String randomNumber="1234987612349876";//随机数
		//传入服务器端的randomNumber参数，通过base 32生成的密钥，就是二维码url里面的密钥
		String pngs_key=GoogleValidate.createCredentials(randomNumber);
		//result是服务端根据保存的密钥进行一种算法运算得到的动态密码，也就是服务端的动态密码
		int result=GoogleValidate.calculateCode(Base32.decode(pngs_key), new Date().getTime()/30000);
		//在APP端输入生成的密钥代替扫码环节，验证app端的动态密码是否与程序输出的服务器端的动态密码一致
		System.out.printf("%06d",result);
		
	}
}
