package com.example.textinput;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.layut);

        TextInputLayout emailLayout = findViewById(R.id.test);
        TextInputEditText text = findViewById(R.id.txt);

        Button btn = findViewById(R.id.buton);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = text.getText().toString();
                if(!isValidEmail(email)){
                    emailLayout.setError("INVALIDO");
                } else {
                    emailLayout.setError(null);
                }
            }
        });

        FloatingActionButton btnn = findViewById(R.id.flot);
        btnn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "aaaaaaaaa", Toast.LENGTH_SHORT).show();
            }
        });

        Button sbtn = findViewById(R.id.snacbuton);

        sbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "This is a snakbar", Snackbar.LENGTH_LONG)
                        .setAction("UNDO", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Snackbar.make(v, "Action undon", Snackbar.LENGTH_SHORT).show();
                            }
                        })
                        .show();
            }
        });
    }



    private boolean isValidEmail(String email) {

        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}