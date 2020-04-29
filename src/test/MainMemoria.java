/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import dom.Administrador;
import dom.Lector;

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
    public static void main(String[] args) throws IOException, InterruptedException {
        
        
        Scanner in = new Scanner(System.in);
        int ram,so;
//        Definimos valores iniciales
        System.out.print("Ingresa el tamaño de la RAM: ");
        ram = in.nextInt();
        System.out.print("Ingresa el tamaño del SO: ");
        so = in.nextInt();
        Administrador AM = new Administrador(ram, so);
        
        
        File f = new File("src/datos.CSV");
        Lector lec = new Lector();
        ArrayList<String> Eventos = new ArrayList<String>();
        Eventos = lec.leerArchivo(f);
        Iterator<String> i = Eventos.iterator();
        
        
        
        
//        while(i.hasNext()){
//            System.out.println(""+i.next());
//        }

        while(!Eventos.isEmpty()){
            
            String  evento = Eventos.get(0);
            StringTokenizer st = new StringTokenizer(evento, ",");
            String instr = st.nextToken();
            
             //Acciones si el evento es llegada (Acciones del Administrador)
            if (instr.equals("L")){
                
                String proceso = st.nextToken();
                int tamanio = Integer.parseInt(st.nextToken());
                
                System.out.println("\n\n\nEvento: Llega  P"+proceso+"  requiere "+tamanio+"k");
                if (AM.AsignaRAM(tamanio, proceso)) {
                    System.out.println("");
                    Thread.sleep(5000);
                    Eventos.remove(0);
                }
                else{
                   /*Verificar que existe espacio suficiente para hacer la compactacion del area libre
                    y hacer la compactacion
                    */
                   
                   
                }
                 
                }
                
            
            //Acciones si el evento es Termina (Elimina el proceso y recuperacion de memoria)
            if (instr.equals("T")){

                String proceso = st.nextToken();
               
                System.out.println("\n\n\nEvento: Termina P"+proceso+"");
                AM.RecuperaMemoria(proceso);
                Thread.sleep(5000);
                Eventos.remove(0);
                
                
            }
            AM.imprimeRAM();
            Thread.sleep(5000);
            
            
               
        }
        
        
        
        
        System.out.println("");
        
    }
    
}
