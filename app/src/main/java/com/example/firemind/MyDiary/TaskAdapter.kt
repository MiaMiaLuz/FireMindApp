package com.example.firemind.MyDiary

import Task
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.firemind.R


class TaskAdapter(context: Context?, tasks: List<Task>) :
    RecyclerView.Adapter<TaskAdapter.ViewHolder>() {
    private val mTasks: List<Task>
    private val mInflater: LayoutInflater

    // Constructor que recibe la lista de tareas y el contexto
    init {
        mInflater = LayoutInflater.from(context)
        mTasks = tasks
    }

    // Método que infla el diseño de la fila y devuelve su ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = mInflater.inflate(R.layout.rv_task_simple, parent, false)
        return ViewHolder(view)
    }

    // Método que enlaza los datos de la tarea con los elementos de la fila
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val task: Task = mTasks[position]
        holder.taskDescriptionTextView.text = task.nameTask
    }

    // Método que devuelve la cantidad de elementos en la lista de tareas
    override fun getItemCount(): Int {
        return mTasks.size
    }

    // Clase ViewHolder que representa cada elemento de la lista de tareas
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var taskDescriptionTextView: TextView
        init {
            taskDescriptionTextView = itemView.findViewById<TextView>(R.id.titulo)
        }
    }
}

