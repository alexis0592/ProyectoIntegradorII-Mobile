package co.edu.udea.pi2.ubicameudea.view.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import co.edu.udea.pi2.ubicameudea.R;
import co.edu.udea.pi2.ubicameudea.domain.process.IBloqueProcess;
import co.edu.udea.pi2.ubicameudea.domain.process.impl.BloqueProcessImpl;
import co.edu.udea.pi2.ubicameudea.model.dto.Bloque;
import co.edu.udea.pi2.ubicameudea.model.dto.Ubicacion;

public class UdeaMapActivity extends Activity implements OnMapReadyCallback {

    private Ubicacion ubicacion;
    private Bloque bloque;
    private IBloqueProcess bloqueProcess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_udea_map);

        Intent intent = getIntent();
        ubicacion = new Ubicacion();
        ubicacion = (Ubicacion) intent.getSerializableExtra("ubicacion");

        this.bloqueProcess = new BloqueProcessImpl(this.getApplicationContext());
        bloque = this.bloqueProcess.findBloqueById(ubicacion.getBloqueId());

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_udea_map, menu);
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

    @Override
    public void onMapReady(GoogleMap map) {
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(
                new LatLng(ubicacion.getLatitud(), ubicacion.getLongitud()), 18));
        map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        map.setMyLocationEnabled(true);
        map.addMarker(new MarkerOptions().position(new LatLng(ubicacion.getLatitud(),
                ubicacion.getLongitud())).title(ubicacion.getBloqueId().toString() + " - " + ubicacion.getOficina().toString()));

    }
}
