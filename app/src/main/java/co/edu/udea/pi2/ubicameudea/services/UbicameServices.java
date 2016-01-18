package co.edu.udea.pi2.ubicameudea.services;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;

import java.net.URI;
import java.net.URL;

import co.edu.udea.pi2.ubicameudea.controllers.BloqueController;
import co.edu.udea.pi2.ubicameudea.controllers.TipoUnidadController;
import co.edu.udea.pi2.ubicameudea.controllers.UnidadController;

/**
 * Created by Alexis on 11/01/16.
 */
public class UbicameServices extends AsyncTask<String, Integer, Boolean> {

    private Activity activity;
    private String url;
    private  String serviceType;
    private BloqueController bloqueController;
    private TipoUnidadController tipoUnidadController;
    private UnidadController unidadController;

    public UbicameServices(String serviceType, String url, Activity activity){
        this.activity = activity;
        this.url = url;
        this.serviceType = serviceType;
        this.bloqueController = new BloqueController(this.activity);
        this.tipoUnidadController = new TipoUnidadController(this.activity);
        this.unidadController = new UnidadController(this.activity);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Boolean doInBackground(String... strings) {

        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(this.url);

        try {
            HttpResponse httpResponse = httpClient.execute(httpGet);
            String respStr = EntityUtils.toString(httpResponse.getEntity());

            JSONArray jsonArray = new JSONArray(respStr);

            switch (this.serviceType){
                case "Bloque":
                    this.bloqueController.processRestfulResponse(jsonArray);
                    break;
                case "TipoUnidad":
                    this.tipoUnidadController.processRestfulResponse(jsonArray);
                    break;
                case "Unidad":
                    this.unidadController.processRestfulResponse(jsonArray);
                    break;
            }


        }catch (Exception e){
            Log.e("ERROR en la consulta", "Error", e);

        }

        return null;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
    }
}
