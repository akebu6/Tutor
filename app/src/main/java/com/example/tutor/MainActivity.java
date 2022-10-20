package com.example.tutor;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.ui.AppBarConfiguration;

import com.example.tutor.databinding.ActivityMainBinding;
import com.example.tutor.ui.account.AccountFragment;
import com.example.tutor.ui.category.CategoryFragment;
import com.example.tutor.ui.community.AboutUsActivity;
import com.example.tutor.ui.howToPlay.HowToPlayActivity;
import com.example.tutor.ui.scoreboard.ScoreBoardFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    FrameLayout frameLayout;
    CategoryFragment categoryFragment = new CategoryFragment();

    BottomNavigationView bottomNavigationView;

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Toolbar toolBar = findViewById(R.id.toolbar);
        setSupportActionBar(toolBar);

        frameLayout = findViewById(R.id.main_frame);

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        navigationView.setNavigationItemSelectedListener(this);

        new ActionBarDrawerToggle(
                this,
                drawer,
                toolBar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        ).syncState();

        new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_score_board, R.id.my_account)
                .setOpenableLayout(drawer)
                .build();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_frame, categoryFragment)
                .commit();

        setUpNavLogic();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_how_to_play) {
            Intent intent = new Intent(MainActivity.this, HowToPlayActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_about) {
            Intent intent = new Intent(MainActivity.this, AboutUsActivity.class);
            startActivity(intent);
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = binding.drawerLayout;
        if (drawer.isDrawerOpen(GravityCompat.START)) {
           drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressLint("NonConstantResourceId")
    private void setUpNavLogic() {
        AccountFragment accountFragment = new AccountFragment();
        ScoreBoardFragment scoreBoardFragment = new ScoreBoardFragment();

        bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.nav_home:
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.main_frame, categoryFragment)
                            .commit();
                    Toast.makeText(MainActivity.this, "Nav Fragment", Toast.LENGTH_SHORT).show();
                    return true;

                case R.id.nav_score_board:
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.main_frame, scoreBoardFragment)
                            .commit();
                    Toast.makeText(MainActivity.this, "Score Fragment", Toast.LENGTH_SHORT).show();
                    return true;

                case R.id.my_account:
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.main_frame, accountFragment)
                            .commit();
                    Toast.makeText(MainActivity.this, "Account Fragment", Toast.LENGTH_SHORT).show();
                    return true;
            }
            return false;
        });
    }
}
