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
    
    private Proceso procesoAsig;
   
   public  ParticionAsignada(int id,int base, Proceso proceso){
       this.estado = 'A';
       this.id = id;
       this.base = base;
       this.procesoAsig = proceso;
       this.limite = base + procesoAsig.getTamanio();
       
       
   }
   
    @Override
    public void imprime(){
        
        System.out.print(""+id+"  "+base+"   "+tamanio+"  "+estado+"   "+procesoAsig.getNombre());
       
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

   
    

    public Proceso getProcesoAsig() {
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

   

    public void setProceso(Proceso proceso) {
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
