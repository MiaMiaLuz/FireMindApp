package com.example.firemind.DatabaseManager

import User
import android.widget.Toast
import com.example.firemind.Clases.Storage
import com.example.firemind.Storage.dialogNotification
import com.google.api.Context
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import java.util.ArrayList

class DatabaseManager {

    suspend fun getStorage(): ArrayList<Storage> {
        val user = FirebaseAuth.getInstance().currentUser
        val db = Firebase.firestore
        val collectionRef = db.collection("Storage")
        var storageList = ArrayList<Storage>()
        if (user != null) {
            val querySnapshot = collectionRef.whereEqualTo("user", user.email).get().await()
            for (document in querySnapshot.documents) {
                val storageData = document.toObject(Storage::class.java)
                storageData?.id = document.id
                if (storageData != null) {
                    storageList.add(storageData)
                }
            }
        }
        return storageList
    }
    suspend fun getListBuys(): ArrayList<Storage> {
        var lista = getStorage()
        var i = 0
        var storageList = ArrayList<Storage>()
        for(buy in lista) {
            if(buy.currentStock <= buy.stockMin){
                storageList.add(buy)
            }
        }
        return storageList
    }
    fun addStorage(newS: Storage) {
        val user = FirebaseAuth.getInstance().currentUser
        newS.user = user?.email.toString()
        val db = FirebaseFirestore.getInstance()
        val collectionRef = db.collection("Storage")
        if (user != null) {
            collectionRef.add(newS)
                .addOnSuccessListener { documentReference ->
                    println("Documento agregado con ID: ${documentReference.id}")
                }
                .addOnFailureListener { e ->
                    println("Error al agregar documento: $e")
                }
        }
    }
    fun modifyStorage(updatedStorage: Storage) {
        val user = FirebaseAuth.getInstance().currentUser
        updatedStorage.user = user?.email.toString()
        val db = FirebaseFirestore.getInstance()
        val collectionRef = db.collection("Storage")
        user?.let {
            val storageId = updatedStorage.name
            storageId?.let { id ->
                collectionRef.document(id)
                    .set(updatedStorage)
                    .addOnSuccessListener {
                        println("Artículo modificado con éxito")
                    }
                    .addOnFailureListener { e ->
                        println("Error al modificar el artículo: $e")
                    }
            }
        }
    }
    fun dropStorage(updatedStorage: Storage) {
        val user = FirebaseAuth.getInstance().currentUser
        updatedStorage.user = user?.email.toString()
        val db = FirebaseFirestore.getInstance()
        val collectionRef = db.collection("Storage")
        user?.let {
            val storageId = updatedStorage.id
            storageId?.let { id ->
                collectionRef.document(id)
                    .delete()
                    .addOnSuccessListener {
                        println("Artículo eliminado con éxito")
                    }
                    .addOnFailureListener { e ->
                        println("Error al eliminar el artículo: $e")
                    }
            }
        }
    }
}