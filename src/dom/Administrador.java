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
public class Administrador  {
    ArrayList<Particion> RAM;
    private int so;
    private int tamanioRAM;
    private int numPA;
    private int numAL;
    private int totalDisp;
    
    public Administrador(int tamanio,int so){
        RAM = new ArrayList();
        this.numAL = 0;
        this.numPA = 0;
        this.tamanioRAM = tamanio;
        this.so = so;
        this.totalDisp = (this.tamanioRAM - so);
        creaAL(so+1,this.totalDisp);
    }
    
    public boolean AsignaRAM(int tamanio, String p) throws InterruptedException {
        int pos = solicitaRAM(tamanio);
         Iterator<Particion> i = RAM.iterator();
        if ( pos != 0){
            Thread.sleep(2000);
//            imprimeRAM();
            System.out.println("\nArea Libre encontrada en la posición: "+pos+"\n");
            Thread.sleep(2000);
            
            Particion part;
            while(i.hasNext()){
                 part = i.next();
                if(part.base == pos ){      //busca dentro de las particiones la coincidencia con la posicion encontrada.
                    if ((part.tamanio - tamanio)>0){        //Compara si el tamaño es del mismo tamaño o es más grande el area libre.
                        numAL--;
                        creaPA(part.base,tamanio,p);
                        creaAL(part.base+tamanio, part.tamanio-tamanio);
                        RAM.remove(part);
                        
                        break;
                    }
                    else{
                        //Crea la particion asignada y elimina el AL
                        creaPA(part.base,part.tamanio, p);
                        RAM.remove(part);
                        numAL--;
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
    
    
    private int calculaATL(){//Calcula el area total llibre
        int ATLibre = 0;
        Iterator<Particion> h = RAM.iterator();
        while(h.hasNext()){
            Particion p = h.next();
            if(p.estado=='D'){
                ATLibre = ATLibre + p.tamanio;
            }
        
        }
        
    return ATLibre;
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

  
    public void RecuperaMemoria(String Proceso){
         Iterator<Particion> i = RAM.iterator();
         Particion p ;
         while(i.hasNext()){
             p = i.next();
             if(p.procesoAsig.equals(Proceso)){
                 creaAL(p.base, p.tamanio);
                 RAM.remove(p);
                 numPA--;
                 System.out.println("Proceso Eliminado");
                 RAM.sort((o1, o2) -> o1.compareTo(o2));
                 break;
             }
         }
    }
    
    /**
     * Verifica la contiguidad de una particion
     */
    private void VerifContig(AreaLibre AL){
       
        Iterator<Particion> h = RAM.iterator();
        while(h.hasNext()){
            Particion p = h.next();
            if(p.limite == AL.base){
                creaAL(p.base, AL.limite);
                RAM.remove(p);
                RAM.remove(AL);
                numAL--;
            }
            if(AL.limite == p.base){
                creaAL(AL.base, p.limite);
                RAM.remove(p);
                RAM.remove(AL);
                numAL--;
            }
        }
       
    }
    
    public boolean ExisteEspacio(int tamanio){
        if(totalDisp >= tamanio){
            return true;
        }
        else{
            return false;
        }
        
    }
    
    public void compactacion(){
        ArrayList<Particion> temp;
        temp = new ArrayList();
        Iterator<Particion> h = RAM.iterator();
         while(h.hasNext()){
             Particion p = h.next();
             
             
         }
    }

    
    

    
    public void imprimeRAM() throws InterruptedException{
        Iterator<Particion> h = RAM.iterator();
        System.out.println("-------------------------------------------------------------------");
        System.out.println("PARTICIONES ASIGNADAS: "+numPA+"                          AREAS LIBRES: "+numAL);
        System.out.println("-------------------------------------------------------------------");
        System.out.println("-------------------- PARTICIONES Y AREAS LIBRES -------------------");
        System.out.println("-------------------------------------------------------------------");
        System.out.println("Numero   Estado      Direccion      Tamaño     Limite      Proceso");
        System.out.println("-------------------------------------------------------------------");
        while(h.hasNext()){
            Thread.sleep(500);
            Particion p = h.next();
            if(p.estado=='D'){
                System.out.println(""+p.id+"        "+p.estado+"ISPONIBLE   "+p.base+"           "+p.tamanio+"         "+p.limite);
            } else {
                System.out.println(""+p.id+"        "+p.estado+"SIGNADO     "+p.base+"            "+p.tamanio+"         "+p.limite+"           P"+p.procesoAsig);
            }
            System.out.println("-------------------------------------------------------------------");
        }
    }
}
