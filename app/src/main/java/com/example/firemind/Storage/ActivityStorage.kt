package com.example.firemind.Storage

import User
import ViewPagerAdapter
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.View.OnClickListener
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.PopupMenu
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.viewpager.widget.ViewPager
import com.example.firemind.DatabaseManager.DatabaseManager
import com.example.firemind.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ActivityStorage : AppCompatActivity(), OnClickListener, dialogNotification, TextWatcher{
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
        var fg : FragmentManager = supportFragmentManager
        var fragmentActivo =  fg.findFragmentById(R.id.FrameStorage) as? FragmentListaCompra
        popupMenu.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.addArt ->{
                    var dialog = DialogAddItem(false,null)
                    dialog.show(supportFragmentManager, "ADD")
                    true
                }
                R.id.end->{
                    fragmentActivo?.actRegistros()
                    recharge()
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

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

    }

    override fun afterTextChanged(s: Editable?) {

    }
}