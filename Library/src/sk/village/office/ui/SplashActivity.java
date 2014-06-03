package sk.village.office.ui;

import sk.village.office.R;
import sk.village.office.core.Constants;
import sk.village.office.core.DataAggregator;
import sk.village.office.util.Log;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;



public class SplashActivity extends BaseActivity{
	ProgressBar progressBar;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_splash);
		setElements();
		
		new InitializationTask().execute();
	}
	
	private void setElements(){
		progressBar = (ProgressBar)findViewById(R.id.progressBar);
	}
	
	private void startMainActivity(){
		Intent intent = new Intent(SplashActivity.this, MainActivity.class);
		startActivity(intent);
		finish();
	}
	
	
	class InitializationTask extends AsyncTask<Void, Void, Void> {
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();			
			progressBar.setVisibility(View.VISIBLE);
		}
		
		@Override
		protected Void doInBackground(Void... params) {
			try {
				
				if(!database.doesTableExist(Constants.GET_CONTENT_MAYOR))
					Log.v("data initialized "+aggreg.updateContetnt(Constants.GET_CONTENT_MAYOR));
				
				if(!database.doesTableExist(Constants.GET_CONTENT_PLACES))
					Log.v("data initialized "+aggreg.updateContetnt(Constants.GET_CONTENT_PLACES));
				
				if(!database.doesTableExist(Constants.GET_CONTENT_NEWS))
					Log.v("data initialized "+aggreg.updateContetnt(Constants.GET_CONTENT_NEWS));
				
				if(!database.doesTableExist(Constants.GET_CONTENT_OFFICE_BOARD))
					Log.v("data initialized "+aggreg.updateContetnt(Constants.GET_CONTENT_OFFICE_BOARD));
				
				if(!database.doesTableExist(Constants.GET_CONTENT_ATRIUM))
					Log.v("data initialized "+aggreg.updateContetnt(Constants.GET_CONTENT_ATRIUM));
				
				if(!database.doesTableExist(Constants.GET_CONTENT_PARLIAMENT))
					Log.v("data initialized "+aggreg.updateContetnt(Constants.GET_CONTENT_PARLIAMENT));
				
			} catch (Exception e) {
				e.printStackTrace();
			} 
			return null;
		}
		
		@Override
		protected void onCancelled() {
			
		}
		
		
		@Override
		protected void onPostExecute(Void result) {
			progressBar.setVisibility(View.INVISIBLE);
			startMainActivity();
		}
	
	}
}
