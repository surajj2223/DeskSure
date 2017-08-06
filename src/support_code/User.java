/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package support_code;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Suraj Malviya
 * Date Created: 28/02/2017
 */
public class User implements Serializable
{
    private String name, MAC;
    private int type;
    
    
   
//  type- 10(Admin) type- 1(Naive)

    public User(String name, String MAC, int type )
    {
        this.name=name;
        this.MAC=MAC;
        this.type=type;
       
    }
    
   
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMAC() {
        return MAC;
    }

    public String getUnFormattedMAC()
    {
        return unFormattedMAC();
        
    }
    
    
    public void setMAC(String MAC) {
        this.MAC = MAC;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
    
    
    
    
    
    
    

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (!Objects.equals(this.MAC, other.MAC)) {
            return false;
        }
        return true;
    }
    
    
    @Override
    public String toString()
    {
        return "Name: "+this.name+" | "
                + "MAC- "+this.MAC+" | "
                + "TYPE:"+this.type;
    }

    private String unFormattedMAC() {
        
        //64 : CC : 2E : 91 : AB : 96
        
        String formattedMAC=this.MAC.trim();
        
        String b1=formattedMAC.substring(0, 2);
        String b2=formattedMAC.substring(5, 7);
        String b3=formattedMAC.substring(10, 12);
        String b4=formattedMAC.substring(15, 17);
        String b5=formattedMAC.substring(20, 22);
        String b6=formattedMAC.substring(25, 27);
        
        return b1+b2+b3+b4+b5+b6;
        
    }
    
    
}
