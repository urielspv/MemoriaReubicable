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
    
    private int so;
    private int tamanio;
    private int numPA;
    private int numAL;   
    
    public Administrador(int tamanio,int so){
        RAM = new ArrayList();
        this.numAL = 0;
        this.numPA = 0;
        this.tamanio = tamanio;
        this.so = so;
        creaAL(so+1,tamanio - so);
        
        
        
    }
    
    public boolean AsignaRAM(int tamanio, String p){
        int pos = solicitaRAM(tamanio);
       
         Iterator<Particion> i = RAM.iterator();
        if ( pos != 0){
            System.out.println("Se encontró una particion disponible en la direccion: "+pos);
            imprimeRAM();
            Particion part;
            
            while(i.hasNext()){
                 part = i.next();
           
                if(part.base == pos ){      //busca dentro de las particiones la coincidencia con la posicion encontrada.
                         
                    if ((part.tamanio - tamanio)>0){        //Compara si el tamaño es del mismo tamaño o es más grande el area libre.
                        
                        creaPA(part.base,tamanio,p);
                        creaAL(part.base+tamanio, part.tamanio-tamanio);
                        
                        RAM.remove(part);
                        break;
                        
                        
                    }
                    else{
                        //Crea la particion asignada y elimina el AL
                        creaPA(part.base,part.tamanio, p);
                        RAM.remove(part);
                    }
                }
                
            }
            
             return true;
                }
        
        else{
            System.out.println("No hay espacio disponible");
            return false;
        }
       
        
        
    }
    
    private  void creaPA(int dir, int tamanio, String proceso){
        this.numPA++;
        ParticionAsignada PA = new ParticionAsignada(numPA,dir,proceso,tamanio);
        RAM.add(PA);
//        System.out.println("se agregó la PA"+RAM.get(RAM.size()-1).id);
        
    }
    
    /**
     *@param dir
     *@param tamanio
     * 
     **/
    private  void creaAL(int dir, int tamanio){
        numAL++;
        AreaLibre  AL = new AreaLibre(numAL, dir,tamanio);
        RAM.add(AL);
           
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
    
    
    public void imprimeRAM(){
        
        Iterator<Particion> h = RAM.iterator();
        
        System.out.println("ID  Estado  Direccion   Tamaño      Proceso");
        while(h.hasNext()){
            Particion p = h.next();
            if(p.estado=='D'){
                System.out.println(""+p.id+"     "+p.estado+"        "+p.base+"         "+p.tamanio);
                
            } else {
                System.out.println(""+p.id+"     "+p.estado+"        "+p.base+"         "+p.tamanio+"");
            }
            
        }
    }
   
   
           
}
