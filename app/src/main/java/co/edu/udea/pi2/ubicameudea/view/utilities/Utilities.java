package co.edu.udea.pi2.ubicameudea.view.utilities;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by Alexis on 19/01/16.
 */
public class Utilities {

    private Context context;
    private ProgressDialog progressDialog;

    public Utilities(Context context){
        this.context = context;
    }

    public void showDialog(String title, String message, Boolean isCancelable){

        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle(title);
        progressDialog.setMessage(message);
        progressDialog.setCancelable(isCancelable);
        progressDialog.show();
    }

    public void cancelProgressDialog(){
        progressDialog.dismiss();
    }
}
