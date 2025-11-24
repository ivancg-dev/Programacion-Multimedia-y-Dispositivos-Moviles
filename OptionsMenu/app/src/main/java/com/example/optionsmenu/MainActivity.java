package com.example.optionsmenu;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;

/**
 * TODO En el examen vamos a tener que hacer un contextmenu en un listview
 *  que nos permita añadir mas o quitar elementos del list view
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        MaterialToolbar toolbar = findViewById(R.id.tb);
        setSupportActionBar(toolbar);
        TextView tv = findViewById(R.id.txt);
        registerForContextMenu(tv);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_dos,menu);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_uno, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        int id = item.getItemId();
        if (id== R.id.action_settings){
            Toast.makeText(this, "Configuracion...", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id== R.id.action_info){
            Toast.makeText(this, "Info...", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id== R.id.action_acercade){
            Toast.makeText(this, "Acerca de...", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id== R.id.action_help){
            Toast.makeText(this, "Ayuda...", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id== R.id.exit){
            System.exit(0);
            return true;
        }
    return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item){
        int id = item.getItemId();
        if (id== R.id.action_settingss){
            Toast.makeText(this, "Configuracion...", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id== R.id.action_infoo){
            Toast.makeText(this, "Info...", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id== R.id.action_acercadee){
            Toast.makeText(this, "Acerca de...", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id== R.id.action_helpp){
            Toast.makeText(this, "Ayuda...", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id== R.id.exitt){
            System.exit(0);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}