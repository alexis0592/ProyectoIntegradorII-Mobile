package co.edu.udea.pi2.ubicameudea.model.dto;

/**
 * Created by Alexis on 18/06/15.
 */
public class Departamento {

    private Integer idDepartamento;
    private String nombre;
    private Unidad unidad;

    public Departamento(){
        super();
    }

    public Integer getDepartamentoId() {
        return idDepartamento;
    }

    public void setDepartamentoId(Integer departamentoId) {
        this.idDepartamento = departamentoId;
    }

    public Unidad getUnidad() {
        return unidad;
    }

    public void setUnidad(Unidad unidad) {
        this.unidad = unidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
