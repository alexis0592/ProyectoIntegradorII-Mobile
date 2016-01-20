package co.edu.udea.pi2.ubicameudea.domain.process;

import java.util.List;

import co.edu.udea.pi2.ubicameudea.model.dto.Ubicacion;

/**
 * Created by Alexis on 18/06/15.
 */
public interface IUbicacionProcess {

    public Ubicacion saveUbicacion(Ubicacion ubicacion);

    public Ubicacion finUbicacionByBloqAndOffice(int bloq, int office);

    public List<Ubicacion> findUbicacion(String idUnidad, String idDepartamento, String idBloque);

}
