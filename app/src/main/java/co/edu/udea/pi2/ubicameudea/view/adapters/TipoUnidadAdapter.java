package co.edu.udea.pi2.ubicameudea.view.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import co.edu.udea.pi2.ubicameudea.R;
import co.edu.udea.pi2.ubicameudea.model.dto.TipoUnidad;

/**
 * Created by CristianCamilo on 14/07/2015.
 */
public class TipoUnidadAdapter extends ArrayAdapter<TipoUnidad>  {

    private Context context;
    private List<TipoUnidad> listaTiposUnidad;
    private LayoutInflater inflater;


    public TipoUnidadAdapter(Context context, int textViewResourceId,
                       List<TipoUnidad> listaTiposUnidad) {
        super(context, textViewResourceId, listaTiposUnidad);
        this.context = context;
        this.listaTiposUnidad = listaTiposUnidad;
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
        View row = inflater.inflate(R.layout.layout_item_tipo_unidad, parent, false);

        /***** Get each Model object from Arraylist ********/
        TipoUnidad tipoUnidad = listaTiposUnidad.get(position);

        TextView txvId = (TextView)row.findViewById(R.id.item_tipo_unidad_txvId);
        TextView txvNombre = (TextView)row.findViewById(R.id.item_tipo_unidad_txvNombre);

        txvId.setText(tipoUnidad.getIdTipoUnidad().toString());
        txvNombre.setText(tipoUnidad.getNombre().toString());

        return row;
    }
}
