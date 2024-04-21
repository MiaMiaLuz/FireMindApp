package com.example.firemind.InicioDeSesion

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.firemind.R
import java.util.Calendar

class InicioDeSesion : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio_de_sesion)
        fondo(R.drawable.fondoinitsessionday, R.drawable.fondoinitsessionnigth)

    }

    fun iniciarSesion(){
        
    }

    fun fondo(noche : Int , dia : Int){
        val hourOfDay = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        val isDayTime = hourOfDay in 7..21
        val rootView = findViewById<View>(android.R.id.content)
        val drawableResourceId = if (isDayTime) dia else noche
        rootView.setBackgroundResource(drawableResourceId)
    }
}