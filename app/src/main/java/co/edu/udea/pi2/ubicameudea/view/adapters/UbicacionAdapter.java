package co.edu.udea.pi2.ubicameudea.view.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import co.edu.udea.pi2.ubicameudea.R;
import co.edu.udea.pi2.ubicameudea.model.dto.Ubicacion;

/**
 * Created by CristianCamilo on 16/07/2015.
 */
public class UbicacionAdapter extends ArrayAdapter<Ubicacion> {

    private Context context;
    private List<Ubicacion> listaUbicaciones;
    private LayoutInflater inflater;


    public UbicacionAdapter(Context context, int textViewResourceId,
                         List<Ubicacion> listaUbicaciones) {
        super(context, textViewResourceId, listaUbicaciones);
        this.context = context;
        this.listaUbicaciones = listaUbicaciones;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getDropDownView(int position, View convertView,ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    // This funtion called for each row ( Called data.size() times )
    public View getCustomView(int position, View convertView, ViewGroup parent) {

        /********** Inflate spinner_rows.xml file for each row ( Defined below ) ************/
        View row = inflater.inflate(R.layout.layout_item_ubicacion, parent, false);

        /***** Get each Model object from Arraylist ********/
        Ubicacion ubicacion = listaUbicaciones.get(position);

        TextView txvId = (TextView)row.findViewById(R.id.item_ubicacion_txvId);
        TextView txvBloque = (TextView)row.findViewById(R.id.item_ubicacion_txvBloque);
        TextView txvOficina = (TextView)row.findViewById(R.id.item_ubicacion_txvOficina);
        TextView txvLatitud = (TextView)row.findViewById(R.id.item_ubicacion_txvLatitud);
        TextView txvLongitud = (TextView)row.findViewById(R.id.item_ubicacion_txvLongitud);

        txvId.setText(ubicacion.getUbicacionId().toString());
        txvBloque.setText(ubicacion.getBloqueId().toString());
        txvOficina.setText(ubicacion.getOficina().toString());
        txvLatitud.setText(ubicacion.getLatitud().toString());
        txvLongitud.setText(ubicacion.getLongitud().toString());

        return row;
    }
}
