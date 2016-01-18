package co.edu.udea.pi2.ubicameudea.database.sqlite.dao.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import co.edu.udea.pi2.ubicameudea.database.sqlite.AccessorSQLiteOpenHelper;
import co.edu.udea.pi2.ubicameudea.database.sqlite.dao.ITipoUnidadDAO;
import co.edu.udea.pi2.ubicameudea.persistance.contract.TipoUnidadContract;

/**
 * Created by CristianCamilo on 13/07/2015.
 */
public class TipoUnidadDAOImpl implements ITipoUnidadDAO{

    public static final String TAG = TipoUnidadDAOImpl.class.getSimpleName();

    private static TipoUnidadDAOImpl instance = null;
    private AccessorSQLiteOpenHelper accessorSQLiteOpenHelper;

    private TipoUnidadDAOImpl(Context context) {
        super();
        this.accessorSQLiteOpenHelper = new AccessorSQLiteOpenHelper(context);
    }

    public static synchronized TipoUnidadDAOImpl getInstance(Context context) {
        if (instance == null) {
            instance = new TipoUnidadDAOImpl(context);
        }

        return instance;
    }

    @Override
    public ContentValues save(ContentValues tipoUnidadContentValue) {
        try {
            SQLiteDatabase sqLiteDatabase = accessorSQLiteOpenHelper.getWritableDatabase();

            long rowId = sqLiteDatabase.insertWithOnConflict(TipoUnidadContract.TABLE_NAME, null, tipoUnidadContentValue,
                    SQLiteDatabase.CONFLICT_IGNORE);
            return ((rowId != -1L) ? tipoUnidadContentValue : null);
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
                TipoUnidadContract.TABLE_NAME,
                TipoUnidadContract.Column.NOMBRE);

        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        String[]columns = new String[]{ TipoUnidadContract.Column.ID_TIPO_UNIDAD,
                TipoUnidadContract.Column.NOMBRE };
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
            columns = TipoUnidadContract.Column.getAllColumns();
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
