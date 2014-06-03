package sk.village.office.adapters;

import java.util.List;

import sk.village.office.R;
import sk.village.office.core.DrawableManager;
import sk.village.office.model.Member;
import sk.village.office.model.New;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MembersListAdapter extends BaseAdapter{

	private List<Member> listItems;
	private Context context;
	
	
	public MembersListAdapter(List<Member> items, Context context){
		this.listItems = items;
		this.context = context;
		
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
        convertView = vi.inflate(R.layout.list_item_members, null);
        
        ((TextView)convertView.findViewById(R.id.member_name)).setText(listItems.get(pos).getName());
        String mail = listItems.get(pos).getEmail();
        if(mail.equalsIgnoreCase("null"))
        	((TextView)convertView.findViewById(R.id.member_mail)).setVisibility(View.GONE);
        else
        	((TextView)convertView.findViewById(R.id.member_mail)).setText(listItems.get(pos).getEmail());
       
		return convertView;
	}

}
