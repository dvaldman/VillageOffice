package sk.village.office.ui.fragments;

import sk.village.office.R;
import sk.village.office.adapters.OfficeListAdapter;
import sk.village.office.listeners.ListMenuOnItemClickListener;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class OfficeFragment extends Fragment{
	
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
        list.setOnItemClickListener(new ListMenuOnItemClickListener(listItemNames, getActivity()));
        return v;
    }

}
