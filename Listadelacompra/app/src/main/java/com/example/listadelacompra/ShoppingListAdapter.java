package com.example.listadelacompra;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ShoppingListAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Item> items;

    public ShoppingListAdapter(Context context, ArrayList<Item> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() { return items.size(); }

    @Override
    public Object getItem(int position) { return items.get(position); }

    @Override
    public long getItemId(int position) { return position; }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.item_shopping, parent, false);
        }

        Item currentItem = items.get(position);

        ImageView image = convertView.findViewById(R.id.itemImage);
        TextView name = convertView.findViewById(R.id.itemName);
        TextView quantity = convertView.findViewById(R.id.itemQuantity);
        Button delete = convertView.findViewById(R.id.btnDelete);

        image.setImageResource(currentItem.getImageResId());
        name.setText(currentItem.getName());
        quantity.setText(String.valueOf(currentItem.getQuantity()));

        delete.setOnClickListener(v -> {
            items.remove(position);
            notifyDataSetChanged();
        });

        return convertView;
    }
}

