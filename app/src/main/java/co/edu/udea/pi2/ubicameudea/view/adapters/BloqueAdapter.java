package co.edu.udea.pi2.ubicameudea.view.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import co.edu.udea.pi2.ubicameudea.R;
import co.edu.udea.pi2.ubicameudea.model.dto.Bloque;

/**
 * Created by CristianCamilo on 14/07/2015.
 */
public class BloqueAdapter extends ArrayAdapter<Bloque> {
    private Context context;
    private List<Bloque> listaBloques;
    private LayoutInflater inflater;


    public BloqueAdapter(Context context, int textViewResourceId,
                             List<Bloque> listaBloques) {
        super(context, textViewResourceId, listaBloques);
        this.context = context;
        this.listaBloques = listaBloques;
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

    public View getCustomView(int position, View convertView, ViewGroup parent) {

        View row = inflater.inflate(R.layout.layout_item_bloque, parent, false);

        Bloque bloque = listaBloques.get(position);

        TextView txvId = (TextView)row.findViewById(R.id.item_bloque_txvId);
        TextView txvNumero = (TextView)row.findViewById(R.id.item_bloque_txvNumero);

        txvId.setText(bloque.getIdBloque().toString());
        txvNumero.setText(bloque.getNumBloque().toString());

        return row;
    }
}
