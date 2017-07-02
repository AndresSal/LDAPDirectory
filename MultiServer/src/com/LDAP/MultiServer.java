/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.LDAP;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andres
 */
public class MultiServer {
    
    public static void main(String args[]) throws IOException
    {
        if (args.length != 1)
        {
            System.err.println("Proveer numero puerto");
            System.exit(1);
        }
        
        int portNumber = Integer.parseInt(args[0]);
        int x = 0;
        
        
        try
        (
            ServerSocket serverSocket = new ServerSocket(portNumber);
        )        
                
        {
            String inputline="";
            
            while( x<= 10)
            {     
                new MultiThreadServer(serverSocket.accept()).start(); 
                x++;
                if(serverSocket.isClosed())
                    x--;
                    
            }
        }
        catch(IOException e)
        {
            System.err.println("No se pudo escuchar el puerto "+portNumber);
            System.exit(-1);
        }
    }
    
}

