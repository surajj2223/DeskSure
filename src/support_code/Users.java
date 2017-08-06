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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Suraj Malviya
 * Date Created: 28/02/2017
 */
public class Users implements Serializable
{
    private List<User> users;
    private Map<String,User> userMACMap;
    private Map<String, User> userNameMap;
    
     private static final long serialVersionUID = 1L;
     
    public Users()
    {
        this.users=new ArrayList<>();
        this.userMACMap=new HashMap<>();
        this.userNameMap=new HashMap<>();
    }
  
    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Map<String, User> getUserMACMap() {
        return userMACMap;
    }

    public void setUserMACMap(Map<String, User> userMap) {
        this.userMACMap = userMap;
    }

    public Map<String, User> getUserNameMap() {
        return userNameMap;
    }

    public void setUserNameMap(Map<String, User> userNameMap) {
        this.userNameMap = userNameMap;
    }
    
    
    
    public void addUser(User user)
    {
        this.users.add(user);
        this.userMACMap.put(user.getMAC(),user);
        this.userNameMap.put(user.getName().toUpperCase(), user);
    }
    
    public void removeUser(User user)
    {
        this.users.remove(user);
        this.userMACMap.remove(user.getMAC());
        this.userNameMap.remove(user.getName().toUpperCase().trim());
    }
    
    
  
    
    
    public void saveUsersObject(Users allusers) throws IOException
    {
        File usersFile=new File("Users.ser");
       
        try (FileOutputStream fout = new FileOutputStream(usersFile)) 
        {
           ObjectOutputStream oout = new ObjectOutputStream(fout);
            oout.writeObject(allusers);
        }
    }
    
    
    public static Users getUsersObject() throws IOException,ClassNotFoundException
    {
        Users users=null;
        
        File usersFile=new File("Users.ser");
        
        
        try (FileInputStream fin = new FileInputStream(usersFile)) {
            ObjectInputStream oin = new ObjectInputStream(fin);
            users=(Users)oin.readObject();
        }
        
        return users;
    }
    
    
    
    
    
}
