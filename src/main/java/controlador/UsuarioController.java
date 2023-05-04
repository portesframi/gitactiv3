package controlador;

import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreException;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.database.annotations.Nullable;
import com.mycompany.viuproyecto.Conexion;
import java.util.concurrent.ExecutionException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import modelo.Concepto;
import modelo.Usuario;
import providers.UsuarioProvider;

/**
 * Controlador de usuario
 * @author frami
 */
public class UsuarioController {

    private UsuarioProvider usuarioProvider;
    private Firestore db;

    /*
    Realizamos la conexión
     */

    /**
     * Aquí se establece la conexión con Firestore
     */

    public UsuarioController() {
        db = Conexion.getConnection(); 
        usuarioProvider = new UsuarioProvider();
    }

    /*
    Fijamos al modelo Concepto los valores que recibimos como parametros
     */

    /**
     * Permite guardar usuarios
     * @param usuario
     * @param contraseña
     * @param dni
     * @param nombre
     * @param tipoUsuario
     * @param telefono
     * @param email
     */

    public void guardarUsuario(
            String usuario,
            String contraseña,
            String dni,
            String nombre,
            String tipoUsuario,
            String telefono,
            String email) {

        Usuario u = new Usuario();
        u.setUsuario(usuario);
        u.setContraseña(contraseña);
        u.setDni(dni);
        u.setNombre(nombre);
        u.setTipo(tipoUsuario);
        u.setTelefono(telefono);
        u.setEmail(email);

        try {
            usuarioProvider.guardarModeloUsuario(u);
            JOptionPane.showMessageDialog(null, "Usuario guardado exitosamente");
            
        } catch (InterruptedException | ExecutionException ex) {
            
            JOptionPane.showMessageDialog(null, "Error al guardar el usuario: " + ex.getMessage());
        }
    }

    /*
    Este metodo carga datos en tiempo real todo es gracias a addSnapshotListener 
    que escucha los cambios en la base de datos
     */

    /**
     * Prmite cargar usuarios
     * @param tabla
     */

    public static void cargarTablaUsuarioTR(JTable tabla) {
        Firestore db = Conexion.getConnection();
        CollectionReference gastos = db.collection("Concepto");

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Nombre");
        model.addColumn("Tipo de usuario");
        model.addColumn("E-mail");
        model.addColumn("Teléfono");
        model.addColumn("Clave");

        gastos.addSnapshotListener((@Nullable QuerySnapshot querySnapshot, @Nullable FirestoreException error) -> {
            if (error != null) {
                System.err.println("Error al escuchar cambios: " + error);
                return;
            }

            for (int i = model.getRowCount() - 1; i >= 0; i--) {
                model.removeRow(i);
            }

            for (DocumentSnapshot document : querySnapshot.getDocuments()) {

                /*
            En el caso de las fechas se fija por defecto un valor tal cual es 0000-00-00
            en caso de venir vacio.
                 */
                model.addRow(new Object[]{
                    document.getId(),
                    /*
                En el caso de las cadenas de texto se fija una cadena sin ningún caracter
                     */
                    document.getString("nombre") != null ? document.getString("nombre") : "",
                    document.getString("tipoUsuario") != null ? document.getString("tipoUsuario") : "",
                    document.getString("email") != null ? document.getString("email") : "",
                    document.getString("tipoUsuario") != null ? document.getString("tipoUsuario") : "",
                    document.getString("telefono") != null ? document.getString("telefono") : "",
                    document.getString("clave") != null ? document.getString("clave") : "",});

            }

            tabla.setModel(model);
        });
    }

    /**
     * Permite actualizar usuarios
     * @param idDoc
     * @param usuario
     * @param contraseña
     * @param dni
     * @param nombre
     * @param tipoUsuario
     * @param telefono
     * @param email
     */
    public void actualizarConcepto(
            String idDoc,
            String usuario,
            String contraseña,
            String dni,
            String nombre,
            String tipoUsuario,
            String telefono,
            String email) {

        Usuario u = new Usuario();
        u.setUsuario(usuario);
        u.setContraseña(contraseña);
        u.setDni(dni);
        u.setNombre(nombre);
        u.setTipo(tipoUsuario);
        u.setTelefono(telefono);
        u.setEmail(email);

        try {
            usuarioProvider.actualizarModeloUsuario(idDoc, u);
            JOptionPane.showMessageDialog(null, "Usuario actualizado exitosamente");
            
        } catch (InterruptedException | ExecutionException ex) {
            JOptionPane.showMessageDialog(null, "Error al actualizar el usuario: " + ex.getMessage());
            
        }
    }

    /**
     * Permite eliminar usuarios
     * @param idDoc
     */
    public void eliminarUsuario(String idDoc) {
        try {
            usuarioProvider.eliminarModeloUsuario(idDoc);
            JOptionPane.showMessageDialog(null, "Usuario eliminado exitosamente");
            
        } catch (InterruptedException | ExecutionException ex) {
            JOptionPane.showMessageDialog(null, "Error al eliminar el usuario: " + ex.getMessage());
            
        }
    }

    /**
     * Permite limpiar campos de usuarios
     * @param txtId
     * @param txtNombre
     * @param txtDescripcion
     */
    public void limpiarCampos(
            JTextField txtId,
            JTextField txtNombre,
            JTextArea txtDescripcion) {

        txtId.setText("");
        txtNombre.setText("");
        txtDescripcion.setText("");
    }
}
