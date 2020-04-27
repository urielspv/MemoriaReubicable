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
public class Particion {
    
    private int direccion;
    private int tamaño;
    private boolean asignada;
    private Proceso proceso;
    
    

    public int getDireccion() {
        return direccion;
    }

    public int getTamaño() {
        return tamaño;
    }

    public boolean isAsignada() {
        return asignada;
    }
    

    public Proceso getProceso() {
        return proceso;
    }

    public void setDireccion(int direccion) {
        this.direccion = direccion;
    }

    public void setTamaño(int tamaño) {
        this.tamaño = tamaño;
    }

    public void setAsignada(boolean asignada) {
        this.asignada = asignada;
    }

    public void setProceso(Proceso proceso) {
        this.proceso = proceso;
    }
    
    
    
    
    
}
