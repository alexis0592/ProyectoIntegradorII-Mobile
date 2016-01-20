package co.edu.udea.pi2.ubicameudea.database.sqlite.dao;

import android.content.ContentValues;

import java.util.List;

/**
 * Created by CristianCamilo on 13/07/2015.
 */
public interface IDepartamentoDAO {

    public ContentValues saveDepartamento(ContentValues departamentoContentValue);

    public List<ContentValues> findAll();

    public List<ContentValues> findByIdUnidad(String idUnidad);
}
