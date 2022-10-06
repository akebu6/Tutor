package com.example.tutor.home

import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import com.example.tutor.R
import com.google.android.material.navigation.NavigationView

class HomeScreen : AppCompatActivity() {
    // variables
    private lateinit var drawerLayout: DrawerLayout
    private var toolbar: Toolbar? = null
    private lateinit var navigationView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)

        drawerLayout = findViewById(R.id.drawer_layout)
        toolbar = findViewById(R.id.toolbar)
        navigationView = findViewById(R.id.nav_view)

        setSupportActionBar(toolbar)

        navigationView.bringToFront()
        val toogle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toogle)
        toogle.syncState()
        navigationView.setNavigationItemSelectedListener {
            true
        }
        navigationView.setCheckedItem(R.id.nav_home)
    }
}
