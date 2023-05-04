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
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import modelo.Proveedor;
import providers.ProveedorProvider;

/**
 * Controlador de proveedor
 * @author frami
 */
public class ProveedorControler {
    
    private ProveedorProvider proveedorProvider;
    private Firestore db;

    /*
    Realizamos la conexión
     */

    /**
     * Aquí se establece la conexión con Firestore
     */

    public ProveedorControler() {
        db = Conexion.getConnection(); 
        proveedorProvider = new ProveedorProvider();
    }

    /*
    Fijamos al modelo Proveedor los valores que recibimos como parametros
     */

    /**
     * Permite guardar un proveedor
     * @param nombre
     * @param telefono
     * @param email
     * @param direccion
     */

    public void guardarProveedor(String nombre,
            String telefono,
            String email,
            String direccion) {

        Proveedor p = new Proveedor();
        p.setNombre(nombre);
        p.setTelefono(telefono);
        p.setEmail(email);
        p.setDireccion(direccion);
        try {
            proveedorProvider.guardarModeloProveedor(p);
            JOptionPane.showMessageDialog(null, "Proveedor guardado exitosamente");
            
        } catch (InterruptedException | ExecutionException ex) {
            
            JOptionPane.showMessageDialog(null, "Error al guardar el proveedor: " + ex.getMessage());
        }
    }

    /*
    Este metodo carga datos en tiempo real todo es gracias a addSnapshotListener 
    que escucha los cambios en la base de datos
     */

    /**
     * Permite cargar la tabla d proveedores
     * @param tabla
     */

    public static void cargarTablaProveedorTR(JTable tabla) {
        Firestore db = Conexion.getConnection();
        CollectionReference gastos = db.collection("Proveedor");

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Nombre");
        model.addColumn("Telefono");
        model.addColumn("E-mail");
        model.addColumn("Dirección");

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
                    document.getString("telefono") != null ? document.getString("telefono") : "",
                    document.getString("email") != null ? document.getString("email") : "",
                    document.getString("direccion") != null ? document.getString("direccion") : "",});
            }

            tabla.setModel(model);
        });
    }

    /**
     *  Permite actualizar proveedores
     * @param idDoc
     * @param nombre
     * @param telefono
     * @param email
     * @param direccion
     */
    public void actualizarProveedor(String idDoc, String nombre,
            String telefono,
            String email,
            String direccion) {
        Proveedor p = new Proveedor();
        p.setNombre(nombre);
        p.setTelefono(telefono);
        p.setEmail(email);
        p.setDireccion(direccion);
        try {
            proveedorProvider.actualizarModeloProveedor(idDoc, p);
            JOptionPane.showMessageDialog(null, "Proveedor actualizado exitosamente");
            
        } catch (InterruptedException | ExecutionException ex) {
            JOptionPane.showMessageDialog(null, "Error al actualizar el proveedor: " + ex.getMessage());
            
        }
    }

    /**
     * Permite elimianr proveedores
     * @param idDoc
     */
    public void eliminarGasto(String idDoc) {
        try {
            proveedorProvider.eliminarModeloProveedor(idDoc);
            JOptionPane.showMessageDialog(null, "Proveedor eliminado exitosamente");
            
        } catch (InterruptedException | ExecutionException ex) {
            JOptionPane.showMessageDialog(null, "Error al eliminar el proveedor: " + ex.getMessage());
            
        }
    }

    /**
     * Permite limpiar proveedores
     * @param txtNombre
     * @param txtTelefono
     * @param txtEmail
     * @param txtDireccion
     */
    public void limpiarCampos(JTextField txtNombre,
            JTextField txtTelefono,
            JTextField txtEmail,
            JTextField txtDireccion) {
        
        txtNombre.setText("");
        txtTelefono.setText("");
        txtEmail.setText("");
        txtDireccion.setText("");
    }
}
