package co.edu.udea.pi2.ubicameudea.domain.process.impl;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import co.edu.udea.pi2.ubicameudea.database.sqlite.dao.IBloqueDAO;
import co.edu.udea.pi2.ubicameudea.database.sqlite.dao.impl.BloqueDAOImpl;
import co.edu.udea.pi2.ubicameudea.domain.process.IBloqueProcess;
import co.edu.udea.pi2.ubicameudea.model.dto.Bloque;
import co.edu.udea.pi2.ubicameudea.persistance.contract.BloqueContract;

/**
 * Created by Alexis on 18/06/15.
 */
public class BloqueProcessImpl implements IBloqueProcess {

    private static final String TAG = BloqueProcessImpl.class.getSimpleName();
    private IBloqueDAO bloqueDAO;

    public BloqueProcessImpl(Context context) {
        super();
        this.bloqueDAO = BloqueDAOImpl.getInstance(context);
    }

    @Override
    public Bloque saveBloque(Bloque bloque) {

        return ((this.bloqueDAO.save(this.convertBloqueToContenValue(bloque)) != null) ? bloque : null);
    }

    @Override
    public Bloque findBloqueById(String id) {

        ContentValues bloqueContentValue = new ContentValues();
        bloqueContentValue = this.bloqueDAO.findBloqueById(id);
        Bloque bloqueFound = null;
        try {
             bloqueFound = convertContentValueToBloque(bloqueContentValue);
        }catch (ParseException e){
            e.printStackTrace();
        }
        return bloqueFound;
    }

    @Override
    public Bloque findBloqueByNum(String bloqNum) {

        ContentValues bloqueContentValues = new ContentValues();
        bloqueContentValues = this.bloqueDAO.findBloqueByNum(bloqNum);
        Bloque bloque = null;
        try {
            bloque = convertContentValueToBloque(bloqueContentValues);
        }catch (ParseException e){
            e.printStackTrace();
        }

        return bloque;
    }

    @Override
    public List<Bloque> findAllBloques() {
        Log.i(TAG, "findAllBloques");

        List<ContentValues> contentValuesList = this.bloqueDAO.findAllBloque(Boolean.FALSE, null,
                null, null, null, null, null, null);
        List<Bloque> bloquesFoundList = new ArrayList<Bloque>();

        for (ContentValues contentValue : contentValuesList) {
            try {
               bloquesFoundList.add(this.convertContentValueToBloque(contentValue));
            }catch (ParseException e){
                e.printStackTrace();
            }
        }

        return bloquesFoundList;
    }

    private Bloque convertContentValueToBloque(ContentValues contentValues) throws ParseException {
        Bloque bloque = new Bloque();

        bloque.setIdBloque(contentValues.getAsString(BloqueContract.Column.ID_BLOQUE));
        bloque.setNumBloque(contentValues.getAsString(BloqueContract.Column.NUMERO));

        return bloque;
    }

    private ContentValues convertBloqueToContenValue(Bloque bloque){

        ContentValues contentValues = new ContentValues();

        contentValues.put(BloqueContract.Column.ID_BLOQUE, bloque.getIdBloque());
        contentValues.put(BloqueContract.Column.NUMERO, bloque.getNumBloque());

        return contentValues;
    }
}
