package com.example.firemind.RV

import Task
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.firemind.R

class RV_Tareas_ViewHolder(val view: View): ViewHolder(view) {

    val type = view.findViewById<LinearLayout>(R.id.linearType)
    val name = view.findViewById<LinearLayout>(R.id.titulo)
    val complete = view.findViewById<LinearLayout>(R.id.finished)

    fun render ( task : Task ){

    }

}