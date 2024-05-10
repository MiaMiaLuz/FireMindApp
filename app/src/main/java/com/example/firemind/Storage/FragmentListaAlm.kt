package com.example.firemind.Storage

import User
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firemind.Clases.Storage
import com.example.firemind.DatabaseManager.DatabaseManager
import com.example.firemind.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class FragmentListaAlm : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MyAdapter
    private var dataList = ArrayList<Storage> ()
    private var db = DatabaseManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_lista_alm, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        fetchStorageData()

        return view
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menucalendar, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }

    private fun fetchStorageData() {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val storageList = db.getStorage()
                dataList.clear()
                dataList.addAll(storageList)
                adapter.notifyDataSetChanged()
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                adapter = MyAdapter(dataList, 1)
                recyclerView.adapter = adapter
            }
        }
    }

}