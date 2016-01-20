package co.edu.udea.pi2.ubicameudea.domain.process;

import java.util.List;

import co.edu.udea.pi2.ubicameudea.model.dto.Departamento;

/**
 * Created by CristianCamilo on 13/07/2015.
 */
public interface IDepartamentoProcess {

    public Departamento saveDepartamento(Departamento departamento);

    public List<Departamento> findAll();

    public List<Departamento> findByIdUnidad(String idUnidad);
}
