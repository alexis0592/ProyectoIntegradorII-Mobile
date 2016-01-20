package co.edu.udea.pi2.ubicameudea.domain.process.impl;

import android.content.ContentValues;
import android.content.Context;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import co.edu.udea.pi2.ubicameudea.database.sqlite.dao.IUbicacionDAO;
import co.edu.udea.pi2.ubicameudea.database.sqlite.dao.impl.UbicacionDAOImpl;
import co.edu.udea.pi2.ubicameudea.domain.process.IUbicacionProcess;
import co.edu.udea.pi2.ubicameudea.model.dto.Ubicacion;
import co.edu.udea.pi2.ubicameudea.persistance.contract.UbicacionContract;

/**
 * Created by Alexis on 18/06/15.
 */
public class UbicacionProcessImpl implements IUbicacionProcess {

    private static final String TAG = UbicacionProcessImpl.class.getSimpleName();
    private IUbicacionDAO ubicacionDAO;

    public UbicacionProcessImpl(Context context) {
        super();
        this.ubicacionDAO = UbicacionDAOImpl.getInstance(context);
    }

    @Override
    public Ubicacion saveUbicacion(Ubicacion ubicacion) {
        return ((this.ubicacionDAO.saveUbicacion(this.convertUbicacionToContentValue(ubicacion))
                != null) ? ubicacion : null);
    }

    @Override
    public Ubicacion finUbicacionByBloqAndOffice(int bloq, int office) {

        Ubicacion ubicacion = new Ubicacion();

        List<ContentValues> contentValuesList = this.ubicacionDAO.findUbicationByBloqueAndOffice(bloq, office);

        for(ContentValues contentValue:contentValuesList) {
            try {
                ubicacion = convertContentValueToUbicacion(contentValue);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return ubicacion;
    }

    @Override
    public List<Ubicacion> findUbicacion(String idUnidad, String idDepartamento, String idBloque){
        List<Ubicacion> listUbicaciones = new ArrayList<Ubicacion>();

        try {
            List<ContentValues> contentValuesList = this.ubicacionDAO.findUbicacion(idUnidad, idDepartamento, idBloque);

            for(ContentValues contentValue:contentValuesList) {
                Ubicacion ubicacion = convertContentValueToUbicacion(contentValue);
                listUbicaciones.add(ubicacion);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return listUbicaciones;
    }

    private ContentValues convertUbicacionToContentValue(Ubicacion ubicacion){

        ContentValues contentValues = new ContentValues();

        contentValues.put(UbicacionContract.Column.ID_UBICACION, ubicacion.getUbicacionId());
        contentValues.put(UbicacionContract.Column.ID_BLOQUE, ubicacion.getBloqueId());
        contentValues.put(UbicacionContract.Column.ID_DEPARTAMENTO, ubicacion.getDepartamentoId());
        contentValues.put(UbicacionContract.Column.ID_UNIDAD, ubicacion.getUnidadId());
        contentValues.put(UbicacionContract.Column.LATITUD, ubicacion.getLatitud().toString());
        contentValues.put(UbicacionContract.Column.LONGITUD, ubicacion.getLongitud().toString());
        contentValues.put(UbicacionContract.Column.OFICINA, ubicacion.getOficina());
        contentValues.put(UbicacionContract.Column.DESCRIPCION, ubicacion.getDescripcion());

        return contentValues;
    }

    private Ubicacion convertContentValueToUbicacion(ContentValues contentValues) throws ParseException{
        Ubicacion ubicacion = new Ubicacion();

        ubicacion.setUbicacionId(contentValues.getAsString(UbicacionContract.Column.ID_UBICACION));
        ubicacion.setBloqueId(contentValues.getAsString(UbicacionContract.Column.ID_BLOQUE));
        ubicacion.setDepartamentoId(contentValues.getAsString(UbicacionContract.Column.ID_DEPARTAMENTO));
        ubicacion.setUnidadId(contentValues.getAsString(UbicacionContract.Column.ID_UNIDAD));
        ubicacion.setLatitud(Double.parseDouble(contentValues.getAsString(UbicacionContract.Column.LATITUD)));
        ubicacion.setLongitud(Double.parseDouble(contentValues.getAsString(UbicacionContract.Column.LONGITUD)));
        ubicacion.setOficina(contentValues.getAsString(UbicacionContract.Column.OFICINA));

        return ubicacion;
    }
}
