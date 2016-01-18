package co.edu.udea.pi2.ubicameudea.domain.process.impl;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import co.edu.udea.pi2.ubicameudea.database.sqlite.dao.ITipoUnidadDAO;
import co.edu.udea.pi2.ubicameudea.database.sqlite.dao.impl.TipoUnidadDAOImpl;
import co.edu.udea.pi2.ubicameudea.domain.process.ITipoUnidadProcess;
import co.edu.udea.pi2.ubicameudea.model.dto.TipoUnidad;
import co.edu.udea.pi2.ubicameudea.persistance.contract.TipoUnidadContract;

/**
 * Created by CristianCamilo on 13/07/2015.
 */
public class TipoUnidadProcessImpl implements ITipoUnidadProcess {

    private static final String TAG = TipoUnidadProcessImpl.class.getSimpleName();
    private ITipoUnidadDAO tipoUnidadDAO;

    public TipoUnidadProcessImpl(Context context) {
        super();
        this.tipoUnidadDAO = TipoUnidadDAOImpl.getInstance(context);
    }

    @Override
    public TipoUnidad saveTipoUnidad(TipoUnidad tipoUnidad) {

        return ((this.tipoUnidadDAO.save(this.convertTipoUnidadToContentValue(tipoUnidad)) != null)
                ? tipoUnidad : null);
    }

    @Override
    public List<TipoUnidad> findAll(){
        Log.i(TAG, "findAll");

        List<TipoUnidad> list = new ArrayList<TipoUnidad>();
        try {

            List<ContentValues> contentValuesList = this.tipoUnidadDAO.findAll();

            for (ContentValues contentValue : contentValuesList) {
                    list.add(this.convertContentValueToDto(contentValue));
            }

        }catch (ParseException e){
            e.printStackTrace();
        }

        return list;
    }


    private ContentValues convertTipoUnidadToContentValue(TipoUnidad tipoUnidad){
        ContentValues contentValues = new ContentValues();

        contentValues.put(TipoUnidadContract.Column.ID_TIPO_UNIDAD, tipoUnidad.getIdTipoUnidad());
        contentValues.put(TipoUnidadContract.Column.NOMBRE, tipoUnidad.getNombre());

        return contentValues;
    }

    private TipoUnidad convertContentValueToDto(ContentValues contentValues) throws ParseException {
        TipoUnidad tipoUnidad = new TipoUnidad();

        tipoUnidad.setIdTipoUnidad(contentValues.getAsString(TipoUnidadContract.Column.ID_TIPO_UNIDAD));
        tipoUnidad.setNombre(contentValues.getAsString(TipoUnidadContract.Column.NOMBRE));

        return tipoUnidad;
    }
}
