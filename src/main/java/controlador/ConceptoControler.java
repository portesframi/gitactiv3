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
import providers.ConceptoProvider;
import vista.VistaConcepto;

/**
 * Controlador de concepto
 * @author frami
 */
public class ConceptoControler {

    private ConceptoProvider conceptoProvider;
    private Firestore db;

    /*
    Realizamos la conexión
     */

    /**
     * Aquí se establece la conexión con Firestore
     */

    public ConceptoControler() {
        db = Conexion.getConnection(); 
        conceptoProvider = new ConceptoProvider();
    }

    /*
    Fijamos al modelo Concepto los valores que recibimos como parametros
     */

    /**
     * atributos de concepto
     * @param nombre
     * @param descripcion
     */

    public void guardarConcepto(String nombre,
            String descripcion) {

        Concepto c = new Concepto();
        c.setNombre(nombre);
        c.setDescripcion(descripcion);

        try {
            conceptoProvider.guardarModeloConcepto(c);
            JOptionPane.showMessageDialog(null, "Concepto guardado exitosamente");
            
        } catch (InterruptedException | ExecutionException ex) {
            
            JOptionPane.showMessageDialog(null, "Error al guardar el concepto: " + ex.getMessage());
        }
    }

    /*
    Este metodo carga datos en tiempo real todo es gracias a addSnapshotListener 
    que escucha los cambios en la base de datos
     */

    /**
     * Cargar tabla conceptos
     * @param tabla
     */

    public static void cargarTablaConceptoTR(JTable tabla) {
        Firestore db = Conexion.getConnection();
        CollectionReference gastos = db.collection("Concepto");

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Nombre");
        model.addColumn("Descripción");

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
                    document.getString("descripcion") != null ? document.getString("descripcion") : "",
                });

            }

            tabla.setModel(model);
        });
    }

    /**
     * atributos de concepto  actualizar
     * @param idDoc
     * @param nombre
     * @param descripcion
     */
    public void actualizarConcepto(String idDoc, String nombre,
            String descripcion) {
        Concepto c = new Concepto();
        c.setNombre(nombre);
        c.setDescripcion(descripcion);

        try {
            conceptoProvider.actualizarModeloConcepto(idDoc, c);
            JOptionPane.showMessageDialog(null, "Concepto actualizado exitosamente");
            
        } catch (InterruptedException | ExecutionException ex) {
            JOptionPane.showMessageDialog(null, "Error al actualizar el concepto: " + ex.getMessage());
            
        }
    }

    /**
     * Permite eliminar un concepto
     * @param idDoc
     */
    public void eliminarConcepto(String idDoc) {
        try {
            conceptoProvider.eliminarModeloConcepto(idDoc);
            JOptionPane.showMessageDialog(null, "Concepto eliminado exitosamente");
            
        } catch (InterruptedException | ExecutionException ex) {
            JOptionPane.showMessageDialog(null, "Error al eliminar el concepto: " + ex.getMessage());
            
        }
    }

    /**
     * Para limpiar los campos de la tabla concepto
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
