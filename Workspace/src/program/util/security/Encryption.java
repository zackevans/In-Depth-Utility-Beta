package program.util.security;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Encryption 
{
	private final static String key = "8DV47AME86F1CLD6"; // 128 bit key 16Char
	
	public static byte[] encryptString(String text)
	{
        byte[] nes = "".getBytes();
        
    	try
    	{
    		Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
    		Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, aesKey);
            byte[] encrypted = cipher.doFinal(text.getBytes());
            return encrypted;
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    		return nes;
    	}
	}
	
	public static String decryptString(byte[] s)
	{
		try 
		{
			Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.DECRYPT_MODE, aesKey);
	        String decrypted = new String(cipher.doFinal(s));
	        return decrypted;
		}
		 
		catch(Exception e)
		{
			e.printStackTrace();
			return s.toString();
		}
	}
}
