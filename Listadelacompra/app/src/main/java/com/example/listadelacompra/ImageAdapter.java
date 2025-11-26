package com.example.listadelacompra;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

public class ImageAdapter extends ArrayAdapter<Integer> {

    private Context context;
    private Integer[] images;

    public ImageAdapter(Context context, Integer[] images) {
        super(context, R.layout.spinner_item, images);
        this.context = context;
        this.images = images;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    private View getCustomView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View row = inflater.inflate(R.layout.spinner_item, parent, false);
        ImageView imageView = row.findViewById(R.id.spinner_image);
        imageView.setImageResource(images[position]);
        return row;
    }
}
