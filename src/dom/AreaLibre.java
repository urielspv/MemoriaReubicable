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
public class AreaLibre extends Particion{
   private int orden;
   
    public AreaLibre(int id,int base, int tamanio){
        this.id = id;
        this.base = base;
        this.tamanio = tamanio;
        this.estado ='D';
        
    }


    @Override
    public int compareTo(Particion o) {
        if (tamanio < o.tamanio) {
                return -1;
            }
            if (tamanio > o.tamanio) {
                return 1;
            }
        return 0;
    }
    public void imprime(){
        System.out.print(""+id+"  "+base+"   "+tamanio+"  "+estado+"  "+orden+"");
       
   }

  
    
}
