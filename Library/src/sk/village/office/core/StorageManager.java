package sk.village.office.core;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class StorageManager {
	
	private static final String APP_SHARED_PREFS = StorageManager.class.getSimpleName();
	private static SharedPreferences sharedPrefs;
    private static Editor prefsEditor;
    private static StorageManager instance;
    
    public static final String APP_HAS_DATA = "app_has_data";  
    public static final String DEFAULT_USER = "default_user";
    
    private StorageManager(Context context) {
    	sharedPrefs = context.getSharedPreferences(APP_SHARED_PREFS, Activity.MODE_PRIVATE);
    	prefsEditor = sharedPrefs.edit();
    }
    
    public static StorageManager getInstance(Context context){
    	if(instance == null)
    		instance = new StorageManager(context);
    	
    	return instance;
    }
    
    public void saveValue(String key,String value){
    	prefsEditor.putString(key, value);
    	prefsEditor.commit();
    }
    
    public void saveValue(String key,int value){
    	prefsEditor.putInt(key, value);
    	prefsEditor.commit();
    }
    
    public void saveValue(String key,long value){
    	prefsEditor.putLong(key, value);
    	prefsEditor.commit();
    }
    
    public void saveValue(String key,boolean value){
    	prefsEditor.putBoolean(key, value);
    	prefsEditor.commit();
    }
    
    public void saveValue(String key,float value){
    	prefsEditor.putFloat(key, value);
    	prefsEditor.commit();
    }
    
    
    public String loadString(String key){
    	return sharedPrefs.getString(key,null);
    }
    
    public int loadInt(String key){
    	return sharedPrefs.getInt(key,0);
    }
    
    public long loadLong(String key){
    	return sharedPrefs.getLong(key,0);
    }
    
    public boolean loadBoolean(String key){
    	return sharedPrefs.getBoolean(key,false);
    }
    
    public float loadFloat(String key){
    	return sharedPrefs.getFloat(key,0);
    }
    
    public String loadString(String key, String defVal){
    	return sharedPrefs.getString(key,defVal);
    }
    
    public int loadInt(String key, int defVal){
    	return sharedPrefs.getInt(key,defVal);
    }
    
    public long loadLong(String key, Long defVal){
    	return sharedPrefs.getLong(key,defVal);
    }
    
    public boolean loadBoolean(String key, boolean defVal){
    	return sharedPrefs.getBoolean(key,defVal);
    }
    
    public float loadFloat(String key, float defVal){
    	return sharedPrefs.getFloat(key,defVal);
    }
    
    public boolean contains(String key){
    	return sharedPrefs.contains(key);
    }
}
