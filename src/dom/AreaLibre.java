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
   
    public AreaLibre(int base, int tamanio){
        this.base = base;
        this.tamanio = tamanio;
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

  
    
}
