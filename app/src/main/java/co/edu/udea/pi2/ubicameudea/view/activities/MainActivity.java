package co.edu.udea.pi2.ubicameudea.view.activities;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import co.edu.udea.pi2.ubicameudea.R;
import co.edu.udea.pi2.ubicameudea.domain.process.impl.UbicacionProcessImpl;
import co.edu.udea.pi2.ubicameudea.model.dto.Ubicacion;
import co.edu.udea.pi2.ubicameudea.services.UbicameServices;


public class MainActivity extends ActionBarActivity {

    private ImageView imgMapButton;
    private EditText etxBloque, etxSalon;
    private ImageView btnVer;
    private UbicameServices ubicameServices;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();
        ubicameServices = new UbicameServices("Bloque", "https://ubicame-udea.herokuapp.com/bloques", this);
        ubicameServices.execute();
        ubicameServices = new UbicameServices("TipoUnidad", "https://ubicame-udea.herokuapp.com/tiposunidad", this);
        ubicameServices.execute();
        ubicameServices = new UbicameServices("Unidad", "https://ubicame-udea.herokuapp.com/unidades", this);
        ubicameServices.execute();
    }

    public void initComponents() {
        //BloqueProcessImpl bloqueProcess = new BloqueProcessImpl(this.getApplicationContext());
        this.etxBloque = (EditText) super.findViewById(R.id.main_etxBloque);
        this.etxBloque.setTextColor(Color.parseColor("#FFFFFF"));
        this.etxSalon = (EditText) super.findViewById(R.id.main_etxSalon);
        this.etxSalon.setTextColor(Color.parseColor("#FFFFFF"));

        UbicacionProcessImpl ubicacionProcess = new UbicacionProcessImpl(getBaseContext());

        this.imgMapButton = (ImageView) super.findViewById(R.id.goMapButton);
        imgMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if ((etxBloque.equals(null)) || (etxBloque.equals(""))) {
                    Toast.makeText(getBaseContext(), getResources().getString(R.string.msg_bloque_obligatorio), Toast.LENGTH_LONG).show();
                    return;
                }

                if ((etxSalon.equals(null)) || (etxSalon.equals(""))) {
                    Toast.makeText(getBaseContext(), getResources().getString(R.string.msg_salon_obligatorio), Toast.LENGTH_LONG).show();
                    return;
                }
                try {
                    Integer idBloque = Integer.parseInt(etxBloque.getText().toString());
                    Integer idSalon = Integer.parseInt(etxSalon.getText().toString());

                    UbicacionProcessImpl ubicacionProcess = new UbicacionProcessImpl(getBaseContext());
                    Ubicacion ubicacion = ubicacionProcess.finUbicacionByBloqAndOffice(idBloque, idSalon);

                    if (ubicacion.getUbicacionId() != null) {
                        UdeaMapActivity udeaMapActivity = new UdeaMapActivity();
                        Intent intent = new Intent(getBaseContext(), udeaMapActivity.getClass());
                        intent.putExtra("ubicacion", ubicacion);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getBaseContext(),getResources().getString(R.string.msg_lugar_no_encontrado), Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(getBaseContext(),getResources().getString(R.string.msg_salon_bloque_formato_numero), Toast.LENGTH_LONG).show();
                    return;
                }
            }
        });
    }

    public void advancedSearchClick(View view) {
        AdvancedSearchActivity advancedSearchActivity = new AdvancedSearchActivity();
        startActivity(new Intent(this, advancedSearchActivity.getClass()));
    }

    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle("Ubicame Udea");
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#224933")));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        //restoreActionBar();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
