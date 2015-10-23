package co.edu.udea.pi2.ubicameudea.domain.process;

import java.util.List;

import co.edu.udea.pi2.ubicameudea.model.dto.Unidad;

/**
 * Created by CristianCamilo on 13/07/2015.
 */
public interface IUnidadProcess {

    public List<Unidad> findUnidadesByTipo(int idTipo);

}
