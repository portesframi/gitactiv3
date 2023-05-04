package controlador;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QuerySnapshot;
import com.mycompany.viuproyecto.Conexion;
import java.util.concurrent.ExecutionException;
import providers.GastoProvider;

/**
 * Controlador de Login
 * @author frami
 */
public class LoginControler extends ComponentesGeneralControler{

    private GastoProvider model;
    private Firestore db;
    
    boolean clic = false;

    /**
     * Autentificación de usuario
     * @param correo
     * @param clave
     * @return
     * @throws InterruptedException
     * @throws ExecutionException
     */
    public boolean autenticarUsuario(String correo, String clave) throws InterruptedException, ExecutionException {
        // Obtener la instancia de Firestore
        db = Conexion.getConnection();

        // Crear una referencia a la colección "Usuario"
        CollectionReference usuarios = db.collection("Usuario");

        // Crear una consulta para buscar el usuario con el correo especificado
        Query consulta = usuarios.whereEqualTo("email", correo);

        // Obtener los resultados de la consulta
        ApiFuture<QuerySnapshot> resultados = consulta.get();

        // Verificar si se encontró algún usuario con el correo especificado
        if (resultados.get().size() == 0) {
            return false;
        }

        // Obtener el primer documento (en teoría solo debería haber uno)
        DocumentSnapshot usuario = resultados.get().getDocuments().get(0);

        // Verificar si la clave es correcta
        String claveAlmacenada = usuario.getString("contraseña");
        return clave.equals(claveAlmacenada);
    }

}
