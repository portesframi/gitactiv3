package modelo;

/**
 * Modelo de inmueble
 * @author frami
 */
public class Inmueble {
    
    private String id;
    private int numero;
    private String direccion;
    private Usuario propietario;

    /**
     *
     * @param id
     * @param numero
     * @param direccion
     * @param propietario
     */
    public Inmueble(String id, int numero, String direccion, Usuario propietario) {
        this.id = id;
        this.numero = numero;
        this.direccion = direccion;
        this.propietario = propietario;
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
    public int getNumero() {
        return numero;
    }

    /**
     *
     * @param numero
     */
    public void setNumero(int numero) {
        this.numero = numero;
    }

    /**
     *
     * @return
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     *
     * @param direccion
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     *
     * @return
     */
    public Usuario getPropietario() {
        return propietario;
    }

    /**
     *
     * @param propietario
     */
    public void setPropietario(Usuario propietario) {
        this.propietario = propietario;
    }
}
