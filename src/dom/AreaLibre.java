/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dom;
import dom.Administrador;

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
        this.limite = (this.base + this.tamanio);
        this.estado ='D';
        
    }
    
}
