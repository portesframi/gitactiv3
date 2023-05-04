package providers;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.mycompany.viuproyecto.Conexion;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import modelo.Servicio;

/**
 * consultas sql de servicio
 * @author frami
 */
public class ServicioProvider {

    private Firestore db;

    /**
     * Conexion base de datos servicio
     */
    public ServicioProvider() {
        this.db = Conexion.getConnection();
    }

    /*
    Recibimos por parametro el modelo correspondiente para realizar la escritura del mismo en la DB Firestore
     */

    /**
     *
     * @param servicio
     * @throws InterruptedException
     * @throws ExecutionException
     */

    public void guardarModeloServicio(Servicio servicio) throws InterruptedException, ExecutionException {
        DocumentReference docRef = db.collection("Servicio").document();
        Map<String, Object> data = new HashMap<>();
        data.put("nombre", servicio.getNombre());
        data.put("detalle", servicio.getDetalle());
        data.put("isObligatorio", servicio.getIsObligatorio());
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
     * @param servicio
     * @throws InterruptedException
     * @throws ExecutionException
     */

    public void actualizarModeloServicio(String idDocumento, Servicio servicio) throws InterruptedException, ExecutionException {
        DocumentReference docRef = db.collection("Servicio").document(idDocumento);
        Map<String, Object> data = new HashMap<>();
        data.put("nombre", servicio.getNombre());
        data.put("detalle", servicio.getDetalle());
        data.put("isObligatorio", servicio.getIsObligatorio());
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

    public void eliminarModeloServicio(String idDocumento) throws InterruptedException, ExecutionException {
        DocumentReference docRef = db.collection("Servicio").document(idDocumento);
        ApiFuture<WriteResult> result = docRef.delete();
        result.get();
    }
}
