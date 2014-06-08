package sk.village.office.ui.fragments;

import sk.village.office.R;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

public class MoreFragment extends Fragment implements OnClickListener{
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			
		View v = inflater.inflate(R.layout.fragment_more, container, false);
		
		((TextView)v.findViewById(R.id.lumino_web)).setOnClickListener(this);
		return v;
	}

	@Override
	public void onClick(View v) {
		if(v.getId() == R.id.lumino_web){
			Intent i = new Intent(Intent.ACTION_VIEW);
			i.setData(Uri.parse("http://"+getActivity().getResources().getString(R.string.web)));
			startActivity(i);
		}
	}

	

}
