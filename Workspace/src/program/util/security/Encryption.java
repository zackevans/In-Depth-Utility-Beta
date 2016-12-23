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

/**
 * Class: Encryption
 * @author ZackEvans
 *
 * This class holds methods that encrypt and decrypt data.
 */

public class Encryption 
{
	private final static String key = "8DV47AME86F1CLD6"; // 128 bit key 16Char
	private static final String transformation = "AES";
	
	/**
	 * Function: encryptString(String text)
	 * @param text
	 * @return encrypted byte array
	 * 
	 * This function takes in a string of text and returns a encrypted byte array.
	 */
	
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
	
	/**
	 * Function: decryptString(byte[] s)
	 * @param s
	 * @return decrypted string
	 * 
	 * This function takes in a byte array and returns a decrypted string value.
	 */
	
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
	
	/**
	 * Function: writeEncryptedSerializedObject(Serializable object, String fileLocation)
	 * @param object
	 * @param fileLocation
	 * 
	 * This function outputs a serialized object to a file
	 */
	
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
	
	/**
	 * Function: readDecryptedSerializedArray (String fileLocation) 
	 * @param fileLocation
	 * @return the object in the file.
	 * 
	 * This function takes in a file location and returns the serialized object in the file.
	 */
	
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
