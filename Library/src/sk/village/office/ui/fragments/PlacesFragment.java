package sk.village.office.ui.fragments;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.json.JSONObject;

import sk.village.office.R;
import sk.village.office.core.Constants;
import sk.village.office.core.GPSProvider;
import sk.village.office.map.PlacesGroup;
import sk.village.office.model.ContentHolder;
import sk.village.office.model.Place;
import sk.village.office.parsers.DirectionsJSONParser;
import sk.village.office.util.AddressProvider;
import sk.village.office.util.ConnectionChecker;
import sk.village.office.util.Log;
import sk.village.office.util.Util;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Point;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.text.TextUtils.TruncateAt;
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
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;


public class PlacesFragment extends Fragment implements OnClickListener{
    
    public static final LatLng CITY_CENTER = new LatLng(48.713194, 21.223133);
    private static final String SAVED_INSTANCE = "saved_instance";
    public static boolean isSavedInstance = false;
    public static int lastSection = 1;
    private static View view;
    
    List<LatLng> polyz;
	GPSProvider gpsProvider;
	AlertDialog alert;
   
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
        
        new InitMap().execute();
        
        return view;
    }
    
    private class InitMap extends AsyncTask<Void, Void, Void>{

		@Override
		protected Void doInBackground(Void... params) {
			 map = ((SupportMapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap(); 
				
		        try {
					MapsInitializer.initialize(PlacesFragment.this.getActivity());
				} catch (GooglePlayServicesNotAvailableException e) {}
				
			return null;
		}
		
		@Override
		protected void onPostExecute(Void result) {
			setListeners();
	        setLastMapState();
		}
    	
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
        setSelectedMarkers(true);
        
    }
    
    @Override
    public void onSaveInstanceState(Bundle outState) {
    	super.onSaveInstanceState(outState);
    }
    
	
    
    private void setUpMap(PlacesGroup markerGroup, List<Place> places, boolean zoomMap){
        map.setInfoWindowAdapter(infoWindowAdapter);
        map.setOnInfoWindowClickListener(new InfoWindowClick());
        map.setOnMarkerClickListener(markerClick);
        map.setMyLocationEnabled(true);
        markerMap = markerGroup.addItemsToMap(map,places,getActivity());
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(CITY_CENTER, 14);
        if(zoomMap)
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
	      try{  
			if (infoWindowView != null) {
	            ViewGroup parent = (ViewGroup) infoWindowView.getParent();
	            if (parent != null)
	                parent.removeView(infoWindowView);
	        }
	        infoWindowView = getLayoutInflater(getArguments()).inflate(R.layout.place_detail_layout, null);
	        Place item = markerMap.get(marker);
			TextView title = (TextView) infoWindowView.findViewById(R.id.title);
			TVaddress = (TextView) infoWindowView.findViewById(R.id.description);
			
			if(item.getAddress() == null || item.getAddress().equals("")){
				if(lastAddress == null){
					TVaddress.setText("H¼ad‡m adresu");
					AddressCallback adrcall = new AddressCallback(marker);
					AddressProvider.getAddressForLatLong(getActivity(), marker.getPosition(), AddressProvider.STREET_CITY,adrcall);
				}
				else
					TVaddress.setText(lastAddress);
			}
			else
				TVaddress.setText(item.getAddress());
			title.setText(item.getName());
			
			
			title.setEllipsize(TruncateAt.MARQUEE);
			TVaddress.setEllipsize(TruncateAt.MARQUEE);
			title.setSelected(true);
			
			title.setSingleLine();
			title.setEllipsize(TruncateAt.MARQUEE);
			title.setHorizontallyScrolling(true);
			
	      }catch(Exception e){}
	      
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
		  public void onInfoWindowClick(Marker marker) {
		   marker.hideInfoWindow();
		   LatLng fromPosition = GPSProvider.getInstance(getActivity()).getLatLong();
		   LatLng toPosition = marker.getPosition();
		   map.clear();
		   setSelectedMarkers(false);
		   
		   if(ConnectionChecker.isOnline(getActivity())){
			   String url = Util.getDirectionsUrl(fromPosition, toPosition);
			   DownloadTask downloadTask = new DownloadTask();
			   downloadTask.execute(url);
		   }
		   else
			   showErrorDialog();
		   
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
		
		setSelectedMarkers(true);
		setButtonIcons();
	}
	
	private void setSelectedMarkers(boolean zoomMap){
		switch (lastSection) {
		case 1:
			if(map!=null) setUpMap(new PlacesGroup(),ContentHolder.getInstance(getActivity()).getPlaces1(),zoomMap);
			break;
		case 2:
			if(map!=null) setUpMap(new PlacesGroup(),ContentHolder.getInstance(getActivity()).getPlaces2(),zoomMap);
			break;
		case 3:
			if(map!=null) setUpMap(new PlacesGroup(),ContentHolder.getInstance(getActivity()).getPlaces3(),zoomMap);
			break;
		case 4:
			if(map!=null) setUpMap(new PlacesGroup(),ContentHolder.getInstance(getActivity()).getPlaces4(),zoomMap);
			break;
		}
		
	}
	
	
	private class DownloadTask extends AsyncTask<String, Void, String>{			
		
		// Downloading data in non-ui thread
		@Override
		protected String doInBackground(String... url) {
				
			// For storing data from web service
			String data = "";
					
			try{
				// Fetching the data from web service
				data = downloadUrl(url[0]);
			}catch(Exception e){
				
			}
			return data;		
		}
		
		// Executes in UI thread, after the execution of
		// doInBackground()
		@Override
		protected void onPostExecute(String result) {			
			super.onPostExecute(result);			
			
			ParserTask parserTask = new ParserTask();
			
			// Invokes the thread for parsing the JSON data
			parserTask.execute(result);
				
		}		
	}
	
	/** A class to parse the Google Places in JSON format */
    private class ParserTask extends AsyncTask<String, Integer, List<List<HashMap<String,String>>> >{
    	
    	// Parsing the data in non-ui thread    	
		@Override
		protected List<List<HashMap<String, String>>> doInBackground(String... jsonData) {
			
			JSONObject jObject;	
			List<List<HashMap<String, String>>> routes = null;			           
            
            try{
            	jObject = new JSONObject(jsonData[0]);
            	DirectionsJSONParser parser = new DirectionsJSONParser();
            	
            	// Starts parsing data
            	routes = parser.parse(jObject);    
            }catch(Exception e){
            	e.printStackTrace();
            }
            return routes;
		}
		
		// Executes in UI thread, after the parsing process
		@Override
		protected void onPostExecute(List<List<HashMap<String, String>>> result) {
			ArrayList<LatLng> points = null;
			PolylineOptions lineOptions = null;
			
			
			// Traversing through all the routes
			if(result == null)
				showErrorDialog();
			else
				for(int i=0;i<result.size();i++){
					points = new ArrayList<LatLng>();
					lineOptions = new PolylineOptions();
					
					// Fetching i-th route
					List<HashMap<String, String>> path = result.get(i);
					
					// Fetching all the points in i-th route
					for(int j=0;j<path.size();j++){
						HashMap<String,String> point = path.get(j);					
						
						double lat = Double.parseDouble(point.get("lat"));
						double lng = Double.parseDouble(point.get("lng"));
						LatLng position = new LatLng(lat, lng);	
						
						points.add(position);						
					}
					
					// Adding all the points in the route to LineOptions
					lineOptions.addAll(points);
					lineOptions.width(5);
					lineOptions.color(Color.RED);	
					
				}
				
				// Drawing polyline in the Google Map for the i-th route
				map.addPolyline(lineOptions);							
			}			
    }   

    protected void showErrorDialog(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
        alertDialog.setTitle(R.string.error);
        alertDialog.setMessage(R.string.not_online);
        alertDialog.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int which) {
            	dialog.dismiss();
            }
        });
        alertDialog.show();
	}
    
    private String downloadUrl(String strUrl) throws IOException{
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try{
                URL url = new URL(strUrl);

                // Creating an http connection to communicate with url 
                urlConnection = (HttpURLConnection) url.openConnection();

                // Connecting to url 
                urlConnection.connect();

                // Reading data from url 
                iStream = urlConnection.getInputStream();

                BufferedReader br = new BufferedReader(new InputStreamReader(iStream));

                StringBuffer sb  = new StringBuffer();

                String line = "";
                while( ( line = br.readLine())  != null){
                        sb.append(line);
                }
                
                data = sb.toString();

                br.close();

        }catch(Exception e){
                
        }finally{
                iStream.close();
                urlConnection.disconnect();
        }
        return data;
     }

		
}
