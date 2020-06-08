package dom;

import java.util.ArrayList;
import java.util.Iterator;


public class Administrador  {
    ArrayList<Particion> RAM;
    private int so;
    public int tamanioRAM;
    private int numPA;
    private int numAL;
    private int totalDisp;
    private int numElim;
    
    
    public Administrador(int tamanio,int so){
        RAM = new ArrayList();
        this.numAL = 0;
        this.numPA = 0;
        this.tamanioRAM = tamanio;
        this.so = so;
        this.totalDisp = this.tamanioRAM - so;
        this.numElim = 0;
        creaAL(so+1,this.totalDisp);
    }
    
    public boolean AsignaRAM(int tamanio, String p) throws InterruptedException {
        
        Thread.sleep(100);
        imprimeRAM();
        int pos = solicitaRAM(tamanio);
         Iterator<Particion> i = RAM.iterator();
        if ( pos != 0){
            Thread.sleep(100);
//            imprimeRAM();
            System.out.println("\n\nArea Libre encontrada en la dirección: ["+pos+"]\n");
            Thread.sleep(2000);
            
            Particion part;
            while(i.hasNext()){
                 part = i.next();
                if(part.base == pos ){      //busca dentro de las particiones la coincidencia con la posicion encontrada.
                    if ((part.tamanio - tamanio)>0){        //Compara si el tamaño es del mismo tamaño o es más grande el area libre.
                        numAL--;
                        creaPA(part.base,tamanio,p);
                        System.out.println("Se asignó el Area Libre al proceso "+p);
                        creaAL(part.base+tamanio, part.tamanio-tamanio);
                        RAM.remove(part);
                        ordenar();
                        break;
                    }
                    else{
                        //Crea la particion asignada y elimina el AL
                        creaPA(part.base,part.tamanio, p);
                        RAM.remove(part);
                        numAL--;
                        ordenar();
                    }
                }
            }
            
             return true;
                }
        else{
            
            System.out.println("\n\n\nNo Existe Partición Suficiente");
            ordenar();
            return false;
        }
        
    }
    
    private  void creaPA(int dir, int tamanio, String proceso){
        this.numPA++;
        ParticionAsignada PA = new ParticionAsignada(numPA + numElim,dir,proceso,tamanio);
        RAM.add(PA);
        ordenar();
//        System.out.println("se agregó la PA"+RAM.get(RAM.size()-1).id);
        
    }
    
    /**
     *@param dir
     *@param tamanio
     * 
     **/
    private  void creaAL(int dir, int tamanio){
        numAL++;
        AreaLibre  AL = new AreaLibre(numAL , dir,tamanio);
        RAM.add(AL);
        ordenar();
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
        ordenar();
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

  
    public void RecuperaMemoria(String Proceso) throws InterruptedException{
         Iterator<Particion> i = RAM.iterator();
         Particion p ;
         imprimeRAM();
         while(i.hasNext()){
             p = i.next();
             System.out.println("comparanto con "+p.procesoAsig);
             if(p.procesoAsig != null){
             if(p.procesoAsig.equals(Proceso)){
                 System.out.println("Sencontró" + Proceso);
                 creaAL(p.base, p.tamanio);
                 RAM.remove(p);
                 numElim ++;
                 numPA--;
                 System.out.println("\n\n\nProceso P"+Proceso+" Eliminado");
                 
                 break;
             }
         }
         }
        
    }
    
    /**
     * Verifica la contiguidad de una particion
     */
    private void verificaContiguidad() throws InterruptedException{
        int tamOriginal = RAM.size();
        int cont =0;
        Iterator<Particion> h = RAM.iterator();
        while(h.hasNext()){
            Particion aComparar = h.next();
            
            if(aComparar.estado == 'D'){
            Iterator<Particion> segIndice = RAM.iterator();
             int indice2 = RAM.size();
             
             int cont2 = 0;
            
            while(cont2 < indice2){
                Particion comparador = segIndice.next();
                if(comparador.estado == 'D'){ //Al encontrar la primer partición DISPONIBLE
                    
                    if(aComparar.base == comparador.limite){ // 
                    numAL--;
                    creaAL(comparador.base, aComparar.tamanio + comparador.tamanio);
                    RAM.remove(aComparar);
                    RAM.remove(comparador);
                
                    }
                else{
                    if(aComparar.limite == comparador.base ){
                    numAL = numAL - 2;
                    creaAL(aComparar.base, comparador.tamanio + aComparar.tamanio);
                    RAM.remove(aComparar);
                    RAM.remove(comparador);

                }
                }
            }
                 cont2++;
            }
            }
            cont++;
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
    
    public void compactacion() throws InterruptedException{
        System.out.println("La Memoria Se Compactará");
        int base = so + 1;
        int indice = RAM.size();
        int cont =0;
        Iterator<Particion> ItRAM = RAM.iterator();
         while(cont < indice){
             Particion p = ItRAM.next();
             if(p.estado=='A'){
                 p.base = base;
                 p.limite = base + p.tamanio;
                 base = base + p.tamanio;
             }
          
             cont++;
         }
         
         Iterator<Particion> j = RAM.iterator();
         cont = 0;
         while(cont < indice){
             
             Particion l = j.next();
             if(l.estado=='D'){
                 l.base = base;
                 l.limite = base + l.tamanio;
               
                 base = base + l.tamanio;
             }
             cont++;
         }
      
         ordenar();
         verificaContiguidad();
         ordenar();
    }
    
    
    public void ordenar(){
    RAM.sort((o1, o2) -> o1.compareTo(o2));
    }

    
    

    
    public void imprimeRAM() throws InterruptedException{
   
        Iterator<Particion> h = RAM.iterator();
     
        System.out.println("___________________________________________________________________");
        System.out.println("PARTICIONES ASIGNADAS: "+numPA+"                          AREAS LIBRES: "+numAL);
        System.out.println("-------------------------------------------------------------------");
        System.out.println("-------------------- PARTICIONES Y AREAS LIBRES -------------------");
        System.out.println("-------------------------------------------------------------------");
        System.out.println("Numero   Estado      Direccion      Tamaño     Limite      Proceso");
        System.out.println("___________________________________________________________________");
         System.out.println("SO"      +"        "+"            "+"1             "+so+"K         Sistema Operativo");
         System.out.println("___________________________________________________________________");
        while(h.hasNext()){
            
            Thread.sleep(100);
            Particion p = h.next();
            int limit = p.limite - 1;
            
            if(p.estado=='D'){
                System.out.println(""+p.id+"        "+p.estado+"ISPONIBLE   "+p.base+"           "+p.tamanio+"K         "+limit);
            } else {
                System.out.println(""+p.id+"        "+p.estado+"SIGNADO     "+p.base+"            "+p.tamanio+"K         "+limit+"           P"+p.procesoAsig);
            }
            System.out.println("___________________________________________________________________");
       
        }
    }
}
