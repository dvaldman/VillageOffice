package sk.village.office.receivers;

import java.util.List;

import sk.village.office.core.GPSProvider;
import sk.village.office.util.Log;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;


	
	public class GPSBroadcastReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			Bundle bundle = intent.getExtras();
		    
		    Location location = (Location)bundle.get(android.location.LocationManager.KEY_LOCATION_CHANGED);    
		    		    
		    LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
		    if(locationManager!=null) {
			    List<String> matchingProviders = locationManager.getAllProviders();	 	    
			 		    
			    float providerAccuracy = 0;
			    for (String provider : matchingProviders) {
						Location local = locationManager.getLastKnownLocation(provider);
						if (local != null) {							
							if (providerAccuracy != 0) {
								providerAccuracy = local.getAccuracy();
							}
							if (providerAccuracy < local.getAccuracy()) {
								location = local;
							}
						}			    
			    }		    	
		    }
		    
		   if(location != null) {
				double myLatitude = location.getLatitude();
				double myLongitude = location.getLongitude();
				
				GPSProvider gpsProvider = GPSProvider.getInstance(context);
				gpsProvider.setLocation(location);
				Log.i("UPDATING POSITION");
				gpsProvider.setmLatitude(myLatitude);
				gpsProvider.setmLongitude(myLongitude);
				
			}
			
		}
		
	}
