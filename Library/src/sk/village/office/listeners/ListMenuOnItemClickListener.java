package sk.village.office.listeners;

import sk.village.office.R;
import sk.village.office.core.Constants;
import sk.village.office.ui.MainActivity;
import sk.village.office.ui.fragments.ArticlesListFragment;
import sk.village.office.ui.fragments.MatrikaFragment;
import sk.village.office.ui.fragments.MayorDetailFragment;
import sk.village.office.ui.fragments.MembersListFragment;
import sk.village.office.ui.fragments.NewsDetailFragment;
import sk.village.office.ui.fragments.ReportFragment;
import sk.village.office.ui.fragments.TrafficFragment;
import sk.village.office.ui.fragments.TrafficParkingFragment;
import android.app.Activity;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class ListMenuOnItemClickListener implements OnItemClickListener{

	private String[] listItemNames;
	private Activity activity;
	
	public ListMenuOnItemClickListener(String[] ItemNames, Activity act){
		this.listItemNames = ItemNames;
		this.activity = act;
	}
	
	public Activity getActivity(){
		return this.activity;
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
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
		if(listItemNames[position].equalsIgnoreCase(getActivity().getString(R.string.office_main_data)))
			openInfo();
		if(listItemNames[position].equalsIgnoreCase(getActivity().getString(R.string.mayor)))
			openMayor();
		if(listItemNames[position].equalsIgnoreCase(getActivity().getString(R.string.assholes)))
			openMembers();
		if(listItemNames[position].equalsIgnoreCase(getActivity().getString(R.string.elections)))
			openElections();
		if(listItemNames[position].equalsIgnoreCase(getActivity().getString(R.string.office_board)))
			openOfficeBoard();
		
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
	
	private void openOfficeBoard(){
		Fragment fragment = new ArticlesListFragment();
		Bundle args = new Bundle();
		args.putInt(ArticlesListFragment.KEYWORD_DISPLAY, ArticlesListFragment.DISPLAY_OFFICE_BOARD);
		fragment.setArguments(args);
		changeFragmentAndDeselect(fragment);
	}
	
	private void openInfo(){
	}
	
	private void openMayor(){
		changeFragmentAndDeselect(new MayorDetailFragment());
	}
	
	private void openMembers(){
		Fragment fragment = new MembersListFragment();
		Bundle args = new Bundle();
		args.putInt(MembersListFragment.KEYWORD_DISPLAY, MembersListFragment.DISPLAY_MEMBERS);
		fragment.setArguments(args);
		changeFragmentAndDeselect(fragment);
	}
	
	private void openElections(){
	}
	
	private void changeFragmentAndDeselect(Fragment fragment){
		((MainActivity)getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.content, fragment).addToBackStack("tag").commit();
		deselect();
	}
	
	private void deselect(){
		Message msg = new Message();
		msg.what = Constants.MESSAGE_DESELECT;
		MainActivity.controlHandler.sendMessage(msg);
	}
}
