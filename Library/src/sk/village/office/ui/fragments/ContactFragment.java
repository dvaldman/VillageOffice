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
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

public class ContactFragment extends Fragment implements OnClickListener{

	
	private View v;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		v = inflater.inflate(R.layout.fragment_contacts, container, false);
		
		((TextView)v.findViewById(R.id.header)).setText(getActivity().getResources().getString(R.string.contacts).toUpperCase());
		
		setListeners(container);
		
		return v;
	}
	
	private void setListeners(ViewGroup vg){
		((TextView)v.findViewById(R.id.mayors_office_tel)).setOnClickListener(this);
		((TextView)v.findViewById(R.id.mayors_office_fax)).setOnClickListener(this);
		((TextView)v.findViewById(R.id.mayors_office_mail)).setOnClickListener(this);
		((TextView)v.findViewById(R.id.matrika_tel)).setOnClickListener(this);
		((TextView)v.findViewById(R.id.checking_tel)).setOnClickListener(this);
		((TextView)v.findViewById(R.id.evidence_tel)).setOnClickListener(this);
		((TextView)v.findViewById(R.id.social_tel)).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		TextView tv = (TextView)v;
		if(tv.getId() == R.id.mayors_office_tel)
			callNumber(tv.getText().toString());
		if(tv.getId() == R.id.mayors_office_fax)
			callNumber(tv.getText().toString());
		if(tv.getId() == R.id.mayors_office_mail)
			sendMail(tv.getText().toString());
		
		if(tv.getId() == R.id.matrika_tel)
			callNumber(tv.getText().toString());
		if(tv.getId() == R.id.checking_tel)
			callNumber(tv.getText().toString());
		if(tv.getId() == R.id.evidence_tel)
			callNumber(tv.getText().toString());
		if(tv.getId() == R.id.social_tel)
			callNumber(tv.getText().toString());
		
	}
	
	private void callNumber(String number){
		String numberToDial = "tel:"+number.trim();
        startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(numberToDial)));
	}
	
	private void sendMail(String mail){
		Intent i = new Intent(Intent.ACTION_SEND_MULTIPLE);
		i.setType("plain/text");
		i.putExtra(Intent.EXTRA_EMAIL  , new String[]{mail});
		
		try {
		    startActivity(Intent.createChooser(i, getActivity().getResources().getString(R.string.send_mail_by)));
		} catch (android.content.ActivityNotFoundException ex) {
		    Toast.makeText(getActivity(), "There are no email clients installed.", Toast.LENGTH_SHORT).show();
		}
	}
	
	

}
