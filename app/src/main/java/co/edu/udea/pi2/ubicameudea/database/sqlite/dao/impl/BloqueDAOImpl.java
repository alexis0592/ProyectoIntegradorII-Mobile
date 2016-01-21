package co.edu.udea.pi2.ubicameudea.database.sqlite.dao.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import co.edu.udea.pi2.ubicameudea.database.sqlite.AccessorSQLiteOpenHelper;
import co.edu.udea.pi2.ubicameudea.database.sqlite.dao.IBloqueDAO;
import co.edu.udea.pi2.ubicameudea.persistance.contract.BloqueContract;

/**
 * Created by Alexis on 18/06/15.
 */
public class BloqueDAOImpl implements IBloqueDAO {

    public static final String TAG = BloqueDAOImpl.class.getSimpleName();

    private static BloqueDAOImpl instance = null;
    private AccessorSQLiteOpenHelper accessorSQLiteOpenHelper;

    private BloqueDAOImpl(Context context) {
        super();
        this.accessorSQLiteOpenHelper = new AccessorSQLiteOpenHelper(context);
    }

    public static synchronized BloqueDAOImpl getInstance(Context context) {
        if (instance == null) {
            instance = new BloqueDAOImpl(context);
        }

        return instance;
    }

    @Override
    public ContentValues save(ContentValues bloqueContentValue) {
        try {
            SQLiteDatabase sqLiteDatabase = accessorSQLiteOpenHelper.getWritableDatabase();

            long rowId = sqLiteDatabase.insertWithOnConflict(BloqueContract.TABLE_NAME, null, bloqueContentValue,
                    SQLiteDatabase.CONFLICT_IGNORE);
            return ((rowId != -1L) ? bloqueContentValue : null);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ContentValues findBloqueById(String bloqId) {
        Log.i(TAG, "findBloqueById");

        SQLiteDatabase sqLiteDatabase = this.accessorSQLiteOpenHelper.getReadableDatabase();
        Cursor cursor  = sqLiteDatabase.rawQuery(String.format("SELECT * FROM %s WHERE %s = ?",
                BloqueContract.TABLE_NAME, BloqueContract.Column.ID_BLOQUE), new String[]{bloqId});
        List<ContentValues> contentValues = this.cursorToContentValues(cursor, null);

        cursor.close();

        return contentValues.get(0);
    }

    @Override
    public ContentValues findBloqueByNum(String bloqNum) {
        Log.i(TAG, "findBLoqueByNum()");

        SQLiteDatabase sqLiteDatabase = this.accessorSQLiteOpenHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(String.format("SELECT * FROM %s WHERE %s = ?",
                BloqueContract.TABLE_NAME, BloqueContract.Column.NUMERO), new String[]{bloqNum});
        List<ContentValues> contentValuesList = this.cursorToContentValues(cursor, null);

        cursor.close();

        return contentValuesList.get(0);
    }

    @Override
    public List<ContentValues> findAllBloque(Boolean distinct, String[] columns,
                                             String selection, String[] selectionArgs, String groupBy,
                                             String having, String orderBy, String limit) {
        Log.i(TAG,
                "finAllBloques(Boolean, String[], String, String[], String, String, String, String):List<ContentValues>");
        SQLiteDatabase sqLiteDatabase = this.accessorSQLiteOpenHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.query(distinct.booleanValue(), BloqueContract.TABLE_NAME, columns, selection,
                selectionArgs, groupBy, having, orderBy, limit);
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
            columns = BloqueContract.Column.getAllColumns();
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
