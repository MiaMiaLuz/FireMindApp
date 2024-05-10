package com.example.firemind.InicioDeSesion

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.firemind.R
import com.example.firemind.Storage.dialogNotification

class DialogInitSession : DialogFragment(), DialogInterface.OnClickListener, TextWatcher {

    private lateinit var nombreUsuario: EditText
    private lateinit var contrasenna: EditText
    private lateinit var contrasennaBis: EditText
    private lateinit var establecerCumple: Button
    private lateinit var biometria: CheckBox
    private var dialogInterface : dialogNotification? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireContext())
        val inflater = LayoutInflater.from(requireContext())
        val view: View = inflater.inflate(R.layout.dialog_iniciar_sesion, null)

        nombreUsuario = view.findViewById(R.id.NombreDeUsuario)
        contrasenna = view.findViewById(R.id.Contrasenna)
        contrasennaBis = view.findViewById(R.id.ContrasennaBis)
        establecerCumple = view.findViewById(R.id.Cumple)
        biometria = view.findViewById(R.id.Biometria)

        contrasennaBis.addTextChangedListener(this)

        builder.setView(view)
        builder.setPositiveButton("Aceptar", this)
        builder.setNegativeButton("Cancelar", this)

        return builder.create()
    }

    override fun onClick(dialog: DialogInterface?, which: Int) {
        when (which) {
            DialogInterface.BUTTON_POSITIVE -> {

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
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

    override fun afterTextChanged(s: Editable?) {
        val contrasennaStr = contrasenna.text.toString()
        val contrasennaBisStr = contrasennaBis.text.toString()
        if(contrasennaStr != contrasennaBisStr){
            Toast.makeText(requireContext(), "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
        }
    }
}
