/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import vista.*;

/**
 * Controlador de inmueble
 * @author frami
 */
public class InmuebleControler {

    /**
     * Declaramos la vista de inmueble
     */
    public static VistaInmueble ventana = new VistaInmueble();

    /**
     * Muestra la vista de inmueble
     */
    public static void mostrar(){ ventana.setVisible(true);}

    /**
     * Oculta la vista de inmueble
     */
    public static void ocultar(){ ventana.setVisible(false);}
}
