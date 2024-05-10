package com.example.firemind.Storage

import User
import ViewPagerAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.FrameLayout
import android.widget.PopupMenu
import android.widget.Toast
import androidx.viewpager.widget.ViewPager
import com.example.firemind.Clases.Storage
import com.example.firemind.DatabaseManager.DatabaseManager
import com.example.firemind.Home.BlankFragmentMainActivity
import com.example.firemind.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ActivityStorage : AppCompatActivity(), OnClickListener, dialogNotification{
    private lateinit var boton : FloatingActionButton
    private var db = DatabaseManager()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_storage)
        boton = findViewById(R.id.menuContextualStorage)
        boton.setOnClickListener(this)

        recharge()
    }

    fun recharge(){
        val viewPager: ViewPager = findViewById(R.id.FrameStorage)
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(FragmentListaAlm(), "FragmentAlmacen")
        adapter.addFragment(FragmentListaCompra(), "FragmentCompra")
        viewPager.adapter = adapter
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
                    var dialog = DialogAddItem()
                    dialog.show(supportFragmentManager, "ADD")
                    true
                }
                R.id.end->{
                    true
                }
                else -> { true }
            }
        }
        popupMenu.show()
    }
    override fun onDismissDialog(b: Boolean) {
        if(b){
            recharge()
        }
    }
    override fun onCreateUser(user: User) {

    }
}