package providers;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.mycompany.viuproyecto.Conexion;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import modelo.Usuario;

/**
 * consultas sql de usuario
 * @author frami
 */
public class UsuarioProvider {

    private Firestore db;

    /**
     * conexion base de datos usuario
     */
    public UsuarioProvider() {
        this.db = Conexion.getConnection();
    }

    /*
    Recibimos por parametro el modelo correspondiente para realizar la escritura del mismo en la DB Firestore
     */

    /**
     *
     * @param usuario
     * @throws InterruptedException
     * @throws ExecutionException
     */

    public void guardarModeloUsuario(Usuario usuario) throws InterruptedException, ExecutionException {
        DocumentReference docRef = db.collection("Usuario").document();
        Map<String, Object> data = new HashMap<>();
        data.put("usuario", usuario.getUsuario());
        data.put("contraseña", usuario.getContraseña());
        data.put("dni", usuario.getDni());
        data.put("nombre", usuario.getNombre());
        data.put("tipo", usuario.getTipo());
        data.put("telefono", usuario.getTelefono());
        data.put("email", usuario.getEmail());
        data.put("fechaAlta", new Date().getTime());
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
     * @param usuario
     * @throws InterruptedException
     * @throws ExecutionException
     */

    public void actualizarModeloUsuario(String idDocumento, Usuario usuario) throws InterruptedException, ExecutionException {
        DocumentReference docRef = db.collection("Usuario").document(idDocumento);
        Map<String, Object> data = new HashMap<>();
        data.put("usuario", usuario.getUsuario());
        data.put("contraseña", usuario.getContraseña());
        data.put("dni", usuario.getDni());
        data.put("nombre", usuario.getNombre());
        data.put("tipo", usuario.getTipo());
        data.put("telefono", usuario.getTelefono());
        data.put("email", usuario.getEmail());
        data.put("fechaAlta", new Date().getTime());
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

    public void eliminarModeloUsuario(String idDocumento) throws InterruptedException, ExecutionException {
        DocumentReference docRef = db.collection("Usuario").document(idDocumento);
        ApiFuture<WriteResult> result = docRef.delete();
        result.get();
    }
}
