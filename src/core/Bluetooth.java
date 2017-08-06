/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import com.intel.bluetooth.RemoteDeviceHelper;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.bluetooth.BluetoothStateException;
import javax.bluetooth.DataElement;
import javax.bluetooth.DeviceClass;
import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.DiscoveryListener;
import javax.bluetooth.LocalDevice;
import javax.bluetooth.RemoteDevice;
import javax.bluetooth.ServiceRecord;
import javax.bluetooth.UUID;
import javax.microedition.io.Connector;
import javax.obex.ClientSession;
import javax.obex.HeaderSet;
import javax.obex.ResponseCodes;

/**
 *
 * @author Suraj Malviya
 */
public class Bluetooth implements DiscoveryListener, Serializable
{
    private static  Object lock=new Object();
    
    private static List<RemoteDevice> remoteDevices;
    
    private String serverURL;
    
    public int flag=0;
    
    private static LocalDevice myDevice;
    
    private  DiscoveryAgent agent;
    
    public Bluetooth() throws BluetoothStateException
    {
        remoteDevices=new ArrayList<>();
        myDevice=LocalDevice.getLocalDevice();
        agent = myDevice.getDiscoveryAgent();
    }
    
    
    public List<RemoteDevice> searchAvailableDevices() throws BluetoothStateException
    {
            agent.startInquiry(DiscoveryAgent.GIAC, this);
            
            try {
                synchronized(lock){
                    lock.wait();
                }
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Device Inquiry Completed. \n"); 
            return remoteDevices;
    }
    
    public String getServerURL(RemoteDevice device) throws BluetoothStateException
    {
        
            UUID[] uuidSet = new UUID[1];
            uuidSet[0]=new UUID(0x1105); //OBEX Object Push service
            
            int[] attrIDs =  new int[] {
                    0x0100 // Service name
            };
            
            agent.searchServices(attrIDs, uuidSet, device, this);
              
            try {
                synchronized(lock){
                    lock.wait();
                }
            }
            catch (InterruptedException e) {
                e.printStackTrace();
                return null;
            }
            
            return serverURL;
            
            
    }
    
    
    
    
    
  
    
public int pairingDevice(RemoteDevice remoteDevice) throws IOException
{
     if (!remoteDevice.isAuthenticated())
    {
        int returnValue=0;
      
        System.err.println("Attempting to Authenticate");
      boolean devicePaired=false;
              String PIN = "111111";
            boolean paired = RemoteDeviceHelper.authenticate(remoteDevice, PIN);                
            
            devicePaired = paired;
            if (devicePaired)
            {
                returnValue+=1; 
            }   
            else
            {
               returnValue+=2;
            }
   
       
        return returnValue;
        
    }
    else{return 3;}

}

   @Override
    public void deviceDiscovered(RemoteDevice btDevice, DeviceClass arg1) {
          System.err.println("deviceDiscovered Started");
        String name,address;
        boolean trust;
        try {
            address = btDevice.getBluetoothAddress();
            name=btDevice.getFriendlyName(true);
            trust=btDevice.isTrustedDevice();
        } catch (Exception e) 
        { 
            name=e.getMessage();
            address=e.getMessage();
            e.printStackTrace();
            trust=false;
        }
        
        remoteDevices.add(btDevice);
       // System.out.println("device found: " + name+" BT Adress: "+address+" Trusted Device "+trust);
        
    }

    @Override
 public void servicesDiscovered(int transID, ServiceRecord[] servRecord) {
            System.err.println("servicesDiscovered Started");
        
        for (int i = 0; i < servRecord.length; i++) 
        {
            String url = servRecord[i].getConnectionURL(ServiceRecord.AUTHENTICATE_ENCRYPT, true);
            if (url == null) {
                continue;
            }
            
            DataElement serviceName = servRecord[i].getAttributeValue(0x0100);
            
            if (serviceName != null) {
                System.out.println("service " + serviceName.getValue() + " found " + url);
                
                System.err.println(serviceName.getValue().toString());
                
                this.serverURL=url;
               // sendMessageToDevice(url);
            } else {
                System.err.println("service found " + url);
            }
            
          
        }
        
    }

    @Override
    public void serviceSearchCompleted(int i, int i1) {
       System.err.println("serviceSearchCompleted Started");
        synchronized (lock) {
            lock.notify();
        }
    }

    @Override
    public void inquiryCompleted(int i) {
        System.err.println("Enquiry Completed !");
           this.flag=1;
         synchronized(lock){
            lock.notify();
        }
      
    }
    
    
    
    
     
    
    
    
}
