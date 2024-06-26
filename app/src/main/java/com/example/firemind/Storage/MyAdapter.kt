package com.example.firemind.Storage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.NumberPicker
import android.widget.NumberPicker.OnValueChangeListener
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.example.firemind.Clases.Storage
import com.example.firemind.DatabaseManager.DatabaseManager
import com.example.firemind.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.Serializable

class MyAdapter(private var dataList: MutableList<Storage>, type: Int) : RecyclerView.Adapter<MyAdapter.ViewHolder>(), Serializable {

    private var Type: Int = type
    private var dbm = DatabaseManager()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view : View
        if(Type == 1) {
            view = LayoutInflater.from(parent.context).inflate(R.layout.rv_article_ware, parent, false)
        } else  {
            view = LayoutInflater.from(parent.context).inflate(R.layout.rv_article_buy, parent, false)
        }
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        holder.bind(item, Type)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), OnValueChangeListener {
        private lateinit var check: CheckBox
        private lateinit var item : Storage
        private lateinit var numberPicker: NumberPicker
        fun bind(item: Storage, Type : Int) {
            this.item = item
            if(Type == 1){
                val textView: TextView = itemView.findViewById(R.id.storageName)
                val textViewDesc: TextView = itemView.findViewById(R.id.DescriptionStorage)
                val menu: TextView = itemView.findViewById(R.id.menu)
                val textViewSm: TextView = itemView.findViewById(R.id.StockMin)
                val textViewSM: TextView = itemView.findViewById(R.id.StockMax)
                val textViewS: TextView = itemView.findViewById(R.id.Stock)

                textView.text = item.name
                textViewDesc.text = item.description
                textViewSm.text = item.stockMin.toString()
                textViewSM.text = item.stockMax.toString()
                textViewS.text = item.currentStock.toString()

                menu.setOnClickListener { view ->
                    val popupMenu = PopupMenu(itemView.context, view)
                    popupMenu.menuInflater.inflate(R.menu.popup_menu_storage, popupMenu.menu)
                    popupMenu.setOnMenuItemClickListener { menuItem ->
                        when (menuItem.itemId) {
                            R.id.modificar -> {
                                val dialogFragment = DialogAddItem(true, item)
                                val activity = itemView.context as AppCompatActivity
                                dialogFragment.show(activity.supportFragmentManager, "my_dialog")
                                fetchStorageData()
                                true
                            }
                            R.id.eliminar -> {
                                dbm.dropStorage(item)
                                fetchStorageData()
                                true
                            }
                            else -> false
                        }
                    }
                    popupMenu.show()
                }

            } else {
                val textViewCompra: TextView = itemView.findViewById(R.id.nombreCompra)
                numberPicker = itemView.findViewById(R.id.elementos)
                numberPicker.minValue = 0
                numberPicker.maxValue = 100
                numberPicker.value = item.currentStock
                check = itemView.findViewById(R.id.comprados)
                textViewCompra.text = item.name

                numberPicker.setOnValueChangedListener(this)
            }
        }

        private fun fetchStorageData() {
            GlobalScope.launch(Dispatchers.Main) {
                try {
                    val storageList = dbm.getStorage()
                    dataList = storageList;
                    notifyDataSetChanged()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }

        override fun onValueChange(picker: NumberPicker?, oldVal: Int, newVal: Int) {
            if(check.isChecked){
                item.currentStock = numberPicker.value
                dataList[adapterPosition] = item
                // Notify the adapter that the item has changed
                notifyItemChanged(adapterPosition)
            }
        }
    }
}
