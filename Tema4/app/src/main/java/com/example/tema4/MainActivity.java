package com.example.tema4;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private TextView txtEstado;
    private ToggleButton toggleBtn;
    private ImageButton imgBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.layout_toggle_button);
    }
    protected void onStart(){
        super.onStart();
        txtEstado = findViewById(R.id.txtEstado);
        toggleBtn = findViewById(R.id.miToggleButton);

        toggleBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(@NonNull CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    txtEstado.setText("Estado: Pulsado");
                } else {
                    txtEstado.setText("Estado: No Pulsado");
                }
            }
        });

        imgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgBtn.setImageResource(R.drawable.imagen);
            }
        });
    }
}