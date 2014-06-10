package sk.village.office.ui.fragments;

import java.io.Serializable;

import sk.village.office.R;
import sk.village.office.model.ContentHolder;
import sk.village.office.model.Mayor;
import sk.village.office.model.New;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ImageButton;
import android.widget.Button;
import android.widget.Toast;

public class MayorDetailFragment extends Fragment implements OnClickListener{

	private Mayor mayor;
	private View v;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		 v = inflater.inflate(R.layout.fragment_mayor_detail, container, false);
		 
		mayor = ContentHolder.getInstance(getActivity()).getMayor();
		 
		 initArticle();
		 ((ImageButton)v.findViewById(R.id.exit_button)).setOnClickListener(this);
		 ((Button)v.findViewById(R.id.mayor_mail)).setOnClickListener(this);
		 ((Button)v.findViewById(R.id.mayor_tel)).setOnClickListener(this);
		 return v;
	}
	
	private void initArticle(){
		((TextView)v.findViewById(R.id.mayor_name)).setText(mayor.getName());
		((Button)v.findViewById(R.id.mayor_mail)).setText(": "+mayor.getEmail());
		((Button)v.findViewById(R.id.mayor_tel)).setText(": "+mayor.getTelNumber());
		((TextView)v.findViewById(R.id.mayor_desc)).setText(mayor.getDescription());
	}

	@Override
	public void onClick(View v) {
		if(v.getId() == R.id.exit_button){
			Fragment fragment = new OfficeFragment();
			getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content, fragment).addToBackStack("tag").commit();
		}
		
		if(v.getId() == R.id.mayor_mail){
			Intent i = new Intent(Intent.ACTION_SEND_MULTIPLE);
			i.setType("plain/text");
			i.putExtra(Intent.EXTRA_EMAIL  , new String[]{mayor.getEmail()});
			
			try {
			    startActivity(Intent.createChooser(i, getActivity().getResources().getString(R.string.send_mail_by)));
			} catch (android.content.ActivityNotFoundException ex) {
			    Toast.makeText(getActivity(), "There are no email clients installed.", Toast.LENGTH_SHORT).show();
			}
		}
		
		if(v.getId() == R.id.mayor_tel){
			String numberToDial = "tel:"+mayor.getTelNumber().trim();
            startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(numberToDial)));
			Intent callIntent = new Intent(Intent.ACTION_CALL);
			
		}
			
	}
	
}
