package sk.village.office.ui.fragments;

import java.io.Serializable;
import java.util.List;

import sk.village.office.R;
import sk.village.office.adapters.MembersListAdapter;
import sk.village.office.adapters.NewsListAdapter;
import sk.village.office.core.Constants;
import sk.village.office.interfaces.IItem;
import sk.village.office.model.ContentHolder;
import sk.village.office.model.Member;
import sk.village.office.model.New;
import sk.village.office.ui.MainActivity;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class MembersListFragment extends Fragment implements OnItemClickListener{
	
	private ListView list;
	private static List<Member> listItems;
	private static String headerString;
	private TextView header;
	private View v;
	public static final String KEYWORD_DISPLAY   = "display_content";
	public static final int DISPLAY_MEMBERS		 = 0;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		obtainData(); 
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		if(v != null)
			return v;
		
		v = inflater.inflate(R.layout.fragment_news, container, false);
		header = ((TextView)v.findViewById(R.id.header));
		header.setText(headerString);
		
        list = (ListView) v.findViewById(R.id.news_list);
        list.setAdapter(new MembersListAdapter(listItems,getActivity()));
        list.setOnItemClickListener(this);
        
        return v;
	}
	
	private void obtainData(){
		try{
	        Bundle data = getArguments();
	        
	        if(data.containsKey(KEYWORD_DISPLAY))
		       	switch (data.getInt(KEYWORD_DISPLAY)) {
				case DISPLAY_MEMBERS:
					listItems = ContentHolder.getInstance(getActivity()).getMembers();
					headerString = getActivity().getResources().getString(R.string.assholes).toUpperCase();
					break;
				default:
					return;
				}
		}catch(Exception e){
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,	long id) {
		Fragment fragment = new MemberDetailFragment();
		Bundle args = new Bundle();
		args.putSerializable(MemberDetailFragment.MEMBER_KEY, (Serializable) list.getAdapter().getItem(position));
		fragment.setArguments(args);
		getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content, fragment).addToBackStack("tag").commit();
		Message msg = new Message();
		msg.what = Constants.MESSAGE_DESELECT;
		MainActivity.controlHandler.sendMessage(msg);
		
	}

}
