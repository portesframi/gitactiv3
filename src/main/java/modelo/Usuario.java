package modelo;

/**
 * Modelo de usuario
 * @author frami
 */
public class Usuario {
    
    private String id;
    private String usuario;
    private String contraseña;
    private String dni;
    private String nombre;
    private String tipo;
    private String telefono;
    private String email;
    private long fechaAlta;

    /**
     * Tabla de usuario en modelo
     */
    public Usuario() {
    }

    /**
     *
     * @param id
     * @param usuario
     * @param contraseña
     * @param dni
     * @param nombre
     * @param tipo
     * @param telefono
     * @param email
     * @param fechaAlta
     */
    public Usuario(String id, String usuario, String contraseña, String dni, String nombre, String tipo, String telefono, String email, long fechaAlta) {
        this.id = id;
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.dni = dni;
        this.nombre = nombre;
        this.tipo = tipo;
        this.telefono = telefono;
        this.email = email;
        this.fechaAlta = fechaAlta;
    }

    /**
     *
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     *
     * @param usuario
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     *
     * @return
     */
    public String getContraseña() {
        return contraseña;
    }

    /**
     *
     * @param contraseña
     */
    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    /**
     *
     * @return
     */
    public String getDni() {
        return dni;
    }

    /**
     *
     * @param dni
     */
    public void setDni(String dni) {
        this.dni = dni;
    }

    /**
     *
     * @return
     */
    public String getNombre() {
        return nombre;
    }

    /**
     *
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     *
     * @return
     */
    public String getTipo() {
        return tipo;
    }

    /**
     *
     * @param tipo
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     *
     * @return
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     *
     * @param telefono
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     *
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return
     */
    public long getFechaAlta() {
        return fechaAlta;
    }

    /**
     *
     * @param fechaAlta
     */
    public void setFechaAlta(long fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

   
}
