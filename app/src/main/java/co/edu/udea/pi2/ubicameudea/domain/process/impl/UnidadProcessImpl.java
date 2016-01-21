package co.edu.udea.pi2.ubicameudea.domain.process.impl;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import co.edu.udea.pi2.ubicameudea.database.sqlite.dao.IUnidadDAO;
import co.edu.udea.pi2.ubicameudea.database.sqlite.dao.impl.UnidadDAOImpl;
import co.edu.udea.pi2.ubicameudea.domain.process.IUnidadProcess;
import co.edu.udea.pi2.ubicameudea.model.dto.Unidad;
import co.edu.udea.pi2.ubicameudea.persistance.contract.UnidadContract;

/**
 * Created by CristianCamilo on 13/07/2015.
 */
public class UnidadProcessImpl implements IUnidadProcess{

    private static final String TAG = UnidadProcessImpl.class.getSimpleName();
    private IUnidadDAO unidadDAO;

    public UnidadProcessImpl(Context context) {
        super();
        this.unidadDAO = UnidadDAOImpl.getInstance(context);
    }

    @Override
    public Unidad saveUnidad(Unidad unidad) {
        return ((this.unidadDAO.saveUnidad(this.convertUnidadToContentValue(unidad)) != null) ? unidad: null);
    }

    @Override
    public List<Unidad> findUnidadesByTipo(String idTipo){
        Log.i(TAG, "findUnidadesByTipo");

        List<Unidad> list = new ArrayList<Unidad>();
        try {
            List<ContentValues> contentValuesList = null;
            if(!(idTipo.equals("56a062bd11e363110030a7d8")))
                contentValuesList = this.unidadDAO.findUnidadesByTipo(idTipo);
            else
                contentValuesList = this.unidadDAO.findAll();

            for (ContentValues contentValue : contentValuesList) {
                list.add(this.convertContentValueToDto(contentValue));
            }
        }catch (ParseException e){
            e.printStackTrace();
        }

        return list;
    }

    private ContentValues convertUnidadToContentValue(Unidad unidad){

        ContentValues contentValues = new ContentValues();

        contentValues.put(UnidadContract.Column.ID_UNIDAD, unidad.getIdUnidad());
        contentValues.put(UnidadContract.Column.NOMBRE, unidad.getNombre());
        contentValues.put(UnidadContract.Column.ID_TIPO_UNIDAD, unidad.getTipoUnidad().getIdTipoUnidad());

        return contentValues;
    }

    private Unidad convertContentValueToDto(ContentValues contentValues) throws ParseException {
        Unidad unidad = new Unidad();

        unidad.setIdUnidad(contentValues.getAsString(UnidadContract.Column.ID_UNIDAD));
        unidad.setNombre(contentValues.getAsString(UnidadContract.Column.NOMBRE));

        return unidad;
    }
}
