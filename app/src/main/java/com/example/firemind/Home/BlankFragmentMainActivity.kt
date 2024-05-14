package com.example.firemind.Home

import Task
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firemind.R
import com.example.firemind.RV.RV_Tareas_Adapter
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class BlankFragmentMainActivity : Fragment(), OnClickListener {

    private var listaTareasDemo = ArrayList<Task>()
    private var listaTareasRecharg = ArrayList<Task>()
    private lateinit var listaDeTareas: RecyclerView
    private lateinit var flechaDer : ImageView
    private lateinit var flechaIzq : ImageView
    private lateinit var day : TextView
    private var dayActual = 1
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
        listaDeTareas = view.findViewById(R.id.ListaDeTareas)
        day = view.findViewById(R.id.Fecha)

        flechaDer = view.findViewById(R.id.FlechaDer)
        flechaIzq = view.findViewById(R.id.FlechaIzq)
        flechaIzq.setOnClickListener(this)
        flechaDer.setOnClickListener(this)
        day.text = getTodayDateString()
        listaDeTareas.layoutManager = LinearLayoutManager(activity)
        adapterTareas()
        val chat: RecyclerView = view.findViewById(R.id.Chat)


        return view
    }
    fun getTodayDateString(): String {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, dayActual)
        val dateFormat = SimpleDateFormat("EEEE, d 'de' MMMM", Locale.getDefault())
        return dateFormat.format(calendar.time)
    }

    fun adapterTareas(){
        var adapter = RV_Tareas_Adapter(calculateTask())
        listaDeTareas.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    fun calculateTask(): ArrayList<Task> {
        listaTareasRecharg.clear()
        val fecha = Calendar.getInstance()
        fecha.add(Calendar.DAY_OF_YEAR, dayActual)
        for(i in listaTareasDemo){
            if(fecha <= i.endDate && fecha >=  i.initDate)
                listaTareasRecharg.add(i)
        }
        return listaTareasRecharg
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
                    .apply { add(Calendar.DAY_OF_MONTH, 7) },
                false,
                false,
                ArrayList()
            )
            tasks.add(task)
        }

        return tasks
    }

    override fun onClick(v: View?) {
        if(v?.id == R.id.FlechaDer){
            dayActual += 1
            day.text = getTodayDateString()
            adapterTareas()
        } else if (v?.id == R.id.FlechaIzq){
            dayActual += -1
            day.text = getTodayDateString()
            adapterTareas()
        }
    }
}