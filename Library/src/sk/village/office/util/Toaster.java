package sk.village.office.util;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

public class Toaster {
	
	public static Toast toast;
	
	public static void showToast(Activity act, String string){
    	Context context = act.getApplicationContext();
    	int duration = Toast.LENGTH_LONG;

    	toast = Toast.makeText(context , string, duration);
    	toast.show();    	
    }

	public static void closeToast(){
		toast.cancel();
	}
	
}
