package sk.village.office.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;



import sk.village.office.util.Log;
import android.content.Context;

public class AssetsManager {

	private Context context;
	
	public AssetsManager(Context context){
		this.context = context;
	}
	
	public String readStringFromFile(String path){
		String str = null;
		StringBuilder buf=new StringBuilder();
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
