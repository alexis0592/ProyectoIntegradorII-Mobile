package co.edu.udea.pi2.ubicameudea.view.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import co.edu.udea.pi2.ubicameudea.R;
import co.edu.udea.pi2.ubicameudea.model.dto.Unidad;

/**
 * Created by CristianCamilo on 14/07/2015.
 */
public class UnidadAdapter extends ArrayAdapter<Unidad> {

    private Context context;
    private List<Unidad> listaUnidades;
    private LayoutInflater inflater;


    public UnidadAdapter(Context context, int textViewResourceId,
                             List<Unidad> listaUnidades) {
        super(context, textViewResourceId, listaUnidades);
        this.context = context;
        this.listaUnidades = listaUnidades;
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
        View row = inflater.inflate(R.layout.layout_item_unidad, parent, false);

        /***** Get each Model object from Arraylist ********/
        Unidad unidad = listaUnidades.get(position);

        TextView txvId = (TextView)row.findViewById(R.id.item_unidad_txvId);
        TextView txvNombre = (TextView)row.findViewById(R.id.item_unidad_txvNombre);

        txvId.setText(unidad.getIdUnidad().toString());
        txvNombre.setText(unidad.getNombre().toString());

        return row;
    }
}
