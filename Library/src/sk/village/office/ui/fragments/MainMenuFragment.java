package sk.village.office.ui.fragments;

import java.util.List;

import sk.village.office.R;
import sk.village.office.adapters.MainMenuListAdapter;
import sk.village.office.core.Constants;
import sk.village.office.ui.MainActivity;
import sk.village.office.util.Log;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnHoverListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ListView;
import android.widget.TextView;

public class MainMenuFragment extends Fragment implements OnItemClickListener{

	private ListView list;
	private String[] listItemNames;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_main_menu, container, false);
        listItemNames = getResources().getStringArray(R.array.main_menu);
        list = (ListView) v.findViewById(R.id.main_menu_list);
        list.setAdapter(new MainMenuListAdapter(listItemNames,getActivity()));
        list.setOnItemClickListener(this);
        
        return v;
    }

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,	long id) {
		if(listItemNames[position].equalsIgnoreCase(getActivity().getString(R.string.news)))
			openNews();
		if(listItemNames[position].equalsIgnoreCase(getActivity().getString(R.string.places)))
			openPlaces();
		if(listItemNames[position].equalsIgnoreCase(getActivity().getString(R.string.free_time)))
			openFreeTime();
		if(listItemNames[position].equalsIgnoreCase(getActivity().getString(R.string.report)))
			openReport();
		if(listItemNames[position].equalsIgnoreCase(getActivity().getString(R.string.traffic)))
			openTraffic();
		if(listItemNames[position].equalsIgnoreCase(getActivity().getString(R.string.contacts)))
			openContacts();
		if(listItemNames[position].equalsIgnoreCase(getActivity().getString(R.string.traffic_parking)))
			openTrafficParking();
		if(listItemNames[position].equalsIgnoreCase(getActivity().getString(R.string.matrika)))
			openMatrika();
		if(listItemNames[position].equalsIgnoreCase(getActivity().getString(R.string.atrium)))
			openAtrium();
		
	}
	
	private void openNews(){
		Fragment fragment = new ArticlesListFragment();
		Bundle args = new Bundle();
		args.putInt(ArticlesListFragment.KEYWORD_DISPLAY, ArticlesListFragment.DISPLAY_NEWS);
		fragment.setArguments(args);
		changeFragmentAndDeselect(fragment);
	}
	
	private void openAtrium(){
		Fragment fragment = new ArticlesListFragment();
		Bundle args = new Bundle();
		args.putInt(ArticlesListFragment.KEYWORD_DISPLAY, ArticlesListFragment.DISPLAY_ATRIUM);
		fragment.setArguments(args);
		changeFragmentAndDeselect(fragment);
	}
	
	private void openTrafficParking(){
		changeFragmentAndDeselect(new TrafficParkingFragment());
	}
	
	private void openFreeTime(){
		changeFragmentAndDeselect(new TrafficFragment());
	}
	
	private void openReport(){
		changeFragmentAndDeselect(new ReportFragment());
	}

	private void openTraffic(){
		changeFragmentAndDeselect(new TrafficFragment());
	}
	
	private void openMatrika(){
		changeFragmentAndDeselect(new MatrikaFragment());
	}
	
	private void openPlaces(){
		Message msg = new Message();
		msg.what = Constants.MESSAGE_SELECT_MAP;
		MainActivity.controlHandler.sendMessage(msg);
	}
	
	private void openContacts(){
		Message msg = new Message();
		msg.what = Constants.MESSAGE_SELECT_CONTACTS;
		MainActivity.controlHandler.sendMessage(msg);
	}
	
	private void changeFragmentAndDeselect(Fragment fragment){
		getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content, fragment).addToBackStack("tag").commit();
		deselect();
	}
	
	private void deselect(){
		Message msg = new Message();
		msg.what = Constants.MESSAGE_DESELECT;
		MainActivity.controlHandler.sendMessage(msg);
	}
	
	
}
