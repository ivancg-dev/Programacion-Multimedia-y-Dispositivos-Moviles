package com.example.edittext;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.RadioButton;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
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
        Spinner spin = findViewById(R.id.spiner);

        String[] valores = {"uno","dos", "tres","cuatro"};

        spin.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,valores));

        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "Has seleccionado: " + parent.getItemAtPosition(position), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                System.out.println("Nothing selected");
            }
            
            
        });

        CheckBox ck = findViewById(R.id.cheki);
        
        ck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(@NonNull CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    Toast.makeText(MainActivity.this, "Selecsionao", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Noooo", Toast.LENGTH_SHORT).show();
                }
            }
        });

        RadioGroup rg = findViewById(R.id.grup);
        Button butrad = findViewById(R.id.radButon);
        TextView tv = findViewById(R.id.radtex);

        butrad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = rg.getCheckedRadioButtonId();

                if(selectedId != -1) {
                    RadioButton selectedRadioButton = findViewById(selectedId);
                    String selectedOption = selectedRadioButton.getText().toString();
                    tv.setText("Has seleccionado esto: " + selectedOption);
                } else {
                    Toast.makeText(MainActivity.this, "Tu eres tonto o k selecciona algo", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Switch swiich = findViewById(R.id.swich);

        swiich.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(@NonNull CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(MainActivity.this, "Encendido", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Apagao", Toast.LENGTH_SHORT).show();
                }
            }
        });

        SeekBar sik = findViewById(R.id.sik);
        sik.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Toast.makeText(MainActivity.this, progress + "", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        RatingBar rt = findViewById(R.id.ratin);
        rt.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

            }
        });

        
    }
}