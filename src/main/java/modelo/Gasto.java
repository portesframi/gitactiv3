package modelo;

import java.util.Date;

/**
 * Modelo de gasto
 * @author frami
 */
public class Gasto {
    private int id;
    private double importe;
    private Date fechaRegistro;
    private Date fechaPago;
    private String numeroComprobante;
    private String proveedor;
    private String concepto;
    private String servicio;
    private String liquidacion;

    /**
     * Tabla de gasto en modelo
     */
    public Gasto() {
    }

    /**
     *
     * @param id
     * @param importe
     * @param fechaRegistro
     * @param fechaPago
     * @param numeroComprobante
     * @param proveedor
     * @param concepto
     * @param servicio
     * @param liquidacion
     */
    public Gasto(int id, double importe, Date fechaRegistro, Date fechaPago, String numeroComprobante, String proveedor, String concepto, String servicio, String liquidacion) {
        this.id = id;
        this.importe = importe;
        this.fechaRegistro = fechaRegistro;
        this.fechaPago = fechaPago;
        this.numeroComprobante = numeroComprobante;
        this.proveedor = proveedor;
        this.concepto = concepto;
        this.servicio = servicio;
        this.liquidacion = liquidacion;
    }

    /**
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public double getImporte() {
        return importe;
    }

    /**
     *
     * @param importe
     */
    public void setImporte(double importe) {
        this.importe = importe;
    }

    /**
     *
     * @return
     */
    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    /**
     *
     * @param fechaRegistro
     */
    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    /**
     *
     * @return
     */
    public Date getFechaPago() {
        return fechaPago;
    }

    /**
     *
     * @param fechaPago
     */
    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    /**
     *
     * @return
     */
    public String getNumeroComprobante() {
        return numeroComprobante;
    }

    /**
     *
     * @param numeroComprobante
     */
    public void setNumeroComprobante(String numeroComprobante) {
        this.numeroComprobante = numeroComprobante;
    }

    /**
     *
     * @return
     */
    public String getProveedor() {
        return proveedor;
    }

    /**
     *
     * @param proveedor
     */
    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    /**
     *
     * @return
     */
    public String getConcepto() {
        return concepto;
    }

    /**
     *
     * @param concepto
     */
    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    /**
     *
     * @return
     */
    public String getServicio() {
        return servicio;
    }

    /**
     *
     * @param servicio
     */
    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    /**
     *
     * @return
     */
    public String getLiquidacion() {
        return liquidacion;
    }

    /**
     *
     * @param liquidacion
     */
    public void setLiquidacion(String liquidacion) {
        this.liquidacion = liquidacion;
    }

   
}