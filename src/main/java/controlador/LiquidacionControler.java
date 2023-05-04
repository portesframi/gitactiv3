package controlador;

import com.google.cloud.Timestamp;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreException;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.database.annotations.Nullable;
import com.mycompany.viuproyecto.Conexion;
import com.toedter.calendar.JDateChooser;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import modelo.Liquidacion;
import providers.LiquidacionProvider;

/**
 * Controlador de liquidación
 * @author frami
 */
public class LiquidacionControler {
   
    private LiquidacionProvider liquidacionProvider;
    private Firestore db;

    /*
    Realizamos la conexión
     */

    /**
     * Aquí se establece la conexión con Firestore
     */

    public LiquidacionControler() {
        db = Conexion.getConnection(); 
        liquidacionProvider = new LiquidacionProvider();
    }

    /*
    Fijamos al modelo Liquidacion los valores que recibimos como parametros
     */

    /**
     * Permite guaardar la liquidación
     * @param fechaInicio
     * @param fechaFin
     * @param detalle
     */

    public void guardarLiquidacion(
            Date fechaInicio,
            Date fechaFin,
            String detalle) {

        Liquidacion l = new Liquidacion();
        l.setFechaIncio(fechaInicio);
        l.setFechaFin(fechaFin);
        l.setDetalle(detalle);

        try {
            liquidacionProvider.guardarModelLiquidacion(l);
            JOptionPane.showMessageDialog(null, "Liquidación guardada exitosamente");
            
        } catch (InterruptedException | ExecutionException ex) {
            
            JOptionPane.showMessageDialog(null, "Error al guardar la liquidación: " + ex.getMessage());
        }
    }

    /*
    Este metodo carga datos en tiempo real todo es gracias a addSnapshotListener 
    que escucha los cambios en la base de datos
     */

    /**
     * Permite cargar la tabla de liquidaciones
     * @param tabla
     */

    public static void cargarTablaLiquidacionTR(JTable tabla) {
        Firestore db = Conexion.getConnection();
        CollectionReference liquidacion = db.collection("Liquidacion");

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Fecha inicio");
        model.addColumn("Fecha Fin");
        model.addColumn("Detalle");

        liquidacion.addSnapshotListener((@Nullable QuerySnapshot querySnapshot, @Nullable FirestoreException error) -> {
            if (error != null) {
                System.err.println("Error al escuchar cambios: " + error);
                return;
            }

            for (int i = model.getRowCount() - 1; i >= 0; i--) {
                model.removeRow(i);
            }

            for (DocumentSnapshot document : querySnapshot.getDocuments()) {
                Timestamp fechaInicioTimestamp = document.getTimestamp("fechaInicio");
                Timestamp fechaFinTimestamp = document.getTimestamp("fechaFin");
                /*
            Se debe validar que los datos traidos desde la BD no vengan nulos
            eso crea un error y por esa razón no cargará la tabla.
                 */
                Date fechaInicio = fechaInicioTimestamp != null ? fechaInicioTimestamp.toDate() : null;
                Date fechaFin = fechaFinTimestamp != null ? fechaFinTimestamp.toDate() : null;
                /*
            Se puede sustituir los valores que se quiera mostrar en la tabla
            En el caso del valor de importe gasto se mostrará por defecto 0.0
            en caso de venir nulo.
                 */
                String detalle = document.getString("detalle") != null ? document.getString("detalle") : "";

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                /*
            En el caso de las fechas se fija por defecto un valor tal cual es 0000-00-00
            en caso de venir vacio.
                 */
                String fechaInicioString = fechaInicio != null ? dateFormat.format(fechaInicio) : "0000-00-00";
                String fechaFinString = fechaFin != null ? dateFormat.format(fechaFin) : "0000-00-00";
                model.addRow(new Object[]{
                    document.getId(),
                    /*
                En el caso de las cadenas de texto se fija una cadena sin ningún caracter
                     */
                    fechaInicioString,
                    fechaFinString,
                    detalle,
                    });
            }

            tabla.setModel(model);
        });
    }

    /**
     * Permite actualizar las liquidaciones
     * @param idDoc
     * @param fechaInicio
     * @param fechaFin
     * @param detalle
     */
    public void actualizarLiquidacion(
            String idDoc,
            Date fechaInicio,
            Date fechaFin,
            String detalle) {
        
        Liquidacion l = new Liquidacion();
        l.setFechaIncio(fechaInicio);
        l.setFechaFin(fechaFin);
        l.setDetalle(detalle);

        try {
            liquidacionProvider.actualizarModeloLiquidacion(idDoc, l);
            JOptionPane.showMessageDialog(null, "Liquidación actualizado exitosamente");
            
        } catch (InterruptedException | ExecutionException ex) {
            JOptionPane.showMessageDialog(null, "Error al actualizar la liquidación: " + ex.getMessage());
            
        }
    }

    /**
     * Permite elimiar una liquidación
     * @param idDoc
     */
    public void eliminarLiquidacion(String idDoc) {
        try {
            liquidacionProvider.eliminarModeloLiquidacion(idDoc);
            JOptionPane.showMessageDialog(null, "Liquidación eliminado exitosamente");
            
        } catch (InterruptedException | ExecutionException ex) {
            JOptionPane.showMessageDialog(null, "Error al eliminar la liquidación: " + ex.getMessage());
            
        }
    }

    /**
     * Permite limpiar los campos de liquidación
     * @param txtId
     * @param mFechaInicio
     * @param mFechFin
     * @param txtDetalle
     */
    public void limpiarCampos(
            JTextField txtId,
            JDateChooser mFechaInicio,
            JDateChooser mFechFin,
            JTextArea txtDetalle) {

        txtId.setText("");
        mFechaInicio.setDate(null);
        mFechFin.setDate(null);
        txtDetalle.setText("");
    }
}
