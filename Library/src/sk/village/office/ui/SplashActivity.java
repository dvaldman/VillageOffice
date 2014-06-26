package sk.village.office.ui;

import sk.village.office.R;
import sk.village.office.core.Configuration;
import sk.village.office.core.Constants;
import sk.village.office.core.DataAggregator;
import sk.village.office.util.Log;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
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
	
	private void startAppDelayed(int SPLASH_DELAY){
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				progressBar.setVisibility(View.INVISIBLE);
				startMainActivity();
			}
		}, SPLASH_DELAY);
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
				
				if(aggreg.doesDataNeedToBeUpdated(Constants.GET_CONTENT_MAYOR))
					Log.v("data mayor initialized "+aggreg.updateContetnt(Constants.GET_CONTENT_MAYOR));
				
				if(aggreg.doesDataNeedToBeUpdated(Constants.GET_CONTENT_PLACES))
					Log.v("data places initialized "+aggreg.updateContetnt(Constants.GET_CONTENT_PLACES));
				
				if(aggreg.doesDataNeedToBeUpdated(Constants.GET_CONTENT_NEWS))
					Log.v("data news initialized "+aggreg.updateContetnt(Constants.GET_CONTENT_NEWS));
				
				if(aggreg.doesDataNeedToBeUpdated(Constants.GET_CONTENT_OFFICE_BOARD))
					Log.v("data office board initialized "+aggreg.updateContetnt(Constants.GET_CONTENT_OFFICE_BOARD));
				
				if(aggreg.doesDataNeedToBeUpdated(Constants.GET_CONTENT_ATRIUM))
					Log.v("data atrium initialized "+aggreg.updateContetnt(Constants.GET_CONTENT_ATRIUM));
				
				if(aggreg.doesDataNeedToBeUpdated(Constants.GET_CONTENT_PARLIAMENT))
					Log.v("data parliament initialized "+aggreg.updateContetnt(Constants.GET_CONTENT_PARLIAMENT));
				
				if(aggreg.doesDataNeedToBeUpdated(Constants.GET_CONTENT_ATRIUM_PROGRAM))
					Log.v("data atrium program initialized "+aggreg.updateContetnt(Constants.GET_CONTENT_ATRIUM_PROGRAM));
				
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
			
			// TODO: nakodit timer, ktory spustim pri onpre ex, pobezi v threade a kazdu sekundu pripocitam 1. v onpost ho stopnem. 
			startAppDelayed(Constants.DELAY_1S);
		}
	
	}
}
