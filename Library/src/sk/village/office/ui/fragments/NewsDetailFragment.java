package sk.village.office.ui.fragments;

import java.io.Serializable;

import sk.village.office.R;
import sk.village.office.model.New;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ImageButton;

public class NewsDetailFragment extends Fragment implements OnClickListener{

	public static final String ARTICLE_KEY = "ARTICLE_KEY";
	private New newsArticle;
	private View v;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		 v = inflater.inflate(R.layout.fragment_news_detail, container, false);
		 Bundle data = getArguments();
			
		 if(data != null)
			 if(data.containsKey(ARTICLE_KEY))
				 newsArticle = (New) data.getSerializable(ARTICLE_KEY);
		 
		 initArticle();
		 ((ImageButton)v.findViewById(R.id.exit_button)).setOnClickListener(this);
		 return v;
	}
	
	private void initArticle(){
		((TextView)v.findViewById(R.id.news_detail_date)).setText(newsArticle.getDate());
		((TextView)v.findViewById(R.id.news_detail_title)).setText(newsArticle.getTitle());
		((TextView)v.findViewById(R.id.news_detail_long_desc)).setText(newsArticle.getLongDesc());
	}

	@Override
	public void onClick(View v) {
		if(v.getId() == R.id.exit_button){
			Fragment fragment = new ArticlesListFragment();
			getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content, fragment).addToBackStack("tag").commit();
		}
			
	}
	
}
