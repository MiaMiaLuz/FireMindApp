package com.example.firemind

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var frame = findViewById<FrameLayout>(R.id.Frame)
        var fragmentManager = supportFragmentManager
        var fragmentTransaction = fragmentManager.beginTransaction()
        var fragmentInicio = BlankFragmentMainActivity()
        fragmentTransaction.replace(R.id.Frame, fragmentInicio).commit()

    }


}