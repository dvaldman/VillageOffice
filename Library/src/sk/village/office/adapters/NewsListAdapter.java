package sk.village.office.adapters;

import java.util.List;

import sk.village.office.R;
import sk.village.office.core.DrawableManager;
import sk.village.office.model.New;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class NewsListAdapter extends BaseAdapter{

	private List<New> listItems;
	private Context context;
	private DrawableManager drawableManager;
	
	public NewsListAdapter(List<New> items, Context context){
		this.listItems = items;
		this.context = context;
		drawableManager = new DrawableManager();
	}
	
	@Override
	public int getCount() {
		return this.listItems.size();
	}

	@Override
	public Object getItem(int position) {
		return listItems.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final int pos = position;
		
		LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = vi.inflate(R.layout.list_item_news, null);
        
        ((TextView)convertView.findViewById(R.id.news_date)).setText(listItems.get(pos).getDate());
        ((TextView)convertView.findViewById(R.id.news_title)).setText(listItems.get(pos).getTitle());
        ((TextView)convertView.findViewById(R.id.news_short_desc)).setText(listItems.get(pos).getShortDesc());
        
        if(listItems.get(pos).getThumbnail()!=null)
        	drawableManager.fetchScaledDrawableOnThread(listItems.get(pos).getThumbnail(), (ImageView)convertView.findViewById(R.id.thumbnail_img), null);
       
        
		return convertView;
	}

}
