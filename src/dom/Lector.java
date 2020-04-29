/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dom;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author URIEL
 */
public class Lector {
    
   
    
    public ArrayList leerArchivo(File rutaArchivo) throws FileNotFoundException, IOException{
        
        ArrayList<String> info = new ArrayList<String>();
        FileReader lectorArchivo = new FileReader(rutaArchivo);
        BufferedReader lectorBuffer = new BufferedReader(lectorArchivo);
        String almacen;
        while (lectorBuffer.ready()){
        almacen = lectorBuffer.readLine();
        info.add(almacen);
    }
        return info;
        
        
    }

    
}
