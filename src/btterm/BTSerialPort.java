/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package btterm;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Vector;
import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;

/**
 *
 * @author vlada
 */
public class BTSerialPort implements Runnable {

    private StreamConnection connection;
    private OutputStreamWriter ow = null;
    private InputStreamReader reader;
    private InputStream dis;
    private OutputStream dos;

    public BTSerialPort() {
    }

    public static Vector discoverDevices() throws IOException {
        BluetoothDeviceDiscovery.main();
        return BluetoothDeviceDiscovery.vecDevices;
    }
    
    /**
     *
     * @param MACaddress
     * @param channel
     * @param encoding
     */
    public void Connect (String MACaddress, byte channel, String encoding){
        try {
            connection = (StreamConnection) Connector.open ("btspp://"+MACaddress+":"+channel, Connector.READ_WRITE);
            dis = connection.openInputStream();
            reader = new InputStreamReader(dis);
            dos = connection.openOutputStream();
            ow = new OutputStreamWriter(dos, encoding);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void Connect (String MACaddress){
        Connect(MACaddress, (byte) 1, "iso-8859-1");
    }
    
    public void Closeconnection(){
        try {
            if  (dis != null) {
                dis.close();
                dis = null;
            }

            if (reader != null){
             reader.close();
             reader = null;
            }

            if  (dos != null) {
                dos.close();
                dos = null;
            }


            if (connection !=null){
                connection.close();
                connection = null;
            }


        } catch (Exception e) {

            e.printStackTrace();

        }
    }
 
    public void run() {
        try {
            while (true) {
                int input;
                if (reader != null){
                    input = reader.read();
                    fireProperty("character read",null,input);
                }
           }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void write(String s) throws IOException {
        ow.write(s);
    }
}
