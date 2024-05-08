package com.example.firemind.Home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.FrameLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.example.firemind.R
import com.example.firemind.Storage.ActivityStorage
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var dv : DrawerLayout
    private lateinit var nav : NavigationView
    private lateinit var toolbar: androidx.appcompat.widget.Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var frame = findViewById<FrameLayout>(R.id.Frame)
        nav = findViewById(R.id.nav)
        dv = findViewById(R.id.drawerLay)
        toolbar = findViewById(R.id.toolbar)
        var fragmentManager = supportFragmentManager
        var fragmentTransaction = fragmentManager.beginTransaction()
        var fragmentInicio = BlankFragmentMainActivity()
        fragmentTransaction.replace(R.id.Frame, fragmentInicio).commit()
        nav.setNavigationItemSelectedListener(this)

        var toggle = ActionBarDrawerToggle(this, dv, toolbar, androidx.compose.ui.R.string.on, androidx.compose.ui.R.string.off)
        dv.addDrawerListener(toggle)
        toggle.syncState()

        if(savedInstanceState == null){
            var fragment = BlankFragmentMainActivity()
            supportFragmentManager.beginTransaction().replace(frame.id, fragment).commit()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.nav_home ->{

            }
            R.id.nav_bank ->{

            }
            //Funcionalidad
            R.id.nav_food ->{
                intent = Intent(this, ActivityStorage::class.java)
                startActivity(intent)
            }
            R.id.nav_list ->{

            }
            R.id.my_profile ->{

            }
            R.id.my_friends ->{

            }
            R.id.nav_settings ->{

            }
        }
        return false
    }


}