package co.edu.udea.pi2.ubicameudea.database.sqlite.dao;

import android.content.ContentValues;

import java.util.List;

/**
 * Created by Alexis on 18/06/15.
 */
public interface IBloqueDAO {

    public ContentValues save(ContentValues bloqueContentValue);

    public ContentValues findBloqueById(String bloqId);

    public ContentValues findBloqueByNum(String bloqNum);

    public List<ContentValues> findAllBloque(Boolean distinct, String[] columns,
                                             String selection, String[] selectionArgs, String groupBy,
                                             String having, String orderBy, String limit);
}
