/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author andres
 */
public class EchoClient 
{
    public static void main (String args[]) throws IOException
    {
        if (args.length != 4)
        {
            System.err.println("Ingrese host name y puerto del servidor");
            System.exit(1);
        }
        
        String hostName = args[0];
        int portNumber = Integer.parseInt(args[1]);
        String nombre = args[2];
        String password = args[3];
        try
        (
            Socket clientsocket = new Socket(hostName, portNumber);
            PrintWriter out = new PrintWriter(clientsocket.getOutputStream(),true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientsocket.getInputStream()));
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        )
        {
            String clientInput;
            out.println(nombre);
            out.println(password);
            
            while((clientInput=stdIn.readLine()) != null)
            {
                
                out.println(clientInput);
                System.out.println("echo: "+in.readLine());
            }
        }
        catch (UnknownHostException e)
        {
            System.err.println("No hay contacto con puerto"+portNumber);
            System.exit(1);   
        }
        catch (IOException e)
        {
            System.err.println("No se puede enviar o recibir contenido al servidor"+hostName);
            System.exit(1);   
        }
        
    }
}