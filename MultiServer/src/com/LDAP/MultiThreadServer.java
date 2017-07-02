/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.LDAP;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author andres
 */
public class MultiThreadServer extends Thread {
    private Socket socket = null;

    public MultiThreadServer(Socket socket) 
    {
        super("MultiThreadServer");
        this.socket = socket;
    }
    
    public void run()
    {
        try
        (
            PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        )     
        {
            String inputline="", nombre="", password="",outputline="";
            
            nombre=in.readLine();
            password=in.readLine();
            
            LogIn nuevaconexion = new LogIn();
            boolean allowed = nuevaconexion.Conectar(nombre, password);
            if (allowed == true)
            {
            
                while ((inputline = in.readLine())!=null)
                {
                    System.out.println(inputline);
                    PrintWriter database = new PrintWriter(new FileWriter ("03.txt",true));
                    database.println(inputline);
                    
                    CeasarEncryption cesar = new CeasarEncryption();
                    out.println(cesar.Encriptar(inputline));
                
                    if (inputline.equals("salir"))
                    {
                        outputline="chao";
                        out.println(outputline);
                        break;
                    }
                    
                    database.println(cesar.Encriptar(inputline));                
                    database.close();
                }
            }
            socket.close();
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }
}
