package com.example.tutor.home.fragments.category

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.cardview.widget.CardView
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.tutor.R
import com.example.tutor.home.HomeScreen
import com.example.tutor.home.fragments.account.AccountActivity
import com.example.tutor.home.fragments.comm.AboutUsActivity
import com.example.tutor.home.fragments.comm.FeedbackActivity
import com.example.tutor.home.fragments.comm.ShareActivity
import com.example.tutor.quiz.QuizActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class CategoryActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var drawerLayout: DrawerLayout
    private var toolbar: Toolbar? = null
    private lateinit var navigationView: NavigationView
    private lateinit var bottomNavigationView: BottomNavigationView

    // cardview ids
    private lateinit var englishCard: CardView
    private lateinit var mathsCard: CardView
    private lateinit var scienceCard: CardView
    private lateinit var socialCard: CardView
    private lateinit var computerCard: CardView
    private lateinit var frenchCard: CardView
    private lateinit var businessCard: CardView
    private lateinit var physicalEdCard: CardView
    private lateinit var musicAndArtCard: CardView
    private lateinit var homeEconomicsCard: CardView
    private lateinit var agricultureCard: CardView
    private lateinit var chineseCard: CardView
    private lateinit var localLanguageCard: CardView
    private lateinit var religiousStudiesCard: CardView

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        drawerLayout = findViewById(R.id.drawer_layout)
        toolbar = findViewById(R.id.toolbar)
        navigationView = findViewById(R.id.nav_view)
        bottomNavigationView = findViewById(R.id.bottom_nav)

        // setting cardview ids
        englishCard = findViewById(R.id.english_card)
        mathsCard = findViewById(R.id.math_card)
        frenchCard = findViewById(R.id.french_card)
        socialCard = findViewById(R.id.social_studies_card)
        scienceCard = findViewById(R.id.integrated_science_card)
        businessCard = findViewById(R.id.business_studies_card)
        computerCard = findViewById(R.id.ict_card)
        physicalEdCard = findViewById(R.id.physical_ed_card)
        musicAndArtCard = findViewById(R.id.music_and_art_card)
        homeEconomicsCard = findViewById(R.id.home_econ_card)
        religiousStudiesCard = findViewById(R.id.religious_studies_card)
        agricultureCard = findViewById(R.id.agri_card)
        chineseCard = findViewById(R.id.chinese_card)
        localLanguageCard = findViewById(R.id.local_language_card)

        englishCard.setOnClickListener {
            val intent = Intent(this, QuizActivity::class.java)
            startActivity(intent)
        }
        mathsCard.setOnClickListener {
            val intent = Intent(this, QuizActivity::class.java)
            startActivity(intent)
        }
        frenchCard.setOnClickListener {
            val intent = Intent(this, QuizActivity::class.java)
            startActivity(intent)
        }
        socialCard.setOnClickListener {
            val intent = Intent(this, QuizActivity::class.java)
            startActivity(intent)
        }
        scienceCard.setOnClickListener {
            val intent = Intent(this, QuizActivity::class.java)
            startActivity(intent)
        }
        businessCard.setOnClickListener {
            val intent = Intent(this, QuizActivity::class.java)
            startActivity(intent)
        }
        computerCard.setOnClickListener {
            val intent = Intent(this, QuizActivity::class.java)
            startActivity(intent)
        }
        physicalEdCard.setOnClickListener {
            val intent = Intent(this, QuizActivity::class.java)
            startActivity(intent)
        }
        musicAndArtCard.setOnClickListener {
            val intent = Intent(this, QuizActivity::class.java)
            startActivity(intent)
        }
        homeEconomicsCard.setOnClickListener {
            val intent = Intent(this, QuizActivity::class.java)
            startActivity(intent)
        }
        religiousStudiesCard.setOnClickListener {
            val intent = Intent(this, QuizActivity::class.java)
            startActivity(intent)
        }
        agricultureCard.setOnClickListener {
            val intent = Intent(this, QuizActivity::class.java)
            startActivity(intent)
        }
        chineseCard.setOnClickListener {
            val intent = Intent(this, QuizActivity::class.java)
            startActivity(intent)
        }
        localLanguageCard.setOnClickListener {
            val intent = Intent(this, QuizActivity::class.java)
            startActivity(intent)
        }


        bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.nav_home -> {
                    val intent = Intent(this, HomeScreen::class.java)
                    startActivity(intent)
                }
                R.id.my_account -> {
                    val intent = Intent(this, AccountActivity::class.java)
                    startActivity(intent)
                }
                else -> {
                    val intent = Intent(this, CategoryActivity::class.java)
                    startActivity(intent)
                }
            }
            true
        }

        setSupportActionBar(toolbar)

        navigationView.bringToFront()
        val toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navigationView.setNavigationItemSelectedListener(this)
        navigationView.setCheckedItem(R.id.nav_home)
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_home -> {
                val intent = Intent(this, HomeScreen::class.java)
                startActivity(intent)
            }
            R.id.nav_feedback -> {
                val intent = Intent(this, FeedbackActivity::class.java)
                startActivity(intent)
            }
            R.id.nav_settings -> {
//                val intent = Intent(this, SettingsActivity::class.java)
//                startActivity(intent)
            }
            R.id.nav_about -> {
                val intent = Intent(this, AboutUsActivity::class.java)
                startActivity(intent)
            }
            R.id.nav_share -> {
                val intent = Intent(this, ShareActivity::class.java)
                startActivity(intent)
            }
            else -> {
                val intent = Intent(this, CategoryActivity::class.java)
                startActivity(intent)
            }
        }

        drawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}
