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
public class UserFiles implements Serializable
{
    List<UserFile> userfiles;
   // Map<User, UserFile> fileMap;
     private static final long serialVersionUID = 1L;

    public UserFiles() 
    {
        this.userfiles = new ArrayList<>();
    //    this.fileMap = new HashMap<>();
    }
    
    public List<UserFile> getUserfiles() {
        return userfiles;
    }

    public void setUserfiles(List<UserFile> userfiles) {
        this.userfiles = userfiles;
    }

//    public Map<User, UserFile> getFileMap() {
//        return fileMap;
//    }
//
//    public void setFileMap(Map<User, UserFile> fileMap) {
//        this.fileMap = fileMap;
//    }


    public void addFile(UserFile file)
    {
        userfiles.add(file);
       // fileMap.put(file.getAddedBy(),file);
    }
    
    public void removeFile(UserFile file)
    {
        userfiles.remove(file);
       // fileMap.remove(file.getAddedBy());
    }
    
    public ArrayList<UserFile> getSelectedUserFile(User user)
    {
        ArrayList<UserFile> files=new ArrayList<>();
        userfiles.forEach((userfile)->
        {
            if(userfile.getAddedBy().equals(user))
            {
                files.add(userfile);
            }
        });
        return files;
    }
    
    
    public void saveFilesObject() throws IOException
    {
        File usersFile=new File("UserFiles.ser");
       
        try (FileOutputStream fout = new FileOutputStream(usersFile)) 
        {
           ObjectOutputStream oout = new ObjectOutputStream(fout);
            oout.writeObject(this);
        }
    }
    
    
    public static UserFiles getFilesObject() throws IOException,ClassNotFoundException
    {
        UserFiles user_files=null;
        
        File usersFile=new File("UserFiles.ser");
        
        
        try (FileInputStream fin = new FileInputStream(usersFile)) {
            ObjectInputStream oin = new ObjectInputStream(fin);
            user_files=(UserFiles)oin.readObject();
        }
        
        return user_files;
    }
    
    
    
}
