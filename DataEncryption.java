/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Talent4Assure.WeBlogger.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author Pravin.Shukla
 */
public class DataEncryption 
{
	private static SecretKey aesKey;
    public static String encryptPassword(String password)
    {
        try
        {
            MessageDigest md=null;
            md = MessageDigest.getInstance("MD5");
            byte[] md5hash = new byte[32];
            md.update(password.getBytes("iso-8859-1"), 0, password.length());
            md5hash = md.digest();
            return convertToHex(md5hash);

        }catch(Exception e)
        {

        }
        return null;
    }
     
    
    
    private static String convertToHex(byte[] data) { 
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < data.length; i++) { 
            int halfbyte = (data[i] >>> 4) & 0x0F;
            int two_halfs = 0;
            do { 
                if ((0 <= halfbyte) && (halfbyte <= 9)) 
                    buf.append((char) ('0' + halfbyte));
                else 
                    buf.append((char) ('a' + (halfbyte - 10)));
                halfbyte = data[i] & 0x0F;
            } while(two_halfs++ < 1);
        } 
        return buf.toString().toUpperCase();
    }
    
    private static void loadKey()
    {
    	byte []keybyte = new byte[16];
    	try
    	{
    		InputStream is=DataEncryption.class.getResourceAsStream("/com/ENC_KEY");
    		is.read(keybyte);
    		aesKey = new SecretKeySpec(keybyte, 0, 16, "AES");
    	}catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    	if(aesKey==null)
		{
			throw new RuntimeException("Cann not load encryption key");
		}
    }
    
    public static byte[] encryptData(String data)
    {
    	byte[] encryptedData=null;
    	try
    	{
	    	// Generate key
		    /*KeyGenerator kgen = KeyGenerator.getInstance("AES");
		    kgen.init(128);
		    aesKey = kgen.generateKey();*/
    		if(aesKey==null)
    		{
    			loadKey();
    		}
	
		    // Encrypt cipher
		    Cipher encryptCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		    IvParameterSpec ivParameterSpec = new IvParameterSpec(aesKey.getEncoded());
		    encryptCipher.init(Cipher.ENCRYPT_MODE, aesKey, ivParameterSpec);
	
		    // Encrypt
		    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		    CipherOutputStream cipherOutputStream = new CipherOutputStream(outputStream, encryptCipher);
		    cipherOutputStream.write(data.getBytes("UTF-8"));
		    cipherOutputStream.flush();
		    cipherOutputStream.close();
		    encryptedData= outputStream.toByteArray();
		    encryptedData=Base64.encodeBase64(encryptedData);
    	}catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    	return encryptedData;
    }
    
    public static String decryptData(byte[] encryptedBytes)
    {  
    	String decryptedString=null;
    	//byte[] tempEncryptedBytes=encryptedBytes;
    	byte[] decryptData=null;
    	try
    	{
    		//KeyGenerator kgen = KeyGenerator.getInstance("AES");
 		    //kgen.init(128);
 		    //SecretKey aesKey = kgen.generateKey();
    		if(aesKey==null)
    		{
    			loadKey();
    		}
    		
    		// Decrypt cipher
    	    Cipher decryptCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
    	    IvParameterSpec ivParameterSpec = new IvParameterSpec(aesKey.getEncoded());
    	    decryptCipher.init(Cipher.DECRYPT_MODE, aesKey, ivParameterSpec);

    	    // Decrypt
    	    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    	    encryptedBytes=Base64.decodeBase64(encryptedBytes);
    	    ByteArrayInputStream inStream = new ByteArrayInputStream(encryptedBytes);
    	    CipherInputStream cipherInputStream = new CipherInputStream(inStream, decryptCipher);
    	    byte[] buf = new byte[1024];
    	    int bytesRead;
    	    while ((bytesRead = cipherInputStream.read(buf)) >= 0) {
    	        outputStream.write(buf, 0, bytesRead);
    	    }
    	    decryptData=outputStream.toByteArray();
    	    decryptedString=new String(decryptData, "UTF-8");
    	}catch(Exception e)
    	{
    		
    	}
    	
    	return decryptedString;
    }
    
    private static String tempDecrypt(byte [] encryptedBytes)
    {
    	String decryptedString=null;
    	byte[] decryptData=null;
    	try
    	{
    		//KeyGenerator kgen = KeyGenerator.getInstance("AES");
 		    //kgen.init(128);
 		    //SecretKey aesKey = kgen.generateKey();
    		if(aesKey==null)
    		{
    			loadKey();
    		}
    		// Decrypt cipher
    	    Cipher decryptCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
    	    IvParameterSpec ivParameterSpec = new IvParameterSpec(aesKey.getEncoded());
    	    decryptCipher.init(Cipher.DECRYPT_MODE, aesKey, ivParameterSpec);

    	    // Decrypt
    	    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    	    //encryptedBytes=Base64.decodeBase64(encryptedBytes);
    	    ByteArrayInputStream inStream = new ByteArrayInputStream(encryptedBytes);
    	    CipherInputStream cipherInputStream = new CipherInputStream(inStream, decryptCipher);
    	    byte[] buf = new byte[1024];
    	    int bytesRead;
    	    while ((bytesRead = cipherInputStream.read(buf)) >= 0) {
    	        outputStream.write(buf, 0, bytesRead);
    	    }
    	    decryptData=outputStream.toByteArray();
    	    decryptedString=new String(decryptData, "UTF-8");
    	}catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    	return decryptedString;
    }
    
    private static void generateKey()
    
    {
    	String fileName="D:\\PRAVIN\\Project\\Library\\src\\com\\ENC_KEY";
    	try
    	{
    		FileOutputStream fos=new FileOutputStream(fileName);
    		KeyGenerator kgen = KeyGenerator.getInstance("AES");
		    kgen.init(128);
		    aesKey = kgen.generateKey();
		    fos.write(aesKey.getEncoded());
		    fos.flush();
		    fos.close();
    	}catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    }
    
    public static void main(String[] args) throws UnsupportedEncodingException {
    	char c=(char)10225;
    	String data="Hello I am Chitraksh"+c;
    	//generateKey();
	}
 }
