package bigWork.utils;

import java.util.Date;
//������������Ե� ���ԡ�����
public class TestGoogleValidate {
	public static void main(String[] args) throws Exception {
		/*
		 * 1.������������һ�������������base32�Դ�����������õ���Կ��ͬʱ�����ڷ����
		 * 2.�û���֤ʱ�������ͨ���Ը��û�����Կ����õ�������ɸ��û���Կ��������������������ʱ����Ϊ����
		 * 	  ͨ���ȸ���㷨�õ�6λ��֤��
		 * 3.��֤����������Ƿ���APP��һ��
		 */
		//������demo����ֱ�Ӱѷ���˵������д����Ϊ����֤��������ɵĶ�̬�����Ƿ���app��һ�£��������޸ģ�Ψһ���������ӦΨһ����Կ
		String randomNumber="1234987612349876";//�����
		//����������˵�randomNumber������ͨ��base 32���ɵ���Կ�����Ƕ�ά��url�������Կ
		String pngs_key=GoogleValidate.createCredentials(randomNumber);
		//result�Ƿ���˸��ݱ������Կ����һ���㷨����õ��Ķ�̬���룬Ҳ���Ƿ���˵Ķ�̬����
		int result=GoogleValidate.calculateCode(Base32.decode(pngs_key), new Date().getTime()/30000);
		//��APP���������ɵ���Կ����ɨ�뻷�ڣ���֤app�˵Ķ�̬�����Ƿ����������ķ������˵Ķ�̬����һ��
		System.out.printf("%06d",result);
		
	}
}
