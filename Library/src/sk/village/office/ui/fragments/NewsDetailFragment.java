package sk.village.office.ui.fragments;

import java.io.Serializable;

import sk.village.office.R;
import sk.village.office.model.New;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebSettings.PluginState;
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
//		((TextView)v.findViewById(R.id.news_detail_long_desc)).setText(newsArticle.getLongDesc());
		
		 String head = "<head><meta name=\"viewport\" content=\"width=device-width; initial-scale=1.0; maximum-scale=5.0; user-scalable=1;\"/></head> <style type='text/css'>body {font-family: Arial, Verdana, Helvetica; text-align:left; color: #333333; padding-bottom: 50px;} img {background:#ffffff max-width: 100%;  margin-top:10px; margin-bottom:10px; display: block;} a {color: #3bc6cf;} iframe{width:100%;margin-top:10px; margin-bottom:10px;} h1,h2,h3,h4 {color: #d6e03d;}</style>";

       WebView webView = (WebView)v.findViewById(R.id.news_detail_long_desc);
       webView.getSettings().setPluginsEnabled(true);
       webView.getSettings().setPluginState(PluginState.ON);
       webView.getSettings().setJavaScriptEnabled(true);
//       webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
       webView.setWebChromeClient(new WebChromeClient());
       
       webView.loadDataWithBaseURL(null, head + newsArticle.getLongDesc(), "text/html", "UTF-8", null);
       
       webView.setBackgroundColor(Color.WHITE);
//       webView.setBackgroundColor(0x60000000);
//       if(android.os.Build.VERSION.SDK_INT <= 10)
//       	webView.setBackgroundColor(0xFF828282);
       
       
	}

	@Override
	public void onClick(View v) {
		if(v.getId() == R.id.exit_button){
			Fragment fragment = new ArticlesListFragment();
			getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content, fragment).addToBackStack("tag").commit();
		}
			
	}
	
}
