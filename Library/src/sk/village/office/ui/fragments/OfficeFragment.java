package sk.village.office.ui.fragments;

import java.io.Serializable;

import sk.village.office.R;
import sk.village.office.adapters.MainMenuListAdapter;
import sk.village.office.adapters.OfficeListAdapter;
import sk.village.office.core.Constants;
import sk.village.office.model.New;
import sk.village.office.ui.MainActivity;
import sk.village.office.util.Log;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class OfficeFragment extends Fragment implements OnItemClickListener{
	
	private ListView list;
	private String[] listItemNames;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_office, container, false);
        listItemNames = getResources().getStringArray(R.array.office_menu);
        list = (ListView) v.findViewById(R.id.office_menu_list);
        list.setAdapter(new OfficeListAdapter(listItemNames,getActivity()));
        list.setOnItemClickListener(this);
        return v;
    }

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
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
		Fragment fragment = new NewsDetailFragment();
		Bundle args = new Bundle();
		
//		args.putSerializable(NewsDetailFragment.ARTICLE_KEY, (Serializable) list.getAdapter().getItem(position));
//		fragment.setArguments(args);
		getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content, fragment).addToBackStack("tag").commit();
		Message msg = new Message();
		msg.what = Constants.MESSAGE_DESELECT;
		MainActivity.controlHandler.sendMessage(msg);
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
		getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content, fragment).addToBackStack("tag").commit();
		deselect();
	}
	
	private void deselect(){
		Message msg = new Message();
		msg.what = Constants.MESSAGE_DESELECT;
		MainActivity.controlHandler.sendMessage(msg);
	}

}
