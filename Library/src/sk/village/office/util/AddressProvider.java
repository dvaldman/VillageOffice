package sk.village.office.util;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import sk.village.office.R;
import sk.village.office.core.Constants;

import com.google.android.gms.maps.model.LatLng;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.widget.TextView;

public class AddressProvider{
	
	public static final int STREET_CITY_COUNTRY = 0;
	
	private static Context context;
	private static LatLng position;
	private static int resultFormatID;
	private static Callback callback; 
	private static TextView textView; 
	 
	
	public static void getAddressForLatLong(Context cont, LatLng ll,int type,Callback call){
		context = cont;
		position = ll;
		resultFormatID = type;
		callback = call;
		textView = null;
		new AddressTask().execute();
	}
	
	public static void getAddressForLatLong(Context cont, LatLng ll,int type,TextView tv){
		context = cont;
		position = ll;
		resultFormatID = type;
		callback = null;
		textView = tv;
		new AddressTask().execute();
	}

	private static class AddressTask extends AsyncTask<Void, Void, String>{
		
		@Override
		protected void onPreExecute() {
			if(textView != null){
				textView.setText("H¼ad‡m adresu");
				textView.postInvalidate();
			}
		}
		
		@Override
		protected String doInBackground(Void... params) {
			String result=context.getResources().getString(R.string.address_not_found);
//			if(!ConnectionChecker.isOnline(context))
//				return result;
			
			Geocoder geo = new Geocoder(context, Locale.getDefault());
	        List<Address> addresses;
	        
	        try {
				addresses = geo.getFromLocation(position.latitude,position.longitude, 1);
				if (addresses.size() > 0)
					switch (resultFormatID) {
					case STREET_CITY_COUNTRY:
						result =  addresses.get(0).getAddressLine(0) + ", " + addresses.get(0).getLocality() +", " + addresses.get(0).getCountryName();
					}
			} catch (IOException e) {
				e.printStackTrace();
			}
			return result;
		}
		
		@Override
		protected void onPostExecute(String result) {
			if(textView != null){
				Log.i("i am setting result "+result);
				textView.setText(result);
				textView.postInvalidate();
			}
			if(callback != null){
				Message msg = new Message();
			    msg.what = Constants.MESSAGE_GET_ADDRESS;
			    msg.obj = result;
			       
			    callback.handleMessage(msg);
			}
		}
	}

}
