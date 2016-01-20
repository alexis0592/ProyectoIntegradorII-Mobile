package co.edu.udea.pi2.ubicameudea.database.sqlite.dao.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import co.edu.udea.pi2.ubicameudea.database.sqlite.AccessorSQLiteOpenHelper;
import co.edu.udea.pi2.ubicameudea.database.sqlite.dao.IUbicacionDAO;
import co.edu.udea.pi2.ubicameudea.persistance.contract.UbicacionContract;

/**
 * Created by Alexis on 18/06/15.
 */
public class UbicacionDAOImpl implements IUbicacionDAO {

    private static final String TAG = UbicacionDAOImpl.class.getSimpleName();
    private AccessorSQLiteOpenHelper accessorSQLiteOpenHelper;
    private static UbicacionDAOImpl instance = null;

    public UbicacionDAOImpl(Context context){
        super();
        this.accessorSQLiteOpenHelper = new AccessorSQLiteOpenHelper(context);
    }

    public static synchronized UbicacionDAOImpl getInstance(Context context){
        if (instance == null){
            instance = new UbicacionDAOImpl(context);
        }

        return instance;
    }

    @Override
    public ContentValues saveUbicacion(ContentValues ubicacionContentValue) {
        try {
            SQLiteDatabase sqLiteDatabase = accessorSQLiteOpenHelper.getWritableDatabase();

            long rowId = sqLiteDatabase.insertWithOnConflict(UbicacionContract.TABLE_NAME, null, ubicacionContentValue,
                    SQLiteDatabase.CONFLICT_IGNORE);
            return ((rowId != -1L) ? ubicacionContentValue : null);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<ContentValues> findUbicationByBloqueAndOffice(int bloque, int numOffice) {
        Log.i(TAG, "findUbicationByBloqueAndOffice");

        SQLiteDatabase sqLiteDatabase = accessorSQLiteOpenHelper.getReadableDatabase();

        String query = String.format("SELECT * FROM %s WHERE %s = %s AND %s = %s",
                UbicacionContract.TABLE_NAME, UbicacionContract.Column.ID_BLOQUE, bloque, UbicacionContract.Column.OFICINA, numOffice);

        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
        String[]columns = new String[]{UbicacionContract.Column.ID_BLOQUE, UbicacionContract.Column.ID_DEPARTAMENTO,
                UbicacionContract.Column.ID_UBICACION, UbicacionContract.Column.ID_UNIDAD,
                UbicacionContract.Column.OFICINA, UbicacionContract.Column.LATITUD,
                UbicacionContract.Column.LONGITUD};
        List<ContentValues> contentValuesList = this.cursorToContentValues(cursor, columns);

        cursor.close();

        return contentValuesList;
    }

    @Override
    public List<ContentValues> findUbicacion(String idUnidad, String idDepartamento, String idBloque){
        Log.i(TAG, "findUbicationByBloqueAndOffice");

        SQLiteDatabase sqLiteDatabase = accessorSQLiteOpenHelper.getReadableDatabase();

        String query = String.format("SELECT * FROM %s WHERE " +
                        "(('%s' = %s) OR (%s = '%s')) AND " +
                        "(('%s' = %s) OR (%s = '%s')) AND " +
                        "(('%s' = %s) OR (%s = '%s'))",
                UbicacionContract.TABLE_NAME,
                idBloque, "-1", UbicacionContract.Column.ID_BLOQUE, idBloque,
                idDepartamento, "-1", UbicacionContract.Column.ID_DEPARTAMENTO, idDepartamento,
                idUnidad, "-1", UbicacionContract.Column.ID_UNIDAD, idUnidad);

        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
        String[]columns = new String[]{UbicacionContract.Column.ID_BLOQUE, UbicacionContract.Column.ID_DEPARTAMENTO,
                UbicacionContract.Column.ID_UBICACION, UbicacionContract.Column.ID_UNIDAD,
                UbicacionContract.Column.OFICINA, UbicacionContract.Column.LATITUD,
                UbicacionContract.Column.LONGITUD};
        List<ContentValues> contentValuesList = this.cursorToContentValues(cursor, columns);

        cursor.close();

        return contentValuesList;
    }

    private List<ContentValues> cursorToContentValues(Cursor cursor, String[] columns){

        List<ContentValues> contentValuesList = new ArrayList<ContentValues>();

        if ((cursor == null) || (cursor.isClosed())) {

            return (contentValuesList);
        }

        ContentValues contentValues = null;
        cursor.moveToFirst();

        while (!cursor.isAfterLast()){
            contentValues = new ContentValues();

            for (String column: columns){
                contentValues.put(column, cursor.getString(cursor.getColumnIndex(column)));
            }

            contentValuesList.add(contentValues);
            cursor.moveToNext();
        }

        return contentValuesList;
    }
}
