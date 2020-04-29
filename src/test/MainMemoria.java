/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import dom.Administrador;
import dom.Lector;
import dom.Proceso;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *
 * @author URIEL
 */
public class MainMemoria {

    /**
     * @param args the command line arguments
     */
    @SuppressWarnings("empty-statement")
    public static void main(String[] args) throws IOException {
        
        
        Scanner in = new Scanner(System.in);
        int ram,so;
//        Definimos valores iniciales
        System.out.print("Ingresa el tamaño de la RAM: ");
        ram = in.nextInt();
        System.out.print("Ingresa el tamaño del SO: ");
        so = in.nextInt();
        Administrador adm = new Administrador(ram, so);
        
        
        File f = new File("src/datos.CSV");
        Lector lec = new Lector();
        ArrayList<String> Eventos = new ArrayList<String>();
        ArrayList<Proceso> Procesos = new ArrayList<Proceso>() ; 
        Eventos = lec.leerArchivo(f);
        Iterator<String> i = Eventos.iterator();
        
        
        
        
//        while(i.hasNext()){
//            System.out.println(""+i.next());
//        }

        while(!Eventos.isEmpty()){
            
            String  evento = Eventos.get(0);
            StringTokenizer st = new StringTokenizer(evento, ",");
            String orden = st.nextToken();
            
             //Acciones si el evento es llegada (Acciones del Administrador)
            if (orden.equals("L")){
            
           
            
            
            
            
            
            
            
                
            }
            
            //Acciones si el evento es Termina (Elimina el proceso y recuperacion de memoria)
            else{
                
                
                
            }
            
            Eventos.remove(0);
            
               
        }
        
        
        
        
        System.out.println("");
        
    }
    
}
