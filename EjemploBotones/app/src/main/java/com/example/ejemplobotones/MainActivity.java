package com.example.ejemplobotones;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linear_layout);
    }
    protected void onStart(){
        super.onStart();

        Button botonTranslate = findViewById(R.id.btnTranslate);
        Button botonRotate = findViewById(R.id.btnRotate);
        Button botonNo = findViewById(R.id.btnNo);
        TextView texto = findViewById(R.id.texto);
        Animation translate = AnimationUtils.loadAnimation(this, R.anim.translate_animation);
        translate.setRepeatMode(Animation.REVERSE);
        translate.setRepeatCount(999);
        Animation rotate = AnimationUtils.loadAnimation(this, R.anim.rotate_animation);
        rotate.setRepeatMode(Animation.RESTART);
        rotate.setRepeatCount(999);
        botonTranslate.setOnClickListener( v ->{texto.startAnimation(translate);});
        botonRotate.setOnClickListener( v ->{texto.startAnimation(rotate);});
        botonNo.setOnClickListener( v ->{detenerAnimacionActual(texto, texto.getAnimation());});

    }
    private void detenerAnimacionActual(TextView texto, Animation animacionActual){
        if(animacionActual != null){
            texto.clearAnimation();
            animacionActual.cancel();
            animacionActual = null;
        }
    }

}