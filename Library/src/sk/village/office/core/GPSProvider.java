package sk.village.office.core;

import java.util.List;

import com.google.android.gms.maps.model.LatLng;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;

public class GPSProvider {

	LocationManager locationManager;
	static final String BROADCAST_ACTION = "sk.village.office.core.gpsupdate";
	private long minTime = 60000;
	private long minDistance = 20;
	private static GPSProvider gpsProvider ;
	private static Context mContext;
	private float bestAccuracy = 100;
	private Location bestResult;
	private long bestTime;
	

	private double mLatitude;
	private double mLongitude;
	private Location mLocation;
	private PendingIntent pendingIntent;
	
	public static GPSProvider getInstance(Context context) {
		
		mContext = context;		
		if(gpsProvider == null) {
			gpsProvider = new GPSProvider();
		}			
		return gpsProvider;		
	}
	
	private GPSProvider() {
			initiateGPSIntent();
	}
	
	public void initiateGPSIntent(){
		
		locationManager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
		List<String> matchingProviders = locationManager.getAllProviders();
		
		Intent intent = new Intent(BROADCAST_ACTION);
		pendingIntent = PendingIntent.getBroadcast(mContext, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
		
		
		for (String provider: matchingProviders) {
			locationManager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
			locationManager.requestLocationUpdates(provider, minTime, minDistance, pendingIntent);
			
			Location location = locationManager.getLastKnownLocation(provider);			
			
			  if (location != null) {
			    float accuracy = location.getAccuracy();
			    long time = location.getTime();
			        
			    if ((time > minTime && accuracy < bestAccuracy)) {
			      bestResult = location;
			      bestAccuracy = accuracy;
			      bestTime = time;
			    }
			    
			    else if (time < minTime && bestAccuracy == Float.MAX_VALUE && time > bestTime){
			      bestResult = location;
			      bestTime = time;
			    }
			  }
		}
		mLocation = bestResult;
		getUpdatedCord();
	}

	public double getLatitude(){
		
		getUpdatedCord();
		return mLatitude;
	}
	
	public double getLongitude(){
		getUpdatedCord();
		return mLongitude;
	}
	
	public LatLng getLatLong(){
		return new LatLng(getLatitude(), getLongitude());
	}

	
	public void setmLatitude(double mLatitude) {
		this.mLatitude = mLatitude;
	}

	public void setmLongitude(double mLongitude) {
		this.mLongitude = mLongitude;
	}

	public void setLocation(Location loc){
		  mLocation = loc;
	}
	
	
	private void getUpdatedCord(){
		if(mLocation != null) {
    		mLatitude = mLocation.getLatitude();
    		mLongitude = mLocation.getLongitude();    		
    	}
	}
	
	
	public  void unregisterListener(){
		locationManager.removeUpdates(pendingIntent);
	}
	
	
}
