package com.example.tema7tablayout;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.coordinator_layout);
        TabLayout tabs = findViewById(R.id.main1);
        tabs.addTab(tabs.newTab().setText("Pestaña1"));
        tabs.addTab(tabs.newTab().setText("Pestaña2"));
        tabs.addTab(tabs.newTab().setText("Pestaña3"));

        TabLayout tabLayout = findViewById(R.id.main1);
        ViewPager viewPager = findViewById(R.id.view);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(new BlankFragment(), "Pestaña1");
        adapter.addFragment(new BlankFragment2(), "Pestaña2");
        adapter.addFragment(new BlankFragment3(), "Pestaña3");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        
        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // Accion cuando se selecciona una pestaña
                int position = tab.getPosition();
                Toast.makeText(MainActivity.this, "Pestaña " + (position +1), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                //Accion cuando una pestaña se deselecciona
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                // Accion cuando una pestaña ya seleccionada se vuelve a seleccionar
            }
        });

    }
}