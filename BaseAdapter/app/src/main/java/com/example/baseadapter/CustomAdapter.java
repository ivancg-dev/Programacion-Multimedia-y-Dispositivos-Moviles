package com.example.baseadapter;

import android.content.Context;
import android.graphics.pdf.models.ListItem;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.List;

public class CustomAdapter extends BaseAdapter {

    private Context context;
    private List<ListItem> items;
    private int selectedPosition = -1;
    public CustomAdapter(Context context, List<ListItem> items){
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.layut, parent);
        }
        ImageView iv = convertView.findViewById(R.id.item_image);
        TextView tv1 = convertView.findViewById(R.id.itemTitle);
        TextView tv2 = convertView.findViewById(R.id.itemContent);
        RadioButton rb = convertView.findViewById(R.id.itemRadioButton);
        ListItem li = items.get(position);
        iv.setImageResource(li.get());

        return convertView;
    }
}
