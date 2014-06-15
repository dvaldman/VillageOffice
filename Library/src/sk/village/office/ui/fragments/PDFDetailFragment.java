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
import android.webkit.WebSettings.PluginState;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.ImageButton;

public class PDFDetailFragment extends Fragment implements OnClickListener{

	public static final String ARTICLE_KEY = "ARTICLE_KEY";
	private New newsArticle;
	private View v;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		 v = inflater.inflate(R.layout.fragment_pdf_detail, container, false);
		 Bundle data = getArguments();
			
		 if(data != null)
			 if(data.containsKey(ARTICLE_KEY))
				 newsArticle = (New) data.getSerializable(ARTICLE_KEY);
		 
		 initArticle();
		 ((ImageButton)v.findViewById(R.id.exit_button)).setOnClickListener(this);
		 return v;
	}
	
	private void initArticle(){
		
		WebView webView = (WebView) v.findViewById(R.id.pdf_webview);
		webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setPluginState(PluginState.ON);

        //---you need this to prevent the webview from
        // launching another browser when a url
        // redirection occurs---
        webView.setWebViewClient(new Callback());
       
        String pdfURL = newsArticle.getPdf();
        webView.loadUrl("http://docs.google.com/gview?embedded=true&url=" + pdfURL);

        
    }

    private class Callback extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(
                WebView view, String url) {
            return(false);
        }
  
	}

	@Override
	public void onClick(View v) {
		if(v.getId() == R.id.exit_button){
			Fragment fragment = new ArticlesListFragment();
			getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content, fragment).addToBackStack("tag").commit();
		}
			
	}
	
}
