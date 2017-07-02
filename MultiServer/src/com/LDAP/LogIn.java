/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.LDAP;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author andres
 */
public class LogIn {

    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
            
    public LogIn() {
    }
        
    public boolean Conectar(String nombre, String password)
    {
        boolean admitido=true;
        
        String script = "SELECT * FROM Persona WHERE nombrePersona=? and password=?";
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/base_CD", "root", "Andres-Mysql10");
            pst=conn.prepareStatement(script);
            pst.setString(1, nombre);
            pst.setString(2, password);
            rs=pst.executeQuery();
            if(rs.next())
            {
                System.out.println("Usuario permitido. \n Bienvenido "+nombre);
            }
            else
            {
                System.out.println("Usuario no admitido.");
                admitido=false;
            }
            
        }catch(Exception e)
        {
            System.out.println(e);
        }
        
        return admitido;
    }
    
    
}
