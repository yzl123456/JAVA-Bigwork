package bigWork.utils;



import java.util.Random;
//�õ�16λ�����
public class GetRandomString {
	private static String string = "123456789";   
	 
	public static String getRandomString(int length){
	    StringBuffer sb = new StringBuffer();
	    Random random=new Random();
	    
	    int len = string.length();
	    for (int i = 0; i < length; i++) {
	        sb.append(string.charAt(random.nextInt(8)));
	    }
	    return sb.toString();
	}
}
