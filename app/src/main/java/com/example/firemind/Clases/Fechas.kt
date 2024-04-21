package com.example.firemind.Clases

import java.util.Calendar

class Fechas {

    private lateinit var day : Calendar

    fun todayInWeekDayplusNumberDay(): ArrayList<String>{
        day = Calendar.getInstance()
        val dayOfWeek = day.get(Calendar.DAY_OF_WEEK)
        val dayNumber = day.get(Calendar.DAY_OF_MONTH)
        return arrayListOf<String>(dayOfWeek.toString(), dayNumber.toString())
    }
}