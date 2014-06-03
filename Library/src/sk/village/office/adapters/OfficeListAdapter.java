package sk.village.office.adapters;

import java.util.List;


import sk.village.office.R;
import sk.village.office.util.Log;
import android.content.Context;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.ImageView;

public class OfficeListAdapter extends BaseAdapter{

	private String[] menuItems;
	private Context context;
	private TypedArray menuItemIcons; 
	
	
	public OfficeListAdapter(String[] items, Context context){
		this.menuItems = items;
		this.context = context;
		menuItemIcons = context.getResources().obtainTypedArray(R.array.office_menu_icons);
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
        convertView = vi.inflate(R.layout.list_item_office, null);
		((TextView)convertView.findViewById(R.id.list_item_name)).setText(menuItems[pos]);
		((ImageView)convertView.findViewById(R.id.list_item_icon)).setBackgroundDrawable(context.getResources().getDrawable(menuItemIcons.getResourceId(pos, -1)));
		return convertView;
	}

}
