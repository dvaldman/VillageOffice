package sk.village.office.util;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;

import com.google.android.gms.maps.model.LatLng;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore.Images;
import android.util.TypedValue;

public class Util {
	
	public static Bitmap decodeUri(Uri selectedImage,Context context) throws FileNotFoundException {

		// Decode image size
		BitmapFactory.Options o = new BitmapFactory.Options();
		o.inJustDecodeBounds = true;
		BitmapFactory.decodeStream(context.getContentResolver().openInputStream(selectedImage), null, o);

		// The new size we want to scale to
		// final int REQUIRED_SIZE = tmp.getHeight();
		final int REQUIRED_SIZE = 150;
		Resources r = context.getResources();

		float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, REQUIRED_SIZE, r.getDisplayMetrics());

		// Find the correct scale value. It should be the power of 2.
		int width_tmp = o.outWidth, height_tmp = o.outHeight;
		int scale = 1;
		while (true) {
			if (width_tmp < px || height_tmp < px)
				break;
			width_tmp /= 2;
			height_tmp /= 2;
			scale *= 2;
		}

		// Decode with inSampleSize
		BitmapFactory.Options o2 = new BitmapFactory.Options();
		o2.inSampleSize = scale;
		o2.inPurgeable = true;
		o2.inTempStorage = new byte[1 * 1024];
		o2.inDither = false;
		o2.inInputShareable = true;
		return BitmapFactory.decodeStream(context.getContentResolver().openInputStream(selectedImage), null, o2);

	}
	
	public static Uri getImageUri(Context inContext, Bitmap inImage) {
		  ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		  inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
		  String path = Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
		  return Uri.parse(path);
		}
	
	public static String getDirectionsUrl(LatLng origin,LatLng dest){
		
		// Origin of route
		String str_origin = "origin="+origin.latitude+","+origin.longitude;
		
		// Destination of route
		String str_dest = "destination="+dest.latitude+","+dest.longitude;		
		
					
		// Sensor enabled
		String sensor = "sensor=false";			
					
		// Building the parameters to the web service
		String parameters = str_origin+"&"+str_dest+"&"+sensor;
					
		// Output format
		String output = "json";
		
		// Building the url to the web service
		String url = "https://maps.googleapis.com/maps/api/directions/"+output+"?"+parameters;
		
		
		return url;
	}

}
