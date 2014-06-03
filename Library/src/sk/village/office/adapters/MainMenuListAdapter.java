package sk.village.office.adapters;

import java.util.List;








import sk.village.office.R;
import sk.village.office.util.Log;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MainMenuListAdapter extends BaseAdapter{

	private String[] menuItems;
	private Context context;
	
	
	public MainMenuListAdapter(String[] items, Context context){
		this.menuItems = items;
		this.context = context;
	}
	
	@Override
	public int getCount() {
		return this.menuItems.length;
	}

	@Override
	public Object getItem(int position) {
		return menuItems[position];
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final int pos = position;
		
		LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = vi.inflate(R.layout.list_item_main_menu, null);
		((TextView)convertView.findViewById(R.id.list_item_name)).setText(menuItems[pos]);
		
		return convertView;
	}

}
