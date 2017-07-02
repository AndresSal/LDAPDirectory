/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.LDAP;

import java.util.Arrays;

/**
 *
 * @author andres
 */
public class CeasarEncryption {
 
    //String muestra;
    char alfabeto[]={'a','b','c',
                     'd','e','f',
                     'g','h','i',
                     'j','k','l',
                     'm','n','o',
                     'p','q','r',
                     's','t','u',
                     'v','w','x',
                     'y','z',' '};
    
    public CeasarEncryption(){
    }
    
    public String Encriptar(String muestra)
    {
        char entrada []= muestra.toLowerCase().toCharArray();
        char salida []= new char [entrada.length];
        char shiftkey = 3;
        for (int i = 0; i < entrada.length;i++)
        {
            for (int j = 0; j < alfabeto.length;j++)
            {
                if(entrada[i]==alfabeto[j])
                {
                    int aux = j;
                    
                    if (aux>23)
                    {
                        int residuo = alfabeto.length-aux;
                        residuo = 3 - residuo;
                        salida[i] = alfabeto[residuo];
                    }
                    
                    else
                    {
                        aux = aux + 3;
                        salida[i]=alfabeto[aux];
                    }
                }
            }
        }   
        String resultado="";
        for (int i=0;i<salida.length;i++)
        {
            resultado+=salida[i];
        }
        return resultado;
    }
}