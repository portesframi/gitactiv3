package modelo;

/**
 * Modelo de cuenta
 * @author frami
 */
public class Cuenta {
    
    private String id;
    private Inmueble inmueble;
    private Servicio servicio;
    private Concepto concepto;
    private double saldo;

    /**
     * atributos de cuenta
     * @param id
     * @param inmueble
     * @param servicio
     * @param concepto
     * @param saldo
     */
    public Cuenta(String id, Inmueble inmueble, Servicio servicio, Concepto concepto, double saldo) {
        this.id = id;
        this.inmueble = inmueble;
        this.servicio = servicio;
        this.concepto = concepto;
        this.saldo = saldo;
    }

    /**
     * cargar id cuenta
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     * modificar id cuenta
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * cargar inmueble
     * @return
     */
    public Inmueble getInmueble() {
        return inmueble;
    }

    /**
     * modificar inmueble
     * @param inmueble
     */
    public void setInmueble(Inmueble inmueble) {
        this.inmueble = inmueble;
    }

    /**
     * cargar servicio
     * @return
     */
    public Servicio getServicio() {
        return servicio;
    }

    /**
     * modificar servicio
     * @param servicio
     */
    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    /**
     *  cargar concepto
     * @return
     */
    public Concepto getConcepto() {
        return concepto;
    }

    /**
     * modificar concepto
     * @param concepto
     */
    public void setConcepto(Concepto concepto) {
        this.concepto = concepto;
    }

    /**
     * cargar saldo
     * @return
     */
    public double getSaldo() {
        return saldo;
    }

    /**
     * modificar saldo
     * @param saldo
     */
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }    
}
