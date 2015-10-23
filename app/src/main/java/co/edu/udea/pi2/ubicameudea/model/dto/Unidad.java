package co.edu.udea.pi2.ubicameudea.model.dto;

/**
 * Created by Alexis on 18/06/15.
 */
public class Unidad {

    private Integer idUnidad;
    private String nombre;
    private TipoUnidad tipoUnidad;

    public Integer getIdUnidad() {
        return idUnidad;
    }

    public void setIdUnidad(Integer idUnidad) {
        this.idUnidad = idUnidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoUnidad getTipoUnidad() {
        return tipoUnidad;
    }

    public void setTipoUnidad(TipoUnidad tipoUnidad) {
        this.tipoUnidad = tipoUnidad;
    }
}
