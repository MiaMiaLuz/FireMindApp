package com.example.firemind.RV

import Task
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.firemind.R

class RV_Tareas_Adapter(val listTareas : ArrayList<Task>) : RecyclerView.Adapter<RV_Tareas_ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RV_Tareas_ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return RV_Tareas_ViewHolder(layoutInflater.inflate(R.layout.rv_task_simple, parent))
    }

    override fun onBindViewHolder(holder: RV_Tareas_ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int = listTareas.size
}