package co.edu.udea.pi2.ubicameudea.model.dto;

/**
 * Created by Alexis on 18/06/15.
 */
public class TipoUnidad {

    private Integer idTipoUnidad;
    private String nombre;

    public TipoUnidad(){
        super();
    }

    public Integer getIdTipoUnidad() {
        return idTipoUnidad;
    }

    public void setIdTipoUnidad(Integer idTipoUnidad) {
        this.idTipoUnidad = idTipoUnidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
