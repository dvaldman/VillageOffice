package sk.village.office.ui.fragments;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import sk.village.office.R;
import sk.village.office.core.Constants;
import sk.village.office.map.PlacesGroup;
import sk.village.office.model.ContentHolder;
import sk.village.office.model.Place;
import sk.village.office.util.AddressProvider;
import sk.village.office.util.Log;
import android.graphics.Point;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;


public class PlacesFragment extends Fragment implements OnClickListener{
    
    public static final LatLng CITY_CENTER = new LatLng(48.713194, 21.223133);
    private static final String SAVED_INSTANCE = "saved_instance";
    public static boolean isSavedInstance = false;
    public static int lastSection = 1;
    private static View view;
   
    GoogleMap map;
    private static Map<Marker, Place> markerMap = new HashMap<Marker, Place>();
    private List<Button> buttons;
   
    
    private static View infoWindowView;
    private static TextView TVaddress;
    
    private static String lastAddress;
     
    public PlacesFragment() {
    }
    
    @Override
    public void onPause() {	
    	super.onPause();
    	isSavedInstance = true;
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        
    	if (view != null) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null)
                parent.removeView(view);
        }
        
    	try {
            view = inflater.inflate(R.layout.fragment_map, container, false);
        } catch (InflateException e) {}
        
        map = ((SupportMapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap(); 
		
        try {
			MapsInitializer.initialize(this.getActivity());
		} catch (GooglePlayServicesNotAvailableException e) {}
		
		setListeners();
        setLastMapState();
        
        return view;
    }
    
    private void setListeners(){
    	setButtons();
    	for(Button but:buttons)
    		but.setOnClickListener(this);
    }
    
    private void setButtons(){
    	buttons = new ArrayList<Button>();
    	buttons.add((Button)view.findViewById(R.id.selected_category));
    	buttons.add((Button)view.findViewById(R.id.button1));
    	buttons.add((Button)view.findViewById(R.id.button2));
    	buttons.add((Button)view.findViewById(R.id.button3));
    	buttons.add((Button)view.findViewById(R.id.button4));
    	
    }
    
    
    private void setButtonIcons(){
    	switch (lastSection) {
		case 1:
			buttons.get(0).setBackgroundResource(R.drawable.subico1_act);
			break;
		case 2:
			buttons.get(0).setBackgroundResource(R.drawable.subico2_act);
			break;
		case 3:
			buttons.get(0).setBackgroundResource(R.drawable.subico3_act);
			break;
		case 4:
			buttons.get(0).setBackgroundResource(R.drawable.subico4_act);
			break;
		}
    	
    	buttons.get(1).setBackgroundResource(R.drawable.subico1_inact);
    	buttons.get(2).setBackgroundResource(R.drawable.subico2_inact);
    	buttons.get(3).setBackgroundResource(R.drawable.subico3_inact);
    	buttons.get(4).setBackgroundResource(R.drawable.subico4_inact);
    }
    
    
    private void setLastMapState(){
    	if(map!=null && isSavedInstance == false) 
    		lastSection = 1;
        setButtonIcons();
        setSelectedMarkers();
        
    }
    
    @Override
    public void onSaveInstanceState(Bundle outState) {
    	super.onSaveInstanceState(outState);
    }
    
	
    
    private void setUpMap(PlacesGroup markerGroup, List<Place> places){
        map.setInfoWindowAdapter(infoWindowAdapter);
        map.setOnInfoWindowClickListener(new InfoWindowClick());
        map.setOnMarkerClickListener(markerClick);
        map.setMyLocationEnabled(true);
        markerMap = markerGroup.addItemsToMap(map,places,getActivity());
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(CITY_CENTER, 14);
        map.animateCamera(cameraUpdate);
    }
    
    public final OnMarkerClickListener markerClick = new OnMarkerClickListener() {
		
		@Override
		public boolean onMarkerClick(Marker marker) {
			
			lastAddress = null;
			Projection projection = map.getProjection();
            LatLng latLng = marker.getPosition();
            Point point = projection.toScreenLocation(latLng);
            int offsetX = getActivity().getResources().getDimensionPixelSize(R.dimen.map_marker_offset_x);
            int offsetY = getActivity().getResources().getDimensionPixelSize(R.dimen.map_marker_offset_y);
            Point point2 = new Point(point.x-offsetX,point.y-offsetY);
            LatLng point3 = projection.fromScreenLocation(point2);
            CameraUpdate zoom1 = CameraUpdateFactory.newLatLng(point3);
            map.animateCamera(zoom1);
            marker.showInfoWindow();
			return true;
		}
	};
    
    public InfoWindowAdapter infoWindowAdapter = new InfoWindowAdapter() {
    	
    	
		@Override
		public View getInfoWindow(final Marker marker) {
	        if (infoWindowView != null) {
	            ViewGroup parent = (ViewGroup) infoWindowView.getParent();
	            if (parent != null)
	                parent.removeView(infoWindowView);
	        }
	        infoWindowView = getLayoutInflater(getArguments()).inflate(R.layout.place_detail_layout, null);
	        Place item = markerMap.get(marker);
			TextView title = (TextView) infoWindowView.findViewById(R.id.title);
			TVaddress = (TextView) infoWindowView.findViewById(R.id.description);
			
			if(lastAddress == null){
				TVaddress.setText("H¼ad‡m adresu");
				AddressCallback adrcall = new AddressCallback(marker);
				AddressProvider.getAddressForLatLong(getActivity(), marker.getPosition(), AddressProvider.STREET_CITY_COUNTRY,adrcall);
			}
			else
				TVaddress.setText(lastAddress);
			title.setText(item.getName());
			
			
			return infoWindowView;
		}
		
		@Override
		public View getInfoContents(Marker marker) {
			return null;
		}
		
	};
	
	private class AddressCallback implements Callback{
	
		private Marker marker;
	
		public AddressCallback(Marker mark){
			this.marker = mark;
		}
	
		@Override
		public boolean handleMessage(Message msg) {
			switch (msg.what) {
			case Constants.MESSAGE_GET_ADDRESS:
				marker.showInfoWindow();
				lastAddress = (String) msg.obj;
				return true;
	
			}
			return false;
		}
		
	}
	
		public class InfoWindowClick implements OnInfoWindowClickListener {

		  @Override
		  public void onInfoWindowClick(Marker arg0) {
		   arg0.hideInfoWindow();
//		   Fragment fragment = new ArticleFragment();
//		   ContentHolder.item = markerMap.get(arg0);
//		   MainActivity.disableExit = true;
//		   Bundle args = new Bundle();
//		   if(ContentHolder.item instanceof CityGuideItem)
//			   args.putBoolean(KosiceApplication.CITY_GUIDE_ITEM, true);
//		   fragment.setArguments(args);
//		   getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragment).addToBackStack("tag").commit();
		   
		  }
		  
		 }

	@Override
	public void onClick(View v) {
		
		if(v.getId() == R.id.button1)
			lastSection = 1;
		if(v.getId() == R.id.button2)
			lastSection = 2;
		if(v.getId() == R.id.button3)
			lastSection = 3;
		if(v.getId() == R.id.button4)
			lastSection = 4;
		
		setSelectedMarkers();
		setButtonIcons();
	}
	
	private void setSelectedMarkers(){
		switch (lastSection) {
		case 1:
			if(map!=null) setUpMap(new PlacesGroup(),ContentHolder.getInstance(getActivity()).getPlaces1());
			break;
		case 2:
			if(map!=null) setUpMap(new PlacesGroup(),ContentHolder.getInstance(getActivity()).getPlaces2());
			break;
		case 3:
			if(map!=null) setUpMap(new PlacesGroup(),ContentHolder.getInstance(getActivity()).getPlaces3());
			break;
		case 4:
			if(map!=null) setUpMap(new PlacesGroup(),ContentHolder.getInstance(getActivity()).getPlaces4());
			break;
		}
		
	}
	
	
	
}
