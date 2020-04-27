/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import dom.Administrador;
import java.util.Scanner;

/**
 *
 * @author URIEL
 */
public class MainMemoria {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int ram,so;
        //Definemos valores iniciales
        System.out.print("Ingresa el tamaño de la RAM: ");
        ram = in.nextInt();
        System.out.print("Ingresa el tamaño del SO: ");
        so = in.nextInt();
        
        Administrador adm = new Administrador();
        
        
    }
    
}
