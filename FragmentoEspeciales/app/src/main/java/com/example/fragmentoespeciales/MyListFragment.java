package com.example.fragmentoespeciales;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.ListFragment;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public class MyListFragment extends ListFragment {

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        String[] items = {
                "elemento1",
                "elemento2",
                "elemento3"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, items);

        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id){
        super.onListItemClick(l,v,position,id);
        String item = (String) l.getItemAtPosition(position);
        Toast.makeText(getContext(), "Seleccionaste: " + item, Toast.LENGTH_SHORT).show();
    }
}
