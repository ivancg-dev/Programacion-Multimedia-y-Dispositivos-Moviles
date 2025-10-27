package com.example.edittext;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

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
        setContentView(R.layout.edittext_layout);
    }
    @Override
    protected void onStart(){
        super.onStart();
        String[] opciones = {"Opcion1","Opcion2","Opcion3","Opcion4","Opcion5"};
        AutoCompleteTextView texto = findViewById(R.id.texto);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,opciones);
        texto.setAdapter(adapter);
    }
}