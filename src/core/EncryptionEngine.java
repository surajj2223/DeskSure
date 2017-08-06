package core;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Date: 30/01/2017
 */
public class EncryptionEngine
{
    public static byte[] encrypt(byte[] c) throws Exception 
    {
        byte[] keyBytes = Resources.xsKey.getBytes();   //"ThisIs128bitSize".getBytes();
        Key key = new SecretKeySpec(keyBytes, "AES");
        Cipher a = Cipher.getInstance("AES");
        a.init(Cipher.ENCRYPT_MODE, key);
        return a.doFinal(c);
    }
    
    public static byte[] decrypt(byte[] encryptedText) throws Exception 
    {
        byte[] keyBytes = Resources.xsKey.getBytes();  //"ThisIs128bitSize".getBytes();
        Key key = new SecretKeySpec(keyBytes, "AES");
        Cipher c = Cipher.getInstance("AES");
        c.init(Cipher.DECRYPT_MODE, key);
        byte[] decValue = c.doFinal(encryptedText); return decValue;
    }
    
    
    public static byte[] fromFile(File file) throws FileNotFoundException,IOException
    {
        FileInputStream fin=new FileInputStream(file);
        int length=(int)file.length();
        byte filebyte[] =new byte[length];
        fin.read(filebyte);
        return filebyte;
    }
    
    public static void toFile(File file,byte[] encrypted) throws Exception
    {
        FileOutputStream fout=new FileOutputStream(file);
        fout.write(encrypted);
    }
    
    
    
    public static void encryptFile(File file) throws Exception
    {
        byte[] originalData=fromFile(file);
        byte[] encryptedData=encrypt(originalData);
        toFile(file, encryptedData);
    }
    
    public static void decrpytFile(File file) throws Exception
    {
        byte[] encryptedData=fromFile(file);
        byte[] decryptedData=decrypt(encryptedData);
        toFile(file, decryptedData);
    }
    
    
    
}
