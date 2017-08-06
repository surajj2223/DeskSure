/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import com.intel.bluetooth.RemoteDeviceHelper;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.bluetooth.BluetoothStateException;
import javax.bluetooth.RemoteDevice;
import support_code.User;
import support_code.Users;

/**
 *
 * @author Suraj Malviya
 */
public class SampleSingleExecute {
    
    public static void main(String[] args) throws BluetoothStateException,IOException,ClassNotFoundException{

    
        try {
             byte[] encryptedDatafromFile=EncryptionEngine.fromFile(new File("C:\\Users\\Suraj Malviya\\Desktop\\SampleData\\Chithi Na Koi - .mp3"));
        
        byte[] decryptedData= EncryptionEngine.encrypt(encryptedDatafromFile);
        
        EncryptionEngine.toFile(new File("C:\\Users\\Suraj Malviya\\Desktop\\SampleData\\Chithi Na Koi - .mp3"), decryptedData);
        } catch (Exception e) 
        {
               e.printStackTrace();
        }
       
        
        
        
        
        
        
        
        
        
        
//        support_code.Users users=new Users();
//
//        users=Users.getUsersObject();
//         int i=0;
//        for(User user: users.getUsers()){
//            i++;
//            System.err.println(i+". "+user);
//        
//        }
//        
        
        

//        Bluetooth myBluetooth=new  Bluetooth();
//        List<RemoteDevice> devices=myBluetooth.searchAvailableDevices();
//        RemoteDevice myDevice=null;
//        for(RemoteDevice device: devices)
//        {
//            System.err.println("BT FriendlyName: "+device.getFriendlyName(true));
//            System.err.println("BT Address: "+device.getBluetoothAddress());
//            System.err.println("BT isTrusted: "+device.isTrustedDevice());
//           if(device.getBluetoothAddress().equals("64CC2E91AB96")){myDevice=device;}
//        }
//        
//        RemoteDeviceHelper.authenticate(myDevice);
//        
//        
//        if(devices.isEmpty()){System.err.println("No Bluetooth Device in range !");}
//        else
//        {
//           // myBluetooth.pairingDevice(myDevice);
//            System.err.println("authenticate()");
//            System.err.println(myDevice.authenticate());
//        }
//        
        
    }
    
}
