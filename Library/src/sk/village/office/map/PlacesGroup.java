package sk.village.office.map;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sk.village.office.R;
import sk.village.office.model.Place;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class PlacesGroup {

	public Map<Marker, Place> addItemsToMap(GoogleMap map,List<Place> places,Context context) {
		map.clear();
		Map<Marker, Place> markerMap = new HashMap<Marker, Place>();
		Bitmap icon = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.marker2);
        for (Place item : places){
        	if(item.getGps()!=null) {
        		MarkerOptions markerOptions = new MarkerOptions()
        		.position(item.getGps())
        		.title(item.getName())
        		.icon(BitmapDescriptorFactory.fromBitmap(icon))
        		.snippet(item.getDescription());
        		
        		Marker marker = map.addMarker(markerOptions);
        		markerMap.put(marker, item);
        	}
        }
        
        return markerMap;
	}
	
}
