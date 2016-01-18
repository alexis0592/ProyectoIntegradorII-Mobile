package co.edu.udea.pi2.ubicameudea.controllers;

import android.app.Activity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import co.edu.udea.pi2.ubicameudea.domain.process.IUnidadProcess;
import co.edu.udea.pi2.ubicameudea.domain.process.impl.UnidadProcessImpl;
import co.edu.udea.pi2.ubicameudea.model.dto.TipoUnidad;
import co.edu.udea.pi2.ubicameudea.model.dto.Unidad;

/**
 * Created by Alexis on 17/01/16.
 */
public class UnidadController {

    private IUnidadProcess unidadProcess;
    private Activity activity;

    public UnidadController(Activity activity){
        this.activity = activity;
        this.unidadProcess = new UnidadProcessImpl(activity.getApplicationContext());
    }

    public void processRestfulResponse(JSONArray jsonArray) throws JSONException {

        String[]response = new String[jsonArray.length()];
        String unidadId;
        String unidadName;
        String tipoUnidadId;
        String tipoUnidadJSON;
        Unidad unidadToSave;
        TipoUnidad tipoUnidadToReference;

        for(int i = 0; i < jsonArray.length(); i++){

            JSONObject jsonObject = jsonArray.getJSONObject(i);
            JSONObject jsonInsideObject = jsonObject.getJSONObject("tipoUnidad");


            unidadId = jsonObject.getString("_id");
            unidadName = jsonObject.getString("nombre");
            tipoUnidadId = jsonInsideObject.getString("_id");

            unidadToSave = new Unidad();
            tipoUnidadToReference = new TipoUnidad();
            tipoUnidadToReference.setIdTipoUnidad(tipoUnidadId);
            unidadToSave.setIdUnidad(unidadId);
            unidadToSave.setNombre(unidadName);
            unidadToSave.setTipoUnidad(tipoUnidadToReference);

            this.unidadProcess.saveUnidad(unidadToSave);
        }

    }

}
