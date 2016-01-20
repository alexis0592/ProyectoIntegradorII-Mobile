package co.edu.udea.pi2.ubicameudea.view.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import co.edu.udea.pi2.ubicameudea.R;

/**
 * Created by Alexis on 12/01/16.
 */
public class WelcomeActivity extends Activity {

    final int WELCOME = 25;
    TextView lineaAyuda;
    ProgressBar progressBar;
    int progress = 0;
    int paso = 500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_activity);

        progressBar = (ProgressBar)findViewById(R.id.progressbar);
        lineaAyuda = (TextView) findViewById(R.id.bienvenida);
    }

    @Override
    protected void onResume() {
        super.onResume();

        //lineaAyuda.setText("Cargando...");
        cuentaAtras(3000);

    }

    public void cuentaAtras(long milisegundos) {
        CountDownTimer mCountDownTimer;
        progressBar.setMax((int) milisegundos);
        progressBar.setProgress(paso);

        mCountDownTimer = new CountDownTimer(milisegundos, paso) {

            @Override
            public void onTick(long arg0) {
                Log.v("Log_tag", "Tick of progress" + progress + arg0);
                progress += paso;
                progressBar.setProgress(progress);

            }

            @Override
            public void onFinish() {
                Toast.makeText(getApplicationContext(), "Bienvenido", Toast.LENGTH_LONG)
                        .show();
                progress += paso;
                progressBar.setProgress(progress);
                progressBar.setVisibility(View.INVISIBLE);
                Intent i = new Intent(
                        ".view.activities.MainActivity");
                startActivityForResult(i, WELCOME);
            }
        };

        mCountDownTimer.start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == WELCOME)
            finish();
        else
            super.onActivityResult(requestCode, resultCode, data);
    }

}
