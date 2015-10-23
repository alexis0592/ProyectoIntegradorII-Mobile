package co.edu.udea.pi2.ubicameudea.database.sqlite.dao;

import android.content.ContentValues;

import java.util.List;

/**
 * Created by Alexis on 18/06/15.
 */
public interface IBloqueDAO {

    public List<ContentValues> findAllBloque(Boolean distinct, String[] columns,
                                             String selection, String[] selectionArgs, String groupBy,
                                             String having, String orderBy, String limit);
}
