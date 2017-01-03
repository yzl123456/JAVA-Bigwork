package bigWork.utils;



import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
/*
 * �ȸ��ṩ���㷨
 * ���潲���ҵĴ������:
 * 1.�û�ע��ɹ����һ�ε�¼ʱ��ϵͳ���Զ�����һ��16λ�����ֵ��ַ��� seed�����ڷ����
 * 2.Ȼ���������ͨ���ȸ��ṩ���㷨createCredentials��seed�� ������һ��secretKey ��Կ�ַ����������Կ�ַ������Ƕ�ά��URL���������Ĳ���
 *   ����ֱ���ڹȸ�APP��ͨ���ֶ�������Կ������ɨ�뻷��
 * 3.�ڹȸ�APP����������Կ�����6λ���Ķ�̬����
 * 4.��������ͨ��һ��ʼΪ�û����ɵĶ��ص��ַ���seed ����Base32����  calculateCode(seed.getBytes(), new Date().getTime()/30000)
 * 	 ����һ��result��Ϊ����˵���֤��
 * 5.�������ȸ�APP����֤��һ������֤�ɹ�
 */
//�ȸ������֤����
public class GoogleValidate {
	private static final String HMAC_HASH_FUNCTION = "HmacSHA1";

	
	/**
	 * ��Ϊ�ȸ��ṩ���㷨���ƺ��õ���hashʲôţ�ƵĶ���
	 * 
	 * @param key keyΪ��������һ��ʼΪ�û����ɵ�����seed
	 * @param tm  ��ǰ��ʱ��  new Date().getTime()/30000 ����30000 ��ʾ30sһ��
	 * @return  ���ط������˼�������Ķ�̬����
	 */
	public static int calculateCode(byte[] key, long tm)
    {
        // Allocating an array of bytes to represent the specified instant
        // of time.
        byte[] data = new byte[8];
        long value = tm;

        // Converting the instant of time from the long representation to a
        // big-endian array of bytes (RFC4226, 5.2. Description).
        for (int i = 8; i-- > 0; value >>>= 8)
        {
            data[i] = (byte) value;
        }

        // Building the secret key specification for the HmacSHA1 algorithm.
        SecretKeySpec signKey = new SecretKeySpec(key, HMAC_HASH_FUNCTION);

        try
        {
            // Getting an HmacSHA1 algorithm implementation from the JCE.
            Mac mac = Mac.getInstance(HMAC_HASH_FUNCTION);

            // Initializing the MAC algorithm.
            mac.init(signKey);

            // Processing the instant of time and getting the encrypted data.
            byte[] hash = mac.doFinal(data);

            // Building the validation code performing dynamic truncation
            // (RFC4226, 5.3. Generating an HOTP value)
            int offset = hash[hash.length - 1] & 0xF;

            // We are using a long because Java hasn't got an unsigned integer type
            // and we need 32 unsigned bits).
            long truncatedHash = 0;

            for (int i = 0; i < 4; ++i)
            {
                truncatedHash <<= 8;

                // Java bytes are signed but we need an unsigned integer:
                // cleaning off all but the LSB.
                truncatedHash |= (hash[offset + i] & 0xFF);
            }

            // Clean bits higher than the 32nd (inclusive) and calculate the
            // module with the maximum validation code value.
            truncatedHash &= 0x7FFFFFFF;
            truncatedHash %= 1000000;

            // Returning the validation code to the caller.
            return (int) truncatedHash;
        }
        catch (NoSuchAlgorithmException | InvalidKeyException ex)
        {
            // Logging the exception.
//	            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);

            // We're not disclosing internal error details to our clients.
//	            throw new GoogleAuthenticatorException("The operation cannot be "
//	                    + "performed now.");
        }
		return 0;
    }
	
	 public static  String createCredentials(String seed){
		   String sharedKey= new Base32().encode(seed.getBytes());
		   System.out.println("-->"+sharedKey);
		   return sharedKey;
	 }
	
	
	
	/*
	 * main�ǵ�ʱ�������Եģ�������д�����Ĳ��ԣ�ע������Ψһ ���ɵ�secretKey��ԿҲ��Ψһ��
	 */
	public static void main(String[] args) {
		String seed="1234abcd1234abcd";//��ĸ���ֻ����������ǿ��Ե�  ������û�Թ��� ����������
//		String seed="abcdefghijkl"; ����ĸ������10+��ûһ�γɹ���  �����ֵ�һ������
		String pngs_key=createCredentials(seed);
		int result=calculateCode(seed.getBytes(), new Date().getTime()/30000);
		System.out.printf("%06d",result);
//		printf("%6d\n",result);
	}
	
	/*
	 * ���ط����������ɵĶ�̬����
	 */
	public static String getValidateCode(String secretKey) throws Exception{
		int result=calculateCode(Base32.decode(secretKey), new Date().getTime()/30000);
		String code = String.format("%06d", result);
		return code;
	}
	
}
