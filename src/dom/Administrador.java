/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dom;

import java.util.ArrayList;
import java.util.Iterator;


/**
 *
 * @author URIEL
 */
public class Administrador {
    ArrayList<Particion> RAM;
    
    int so;
    int tamanio;
    int numPart = 0;
    int numAL;
    
    public Administrador(int tamanio,int so){
        this.tamanio = tamanio;
        this.so = so;
        creaAL(0,tamanio);
        
        
        
    }
    
    private void AsignaRAM(int tamaño, Proceso p){
        

    }
    
    private  void creaPA(int dir, int tamanio){
        
        AreaLibre AL;
        
        AL = new AreaLibre(dir,tamanio);
      
        this.numAL++;
        System.out.println(""+numAL);
        
    }
    /**
     *@param dir
     *@param tamanio
     * 
     **/
    private  void creaAL(int dir, int tamanio){
        
        AreaLibre AL;
        
        AL = new AreaLibre(dir,tamanio);
      
        this.numAL++;
        System.out.println(""+numAL);
        
    }
   
    
    /**
     * Solicita espacio RAM com Mejor Ajuste
     * @param tam 
     * @return 
     */
    public int solicitaRAM(int tam){
        int pos = 0;
        int ma = 0;
        Iterator<Particion> i = RAM.iterator();
        while(i.hasNext()){
        Particion p = i.next();
        
        if((p.tamanio>= tam)&&(p.estado =='D')){
            if (p.tamanio > ma){
                pos = p.base;
                ma = p.tamanio;
            }
            
            
        }
        }
        
        
        return pos;
    }
    
        

     

  
    private void RecuperaMemoria(ParticionAsignada part){
        
    }
    /**
     * Verifica la contiguidad de una particion
     */
    private void VerifContig(int direccion){
        
    }
    
    private void compactacion(){
        
    }
    private void reubicacion(){
        
        
    }
   
   
           
}
