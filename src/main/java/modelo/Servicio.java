package modelo;

/**
 * Modelo de servicio
 * @author frami
 */
public class Servicio {
 
    private String id;
    private String nombre;
    private String detalle;
    private boolean isObligatorio;

    /**
     * Tabla de servicio en modelo
     */
    public Servicio() {
    }
    
    /**
     *
     * @param id
     * @param nombre
     * @param detalle
     * @param isObligatorio
     */
    public Servicio(String id, String nombre, String detalle, boolean isObligatorio) {
        this.id = id;
        this.nombre = nombre;
        this.detalle = detalle;
        this.isObligatorio = isObligatorio;
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
    public String getDetalle() {
        return detalle;
    }

    /**
     *
     * @param detalle
     */
    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    /**
     *
     * @return
     */
    public boolean getIsObligatorio() {
        return isObligatorio;
    }

    /**
     *
     * @param isObligatorio
     */
    public void setIsObligatorio(boolean isObligatorio) {
        this.isObligatorio = isObligatorio;
    }
}
