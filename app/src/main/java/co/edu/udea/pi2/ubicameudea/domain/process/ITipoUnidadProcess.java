package co.edu.udea.pi2.ubicameudea.domain.process;

import java.util.List;

import co.edu.udea.pi2.ubicameudea.model.dto.TipoUnidad;

/**
 * Created by CristianCamilo on 13/07/2015.
 */
public interface ITipoUnidadProcess {

    public TipoUnidad saveTipoUnidad(TipoUnidad tipoUnidad);

    public List<TipoUnidad> findAll();
}
