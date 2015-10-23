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

        bloque.setIdBloque(Integer.parseInt(contentValues.getAsString(BloqueContract.Column.ID_BLOQUE)));
        bloque.setNumBloque(contentValues.getAsString(BloqueContract.Column.NUMERO));

        return bloque;
    }
}
