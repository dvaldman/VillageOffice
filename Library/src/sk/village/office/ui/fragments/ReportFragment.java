package sk.village.office.ui.fragments;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import com.google.android.gms.maps.model.LatLng;

import sk.village.office.R;
import sk.village.office.core.Configuration;
import sk.village.office.core.Constants;
import sk.village.office.core.GPSProvider;
import sk.village.office.ui.BaseActivity;
import sk.village.office.util.AddressProvider;
import sk.village.office.util.Log;
import sk.village.office.util.Util;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.Handler.Callback;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ReportFragment extends Fragment implements OnClickListener{
	
	private LinearLayout photosContainer;
	private static final int SELECT_PICTURE_CAMERA = 0;
	private LayoutInflater inflater;
	private static ArrayList<Bitmap> photos;
	private EditText et;
	private static String report="";
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if(photos == null)
			photos = new ArrayList<Bitmap>();
		
	}

		
	@SuppressLint("HandlerLeak")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		this.inflater = inflater;
		final View v = inflater.inflate(R.layout.fragment_report, container, false);
		((TextView)v.findViewById(R.id.header)).setText(getActivity().getResources().getString(R.string.report).toUpperCase());
	    photosContainer = (LinearLayout) v.findViewById(R.id.photos_container);
		v.findViewById(R.id.send_report).setOnClickListener(this);
	    
	    LatLng pos = new LatLng(GPSProvider.getInstance(getActivity()).getLatitude(), GPSProvider.getInstance(getActivity()).getLongitude());
//	    Callback callback = new Callback(){
//	    	@Override
//	    	public boolean handleMessage(Message msg) {
//	    		
//	    		switch (msg.what) {
//				case Constants.MESSAGE_GET_ADDRESS:
//					((TextView)v.findViewById(R.id.report_address)).setText((String)msg.obj);
//					return true;
//
//				}
//	    	return false;
//	    	}
//	    };
	    
	    AddressProvider.getAddressForLatLong(getActivity(), pos, AddressProvider.STREET_CITY_COUNTRY,((TextView)v.findViewById(R.id.report_address)));
		
	    et = (EditText) v.findViewById(R.id.report_desc);
	    et.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				report = et.getText().toString();
			}
		});
	    
        return v;
	}
	
	private void fillPhotoContainer(){
		photosContainer.removeAllViews();
		
		for(int i=0;i < photos.size();i++){
			RelativeLayout v = (RelativeLayout) inflater.inflate(R.layout.report_photo, photosContainer, false);
			ImageView img = (ImageView) v.findViewById(R.id.photo);
			Drawable d = new BitmapDrawable(getResources(),photos.get(i));
			img.setBackgroundDrawable(d);
			v.findViewById(R.id.delete_photo).setOnClickListener(new OnPhotoDeleteClickListener(i));
			photosContainer.addView(v);
		}
		 
		ImageButton plus = new ImageButton(getActivity());
		plus.setBackgroundDrawable(getActivity().getResources().getDrawable(R.drawable.button_plus));
		plus.setOnClickListener(new OnAddPhotoClickListener(getActivity()));
		photosContainer.addView(plus);
	}
	
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		((BaseActivity)getActivity()).hideProgress();
		if (resultCode == Activity.RESULT_OK) {
			File file = null;
			if (requestCode == SELECT_PICTURE_CAMERA)
				file = getTempFile(getActivity());
			
			if (file == null) {
			} else {
				try {
					Bitmap captureBmp = Util.decodeUri(Uri.fromFile(file),getActivity());
					
					if(photos.size() == Configuration.numberOfReportPhotos)
						photos.remove(photos.size()-1);
					photos.add(captureBmp);
					
				} catch (NullPointerException e) {
					e.printStackTrace();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	@Override
	public void onResume() {
		super.onResume();
		fillPhotoContainer();
		
		if(report != null && !report.equalsIgnoreCase(""))
			 et.setText(report);
		    
	}
	
	
	
	private File getTempFile(Context context) {
		return new File(Environment.getExternalStorageDirectory(), "image.tmp");
	}

	
	private class OnAddPhotoClickListener implements OnClickListener{
		
		private Context context;
		
		public OnAddPhotoClickListener(Context cont){
			this.context = cont;
		}

		@Override
		public void onClick(View v) {
			((BaseActivity)getActivity()).showProgress(getActivity().getResources().getString(R.string.please_wait));
			Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE); 
			intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(getTempFile(context)));
			startActivityForResult(intent, SELECT_PICTURE_CAMERA);
			
		}
		
		
	}
	
	private class OnPhotoDeleteClickListener implements OnClickListener{
		private int index; 
		
		public OnPhotoDeleteClickListener(int i){
			this.index = i;
		}
		
		@Override
		public void onClick(View v) {
			photos.remove(index);
			fillPhotoContainer();
		}
		
		
	}

	@Override
	public void onClick(View v) {
		
		if(Configuration.reportToMail)
			reportToMail();
		
	}
	
	private void reportToMail(){
		Intent i = new Intent(Intent.ACTION_SEND_MULTIPLE);
		i.setType("plain/text");
		i.putExtra(Intent.EXTRA_EMAIL  , new String[]{Configuration.mailForReport});
		i.putExtra(Intent.EXTRA_SUBJECT, getActivity().getResources().getString(R.string.problem_report));
		i.putExtra(Intent.EXTRA_TEXT   , et.getText());
		
		ArrayList<Uri> uris = new ArrayList<Uri>();
		
		for(Bitmap bm : photos)
			uris.add(Util.getImageUri(getActivity(), bm));
		i.putParcelableArrayListExtra(Intent.EXTRA_STREAM, uris); 
		try {
		    startActivity(Intent.createChooser(i, getActivity().getResources().getString(R.string.send_mail_by)));
		} catch (android.content.ActivityNotFoundException ex) {
		    Toast.makeText(getActivity(), "There are no email clients installed.", Toast.LENGTH_SHORT).show();
		}
	}
}
