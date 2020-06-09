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
     
        creaAL(so+1,this.totalDisp);//Primer area libre sin area del SO
    }
    
    
    //
    public boolean AsignaRAM(int tamanio, String p) throws InterruptedException {
        
        Thread.sleep(100);
        imprimeRAM();
        
        int pos = solicitaRAM(tamanio);     //Solicita al AM el tamaño que necesita
         Iterator<Particion> i = RAM.iterator();
         
         
            if ( pos != 0){     // Verifica que encontró una dirección 
            Thread.sleep(100);
//            imprimeRAM();
            System.out.println("\n\nPartición Suficiente en la dirección: ["+pos+"]\n");
            Thread.sleep(2000);
            
            Particion part;
            while(i.hasNext()){
                 part = i.next();
                if(part.base == pos ){                      //busca dentro de las particiones la coincidencia con la posicion encontrada.
                    if ((part.tamanio - tamanio)>0){        //Compara si el AL es más grande de lo necesario
                        creaPA(part.base,tamanio,p);
                                                            //Crea la PA y le resta al AL lo asignado
                        System.out.println("Se le asignan "+tamanio+"K del AREA LIBRE "+part.id+" al proceso P"+p);
                        numAL--;
                        totalDisp = totalDisp - tamanio;
                        creaAL(part.base+tamanio, part.tamanio-tamanio);
                        RAM.remove(part);
                        ordenar();
                        break;
                    }
                    else{                                    //Ingresa si el AL es del mismo tamaño que lo necesario 
                        
                                                             //Crea la particion asignada y elimina el AL
                        creaPA(part.base,part.tamanio, p);
                        RAM.remove(part);
                        totalDisp = totalDisp - tamanio;
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
    
    private  void creaPA(int dir, int tamanio, String proceso){     //Partición Asignada
        this.numPA++;
        ParticionAsignada PA = new ParticionAsignada(numPA + numElim,dir,proceso,tamanio);
        
        RAM.add(PA);
        ordenar();        
    }
  
    private  void creaAL(int dir, int tamanio){                     //Area Libre
        numAL++;
        AreaLibre  AL = new AreaLibre(numAL, dir,tamanio);
        
        RAM.add(AL);
        ordenar();
    }
    

    public int solicitaRAM(int tam){//Tamaño que necesito encontrar
        ordenar();
        int pos = 0;
        int pa = 0;
        int ma = tamanioRAM + 1;
        Iterator<Particion> i = RAM.iterator();
        
       //Mejor Ajuste
        while(i.hasNext()){
        Particion p = i.next(); 
        if((p.tamanio >= tam)&&(p.estado =='D')){
            if (p.tamanio < ma){
                pos = p.base;
                ma = p.tamanio;
            }
        }
        }
        
        
        //Peor Ajuste
//        while(i.hasNext()){
//        Particion p = i.next();
//        if((p.tamanio>= tam)&&(p.estado =='D')){
//            if (p.tamanio > pa){
//                pos = p.base;
//                pa = p.tamanio;
//            }
//        }
//        }
        
        
        return pos;
    }

  
    public void RecuperaMemoria(String Proceso) throws InterruptedException{    //
         Iterator<Particion> i = RAM.iterator();
         Particion p ;
         imprimeRAM();
         while(i.hasNext()){
             p = i.next();
        
             if(p.procesoAsig != null){ //Solo entra si el proceso es ASIGNADO
             if(p.procesoAsig.equals(Proceso)){// Identifica el proceso a ELIMINAR
                 totalDisp = totalDisp + p.tamanio;
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
            
            if(aComparar.estado == 'D'){        //Primero encuentra un AREA LIBRE
            Iterator<Particion> segIndice = RAM.iterator();
             int indice2 = RAM.size();
             
             int cont2 = 0;
            
            while(cont2 < indice2){              //Busca otra AREA LIBRE para verificar que sean contiguas
                Particion comparador = segIndice.next();
                if(comparador.estado == 'D'){ //Al encontrar la otra Area Libre
                    
                    if(aComparar.base == comparador.limite){   //Entra si tiene contiguidad con su base
                    numAL--;
                    creaAL(comparador.base, aComparar.tamanio + comparador.tamanio);
                    RAM.remove(aComparar);
                    RAM.remove(comparador);
                
                    }
                else{                                           //Entra si tiene contiguidad con su limite
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
        System.out.println("El Total de area libre de la Memoria es: "+totalDisp);
        if(totalDisp >= tamanio){
            return true;
        }
        else{
            return false;
        }
        
    }
    
    public void compactacion() throws InterruptedException{ //Mueve los procesos asignados al principio y las AL al final
        System.out.println("La Memoria Se Compactará");
        int base = so + 1;
        int indice = RAM.size();
        int cont =0;
        Iterator<Particion> ItRAM = RAM.iterator();
         while(cont < indice){                              //Compactación de ASIGNADAS
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
         while(cont < indice){                              //Compactación de AREAS LIBRES
             
             Particion l = j.next();
             if(l.estado=='D'){
                 l.base = base;
                 l.limite = base + l.tamanio;
               
                 base = base + l.tamanio;
             }
             cont++;
         }
      
         ordenar();
         verificaContiguidad();                             //Al quedar separadas AL y PA intenta unificar AL
         ordenar();
    }
    
    
    public void ordenar(){              //Ordena por dirección para mejor comprensión.
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
