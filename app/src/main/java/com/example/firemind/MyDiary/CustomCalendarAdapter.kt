package com.example.firemind.MyDiary

import Task
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firemind.R


class CustomCalendarAdapter(context: Context, dates: List<Task>) : BaseAdapter() {
    private val mContext: Context
    private val mDates: List<Task>
    private val mInflater: LayoutInflater

    init {
        mContext = context
        mDates = dates
        mInflater = LayoutInflater.from(context)
    }

    override fun getCount(): Int {
        return mDates.size
    }

    override fun getItem(position: Int): Any? {
        return null
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view : View = mInflater.inflate(R.layout.days_month, parent, false)
        val dayTextView: TextView = view.findViewById(R.id.dayTextView)
        val tasksRecyclerView: RecyclerView = view.findViewById(R.id.tasksRecyclerView)

        dayTextView.text = (position + 1).toString()

        val tasksForDay: List<Task> = mDates
        val taskAdapter = TaskAdapter(mContext, tasksForDay)
        tasksRecyclerView.layoutManager = LinearLayoutManager(mContext)
        tasksRecyclerView.adapter = taskAdapter

        return view
    }


}
