package providers;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.mycompany.viuproyecto.Conexion;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import modelo.Proveedor;

/**
 * consultas sql de proveedor
 * @author frami
 */
public class ProveedorProvider {

    private Firestore db;

    /**
     * Conexion base de datos proveedor
     */
    public ProveedorProvider() {
        this.db = Conexion.getConnection();
    }

    /*
    Recibimos por parametro el modelo correspondiente para realizar la escritura del mismo en la DB Firestore
     */

    /**
     *
     * @param proveedor
     * @throws InterruptedException
     * @throws ExecutionException
     */

    public void guardarModeloProveedor(Proveedor proveedor) throws InterruptedException, ExecutionException {
        DocumentReference docRef = db.collection("Proveedor").document();
        Map<String, Object> data = new HashMap<>();
        data.put("nombre", proveedor.getNombre());
        data.put("telefono", proveedor.getTelefono());
        data.put("email", proveedor.getEmail());
        data.put("direccion", proveedor.getDireccion());
        ApiFuture<WriteResult> result = docRef.set(data);
        result.get();
    }

    /*
    Recibimos por parametro el modelo correspondiente para realizar la escritura del mismo en la DB Firestore
    Además se recibe el id del documento el cual va a ser actualizado. 
     */

    /**
     *
     * @param idDocumento
     * @param proveedor
     * @throws InterruptedException
     * @throws ExecutionException
     */

    public void actualizarModeloProveedor(String idDocumento, Proveedor proveedor) throws InterruptedException, ExecutionException {
        DocumentReference docRef = db.collection("Proveedor").document(idDocumento);
        Map<String, Object> data = new HashMap<>();
        data.put("nombre", proveedor.getNombre());
        data.put("telefono", proveedor.getTelefono());
        data.put("email", proveedor.getEmail());
        data.put("direccion", proveedor.getDireccion());
        ApiFuture<WriteResult> result = docRef.update(data);
        result.get();
    }

    /*
    Se recibe por parametro el id del documento el cual se va a eliminar
     */

    /**
     *
     * @param idDocumento
     * @throws InterruptedException
     * @throws ExecutionException
     */

    public void eliminarModeloProveedor(String idDocumento) throws InterruptedException, ExecutionException {
        DocumentReference docRef = db.collection("Proveedor").document(idDocumento);
        ApiFuture<WriteResult> result = docRef.delete();
        result.get();
    }
}
