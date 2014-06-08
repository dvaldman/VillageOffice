package sk.village.office.ui;



import sk.village.office.R;
import sk.village.office.core.Constants;
import sk.village.office.ui.fragments.ContactFragment;
import sk.village.office.ui.fragments.MainMenuFragment;
import sk.village.office.ui.fragments.MoreFragment;
import sk.village.office.ui.fragments.OfficeFragment;
import sk.village.office.ui.fragments.PlacesFragment;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class MainActivity extends BaseActivity implements OnClickListener{

	private static Button button1, button2, button3, button4, button5;

	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		
		setContentView(R.layout.activity_main);
		setListeners();
		setDefaultButtonBackground();
		
		button1.setBackgroundResource(R.drawable.ico1_act);
		switchFragment(new MainMenuFragment());
	}
	
	private static void setDefaultButtonBackground(){
		button1.setBackgroundResource(R.drawable.ico1_inact);
		button2.setBackgroundResource(R.drawable.ico2_inact);
		button3.setBackgroundResource(R.drawable.ico3_inact);
		button4.setBackgroundResource(R.drawable.ico4_inact);
		button5.setBackgroundResource(R.drawable.ico5_inact);
	}
	
	private void setListeners(){
   	 	button1 = (Button)findViewById(R.id.button1);
        button1.setOnClickListener(this);
        button2 = (Button)findViewById(R.id.button2);
        button2.setOnClickListener(this);
        button3 = (Button)findViewById(R.id.button3);
        button3.setOnClickListener(this);
        button4 = (Button)findViewById(R.id.button4);
        button4.setOnClickListener(this);
        button5 = (Button)findViewById(R.id.button5);
        button5.setOnClickListener(this);
    }
	
	
	public static Handler controlHandler = new Handler(){
			@Override
			public void handleMessage(Message msg) {
				switch(msg.what){
				case Constants.MESSAGE_SELECT_MAIN:
					button1.performClick();
					break;
				case Constants.MESSAGE_SELECT_MAP:
					button2.performClick();
					break;
				case Constants.MESSAGE_SELECT_OFFICE:
					button3.performClick();
					break;
				case Constants.MESSAGE_SELECT_CONTACTS:
					button4.performClick();
					break;
				case Constants.MESSAGE_SELECT_MORE:
					button5.performClick();
					break;
				case Constants.MESSAGE_DESELECT:
					setDefaultButtonBackground();
				}
			};
		};
		
		private void switchFragment(Fragment fragment){
			android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
			fragmentManager
					.beginTransaction()
					.replace(R.id.content, fragment).addToBackStack("tag")
					.commit();
		}

		@Override
		public void onClick(View v) {
			setDefaultButtonBackground();
			
			if(v.getId()==R.id.button1){
				button1.setBackgroundResource(R.drawable.ico1_act);
				switchFragment(new MainMenuFragment());
			}
			if(v.getId()==R.id.button2){
				button2.setBackgroundResource(R.drawable.ico2_act);
				switchFragment(new PlacesFragment());
			}
			if(v.getId()==R.id.button3){
				button3.setBackgroundResource(R.drawable.ico3_act);
				switchFragment(new OfficeFragment());
			}
			if(v.getId()==R.id.button4){
				button4.setBackgroundResource(R.drawable.ico4_act);
				switchFragment(new ContactFragment());
			}
			if(v.getId()==R.id.button5){
				button5.setBackgroundResource(R.drawable.ico5_act);
				switchFragment(new MoreFragment());
			}
		}
		
		@Override
		public void onBackPressed() {
			finish();
		}
	
}
