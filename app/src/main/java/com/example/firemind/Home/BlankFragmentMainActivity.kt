package com.example.firemind.Home

import Task
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firemind.R
import com.example.firemind.RV.RV_Tareas_Adapter
import com.example.firemind.Storage.MyAdapter
import java.util.Calendar


class BlankFragmentMainActivity : Fragment() {

    private var listaTareasDemo = ArrayList<Task>()
    private var listaChatsDemo = ArrayList<Task>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        listaTareasDemo.addAll(generateTasks())

        val view = inflater.inflate(R.layout.fragment_blank_main_activity, container, false)
        val listaDeTareas: RecyclerView = view.findViewById(R.id.ListaDeTareas)
        listaDeTareas.layoutManager = LinearLayoutManager(activity)

        val chat: RecyclerView = view.findViewById(R.id.Chat)
        var adapter = RV_Tareas_Adapter(listaTareasDemo)

        listaDeTareas.adapter = adapter
        adapter.notifyDataSetChanged()

        return view
    }

    fun generateTasks(): List<Task> {
        val tasks = mutableListOf<Task>()

        val names = listOf(
            "Reunión con el equipo de desarrollo",
            "Preparar informe mensual",
            "Enviar correos electrónicos a clientes",
            "Revisar el diseño del nuevo producto",
            "Actualizar la base de datos de clientes",
            "Entrevistar a candidatos para el puesto vacante",
            "Investigar nuevas tecnologías",
            "Crear presentación para el cliente",
            "Resolver problemas técnicos del sitio web",
            "Planificar el próximo proyecto"
        )

        val descriptions = listOf(
            "Reunión para discutir el progreso del proyecto y asignar tareas.",
            "Preparar un informe detallado de las actividades del último mes.",
            "Contactar a los clientes para obtener retroalimentación y responder preguntas.",
            "Revisar el diseño propuesto y hacer sugerencias de mejora.",
            "Actualizar la información de contacto y preferencias de los clientes.",
            "Entrevistar a varios candidatos y tomar notas para la selección final.",
            "Investigar nuevas tecnologías que podrían mejorar nuestro producto.",
            "Crear una presentación visualmente atractiva para presentar al cliente.",
            "Solucionar problemas técnicos reportados por los usuarios.",
            "Definir los objetivos y requisitos del próximo proyecto."
        )

        for (i in names.indices) {
            val task = Task(
                names[i],
                descriptions[i],
                Calendar.getInstance(),
                Calendar.getInstance()
                    .apply { add(Calendar.DAY_OF_MONTH, 7) }, // Fecha de finalización en una semana
                false,
                false,
                ArrayList()
            )
            tasks.add(task)
        }

        return tasks
    }
}