package program.util.security;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SealedObject;
import javax.crypto.spec.SecretKeySpec;

public class Encryption 
{
	private final static String key = "8DV47AME86F1CLD6"; // 128 bit key 16Char
	private static final String transformation = "AES";
	
	
	public static byte[] encryptString(String text)
	{
        byte[] nes = "".getBytes();
        
    	try
    	{
    		Key aesKey = new SecretKeySpec(key.getBytes(), transformation);
    		Cipher cipher = Cipher.getInstance(transformation);
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
			Key aesKey = new SecretKeySpec(key.getBytes(), transformation);
			Cipher cipher = Cipher.getInstance(transformation);
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
	
	public static void writeEncryptedSerializedObject(Serializable object, String fileLocation)  
	{
	    try 
	    {
	    	OutputStream ostream = new FileOutputStream(fileLocation);
	    	
	        SecretKeySpec sks = new SecretKeySpec(key.getBytes(), transformation);

	        // Create cipher
	        Cipher cipher = Cipher.getInstance(transformation);
	        cipher.init(Cipher.ENCRYPT_MODE, sks);
	        SealedObject sealedObject = new SealedObject(object, cipher);

	        // Wrap the output stream
	        CipherOutputStream cos = new CipherOutputStream(ostream, cipher);
	        ObjectOutputStream outputStream = new ObjectOutputStream(cos);
	        outputStream.writeObject(sealedObject);
	        outputStream.close();
	    } 
	    
	    catch (IllegalBlockSizeException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IOException e) 
	    {
	        e.printStackTrace();
	    }
	}
	
	public static Object readDecryptedSerializedArray (String fileLocation) 
	{
	    try 
	    {
	    	InputStream istream = new FileInputStream(fileLocation);
	    	
	    	SecretKeySpec sks = new SecretKeySpec(key.getBytes(), transformation);
		    Cipher cipher = Cipher.getInstance(transformation);
		    cipher.init(Cipher.DECRYPT_MODE, sks);

		    CipherInputStream cipherInputStream = new CipherInputStream(istream, cipher);
		    ObjectInputStream inputStream = new ObjectInputStream(cipherInputStream);
		    SealedObject sealedObject;
	    	
	        sealedObject = (SealedObject) inputStream.readObject();
	        return sealedObject.getObject(cipher);
	    } 
	    
	    catch (ClassNotFoundException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IOException | IllegalBlockSizeException | BadPaddingException e) 
	    {
	        e.printStackTrace();
	        return null;
	    }
	}
}
