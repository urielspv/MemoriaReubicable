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
    
    private void AsignaRAM(int direccion, int tamaño, Proceso p){
        ParticionAsignada part = new ParticionAsignada(numPart,direccion, p );
        RAM.add(part);
        this.numPart ++;

    }
    private void creaAL(int dir, int tamanio){
        AreaLibre AL = new AreaLibre(dir,tamanio);
        RAM.add(AL);
        this.numAL++;
        
    }
    
    private  int solicitaRAM(int tam){
        int pos = 0;
        Iterator<Particion> i = RAM.iterator();
        while(i.hasNext()){
        Particion p = i.next();
        
        if((p.tamanio>= tam)&&(p.estado =='D')){
            pos = p.base;
        }
        }
        
        
        return pos;
    }
    
        

     

  
    private void elimina(ParticionAsignada part){
        
    }
    
    private void compactacion(){
        
    }
    private void reubicacion(){
        
        
    }
    
    private void verifica(){
        
    }
    private int mejorajuste(){
        int numpart = 0;
        return numpart;
    }
           
}
