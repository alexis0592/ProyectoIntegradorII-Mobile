package co.edu.udea.pi2.ubicameudea.controllers;

import android.app.Activity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import co.edu.udea.pi2.ubicameudea.domain.process.IUbicacionProcess;
import co.edu.udea.pi2.ubicameudea.domain.process.impl.UbicacionProcessImpl;
import co.edu.udea.pi2.ubicameudea.model.dto.Ubicacion;

/**
 * Created by Alexis on 19/01/16.
 */
public class UbicacionController {

    private IUbicacionProcess ubicacionProcess;
    private Activity activity;

    public UbicacionController(Activity activity){
        this.activity = activity;
        this.ubicacionProcess = new UbicacionProcessImpl(activity.getApplicationContext());
    }

    public void processRestfulResponse(JSONArray jsonArray) throws JSONException {

        String[]response = new String[jsonArray.length()];
        String ubicacionId;
        String bloqueId;
        String oficina;
        Double latitud;
        Double longitud;
        String departamentoId;
        String unidadId;
        Ubicacion ubicacionToSave;

        for(int i = 0; i < jsonArray.length(); i++){

            JSONObject jsonObject = jsonArray.getJSONObject(i);
            JSONObject jsonInsideBloque = jsonObject.getJSONObject("bloque");
            JSONObject jsonInsideDepartamento = jsonObject.getJSONObject("departamento");
            JSONObject jsonInsideUnidad = jsonObject.getJSONObject("unidad");

            ubicacionId = jsonObject.getString("_id");
            bloqueId = jsonInsideBloque.getString("_id");
            oficina = jsonObject.getString("oficina");
            latitud = Double.parseDouble(jsonObject.getString("latitud"));
            longitud = Double.parseDouble(jsonObject.getString("longitud"));
            departamentoId = jsonInsideDepartamento.getString("_id");
            unidadId = jsonInsideUnidad.getString("_id");

            ubicacionToSave = new Ubicacion();
            ubicacionToSave.setUbicacionId(ubicacionId);
            ubicacionToSave.setBloqueId(bloqueId);
            ubicacionToSave.setOficina(oficina);
            ubicacionToSave.setLatitud(latitud);
            ubicacionToSave.setLongitud(longitud);
            ubicacionToSave.setDepartamentoId(departamentoId);
            ubicacionToSave.setUnidadId(unidadId);


            this.ubicacionProcess.saveUbicacion(ubicacionToSave);
        }

    }
}
