package co.edu.udea.pi2.ubicameudea.database.sqlite.dao.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import co.edu.udea.pi2.ubicameudea.database.sqlite.AccessorSQLiteOpenHelper;
import co.edu.udea.pi2.ubicameudea.database.sqlite.dao.IDepartamentoDAO;
import co.edu.udea.pi2.ubicameudea.persistance.contract.DepartamentoContract;

/**
 * Created by CristianCamilo on 13/07/2015.
 */
public class DepartamentoDAOImpl implements IDepartamentoDAO {

    public static final String TAG = DepartamentoDAOImpl.class.getSimpleName();
    private static DepartamentoDAOImpl instance = null;
    private AccessorSQLiteOpenHelper accessorSQLiteOpenHelper;

    private DepartamentoDAOImpl(Context context) {
        super();
        this.accessorSQLiteOpenHelper = new AccessorSQLiteOpenHelper(context);
    }

    public static synchronized DepartamentoDAOImpl getInstance(Context context) {
        if (instance == null) {
            instance = new DepartamentoDAOImpl(context);
        }

        return instance;
    }

    @Override
    public ContentValues saveDepartamento(ContentValues departamentoContentValue) {
        try {
            SQLiteDatabase sqLiteDatabase = accessorSQLiteOpenHelper.getWritableDatabase();

            long rowId = sqLiteDatabase.insertWithOnConflict(DepartamentoContract.TABLE_NAME, null, departamentoContentValue,
                    SQLiteDatabase.CONFLICT_IGNORE);
            return ((rowId != -1L) ? departamentoContentValue : null);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<ContentValues> findAll() {
        Log.i(TAG, "findAll");

        SQLiteDatabase sqLiteDatabase = accessorSQLiteOpenHelper.getReadableDatabase();

        String query = String.format("SELECT * FROM %s ORDER BY %s",
                DepartamentoContract.TABLE_NAME,
                DepartamentoContract.Column.NOMBRE);

        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        String[]columns = new String[]{ DepartamentoContract.Column.ID_DEPARTAMENTO,
                DepartamentoContract.Column.NOMBRE };
        List<ContentValues> contentValuesList = this.cursorToContentValues(cursor, columns);

        cursor.close();

        return contentValuesList;
    }

    @Override
    public List<ContentValues> findByIdUnidad(String idUnidad){
        Log.i(TAG, "findByIdUnidad");

        SQLiteDatabase sqLiteDatabase = accessorSQLiteOpenHelper.getReadableDatabase();

        String query = String.format("SELECT * FROM %s WHERE %s = '%s' OR %s = '%s' ORDER BY %s",
                DepartamentoContract.TABLE_NAME,
                DepartamentoContract.Column.ID_UNIDAD, idUnidad,
                DepartamentoContract.Column.ID_UNIDAD, -1,
                DepartamentoContract.Column.NOMBRE);

        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        String[]columns = new String[]{ DepartamentoContract.Column.ID_DEPARTAMENTO,
                DepartamentoContract.Column.NOMBRE, DepartamentoContract.Column.ID_UNIDAD };
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
            columns = DepartamentoContract.Column.getAllColumns();
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
