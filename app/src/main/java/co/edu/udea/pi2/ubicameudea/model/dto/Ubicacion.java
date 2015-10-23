package co.edu.udea.pi2.ubicameudea.model.dto;

import java.io.Serializable;

/**
 * Created by Alexis on 18/06/15.
 */
public class Ubicacion implements Serializable{

    private Integer ubicacionId;
    private Integer bloqueId;
    private Integer oficina;
    private Double latitud;
    private Double longitud;
    private Integer departamentoId;
    private Integer unidadId;

    public Ubicacion(){
        super();
    }

    public Integer getUbicacionId() {
        return ubicacionId;
    }

    public void setUbicacionId(Integer ubicacionId) {
        this.ubicacionId = ubicacionId;
    }

    public Integer getBloqueId() {
        return bloqueId;
    }

    public void setBloqueId(Integer bloqueId) {
        this.bloqueId = bloqueId;
    }

    public Integer getOficina() {
        return oficina;
    }

    public void setOficina(Integer oficina) {
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

    public Integer getDepartamentoId() {
        return departamentoId;
    }

    public void setDepartamentoId(Integer departamentoId) {
        this.departamentoId = departamentoId;
    }

    public Integer getUnidadId() {
        return unidadId;
    }

    public void setUnidadId(Integer unidadId) {
        this.unidadId = unidadId;
    }
}
