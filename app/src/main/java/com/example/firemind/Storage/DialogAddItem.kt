package com.example.firemind.Storage

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.NumberPicker
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.firemind.Clases.Storage
import com.example.firemind.DatabaseManager.DatabaseManager
import com.example.firemind.R

class DialogAddItem : DialogFragment(), DialogInterface.OnClickListener {

    private lateinit var nombreArticulo: EditText
    private lateinit var descripcionArticulo: EditText
    private lateinit var stockMin: NumberPicker
    private lateinit var stockMax: NumberPicker
    private lateinit var stockActual: NumberPicker
    private lateinit var tipoSpinner: Spinner
    private var DB = DatabaseManager()
    private var dialogInterface : dialogNotification? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireContext())
        val inflater = requireActivity().layoutInflater
        val view: View = inflater.inflate(R.layout.dialog_add_item, null)

        nombreArticulo = view.findViewById(R.id.nomrbeArt)
        descripcionArticulo = view.findViewById(R.id.desciptionArt)
        stockMin = view.findViewById(R.id.stockMin)
        stockMax = view.findViewById(R.id.stockMax)
        stockActual = view.findViewById(R.id.stockAct)
        tipoSpinner = view.findViewById(R.id.type)

        stockMin.minValue = 0
        stockMax.minValue = 0
        stockActual.minValue = 0
        stockMin.maxValue = 1000
        stockMax.maxValue = 1000
        stockActual.maxValue = 1000
        tipoSpinner.adapter = ArrayAdapter.createFromResource(requireContext(), R.array.typesItems, android.R.layout.simple_spinner_item)

        builder.setView(view)
        builder.setPositiveButton("Aceptar", this)
        builder.setNegativeButton("Cancelar", this)

        return builder.create()
    }

    override fun onClick(dialog: DialogInterface?, which: Int) {

            when (which) {

                DialogInterface.BUTTON_POSITIVE -> {
                    if(nombreArticulo.text.isNotEmpty()) {
                        val nombre = nombreArticulo.text.toString()
                        val descripcion = descripcionArticulo.text.toString()
                        val stockMinValue = stockMin.value
                        val stockMaxValue = stockMax.value
                        val stockActualValue = stockActual.value
                        val tipo = tipoSpinner.selectedItem.toString()

                        var newStorage = Storage(
                            nombre,
                            descripcion,
                            stockMinValue,
                            stockMaxValue,
                            stockActualValue,
                            tipo
                        )
                        DB.addStorage(newStorage)
                    } else {
                        Toast.makeText(requireContext(), "Introduce todos los datos", Toast.LENGTH_SHORT).show()
                    }
                }
                DialogInterface.BUTTON_NEGATIVE -> {
                    dialog?.dismiss()
                }
            }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is dialogNotification){
            dialogInterface = context
        }
    }
    override fun onDetach() {
        super.onDetach()
        this.dialogInterface = null
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        dialogInterface?.onDismissDialog(true)
    }
}
