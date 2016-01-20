package co.edu.udea.pi2.ubicameudea.model.dto;

import java.io.Serializable;

/**
 * Created by Alexis on 18/06/15.
 */
public class Ubicacion implements Serializable{

    private String ubicacionId;
    private String bloqueId;
    private String oficina;
    private Double latitud;
    private Double longitud;
    private String departamentoId;
    private String unidadId;
    private String descripcion;

    public Ubicacion(){
        super();
    }

    public String getUbicacionId() {
        return ubicacionId;
    }

    public void setUbicacionId(String ubicacionId) {
        this.ubicacionId = ubicacionId;
    }

    public String getBloqueId() {
        return bloqueId;
    }

    public void setBloqueId(String bloqueId) {
        this.bloqueId = bloqueId;
    }

    public String getOficina() {
        return oficina;
    }

    public void setOficina(String oficina) {
        this.oficina = oficina;
    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    public String getDepartamentoId() {
        return departamentoId;
    }

    public void setDepartamentoId(String departamentoId) {
        this.departamentoId = departamentoId;
    }

    public String getUnidadId() {
        return unidadId;
    }

    public void setUnidadId(String unidadId) {
        this.unidadId = unidadId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
