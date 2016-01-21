package co.edu.udea.pi2.ubicameudea.domain.process.impl;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import co.edu.udea.pi2.ubicameudea.database.sqlite.dao.IDepartamentoDAO;
import co.edu.udea.pi2.ubicameudea.database.sqlite.dao.impl.DepartamentoDAOImpl;
import co.edu.udea.pi2.ubicameudea.domain.process.IDepartamentoProcess;
import co.edu.udea.pi2.ubicameudea.model.dto.Departamento;
import co.edu.udea.pi2.ubicameudea.persistance.contract.DepartamentoContract;

/**
 * Created by CristianCamilo on 13/07/2015.
 */
public class DepartamentoProcessImpl implements IDepartamentoProcess {

    private static final String TAG = DepartamentoProcessImpl.class.getSimpleName();
    private IDepartamentoDAO departamentoDAO;

    public DepartamentoProcessImpl(Context context) {
        super();
        this.departamentoDAO = DepartamentoDAOImpl.getInstance(context);
    }

    @Override
    public Departamento saveDepartamento(Departamento departamento) {
        return ((this.departamentoDAO.saveDepartamento(this.convertDepartamentoToContentValue(departamento))
                != null) ? departamento : null);
    }

    @Override
    public List<Departamento> findAll() {
        Log.i(TAG, "findAll");

        List<Departamento> list = new ArrayList<Departamento>();
        try {

            List<ContentValues> contentValuesList = this.departamentoDAO.findAll();

            for (ContentValues contentValue : contentValuesList) {
                list.add(this.convertContentValueToDto(contentValue));
            }

        }catch (ParseException e){
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public List<Departamento> findByIdUnidad(String idUnidad){
        Log.i(TAG, "findByIdUnidad");

        List<Departamento> list = new ArrayList<Departamento>();
        try {
            List<ContentValues> contentValuesList = null;
            if(!(idUnidad.equals("56a0646311e363110030a7db")))
                contentValuesList = this.departamentoDAO.findByIdUnidad(idUnidad);
            else
                contentValuesList = this.departamentoDAO.findAll();

            for (ContentValues contentValue : contentValuesList) {
                list.add(this.convertContentValueToDto(contentValue));
            }
        }catch (ParseException e){
            e.printStackTrace();
        }

        return list;
    }

    private ContentValues convertDepartamentoToContentValue(Departamento departamento){

        ContentValues contentValues = new ContentValues();

        contentValues.put(DepartamentoContract.Column.ID_DEPARTAMENTO, departamento.getDepartamentoId());
        contentValues.put(DepartamentoContract.Column.NOMBRE, departamento.getNombre());
        contentValues.put(DepartamentoContract.Column.ID_UNIDAD, departamento.getUnidad().getIdUnidad());

        return contentValues;
    }

    private Departamento convertContentValueToDto(ContentValues contentValues) throws ParseException {
        Departamento departamento = new Departamento();

        departamento.setDepartamentoId(contentValues.getAsString(DepartamentoContract.Column.ID_DEPARTAMENTO));
        departamento.setNombre(contentValues.getAsString(DepartamentoContract.Column.NOMBRE));

        return departamento;
    }
}
