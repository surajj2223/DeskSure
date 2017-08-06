package core;


import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import javax.obex.ClientSession;
import support_code.Logs;
import support_code.UserFiles;
import support_code.Users;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Suraj Malviya
 */
public class Resources 
{
    public static final String xsKey="ThisIs128bitSize";
    
    private static final String xmKey="ThisIs192bitSize";
    
    private static final String xlKey="ThisIs256bitSize";

    private static final Resources singleResource=new Resources(); //singleton !
    
    public static ClientSession connection;
     
    private int lockFlag=0;  // 0- locked, 1- unlocked
    
    private Bluetooth bluetooth;
    
    private  Users allUsers;
    private  Logs allLogs;
    private  UserFiles allFiles;
  //  public static String currentKeySize;
    

    private final Image image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("logo.png"));
    private final Image lockimage = Toolkit.getDefaultToolkit().getImage(getClass().getResource("../icons/locked_1.png"));
    private final Image unlockimage = Toolkit.getDefaultToolkit().getImage(getClass().getResource("../icons/unlocked.png"));
    
    private Resources() 
    {
        allUsers=new Users();
        allLogs=new Logs();
        allFiles=new UserFiles();
    }
    
    public static Resources getResourceInstance()
    {
        return singleResource;
    }
    
    
    
    
    public void setBluetoothStack(Bluetooth myBluetooth)
    {
        singleResource.bluetooth=myBluetooth;
    }
    
    public Bluetooth getBluetoothStack()
    {
        return singleResource.bluetooth; 
    }
    
    
    public Users getUsers(){return singleResource.allUsers;}
    public void setUsers(Users fileUsers){singleResource.allUsers=fileUsers;}    
  
    public UserFiles getUserFiles(){return singleResource.allFiles;}
    public void setUserFiles(UserFiles files){singleResource.allFiles=files;}
    
    
    public Logs getUserLogs(){return singleResource.allLogs;}
    public void setUserLogs(Logs logs){singleResource.allLogs=logs;}
    
    
   
    
    public Image giveImageIcon()
    {
        return image;
    }
    
    
    
    public Image giveLockIcon()
    {
        return lockimage;
    }
    
    
    
    public Image giveUnlockIcon()
    {
        return unlockimage;
    }
    
    

    public  int getLockFlag() {
        return lockFlag;
    }

    public  void setLockFlag(int lockFlag) {
        singleResource.lockFlag = lockFlag;
    }
    
    
    
    private void getCurrentKeySize() throws Exception
    {
        File config_file=new File("config.txt");
        FileInputStream fin=new FileInputStream(config_file);
        while(fin.read()!= -1)
        {
            
        }
    }
    
    
    
    public static String getFormattedAddress(String unFormatted)
    {   //as1234er4512
        String b1=unFormatted.substring(0, 2);
        String b2=unFormatted.substring(2, 4);
        String b3=unFormatted.substring(4, 6);
        String b4=unFormatted.substring(6, 8);
        String b5=unFormatted.substring(8, 10);
        String b6=unFormatted.substring(10, 12);
        
        return b1+" : "+b2+" : " +b3+" : "+b4+" : "+b5+" : "+b6;
    }
    
    
    public static final String fetchFileExtension(String path)
    {
        int i=path.lastIndexOf('.');
         return path.substring(i+1, path.length()).trim();
    }
    
    
    
    public static String getFileSize(long bytes)
    {
        double size=0.0; String sizeName="";
        double KBs= bytes/1024;
        
        if(KBs<1024)
        {
            sizeName+="KB";
            size+=KBs;
        }
        double MBs=0.0;
        if(KBs>1023)
        {
            MBs=MBs+KBs/1024;
            sizeName+="MB";
            size+=MBs;
        }
        if(KBs==0)
        {
            size+=bytes;
            sizeName="Bytes";
        }
        return Math.round(size)+" "+sizeName;
    }
    
    
    
    
    
    
    
    
}
