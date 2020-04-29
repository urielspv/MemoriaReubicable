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
public class Proceso {
    private int nombre;
    
    private int tamanio;
    public Proceso(int id, int tamanio){
        this.nombre = id;
        this.tamanio = tamanio;
        
    }

    public void setNombre(int nombre) {
        this.nombre = nombre;
    }

   

    public void setTamanio(int tamanio) {
        this.tamanio = tamanio;
    }

    public int getNombre() {
        return nombre;
    }

   
    public int getTamanio() {
        return tamanio;
    }
    
    public void imprime(){
        
        System.out.println("  "+nombre+"        "+tamanio);
    }
    
    
}
