package modelo;

import java.util.Date;

/**
 * Modelo de liquidación
 * @author frami
 */
public class Liquidacion {
    
    private String id;
    private Date fechaIncio;
    private Date fechaFin;
    private String detalle;

    /**
     * Tabla de liquidación en moelo
     */
    public Liquidacion() {
    }
    
    /**
     *
     * @param id
     * @param fechaIncio
     * @param fechaFin
     * @param detalle
     */
    public Liquidacion(String id, Date fechaIncio, Date fechaFin, String detalle) {
        this.id = id;
        this.fechaIncio = fechaIncio;
        this.fechaFin = fechaFin;
        this.detalle = detalle;
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
    public Date getFechaIncio() {
        return fechaIncio;
    }

    /**
     *
     * @param fechaIncio
     */
    public void setFechaIncio(Date fechaIncio) {
        this.fechaIncio = fechaIncio;
    }

    /**
     *
     * @return
     */
    public Date getFechaFin() {
        return fechaFin;
    }

    /**
     *
     * @param fechaFin
     */
    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
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
}
