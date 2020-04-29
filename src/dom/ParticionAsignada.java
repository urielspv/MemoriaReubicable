  /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dom;

/**
 *
 * @author URIEL
 */
public class ParticionAsignada extends Particion{
    
    private String procesoAsig;
   
   public  ParticionAsignada(int id,int base, String proceso, int tamanio){
       this.estado = 'A';
       this.id = id;
       this.base = base;
       this.procesoAsig = proceso;
       this.tamanio = tamanio;
       
   }
   
    @Override
    public void imprime(){
        
        System.out.print(""+id+"  "+base+"   "+tamanio+"  "+estado+"   "+procesoAsig);
       
   }

    public int getId() {
        return id;
    }

    public int getbase() {
        return base;
    }

    public int getTamaño() {
        return tamanio;
    }
    
    public String getProcesoAsig() {
        return procesoAsig;
    }

    
    
    
    public void setId(int numero) {
        this.id = numero;
    }

    public void setBase(int direccion) {
        this.base = direccion;
    }

    public void setTamaño(int tamaño) {
        this.tamanio = tamaño;
    }

   

    public void setProceso(String proceso) {
        this.procesoAsig = proceso;
    }
    
    
   

    @Override
    public int compareTo(Particion o) {
        if (base < o.base) {
                return -1;
            }
            if (base > o.base) {
                return 1;
            }
        
        return 0;
    }

    
    
    
    
    
    
}
