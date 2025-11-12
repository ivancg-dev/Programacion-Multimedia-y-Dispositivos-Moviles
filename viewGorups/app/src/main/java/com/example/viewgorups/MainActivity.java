package com.example.viewgorups;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.principal);
    }

    public void onStart(){
        super.onStart();

        List<String> data = Arrays.asList("uno", "dos", "tres", "cuatro","cinco","seis");
        ListView lv = findViewById(R.id.list);
        TextView tv = findViewById(R.id.text);

        ArrayAdapter<String> adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1,data);
        lv.setAdapter(adaptador);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String string = (String) parent.getItemAtPosition(position);
                string = "Has seleccionao: " + string;
                tv.setText(string);
            }
        });

        GridView gv = findViewById(R.id.grid);

        ArrayAdapter<String> adaptador2 = new ArrayAdapter(this, android.R.layout.simple_list_item_1,data);
        gv.setAdapter(adaptador2);

        gv.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String string = (String) parent.getItemAtPosition(position);
                string = "Has seleccionao: " + string;
                tv.setText(string);
            }
        });

        Spinner sp = findViewById(R.id.spiner);

        ArrayAdapter<String> adaptador3 = new ArrayAdapter(this, android.R.layout.simple_list_item_1,data);
        sp.setAdapter(adaptador3);

        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "Has seleccionado: " + parent.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(MainActivity.this, "No has seleccionado nada malandrin", Toast.LENGTH_SHORT).show();
            }
        });
    }
}