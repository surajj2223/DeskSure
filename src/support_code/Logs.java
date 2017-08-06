/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package support_code;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Suraj Malviya
 * Date Created: 28/02/2017
 */
public class Logs implements Serializable
{
     ArrayList<Log> logs;

    public Logs() 
    {
        this.logs = new ArrayList<>();
    }

    public ArrayList<Log> getLogs() {
        return logs;
    }

    public void setLogs(ArrayList<Log> logs) {
        this.logs = logs;
    }
    
    public void addLog(Log log)
    {
        this.logs.add(log);
    }
    
    public void removeLog(Log log)
    {
        this.logs.remove(log);
    }
 
    public ArrayList<Log> rangedLogs(Date init, Date fin)
    {
        ArrayList<Log> range=new ArrayList<>();
        this.logs.forEach((log)->
        {
            Date logDate=log.getDate();
            if(logDate.compareTo(init)==0)
            {
                range.add(log);
            }
            else if(logDate.compareTo(init)>0 && logDate.compareTo(fin)<0)
            {
                range.add(log);
            }
            else if(logDate.compareTo(fin)==0)
            {
                range.add(log);
            }
           
        });
        return range;
    }
    
    public void saveLogsObject() throws IOException
    {
        File usersLogs=new File("UserLogs.ser");
       
        try (FileOutputStream fout = new FileOutputStream(usersLogs)) 
        {
           ObjectOutputStream oout = new ObjectOutputStream(fout);
            oout.writeObject(this);
        }
    }
    
    
    public static Logs getLogsObject() throws IOException,ClassNotFoundException
    {
        Logs user_logs=null;
        
        File usersLog=new File("UserLogs.ser");
        
        try (FileInputStream fin = new FileInputStream(usersLog)) 
        {
            ObjectInputStream oin = new ObjectInputStream(fin);
            user_logs=(Logs)oin.readObject();
        }
        
        return user_logs;
    }
    
}
