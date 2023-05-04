package controlador;

import providers.GastoProvider;
import com.google.api.core.ApiFuture;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreException;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.database.annotations.Nullable;
import com.mycompany.viuproyecto.Conexion;
import com.toedter.calendar.JDateChooser;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import modelo.*;
import vista.*;

/**
 *Controlador de gasto
 * @author frami
 */
public class GastoControler {

    /**
     *
     */
    public static VistaGasto ventana = new VistaGasto();

    private GastoProvider model;
    private Firestore db;

    /*
    Realizamos la conexión
     */

    /**
     * Aquí se establece la conexión con Firestore
     */

    public GastoControler() {
        db = Conexion.getConnection(); 
        model = new GastoProvider();
    }

    /**
     * Nos muestra el gasto en la tabla
     */
    public static void mostrar() {
        ventana.setVisible(true);
    }

    /**
     * Oculta la tabla
     */
    public static void ocultar() {
        ventana.setVisible(false);
    }

    /*
    Fijamos al modelo Gasto los valores que recibimos como parametros
     */

    /**
     * Permite guardar el gasto
     * @param importe
     * @param fechaRegistro
     * @param fechaPago
     * @param numeroComprobante
     * @param proveedor
     * @param concepto
     * @param servicio
     * @param liquidacion
     */

    public void guardarGasto(double importe, Date fechaRegistro,
            Date fechaPago,
            String numeroComprobante,
            String proveedor,
            String concepto,
            String servicio,
            String liquidacion) {

        Gasto gasto = new Gasto();
        gasto.setImporte(importe);
        gasto.setFechaRegistro(fechaRegistro);
        gasto.setFechaPago(fechaPago);
        gasto.setNumeroComprobante(numeroComprobante);
        gasto.setProveedor(proveedor);
        gasto.setConcepto(concepto);
        gasto.setServicio(servicio);
        gasto.setLiquidacion(liquidacion);

        try {
            model.guardarModeloGasto(gasto);
            JOptionPane.showMessageDialog(null, "Gasto guardado exitosamente");
            
        } catch (InterruptedException | ExecutionException ex) {
            
            JOptionPane.showMessageDialog(null, "Error al guardar el gasto: " + ex.getMessage());
        }
    }

    /*
    Este metodo carga datos en tiempo real todo es gracias a addSnapshotListener 
    que escucha los cambios en la base de datos
     */

    /**
     * Permite cargar la tabla
     * @param tabla
     */

    public static void cargarTablaGastosTR(JTable tabla) {
        Firestore db = Conexion.getConnection();
        CollectionReference gastos = db.collection("Gastos");

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Importe");
        model.addColumn("Fecha Registro");
        model.addColumn("Fecha Pago");
        model.addColumn("Número Comprobante");
        model.addColumn("Proveedor");
        model.addColumn("Concepto");
        model.addColumn("Servicio");
        model.addColumn("Liquidación");

        gastos.addSnapshotListener((@Nullable QuerySnapshot querySnapshot, @Nullable FirestoreException error) -> {
            if (error != null) {
                System.err.println("Error al escuchar cambios: " + error);
                return;
            }

            for (int i = model.getRowCount() - 1; i >= 0; i--) {
                model.removeRow(i);
            }

            for (DocumentSnapshot document : querySnapshot.getDocuments()) {
                Timestamp fechaRegistroTimestamp = document.getTimestamp("fechaRegistro");
                Timestamp fechaPagoTimestamp = document.getTimestamp("fechaPago");
                /*
            Se debe validar que los datos traidos desde la BD no vengan nulos
            eso crea un error y por esa razón no cargará la tabla.
                 */
                Date fechaRegistro = fechaRegistroTimestamp != null ? fechaRegistroTimestamp.toDate() : null;
                Date fechaPago = fechaPagoTimestamp != null ? fechaPagoTimestamp.toDate() : null;
                /*
            Se puede sustituir los valores que se quiera mostrar en la tabla
            En el caso del valor de importe gasto se mostrará por defecto 0.0
            en caso de venir nulo.
                 */
                Double importeGasto = document.getDouble("importe") != null ? document.getDouble("importe") : 0.0;

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                /*
            En el caso de las fechas se fija por defecto un valor tal cual es 0000-00-00
            en caso de venir vacio.
                 */
                String fechaRegistroString = fechaRegistro != null ? dateFormat.format(fechaRegistro) : "0000-00-00";
                String fechaPagoString = fechaPago != null ? dateFormat.format(fechaPago) : "0000-00-00";
                model.addRow(new Object[]{
                    document.getId(),
                    /*
                En el caso de las cadenas de texto se fija una cadena sin ningún caracter
                     */
                    importeGasto,
                    fechaRegistroString,
                    fechaPagoString,
                    document.getString("numeroComprobante") != null ? document.getString("numeroComprobante") : "",
                    document.getString("proveedor") != null ? document.getString("proveedor") : "",
                    document.getString("concepto") != null ? document.getString("concepto") : "",
                    document.getString("servicio") != null ? document.getString("servicio") : "",
                    document.getString("liquidacion") != null ? document.getString("liquidacion") : "",
                });
            }

            tabla.setModel(model);
        });
    }

    /**
     * Permite actualizar el gasto
     * @param idDoc
     * @param importe
     * @param fechaRegistro
     * @param fechaPago
     * @param numeroComprobante
     * @param proveedor
     * @param concepto
     * @param servicio
     * @param liquidacion
     */
    public void actualizarGasto(String idDoc, double importe, Date fechaRegistro,
            Date fechaPago, String numeroComprobante,
            String proveedor, String concepto, String servicio,
            String liquidacion) {
        Gasto gasto = new Gasto();
        gasto.setImporte(importe);
        gasto.setFechaRegistro(fechaRegistro);
        gasto.setFechaPago(fechaPago);
        gasto.setNumeroComprobante(numeroComprobante);
        gasto.setProveedor(proveedor);
        gasto.setConcepto(concepto);
        gasto.setServicio(servicio);
        gasto.setLiquidacion(liquidacion);
        
        try {
            model.actualizarModeloGasto(idDoc, gasto);
            JOptionPane.showMessageDialog(null, "Gasto actualizado exitosamente");
            System.out.println("Gasto actualizado exitosamente");
        } catch (InterruptedException | ExecutionException ex) {
            JOptionPane.showMessageDialog(null, "Error al actualizar el gasto: " + ex.getMessage());
            
        }
    }

    /**
     * Permite seleccionar datos de los desplegables
     * @param box
     * @throws InterruptedException
     * @throws ExecutionException
     * @throws IOException
     */
    public static void cargarComboConcepto(JComboBox<String> box) throws InterruptedException, ExecutionException, IOException {
        Firestore db = Conexion.getConnection();
        CollectionReference proveedores = db.collection("Concepto");
        DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<>();
        modelo.addElement("Seleccione uno");
        proveedores.addSnapshotListener((querySnapshot, e) -> {
            if (e != null) {
                System.err.println("Ocurrió un error al escuchar los cambios de proveedores: " + e);
                return;
            }
            modelo.removeAllElements();
            modelo.addElement("Seleccione uno");
            for (DocumentSnapshot document : querySnapshot.getDocuments()) {
                modelo.addElement(document.getString("nombre"));
            }
            box.setModel(modelo);
        });
    }

    /**
     *Permite seleccionar datos de los desplegables
     * @param box
     * @throws InterruptedException
     * @throws ExecutionException
     * @throws IOException
     */
    public static void cargarComboProveedor(JComboBox<String> box) throws InterruptedException, ExecutionException, IOException {
        Firestore db = Conexion.getConnection();
        CollectionReference proveedores = db.collection("Proveedor");
        DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<>();
        modelo.addElement("Seleccione uno");
        proveedores.addSnapshotListener((querySnapshot, e) -> {
            if (e != null) {
                System.err.println("Ocurrió un error al escuchar los cambios de proveedores: " + e);
                return;
            }
            modelo.removeAllElements();
            modelo.addElement("Seleccione uno");
            for (DocumentSnapshot document : querySnapshot.getDocuments()) {
                modelo.addElement(document.getString("nombre"));
            }
            box.setModel(modelo);
        });
    }

    /**
     *Permite seleccionar datos de los desplegables
     * @param box
     * @throws InterruptedException
     * @throws ExecutionException
     * @throws IOException
     */
    public static void cargarComboServicio(JComboBox<String> box) throws InterruptedException, ExecutionException, IOException {
        Firestore db = Conexion.getConnection();
        CollectionReference proveedores = db.collection("Servicio");
        DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<>();
        modelo.addElement("Seleccione uno");
        proveedores.addSnapshotListener((querySnapshot, e) -> {
            if (e != null) {
                System.err.println("Ocurrió un error al escuchar los cambios de proveedores: " + e);
                return;
            }
            modelo.removeAllElements();
            modelo.addElement("Seleccione uno");
            for (DocumentSnapshot document : querySnapshot.getDocuments()) {
                modelo.addElement(document.getString("nombre"));
            }
            box.setModel(modelo);
        });
    }
    
    /**
     *Permite seleccionar datos de los desplegables
     * @param box
     * @throws InterruptedException
     * @throws ExecutionException
     * @throws IOException
     */
    public static void cargarComboLiquidacion(JComboBox<String> box) throws InterruptedException, ExecutionException, IOException {
        Firestore db = Conexion.getConnection();
        CollectionReference proveedores = db.collection("Liquidacion");
        DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<>();
        modelo.addElement("Seleccione uno");
        proveedores.addSnapshotListener((querySnapshot, e) -> {
            if (e != null) {
                System.err.println("Ocurrió un error al escuchar los cambios de proveedores: " + e);
                return;
            }
            modelo.removeAllElements();
            modelo.addElement("Seleccione uno");
            for (DocumentSnapshot document : querySnapshot.getDocuments()) {
                modelo.addElement(document.getString("detalle"));
            }
            box.setModel(modelo);
        });
    }

    /**
     * Permite eliminar un gasto
     * @param idDoc
     */
    public void eliminarGasto(String idDoc) {
        try {
            model.eliminarModeloGasto(idDoc);
            JOptionPane.showMessageDialog(null, "Gasto eliminado exitosamente");
            
        } catch (InterruptedException | ExecutionException ex) {
            JOptionPane.showMessageDialog(null, "Error al eliminar el gasto: " + ex.getMessage());
            
        }
    }

    /**
     * Permite limpiar los campos de los gastos
     * @param txtImporte
     * @param txtNroComprobante
     * @param jFechaGasto
     * @param jFechaPago
     * @param cmbProveedor
     * @param cmbServicio
     * @param cmbConcepto
     * @param cmbLiquidacion
     */
    public void limpiarCampos(
            JTextField txtImporte,
            JTextField txtNroComprobante,
            JDateChooser jFechaGasto,
            JDateChooser jFechaPago,
            JComboBox cmbProveedor,
            JComboBox cmbServicio,
            JComboBox cmbConcepto,
            JComboBox cmbLiquidacion) {
        
        txtImporte.setText("");
        txtNroComprobante.setText("");
        jFechaGasto.setDate(null);
        jFechaPago.setDate(null);
        cmbProveedor.setSelectedIndex(0);
        cmbServicio.setSelectedIndex(0);
        cmbConcepto.setSelectedIndex(0);
        cmbLiquidacion.setSelectedIndex(0);
    }
}