package co.edu.udea.pi2.ubicameudea.controllers;

import android.app.Activity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import co.edu.udea.pi2.ubicameudea.domain.process.IDepartamentoProcess;
import co.edu.udea.pi2.ubicameudea.domain.process.impl.DepartamentoProcessImpl;
import co.edu.udea.pi2.ubicameudea.model.dto.Departamento;
import co.edu.udea.pi2.ubicameudea.model.dto.Unidad;

/**
 * Created by Alexis on 19/01/16.
 */
public class DepartemantoController {

    private IDepartamentoProcess departamentoProcess;
    private Activity activity;

    public DepartemantoController(Activity activity){
        this.activity = activity;
        this.departamentoProcess = new DepartamentoProcessImpl(activity.getApplicationContext());
    }

    public void processRestfulResponse(JSONArray jsonArray) throws JSONException {

        String[]response = new String[jsonArray.length()];
        String departmentId;
        String departmentName;
        String idUnidad;
        Departamento departamentoToSave;
        Unidad unidadToSave;

        for(int i = 0; i < jsonArray.length(); i++){

            JSONObject jsonObject = jsonArray.getJSONObject(i);
            JSONObject insideJsonObject = jsonObject.getJSONObject("unidad");

            departmentId = jsonObject.getString("_id");
            departmentName = jsonObject.getString("nombre");
            idUnidad = insideJsonObject.getString("_id");

            unidadToSave = new Unidad();
            unidadToSave.setIdUnidad(idUnidad);
            departamentoToSave = new Departamento();
            departamentoToSave.setDepartamentoId(departmentId);
            departamentoToSave.setNombre(departmentName);
            departamentoToSave.setUnidad(unidadToSave);

            this.departamentoProcess.saveDepartamento(departamentoToSave);
        }

    }

}
