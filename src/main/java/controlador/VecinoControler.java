/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import vista.*;

/**
 * Controlador de vecino
 * @author frami
 */
public class VecinoControler {

    /**
     * Clase de vista de vecino
     */
    public static VistaVecino ventana = new VistaVecino();

    /**
     * Muestra la vista de vecino
     */
    public static void mostrar(){ ventana.setVisible(true);}

    /**
     * Oculta la vista de vecino
     */
    public static void ocultar(){ ventana.setVisible(false);}
}
