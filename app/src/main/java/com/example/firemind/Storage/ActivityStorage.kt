package com.example.firemind.Storage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.FrameLayout
import android.widget.PopupMenu
import com.example.firemind.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ActivityStorage : AppCompatActivity(), OnClickListener{
    private lateinit var frame : FrameLayout
    private lateinit var boton : FloatingActionButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_storage)
        frame = findViewById(R.id.FrameStorage)
        boton = findViewById(R.id.menuContextualStorage)
        boton.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        val popupMenu = PopupMenu(applicationContext, v)
        popupMenu.inflate(R.menu.contextual_menu_storage)
        popupMenu.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.filtrar ->{
                    true
                }
                R.id.tipo ->{
                    true
                }
                R.id.nombre ->{
                    true
                }
                R.id.stock ->{

                    true
                }
                R.id.addArt ->{
                    true
                }
                R.id.end->{
                    true
                }
                else -> { true }
            }
        }
    }

}