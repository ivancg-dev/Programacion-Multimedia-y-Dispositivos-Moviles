package com.example.ejemplotextview;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

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
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    protected void onStart(){
        super.onStart();
        TextView mi_texto = (TextView) findViewById(R.id.text);
        mi_texto.setText("Nuevo texto");
        mi_texto.setTextColor(Color.parseColor("#00000f"));
        mi_texto.setTextColor(Color.RED);
        mi_texto.setTypeface(null, Typeface.ITALIC);
        mi_texto.setTextSize(24);
        mi_texto.setTypeface(Typeface.SANS_SERIF);

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.animation_text);
        animation.setRepeatCount(9999999);
        animation.setRepeatMode(Animation.RESTART);
        mi_texto.startAnimation(animation);
    }
}