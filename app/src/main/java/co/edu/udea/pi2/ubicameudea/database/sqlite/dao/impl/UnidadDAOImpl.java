package co.edu.udea.pi2.ubicameudea.database.sqlite.dao.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import co.edu.udea.pi2.ubicameudea.database.sqlite.AccessorSQLiteOpenHelper;
import co.edu.udea.pi2.ubicameudea.database.sqlite.dao.IUnidadDAO;
import co.edu.udea.pi2.ubicameudea.persistance.contract.UnidadContract;

/**
 * Created by CristianCamilo on 13/07/2015.
 */
public class UnidadDAOImpl implements IUnidadDAO {
    public static final String TAG = UnidadDAOImpl.class.getSimpleName();

    private static UnidadDAOImpl instance = null;
    private AccessorSQLiteOpenHelper accessorSQLiteOpenHelper;

    private UnidadDAOImpl(Context context) {
        super();
        this.accessorSQLiteOpenHelper = new AccessorSQLiteOpenHelper(context);
    }

    public static synchronized UnidadDAOImpl getInstance(Context context) {
        if (instance == null) {
            instance = new UnidadDAOImpl(context);
        }

        return instance;
    }

    @Override
    public ContentValues saveUnidad(ContentValues unidadContentValue) {
        try {
            SQLiteDatabase sqLiteDatabase = accessorSQLiteOpenHelper.getWritableDatabase();

            long rowId = sqLiteDatabase.insertWithOnConflict(UnidadContract.TABLE_NAME, null, unidadContentValue,
                    SQLiteDatabase.CONFLICT_IGNORE);
            return ((rowId != -1L) ? unidadContentValue : null);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<ContentValues> findUnidadesByTipo(String idTipo) {
        Log.i(TAG, "findUnidadesByTipo");

        SQLiteDatabase sqLiteDatabase = accessorSQLiteOpenHelper.getReadableDatabase();

        String query = String.format("SELECT * FROM %s WHERE %s = '%s' OR %s = '%s' ORDER BY %s",
                UnidadContract.TABLE_NAME,
                UnidadContract.Column.ID_TIPO_UNIDAD, idTipo,
                UnidadContract.Column.ID_TIPO_UNIDAD, -1,
                UnidadContract.Column.NOMBRE);

        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        String[]columns = new String[]{ UnidadContract.Column.ID_UNIDAD,
                UnidadContract.Column.NOMBRE, UnidadContract.Column.ID_TIPO_UNIDAD };
        List<ContentValues> contentValuesList = this.cursorToContentValues(cursor, columns);

        cursor.close();

        return contentValuesList;
    }

    @Override
    public List<ContentValues> findAll(){
        Log.i(TAG, "findAll");

        SQLiteDatabase sqLiteDatabase = accessorSQLiteOpenHelper.getReadableDatabase();

        String query = String.format("SELECT * FROM %s ORDER BY %s",
                UnidadContract.TABLE_NAME,
                UnidadContract.Column.NOMBRE);

        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        String[]columns = new String[]{ UnidadContract.Column.ID_UNIDAD,
                UnidadContract.Column.NOMBRE, UnidadContract.Column.ID_TIPO_UNIDAD };
        List<ContentValues> contentValuesList = this.cursorToContentValues(cursor, columns);

        cursor.close();

        return contentValuesList;
    }

    private List<ContentValues> cursorToContentValues(Cursor cursor, String[] columns){
        List<ContentValues> contentValuesList = new ArrayList<ContentValues>();

        if ((cursor == null) || (cursor.isClosed())) {

            return (contentValuesList);
        }

        if (columns == null){
            columns = UnidadContract.Column.getAllColumns();
        }

        ContentValues contentValues = null;
        cursor.moveToFirst();

        while (!cursor.isAfterLast()){
            contentValues = new ContentValues();

            for(String column : columns){
                contentValues.put(column, cursor.getString(cursor.getColumnIndex(column)));
            }

            contentValuesList.add(contentValues);
            cursor.moveToNext();
        }

        return contentValuesList;
    }
}
