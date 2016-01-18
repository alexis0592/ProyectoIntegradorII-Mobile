package co.edu.udea.pi2.ubicameudea.database.sqlite.dao;

import android.content.ContentValues;

import java.util.List;

/**
 * Created by Alexis on 18/06/15.
 */
public interface IUbicacionDAO {

    public List<ContentValues> findUbicationByBloqueAndOffice(int bloque, int numOffice);

    public List<ContentValues> findUbicacion(String idUnidad, String idDepartamento, String idBloque);
}
