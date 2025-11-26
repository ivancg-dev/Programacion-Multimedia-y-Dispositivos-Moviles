package com.example.listadelacompra;

// Adaptador para Spinner con imágenes
// Adapter for Spinner with images
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageSpinnerAdapter extends BaseAdapter {
    private Context context;
    private int[] images;
    private String[] names;

    public ImageSpinnerAdapter(Context context, int[] images, String[] names) {
        this.context = context;
        this.images = images;
        this.names = names;
    }

    @Override
    public int getCount() { return images.length; }

    @Override
    public Object getItem(int position) { return names[position]; }

    @Override
    public long getItemId(int position) { return position; }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.spinner_item, parent, false);
        }
        ImageView img = convertView.findViewById(R.id.spinner_image);
        TextView txt = convertView.findViewById(R.id.spinner_text);

        img.setImageResource(images[position]);
        txt.setText(names[position]);

        return convertView;
    }
}

