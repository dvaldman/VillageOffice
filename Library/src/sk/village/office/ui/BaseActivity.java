package sk.village.office.ui;


import sk.village.office.core.Configuration;
import sk.village.office.core.DataAggregator;
import sk.village.office.core.StorageManager;
import sk.village.office.db.DBHelper;
import sk.village.office.model.ContentHolder;
import sk.village.office.util.ConnectionChecker;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public abstract class BaseActivity extends FragmentActivity {
	
	
	public static DBHelper database;
	public static StorageManager storageMng;
	public static DataAggregator aggreg;
	public static Configuration config;
	public static ContentHolder content;
	private static ProgressDialog progressDialog;
	
	
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		init();
	}
	
	private void init(){
		if(config == null)
			config = Configuration.getInstance(this);
		if(storageMng == null)
			storageMng = StorageManager.getInstance(this);
		if(database == null)
			database = DBHelper.getInstance(this);
		if(aggreg == null)
			aggreg = DataAggregator.getInstance(this);
		if(content == null)
			content = ContentHolder.getInstance(this);
		
	}
	
	public boolean isOnline(){
		return ConnectionChecker.isOnline(this);
	}
	
	public void hideProgress(){
		progressDialog.dismiss();
	}
	
	public void showProgress(String str){
//		if(progressDialog == null)
		progressDialog = new ProgressDialog(this);
		progressDialog.setMessage(str);
		progressDialog.setCancelable(false);
		progressDialog.show();
	}
}
