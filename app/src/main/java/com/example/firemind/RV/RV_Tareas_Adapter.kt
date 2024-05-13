package com.example.firemind.RV

import Task
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.firemind.R

class RV_Tareas_Adapter(private val dataList: List<Task>) : RecyclerView.Adapter<RV_Tareas_Adapter.TareaViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TareaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_task_simple, parent, false)
        return TareaViewHolder(view)
    }

    override fun onBindViewHolder(holder: TareaViewHolder, position: Int) {
        val tarea = dataList[position]
        holder.bind(tarea)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    inner class TareaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val tituloTextView: TextView = itemView.findViewById(R.id.titulo)
        private val finishedCheckBox: CheckBox = itemView.findViewById(R.id.finished)

        fun bind(tarea: Task) {
            tituloTextView.text = tarea.nameTask
            finishedCheckBox.isChecked = tarea.finish
            finishedCheckBox.setOnCheckedChangeListener { _, isChecked ->
                tarea.finish = isChecked
            }
        }
    }
}