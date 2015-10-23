package co.edu.udea.pi2.ubicameudea.view.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import co.edu.udea.pi2.ubicameudea.R;
import co.edu.udea.pi2.ubicameudea.model.dto.Departamento;

/**
 * Created by CristianCamilo on 14/07/2015.
 */
public class DepartamentoAdapter extends ArrayAdapter<Departamento> {

    private Context context;
    private List<Departamento> listaDepartamentos;
    private LayoutInflater inflater;


    public DepartamentoAdapter(Context context, int textViewResourceId,
                             List<Departamento> listaDepartamentos) {
        super(context, textViewResourceId, listaDepartamentos);
        this.context = context;
        this.listaDepartamentos = listaDepartamentos;
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
        View row = inflater.inflate(R.layout.layout_item_departamento, parent, false);

        /***** Get each Model object from Arraylist ********/
        Departamento departamento = listaDepartamentos.get(position);

        TextView txvId = (TextView)row.findViewById(R.id.item_departamento_txvId);
        TextView txvNombre = (TextView)row.findViewById(R.id.item_departamento_txvNombre);

        txvId.setText(departamento.getDepartamentoId().toString());
        txvNombre.setText(departamento.getNombre().toString());

        return row;
    }
}
