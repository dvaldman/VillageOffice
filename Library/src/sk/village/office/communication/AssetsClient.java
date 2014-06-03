package sk.village.office.communication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;



import sk.village.office.util.Log;
import android.content.Context;

public class AssetsClient {

	private Context context;
	
	public AssetsClient(Context context){
		this.context = context;
	}
	
	public String readStringFromFile(String path){
		String str = null;
		StringBuilder buf=new StringBuilder();
		Log.i("getting data from: "+path);
		try {
			
		    InputStream json = context.getAssets().open(path);
		    
			BufferedReader in = new BufferedReader(new InputStreamReader(json));
		    
		    while ((str=in.readLine()) != null) {
		      buf.append(str);
		    }
		    in.close();
		} catch (IOException e) {
			Log.e(e.toString());
		}
	    return buf.toString();
	}
	
}
