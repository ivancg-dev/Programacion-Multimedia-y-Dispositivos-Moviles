package com.example.ciclodevida;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("ejemplo","Estoy on create");
    }
    protected void onStart() {
        super.onStart();
        Log.i("ejemplo","Estoy on start");
    }
    protected void onRestart() {
        super.onRestart();
        Log.i("ejemplo","Estoy on restart");
    }
    protected void onResume() {
        super.onResume();
        Log.i("ejemplo","Estoy on resume");
    }
    protected void onPause() {
        super.onPause();
        Log.i("ejemplo","Estoy on pause");
    }
    protected void onStop() {
        super.onStop();
        Log.i("ejemplo","Estoy on stop");
    }
    protected void onDestroy() {
        super.onDestroy();
        Log.i("ejemplo","Estoy on destroy");
    }
}