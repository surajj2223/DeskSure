package core;


import user_interfaces.AdminLogin;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import javax.bluetooth.BluetoothStateException;
import support_code.Logs;
import support_code.User;
import support_code.UserFile;
import support_code.UserFiles;
import support_code.Users;
import user_interfaces.ErrorPage;
import user_interfaces.GlobalFileAccess;
import user_interfaces.NaiveLogin;
import user_interfaces.NewInstallation;

public class Main 
{

    public static Resources resources;
   
    
    public static void main(String[] args) 
    {
        
        try 
        {
            resources=Resources.getResourceInstance();
            resources.setBluetoothStack(new Bluetooth());
            if(filesNotPresent()) //means software is getting installed !
            {
                new NewInstallation().setVisible(true);
            }
            else
            {

               // if(resources==null){System.err.println("NULL");}
                Main mainClass=new Main();
            }
        } 
        catch (BluetoothStateException bse) 
        {
            new ErrorPage(bse).setVisible(true);
        }
        catch(ClassNotFoundException cnfe)
        {
            cnfe.printStackTrace();
        }
        catch(IOException ie)
        {
            ie.printStackTrace();
        }
        
        
       
        
        
    }
    public Main() throws IOException,ClassNotFoundException
    {
    
        if (!SystemTray.isSupported()) {
            System.out.println("SystemTray is not supported");
            return;
        }
        
        final PopupMenu popup = new PopupMenu();
        final TrayIcon trayIcon =
                new TrayIcon(ImageIO.read(getClass().getResource("logo.png")));
        final SystemTray tray = SystemTray.getSystemTray();
       
        
        MenuItem AdminPanel = new MenuItem("Open Admin Panel");
        MenuItem ManageFiles=new MenuItem("My Files");  
        MenuItem exitApp=new MenuItem("Exit");
        readFileResources();
       
        popup.add(AdminPanel);
     
        popup.add(ManageFiles);
        popup.add(exitApp);
        
      
       AdminPanel.addActionListener(new ActionListener() 
       {
           @Override 
           public void actionPerformed(ActionEvent e) 
            {
                if(Resources.connection==null)
                {
                    user_interfaces.AdminLogin login=new AdminLogin();
                    login.setVisible(true);
                }
                else
                {
                    System.err.println("An Instance is already workking");
                }
                
            }
        });
       
      
       
       ManageFiles.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                if(Resources.connection==null)
                {
                   // if(resources.getLockFlag()=)
                    if(resources.getLockFlag()!=0)
                    {
                         new GlobalFileAccess().setVisible(true);
                    }
                    else
                    {
                        new NaiveLogin().setVisible(true);
                    }
                }
                else
                {
                    System.err.println("An Instance is already workking");
                }
            }
        });
       
       exitApp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                System.exit(0);
            }
        });
       
        trayIcon.setPopupMenu(popup);
       
        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            System.out.println("TrayIcon could not be added.");
        }
    }
    
    
    private static boolean filesNotPresent()
    {
        System.err.println("CHECKING FILE PRESENCE !!");
        File file=new  File("Users.ser");
        return !file.exists();
    }
    
    
    
    
    
    private void readFileResources() throws ClassNotFoundException,IOException
    {
        System.out.println("Reading Resources from HDD !");
        System.err.println("Reading Users File");
        Users fileUsers=Users.getUsersObject();
        resources.setUsers(fileUsers);
        for(User user: fileUsers.getUsers())
        {
            System.out.println(user);
            System.err.println("---------------------------------------");
            
        }
        System.err.println("looking name map:");
        
        for(Map.Entry entry : fileUsers.getUserNameMap().entrySet())
        {
                System.err.println(entry.getKey()+" : "+entry.getValue());
        }
        
        System.out.println("Users Fetched !");
        System.err.println("=============================================");
        
        System.err.println("Reading UserFiles File");
        
        File usersFile=new File("UserFiles.ser");
        
        if(!usersFile.exists())
        {
            UserFiles newFiles=new UserFiles();
            newFiles.saveFilesObject();
        }
        
        UserFiles myFiles=UserFiles.getFilesObject();
        resources.setUserFiles(myFiles);
        
        for(UserFile file: myFiles.getUserfiles())
        {
            System.err.println(file);
            System.err.println("---------------------------------------");
        }
        System.out.println("UserFiles Fetched !");
        System.err.println("=============================================");
        
        
        System.err.println("Reading Logs File");
        
        File logsFile=new File("UserLogs.ser");
        
        if(!logsFile.exists())
        {
            Logs newLogsFile=new Logs();
            newLogsFile.saveLogsObject();
        }
        
        Logs myLogs=Logs.getLogsObject();
        resources.setUserLogs(myLogs);
        
        resources.getUserLogs().getLogs().forEach((log)->
        {
            System.err.println("LOG:"+log);
        });
        
        System.out.println("User Logs Fetched !");
        System.err.println("=============================================");
        
        
                
        
        
    }
    
    
    
    
    
    
    
}