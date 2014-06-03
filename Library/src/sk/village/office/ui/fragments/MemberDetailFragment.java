package sk.village.office.ui.fragments;

import java.io.Serializable;

import sk.village.office.R;
import sk.village.office.model.Member;
import sk.village.office.model.New;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ImageButton;

public class MemberDetailFragment extends Fragment implements OnClickListener{

	public static final String MEMBER_KEY = "MEMBER_KEY";
	private Member member;
	private View v;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		 v = inflater.inflate(R.layout.fragment_member_detail, container, false);
		 Bundle data = getArguments();
			
		 if(data != null)
			 if(data.containsKey(MEMBER_KEY))
				 member = (Member) data.getSerializable(MEMBER_KEY);
		 
		 initArticle();
		 ((ImageButton)v.findViewById(R.id.exit_button)).setOnClickListener(this);
		 return v;
	}
	
	private void initArticle(){
//		((TextView)v.findViewById(R.id.news_detail_date)).setText(member.getDate());
//		((TextView)v.findViewById(R.id.news_detail_title)).setText(member.getTitle());
//		((TextView)v.findViewById(R.id.news_detail_long_desc)).setText(member.getLongDesc());
	}

	@Override
	public void onClick(View v) {
		if(v.getId() == R.id.exit_button){
			Fragment fragment = new MembersListFragment();
			getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content, fragment).addToBackStack("tag").commit();
		}
			
	}
	
}
