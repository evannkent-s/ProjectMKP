package id.ac.binus.project;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import id.ac.binus.project.fragment.FormFragment;
import id.ac.binus.project.fragment.HistoryFragment;
import id.ac.binus.project.fragment.ValidatedFormFragment;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                if (item.getItemId() == R.id.nav_history) {
                    selectedFragment = new HistoryFragment();
                } else if (item.getItemId() == R.id.nav_form) {
                    selectedFragment = new FormFragment();
                } else if (item.getItemId() == R.id.nav_validated_form) {
                    selectedFragment = new ValidatedFormFragment();
                }

                if (selectedFragment != null) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, selectedFragment)
                            .commit();
                }
                return true;
            }
            
        });

        // Default fragment
        if (savedInstanceState == null) {
            bottomNavigationView.setSelectedItemId(R.id.nav_history);
        }
    }
}