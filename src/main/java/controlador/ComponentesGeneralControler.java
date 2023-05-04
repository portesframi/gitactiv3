package controlador;

import java.awt.Component;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

/**
 * Clase que permite gestionar los diferentes componentes de controller
 * @author Frami
 */
public class ComponentesGeneralControler {

    boolean clic = false;

    /*
    Metodo para para controlar los eventos en un JPasswordField
    *
    */

    /**
     * Contraseña
     * @param passwordField
     */

    public void isPasswordFieldClicked(JPasswordField passwordField) {
        /*
        Esta logica lo que hace es cambiar el estado de una variable 
        de tipo boolean al realizar un clic sobre el JPasswordField
        de forma que quite el texto puesto y coloque la variable como
        verdadera para que no se vuelva a quitar el nuevo texto colocado.
        *
        */

        passwordField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                clic = true;
            }

            @Override
            public void focusLost(FocusEvent e) {

            }
        });

        if (clic) {

        } else {
            passwordField.setText("");
        }
    }

    /**
     * Control de contraseñas valida
     * @param contraseña
     * @param contraseñaRep
     * @return
     */
    public boolean contraseñasIguales(String contraseña, String contraseñaRep) {
        // Validar contraseñas que sean iguales
        if (!contraseña.equals(contraseñaRep)) {
            JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden");
            return false;
        }

        return true;
    }

    /**
     * Variables de conexion validas
     * @param correo
     * @param contrasena
     * @return
     */
    public boolean validarCorreoContrasena(String correo, String contrasena) {

        // Validar correo electrónico
        if (!correo.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            JOptionPane.showMessageDialog(null, "Introduzca un correo válido");
            return false;
        }

        // Validar contraseña
        if (contrasena.length() < 6) {
            JOptionPane.showMessageDialog(null, "Introduzca una clave mayor a 5 digitos");
            return false;
        }

        return true;
    }

    /**
     * Selección de opciones
     * @param comboBox
     * @return
     */
    public boolean verificarSeleccion(JComboBox comboBox) {
      if (comboBox.getSelectedIndex()==0) {
          JOptionPane.showMessageDialog(null, "Selecciona una opción");
          //comboBox.setSelectedIndex(1);
          return false;
      }
   return true;
}




}
