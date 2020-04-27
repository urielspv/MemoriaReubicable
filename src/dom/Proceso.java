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
    private String nombre;
    private int duracion;
    private int llegada;
    private int prioridad;
    private int tiempoEspera;
    private int quantum;
    private boolean estado;
    private Proceso sig;
    private int quantumUsado;
}
