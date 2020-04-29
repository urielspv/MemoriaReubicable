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
public  class Particion implements Comparable<Particion> {
    protected int tamanio;
    protected int base;
    protected int limite;
    protected int id;
    protected char estado;
    protected String procesoAsig;

    @Override
   
    public int compareTo(Particion o) {
        if (base < o.base) {
                return -1;
            }
            if (base > o.base) {
                return 1;
            }
        return 0;    }
    
    /**
     *
     */
    
  
    
    
    
}
