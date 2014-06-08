package sk.village.office.ui.fragments;

import sk.village.office.R;
import sk.village.office.adapters.MainMenuListAdapter;
import sk.village.office.listeners.ListMenuOnItemClickListener;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class MainMenuFragment extends Fragment{

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
        list.setOnItemClickListener(new ListMenuOnItemClickListener(listItemNames, getActivity()));
        
        return v;
    }

}
