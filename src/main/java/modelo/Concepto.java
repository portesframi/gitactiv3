package modelo;

import java.util.List;

/**
 * Modelo de concepto
 * @author frami
 */
public class Concepto {
    
    private String id;
    private String nombre;
    private String descripcion;

    /**
     * Tabla de concepto en el modelo
     */
    public Concepto() {
    }
    
    /**
     * atributos de concepto
     * @param id
     * @param nombre
     * @param descripcion
     */
    public Concepto(String id, String nombre, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    /**
     * cargar concepto
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     * modificar concepto
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * cargar nombre
     * @return
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * modificar nombre
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * cargaar descripción
     * @return
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * modificar descripción
     * @param descripcion
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
