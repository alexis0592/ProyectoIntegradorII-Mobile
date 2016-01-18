package co.edu.udea.pi2.ubicameudea.controllers;

import android.app.Activity;
import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import co.edu.udea.pi2.ubicameudea.domain.process.IBloqueProcess;
import co.edu.udea.pi2.ubicameudea.domain.process.impl.BloqueProcessImpl;
import co.edu.udea.pi2.ubicameudea.model.dto.Bloque;

/**
 * Created by Alexis on 17/01/16.
 */
public class BloqueController {

    private IBloqueProcess bloqueProcess;
    private Activity activity;

    public BloqueController(Activity activity){
        this.activity = activity;
        this.bloqueProcess = new BloqueProcessImpl(activity.getApplicationContext());
    }

    public void processRestfulResponse(JSONArray jsonArray) throws JSONException{

        String[]response = new String[jsonArray.length()];
        String bloqId;
        String bloqNum;
        Bloque bloqueToSave;

        for(int i = 0; i < jsonArray.length(); i++){

            JSONObject jsonObject = jsonArray.getJSONObject(i);

            bloqId = jsonObject.getString("_id");
            bloqNum = jsonObject.getString("numero_bloque");

            bloqueToSave = new Bloque();
            bloqueToSave.setIdBloque(bloqId);
            bloqueToSave.setNumBloque(bloqNum);

            this.bloqueProcess.saveBloque(bloqueToSave);
        }

    }
}
