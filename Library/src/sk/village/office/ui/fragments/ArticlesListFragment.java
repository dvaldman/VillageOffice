package sk.village.office.ui.fragments;

import java.io.Serializable;
import java.util.List;

import sk.village.office.R;
import sk.village.office.adapters.NewsListAdapter;
import sk.village.office.core.Constants;
import sk.village.office.model.ContentHolder;
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

public class ArticlesListFragment extends Fragment implements OnItemClickListener{
	
	private ListView list;
	private static List<New> listItems;
	private static String headerString;
	private TextView header;
	private View v;
	public static final String KEYWORD_DISPLAY   = "display_content";
	public static final int DISPLAY_NEWS  		 = 0;
	public static final int DISPLAY_OFFICE_BOARD = 1;
	public static final int DISPLAY_ATRIUM 		 = 2;
	
	
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
        list.setAdapter(new NewsListAdapter(listItems,getActivity()));
        list.setOnItemClickListener(this);
         
        return v;
	}
	
	private void obtainData(){
		try{
	        Bundle data = getArguments();
	        
	        if(data.containsKey(KEYWORD_DISPLAY))
		       	switch (data.getInt(KEYWORD_DISPLAY)) {
				case DISPLAY_NEWS:
					headerString = getActivity().getResources().getString(R.string.news).toUpperCase();
					listItems = ContentHolder.getInstance(getActivity()).getNews();
					break;
				case DISPLAY_OFFICE_BOARD:
					headerString = getActivity().getResources().getString(R.string.office_board).toUpperCase();
					listItems = ContentHolder.getInstance(getActivity()).getOfficeBoard();
					break;
				case DISPLAY_ATRIUM:
					headerString = getActivity().getResources().getString(R.string.atrium).toUpperCase();
					listItems = ContentHolder.getInstance(getActivity()).getAtrium();
					break;
				default:
					return;
				}
		}catch(Exception e){
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,	long id) {
		
		if(((New)list.getAdapter().getItem(position)).getPdf() == null || ((New)list.getAdapter().getItem(position)).getPdf().equalsIgnoreCase(""))
			prepareArticleDetail(position);
		else
			preparePDF(((New)list.getAdapter().getItem(position)));
		
		
	}
	
	private void prepareArticleDetail(int position){
		Fragment fragment = new NewsDetailFragment();
		Bundle args = new Bundle();
		args.putSerializable(NewsDetailFragment.ARTICLE_KEY, (Serializable) list.getAdapter().getItem(position));
		fragment.setArguments(args);
		getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content, fragment).addToBackStack("tag").commit();
		Message msg = new Message();
		msg.what = Constants.MESSAGE_DESELECT;
		MainActivity.controlHandler.sendMessage(msg);
	}
	
	private void preparePDF(New pdfItem){
		Fragment fragment = new PDFDetailFragment();
		Bundle args = new Bundle();
		args.putSerializable(PDFDetailFragment.ARTICLE_KEY, (Serializable) list.getAdapter().getItem(0));
		fragment.setArguments(args);
		getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content, fragment).addToBackStack("tag").commit();
		Message msg = new Message();
		msg.what = Constants.MESSAGE_DESELECT;
		MainActivity.controlHandler.sendMessage(msg);
	}

}
