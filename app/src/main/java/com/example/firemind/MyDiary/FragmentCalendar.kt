package com.example.firemind.MyDiary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.firemind.R
import java.util.Calendar


class FragmentCalendar : Fragment() {

    private lateinit var calendar: CalendarView
    private lateinit var dayView : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        var view : View = inflater.inflate(R.layout.fragment_calendar, container, false)
        getDays(view)

        return view
    }

    fun getDays(view: View){
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        val firstDayOfMonth = calendar.get(Calendar.DAY_OF_WEEK)
        val daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
        val totalDayViews = 41
        val positionOfFirstDayOfMonth = when (firstDayOfMonth) {
            Calendar.SUNDAY -> 0
            else -> firstDayOfMonth - Calendar.SUNDAY
        }
        for (i in 0 until totalDayViews) {
            val dayView = view.findViewById<TextView>(getDayViewId(i))
            if (i < positionOfFirstDayOfMonth || i >= positionOfFirstDayOfMonth + daysInMonth) {
                dayView.text = ""
            } else {
                val dayOfMonth = i - positionOfFirstDayOfMonth + 1
                dayView.text = dayOfMonth.toString()
            }
        }
    }
    private fun getDayViewId(position: Int): Int {
        return if (position in 0..41) {
            R.id.day_0 + position
        } else {
            throw IllegalArgumentException("Invalid position: $position")
        }
    }


}