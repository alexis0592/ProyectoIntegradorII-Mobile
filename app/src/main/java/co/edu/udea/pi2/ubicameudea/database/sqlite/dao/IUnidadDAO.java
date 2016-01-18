package co.edu.udea.pi2.ubicameudea.database.sqlite.dao;

import android.content.ContentValues;

import java.util.List;

/**
 * Created by CristianCamilo on 13/07/2015.
 */
public interface IUnidadDAO {

    public ContentValues saveUnidad(ContentValues unidadContentValue);

    public List<ContentValues> findUnidadesByTipo(String idTipo);

    public List<ContentValues> findAll();
}
