package co.edu.udea.pi2.ubicameudea.controllers;

import android.app.Activity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import co.edu.udea.pi2.ubicameudea.domain.process.ITipoUnidadProcess;
import co.edu.udea.pi2.ubicameudea.domain.process.impl.TipoUnidadProcessImpl;
import co.edu.udea.pi2.ubicameudea.model.dto.TipoUnidad;

/**
 * Created by Alexis on 17/01/16.
 */
public class TipoUnidadController {

    private ITipoUnidadProcess tipoUnidadProcess;
    private Activity activity;


    public TipoUnidadController(Activity activity){
        this.activity = activity;
        this.tipoUnidadProcess = new TipoUnidadProcessImpl(activity.getApplicationContext());
    }

    public void processRestfulResponse(JSONArray jsonArray) throws JSONException {

        String[]response = new String[jsonArray.length()];
        String tipoUnidadId;
        String tipoUnidadName;
        TipoUnidad tipoUnidadToSave;

        for(int i = 0; i < jsonArray.length(); i++){

            JSONObject jsonObject = jsonArray.getJSONObject(i);

            tipoUnidadId = jsonObject.getString("_id");
            tipoUnidadName = jsonObject.getString("nombre");

            tipoUnidadToSave = new TipoUnidad();
            tipoUnidadToSave.setIdTipoUnidad(tipoUnidadId);
            tipoUnidadToSave.setNombre(tipoUnidadName);

            this.tipoUnidadProcess.saveTipoUnidad(tipoUnidadToSave);
        }

    }
}
