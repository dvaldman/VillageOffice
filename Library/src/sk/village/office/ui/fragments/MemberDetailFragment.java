package sk.village.office.ui.fragments;

import java.io.Serializable;
import java.util.ArrayList;

import sk.village.office.R;
import sk.village.office.core.Configuration;
import sk.village.office.core.DrawableManager;
import sk.village.office.model.Member;
import sk.village.office.model.New;
import sk.village.office.util.Util;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ImageButton;
import android.widget.Button;
import android.widget.Toast;

public class MemberDetailFragment extends Fragment implements OnClickListener{

	public static final String MEMBER_KEY = "MEMBER_KEY";
	private Member member;
	private View v;
	private DrawableManager drawableManager;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		 v = inflater.inflate(R.layout.fragment_member_detail, container, false);
		
		 drawableManager = new DrawableManager();
		 Bundle data = getArguments();
		 
		 if(data != null)
			 if(data.containsKey(MEMBER_KEY))
				 member = (Member) data.getSerializable(MEMBER_KEY);
		 
		 if(member.getThumbnail()!=null)
	        	drawableManager.fetchScaledDrawableOnThread(member.getThumbnail(), ((ImageView) v.findViewById(R.id.member_photo)), null);
	       
		 
		 initArticle();
		 
		 
		 ((ImageButton)v.findViewById(R.id.exit_button)).setOnClickListener(this);
		 ((Button)v.findViewById(R.id.member_detail_mail)).setOnClickListener(this);
		 return v;
	}
	
	private void initArticle(){
		
		
		((TextView)v.findViewById(R.id.member_detail_name)).setText(member.getName());
		if(member.getEmail()==null||member.getEmail().equalsIgnoreCase("null"))
			((Button)v.findViewById(R.id.member_detail_mail)).setVisibility(View.GONE);
		else
			((Button)v.findViewById(R.id.member_detail_mail)).setText(": "+member.getEmail());
		
		
		((TextView)v.findViewById(R.id.member_age))
									.setText(member.getAge());
		((TextView)v.findViewById(R.id.member_job))
									.setText(member.getJob());
		((TextView)v.findViewById(R.id.member_vote_location))
									.setText(member.getVoteLocation());
		((TextView)v.findViewById(R.id.member_time_interval))
									.setText(member.getTimeInterval());
		((TextView)v.findViewById(R.id.member_coalition))
									.setText(member.getCoalition());
		((TextView)v.findViewById(R.id.member_function))
									.setText(member.getFunction());
		((TextView)v.findViewById(R.id.member_party))
									.setText(member.getParty());
	}

	@Override
	public void onClick(View v) {
		if(v.getId() == R.id.exit_button){
			Fragment fragment = new MembersListFragment();
			getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content, fragment).addToBackStack("tag").commit();
		}
		
		if(v.getId() == R.id.member_detail_name){
			Intent i = new Intent(Intent.ACTION_SEND_MULTIPLE);
			i.setType("plain/text");
			i.putExtra(Intent.EXTRA_EMAIL  , new String[]{member.getEmail()});
			
			try {
			    startActivity(Intent.createChooser(i, getActivity().getResources().getString(R.string.send_mail_by)));
			} catch (android.content.ActivityNotFoundException ex) {
			    Toast.makeText(getActivity(), "There are no email clients installed.", Toast.LENGTH_SHORT).show();
			}
		}
			
	}
	
}
