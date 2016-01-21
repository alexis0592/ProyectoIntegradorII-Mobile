package co.edu.udea.pi2.ubicameudea.view.adapters;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

import co.edu.udea.pi2.ubicameudea.R;

/**
 * Created by Alexis on 20/01/16.
 */
public class MapAdapter extends Activity implements GoogleMap.OnMapLongClickListener{

    class MyInfoWindow implements GoogleMap.InfoWindowAdapter{

        private final View myContentsView;
        private TextView markerTitle;
        private TextView markerSnippet;

        public MyInfoWindow(){
            this.myContentsView = getLayoutInflater().inflate(R.layout.custom_info_windows, null);
        }

        @Override
        public View getInfoWindow(Marker marker) {

            markerTitle = ((TextView)myContentsView.findViewById(R.id.title));
            markerTitle.setText(marker.getTitle());
            markerSnippet = ((TextView)myContentsView.findViewById(R.id.snippet));
            markerSnippet.setText(marker.getTitle());

            return myContentsView;
        }

        @Override
        public View getInfoContents(Marker marker) {
            return null;
        }
    }


    @Override
    public void onMapLongClick(LatLng latLng) {

    }

}
