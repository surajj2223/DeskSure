/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package support_code;

import core.EncryptionEngine;
import java.io.File;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Suraj Malviya
 * Date Created: 28/02/2017
 */
public class UserFile implements Serializable
{
    private File file;
    private String extension;
    private User addedBy;
    private String timeStamp; 
    private FileStatus status;//
    
    public UserFile(File file, String extension, User addedBy, FileStatus status) {
        this.file = file;
        this.extension = extension;
        this.addedBy = addedBy;
        this.status=status;
    
        SimpleDateFormat dateFormat= new SimpleDateFormat(" dd.MM.yyyy | hh:mm a");
        timeStamp=dateFormat.format(new Date());
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

  
    
    
    public File getFile() {
        return file;
        
    }

    public void setFile(File file) {
        this.file = file;
    }

    public User getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(User addedBy) {
        this.addedBy = addedBy;
    }

    

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public void setEncrypted(boolean encrypt)
    {
        if(encrypt)
        {
            this.status=FileStatus.ENCRYPTED;
        }
        else
        {
            this.status=FileStatus.DECRYPTED;
        }
    }
    
    
    public boolean isEnrypted()
    {
        return this.status==FileStatus.ENCRYPTED;
    }

    @Override
    public String toString() {
        return "UserFile{" + "file=" + file + ", extension=" + extension + ", \nAddedBy=" + addedBy + ", timeStamp=" + timeStamp + ", status=" + status + '}';
    }
    
   
    @Override
    public boolean equals(Object obj) {
       UserFile file=(UserFile)obj;
       
       String path=file.file.getPath();
       
       if(this.file.getPath().equals(path))
       {
           return true;
       }
       else{
           return false;
       }
    }

    
    
   

    
    
   

}


