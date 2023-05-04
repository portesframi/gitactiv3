package providers;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.mycompany.viuproyecto.Conexion;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import modelo.Liquidacion;

/**
 * consultas sql de liquidación
 * @author frami
 */
public class LiquidacionProvider {

    private Firestore db;

    /**
     * Conexion base de datos liquidación
     */
    public LiquidacionProvider() {
        this.db = Conexion.getConnection();
    }

    /*
    Recibimos por parametro el modelo correspondiente para realizar la escritura del mismo en la DB Firestore
     */

    /**
     *
     * @param liquidacion
     * @throws InterruptedException
     * @throws ExecutionException
     */

    public void guardarModelLiquidacion(Liquidacion liquidacion) throws InterruptedException, ExecutionException {
        DocumentReference docRef = db.collection("Liquidacion").document();
        Map<String, Object> data = new HashMap<>();
        data.put("fechaInicio", liquidacion.getFechaIncio());
        data.put("fechaFin", liquidacion.getFechaFin());
        data.put("detalle", liquidacion.getDetalle());
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
     * @param liquidacion
     * @throws InterruptedException
     * @throws ExecutionException
     */

    public void actualizarModeloLiquidacion(String idDocumento, Liquidacion liquidacion) throws InterruptedException, ExecutionException {
        DocumentReference docRef = db.collection("Liquidacion").document(idDocumento);
        Map<String, Object> data = new HashMap<>();
        data.put("fechaInicio", liquidacion.getFechaIncio());
        data.put("fechaFin", liquidacion.getFechaFin());
        data.put("detalle", liquidacion.getDetalle());
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

    public void eliminarModeloLiquidacion(String idDocumento) throws InterruptedException, ExecutionException {
        DocumentReference docRef = db.collection("Liquidacion").document(idDocumento);
        ApiFuture<WriteResult> result = docRef.delete();
        result.get();
    }
}
