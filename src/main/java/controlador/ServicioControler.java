package controlador;

import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreException;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.database.annotations.Nullable;
import com.mycompany.viuproyecto.Conexion;
import java.util.concurrent.ExecutionException;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import modelo.Proveedor;
import modelo.Servicio;
import providers.ServicioProvider;

/**
 * Controlador de servicios
 * @author frami
 */
public class ServicioControler {

    private ServicioProvider servicioProvider;
    private Firestore db;

    /*
    Realizamos la conexión
     */

    /**
     * Aquí se establece la conexión con Firestore
     */

    public ServicioControler() {
        db = Conexion.getConnection(); 
        servicioProvider = new ServicioProvider();
    }

    /*
    Fijamos al modelo Servicio los valores que recibimos como parametros
     */

    /**
     * Permite guardar servicios
     * @param nombre
     * @param detalle
     * @param isObligatorio
     */

    public void guardarServicio(
            String nombre,
            String detalle,
            boolean isObligatorio) {

        Servicio s = new Servicio();
        s.setNombre(nombre);
        s.setDetalle(detalle);
        s.setIsObligatorio(isObligatorio);

        try {
            servicioProvider.guardarModeloServicio(s);
            JOptionPane.showMessageDialog(null, "Servicio guardado exitosamente");
            
        } catch (InterruptedException | ExecutionException ex) {
            
            JOptionPane.showMessageDialog(null, "Error al guardar el servicio: " + ex.getMessage());
        }
    }

    /*
    Este metodo carga datos en tiempo real todo es gracias a addSnapshotListener 
    que escucha los cambios en la base de datos
     */

    /**
     * Permite cargar la tabla de servicios
     * @param tabla
     */

    public static void cargarTablaServicioTR(JTable tabla) {
        Firestore db = Conexion.getConnection();
        CollectionReference gastos = db.collection("Servicio");

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Nombre");
        model.addColumn("Detalle");
        model.addColumn("Es obligatorio");

        gastos.addSnapshotListener((@Nullable QuerySnapshot querySnapshot, @Nullable FirestoreException error) -> {
            if (error != null) {
                System.err.println("Error al escuchar cambios: " + error);
                return;
            }

            for (int i = model.getRowCount() - 1; i >= 0; i--) {
                model.removeRow(i);
            }

            for (DocumentSnapshot document : querySnapshot.getDocuments()) {

                String isObligatorio = String.valueOf(document.getBoolean("isObligatorio"));
                if (isObligatorio.equals("true")) {
                    isObligatorio = "Si";
                }else{
                    isObligatorio = "No";
                }
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
                    document.getString("detalle") != null ? document.getString("detalle") : "",
                    isObligatorio != null ? isObligatorio : "",});
            }

            tabla.setModel(model);
        });
    }

    /**
     * Permite actualizar servicios
     * @param idDoc
     * @param nombre
     * @param detalle
     * @param isObligatorio
     */
    public void actualizarServicio(
            String idDoc,
            String nombre,
            String detalle,
            boolean isObligatorio) {

        Servicio s = new Servicio();
        s.setNombre(nombre);
        s.setDetalle(detalle);
        s.setIsObligatorio(isObligatorio);

        try {
            servicioProvider.actualizarModeloServicio(idDoc, s);
            JOptionPane.showMessageDialog(null, "Servicio actualizado exitosamente");
            
        } catch (InterruptedException | ExecutionException ex) {
            JOptionPane.showMessageDialog(null, "Error al actualizar el servicio: " + ex.getMessage());
            
        }
    }

    /**
     * Permite eliminar servicios
     * @param idDoc
     */
    public void eliminarServicio(String idDoc) {
        try {
            servicioProvider.eliminarModeloServicio(idDoc);
            JOptionPane.showMessageDialog(null, "Servicio eliminado exitosamente");
            
        } catch (InterruptedException | ExecutionException ex) {
            JOptionPane.showMessageDialog(null, "Error al eliminar el servicio: " + ex.getMessage());
            
        }
    }

    /**
     * Permite limpiar los campos de servicios
     * @param txtNombre
     * @param txtAreaDetalle
     * @param checkBoxObligatorio
     */
    public void limpiarCampos(JTextField txtNombre,
            JTextArea txtAreaDetalle,
            JCheckBox checkBoxObligatorio) {

        txtNombre.setText("");
        txtAreaDetalle.setText("");
        checkBoxObligatorio.setSelected(false);

    }
}
