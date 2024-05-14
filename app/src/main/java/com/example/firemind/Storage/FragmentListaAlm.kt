package com.example.firemind.Storage

import User
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firemind.Clases.Storage
import com.example.firemind.DatabaseManager.DatabaseManager
import com.example.firemind.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class FragmentListaAlm : Fragment(), TextWatcher {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MyAdapter
    private lateinit var editText: EditText
    private lateinit var spinner : Spinner

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
        spinner = view.findViewById(R.id.TipoFiltro)
        editText = view.findViewById(R.id.editTextFiltro)
        editText.addTextChangedListener(this)

        spinner.adapter = ArrayAdapter.createFromResource(requireContext(), R.array.filtros, android.R.layout.simple_spinner_item)

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

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
    }

    override fun afterTextChanged(s: Editable?) {
        if(s.toString().isNotEmpty()) {
            val selectedFilter = spinner.selectedItem.toString()
            var column = ""
            if (selectedFilter == "Nombre") {
                column = "name"
            } else if (selectedFilter == "Tipo") {
                column = "type"
            } else {
                column = "currentStock"
            }

            GlobalScope.launch(Dispatchers.Main) {
                try {
                    val storageList = db.getStorageFiltered(column, s.toString())
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

}