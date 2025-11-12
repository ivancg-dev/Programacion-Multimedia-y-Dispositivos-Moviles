package com.example.adaptadores;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.principal);

    }

    public void onStart(){

        super.onStart();

        Datos[] datos = new Datos[] {
                new Datos("Madrid","España"),
                new Datos("Paris","Francia"),
                new Datos("Berlin","Alemania"),
                new Datos("Rejkavic","Islandia")
        };


        ListView lv = findViewById(R.id.list);

        Adaptador ad = new Adaptador(this, datos);

        lv.setAdapter(ad);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String string =((Datos)parent.getItemAtPosition(position)).getString1() + ((Datos)parent.getItemAtPosition(position)).getString2();
                Toast.makeText(MainActivity.this, string, Toast.LENGTH_SHORT).show();
            }
        });
    }
}