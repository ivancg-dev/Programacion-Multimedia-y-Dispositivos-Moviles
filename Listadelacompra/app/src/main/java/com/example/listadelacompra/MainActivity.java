package com.example.listadelacompra;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Item> shoppingList;
    private ShoppingListAdapter adapter;
    private int[] imageRes = {R.drawable.apple, R.drawable.bread, R.drawable.milk};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        shoppingList = new ArrayList<>();
        adapter = new ShoppingListAdapter(this, shoppingList);

        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);

        Spinner spinner = findViewById(R.id.spinnerImages);
        Integer[] imageResArray = {R.drawable.apple, R.drawable.bread, R.drawable.milk};

// aquí usas tu adaptador personalizado
        ImageAdapter spinnerAdapter = new ImageAdapter(this, imageResArray);
        spinner.setAdapter(spinnerAdapter);


        EditText editName = findViewById(R.id.editName);
        EditText editQuantity = findViewById(R.id.editQuantity);
        Button btnAdd = findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(v -> {
            String name = editName.getText().toString();
            int quantity = Integer.parseInt(editQuantity.getText().toString());
            int image = imageRes[spinner.getSelectedItemPosition()];

            shoppingList.add(new Item(name, quantity, image));
            adapter.notifyDataSetChanged();
        });

        registerForContextMenu(listView);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        int id = item.getItemId();

        if (id == R.id.menu_add) {
            shoppingList.add(new Item("Extra", 1, R.drawable.apple));
            adapter.notifyDataSetChanged();
            return true;
        } else if (id == R.id.menu_delete) {
            shoppingList.remove(info.position);
            adapter.notifyDataSetChanged();
            return true;
        } else {
            return super.onContextItemSelected(item);
        }
    }

}
