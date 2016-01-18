package co.edu.udea.pi2.ubicameudea.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

import co.edu.udea.pi2.ubicameudea.R;
import co.edu.udea.pi2.ubicameudea.domain.process.IBloqueProcess;
import co.edu.udea.pi2.ubicameudea.domain.process.IDepartamentoProcess;
import co.edu.udea.pi2.ubicameudea.domain.process.ITipoUnidadProcess;
import co.edu.udea.pi2.ubicameudea.domain.process.IUbicacionProcess;
import co.edu.udea.pi2.ubicameudea.domain.process.IUnidadProcess;
import co.edu.udea.pi2.ubicameudea.domain.process.impl.BloqueProcessImpl;
import co.edu.udea.pi2.ubicameudea.domain.process.impl.DepartamentoProcessImpl;
import co.edu.udea.pi2.ubicameudea.domain.process.impl.TipoUnidadProcessImpl;
import co.edu.udea.pi2.ubicameudea.domain.process.impl.UbicacionProcessImpl;
import co.edu.udea.pi2.ubicameudea.domain.process.impl.UnidadProcessImpl;
import co.edu.udea.pi2.ubicameudea.model.dto.Bloque;
import co.edu.udea.pi2.ubicameudea.model.dto.Departamento;
import co.edu.udea.pi2.ubicameudea.model.dto.TipoUnidad;
import co.edu.udea.pi2.ubicameudea.model.dto.Ubicacion;
import co.edu.udea.pi2.ubicameudea.model.dto.Unidad;
import co.edu.udea.pi2.ubicameudea.view.adapters.BloqueAdapter;
import co.edu.udea.pi2.ubicameudea.view.adapters.DepartamentoAdapter;
import co.edu.udea.pi2.ubicameudea.view.adapters.TipoUnidadAdapter;
import co.edu.udea.pi2.ubicameudea.view.adapters.UbicacionAdapter;
import co.edu.udea.pi2.ubicameudea.view.adapters.UnidadAdapter;

public class AdvancedSearchActivity extends ActionBarActivity {

    private Spinner spnTipoUnidad;
    private Spinner spnUnidades;
    private Spinner spnDepartamentos;
    private Spinner spnBloques;
    private Spinner spnUbicaciones;
    private ITipoUnidadProcess tipoUnidadProcess;
    private IDepartamentoProcess departamentoProcess;
    private IBloqueProcess bloqueProcess;
    private IUnidadProcess unidadProcess;
    private IUbicacionProcess ubicacionProcess;
    private TipoUnidadAdapter tipoUnidadAdapter;
    private UbicacionAdapter ubicacionAdapter;
    private BloqueAdapter bloqueAdapter;
    private UnidadAdapter unidadAdapter;
    private DepartamentoAdapter departamentoAdapter;
    private List<TipoUnidad> tiposUnidad;
    private List<Unidad> unidades;
    private List<Departamento> departamentos;
    private String idUnidad;
    private String idDepartamento;
    private String idBloque;
    private Ubicacion ubicacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_search);

        tipoUnidadProcess = new TipoUnidadProcessImpl(this);
        departamentoProcess = new DepartamentoProcessImpl(this);
        bloqueProcess = new BloqueProcessImpl(this);
        unidadProcess = new UnidadProcessImpl(this);
        ubicacionProcess = new UbicacionProcessImpl(this);

        initComponents();
    }

    public void initComponents() {
        this.spnTipoUnidad = (Spinner) super.findViewById(R.id.advanced_search_spnTipoUnidad);
        this.spnUnidades = (Spinner) super.findViewById(R.id.advanced_search_spnUnidades);
        this.spnDepartamentos = (Spinner) super.findViewById(R.id.advanced_search_spnDepartamentos);
        this.spnBloques = (Spinner) super.findViewById(R.id.advanced_search_spnBloques);
        this.spnUbicaciones = (Spinner) super.findViewById(R.id.advanced_search_spnUbicaciones);

        tiposUnidad = tipoUnidadProcess.findAll();

        tipoUnidadAdapter = new TipoUnidadAdapter(getBaseContext(),
                R.layout.layout_item_tipo_unidad, tiposUnidad);
        spnTipoUnidad.setAdapter(tipoUnidadAdapter);

        spnTipoUnidad.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View v, int position, long id) {
                String idTipoUnidad = (((TextView) v.findViewById(R.id.item_tipo_unidad_txvId)).getText().toString());
                if (!idTipoUnidad.equals(null)) {
                    unidades = unidadProcess.findUnidadesByTipo(idTipoUnidad);
                    unidadAdapter = new UnidadAdapter(getBaseContext(), R.layout.layout_item_unidad, unidades);
                    spnUnidades.setAdapter(unidadAdapter);
                    spnUnidades.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            idUnidad = unidadAdapter.getItem(i).getIdUnidad();
                            if (!idUnidad.equals(null)) {
                                departamentos = departamentoProcess.findByIdUnidad(idUnidad);
                                if (departamentos.size() != 0) {
                                    departamentoAdapter = new DepartamentoAdapter(getBaseContext(), R.layout.layout_item_departamento, departamentos);
                                    spnDepartamentos.setAdapter(departamentoAdapter);
                                    spnDepartamentos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                        @Override
                                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                            idDepartamento = departamentoAdapter.getItem(i).getDepartamentoId();
                                            buscarUbicaciones();
                                        }

                                        @Override
                                        public void onNothingSelected(AdapterView<?> adapterView) {

                                        }
                                    });

                                }
                                buscarUbicaciones();
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });


                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        List<Bloque> bloques = bloqueProcess.findAllBloques();
        bloqueAdapter = new BloqueAdapter(getBaseContext(),
                R.layout.layout_item_bloque, bloques);
        spnBloques.setAdapter(bloqueAdapter);
        spnBloques.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                idBloque = bloqueAdapter.getItem(i).getIdBloque();
                buscarUbicaciones();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Button btnVer = (Button) super.findViewById(R.id.advanced_search_btnVer);
        btnVer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UdeaMapActivity udeaMapActivity = new UdeaMapActivity();
                Intent intent = new Intent(getBaseContext(), udeaMapActivity.getClass());
                intent.putExtra("ubicacion", ubicacion);
                startActivity(intent);
            }
        });

    }

    private void buscarUbicaciones() {
        if((idUnidad != null)&& (idDepartamento != null)&&(idBloque != null)) {
            List<Ubicacion> ubicaciones = ubicacionProcess.findUbicacion(idUnidad, idDepartamento, idBloque);
            ubicacionAdapter = new UbicacionAdapter(getBaseContext(), R.layout.layout_item_ubicacion, ubicaciones);
            spnUbicaciones.setAdapter(ubicacionAdapter);
            spnUbicaciones.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    ubicacion = ubicacionAdapter.getItem(i);
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_advanced_search, menu);
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
