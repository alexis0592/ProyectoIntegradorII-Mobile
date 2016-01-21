package co.edu.udea.pi2.ubicameudea.domain.process;

import java.util.List;

import co.edu.udea.pi2.ubicameudea.model.dto.Bloque;

/**
 * Created by Alexis on 18/06/15.
 */
public interface IBloqueProcess {

    public Bloque saveBloque(Bloque bloque);

    public Bloque findBloqueById(String id);

    public Bloque findBloqueByNum(String bloqNum);

    public List<Bloque> findAllBloques();

}
