package com.mycompany.viuproyecto;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase que me permite conectar con la base de datos
 * @author frami
 */
public class Conexion {
    
    /** Esta es la conexión a la Base de datos Firebase y la llamo db
     * 
     */
    
    public static Firestore db;
    
    /**En este caso utilizo un metodo de tipo Firestore para conectar aplicando la arquitectura MVC
     * 
     * @return 
     */
     public static Firestore getConnection() {
        FirestoreOptions firestoreOptions = null;
        try {
            firestoreOptions = FirestoreOptions.newBuilder()
                    .setCredentials(GoogleCredentials.fromStream(new FileInputStream("viuproyecto.json")))
                    .build();
        } catch (IOException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        Firestore dbMVC = firestoreOptions.getService();
        return dbMVC;
    }
}
