package com.example.firemind.InicioDeSesion

import User
import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Build
import android.service.autofill.UserData
import androidx.annotation.RequiresApi

class UserRecords (context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    private val COLUMN_ID = "id"
    private val COLUMN_EMAIL = "email"
    private val COLUMN_PASSWORD= "password"
    private val COLUMN_BIO= "biometric"
    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "UserData.db"

        private const val USER_DATA = "CREATE TABLE Ejemplo (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "email TEXT NOT NULL," +
                "password TEXT NOT NULL," +
                "biometric INTEGER NOT NULL)"
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(USER_DATA)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

    }
    fun insertUserData(email: String, password: String, bio: Boolean): Long {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COLUMN_EMAIL, email)
        contentValues.put(COLUMN_PASSWORD, password)
        bio?.let {
            contentValues.put(COLUMN_BIO, bio)
        }
        val id = db.insert(USER_DATA, null, contentValues)
        db.close()
        return id
    }

    @RequiresApi(Build.VERSION_CODES.P)
    @SuppressLint("Range")
    fun getUserDataDefault(): User {
        val db = this.readableDatabase
        val columns = arrayOf(COLUMN_ID, COLUMN_EMAIL, COLUMN_PASSWORD, COLUMN_BIO)
        var cursor = db.query(USER_DATA, columns, null, null, null, null, null)
        lateinit var userData : User
        cursor.use {
            while (cursor.moveToNext()) {
                val email = cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL))
                val password = cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD))
                val bio = cursor.getString(cursor.getColumnIndex(COLUMN_BIO))
                userData = User(email, password, bio as Boolean)
            }
        }
        return userData
    }
    @RequiresApi(Build.VERSION_CODES.P)
    @SuppressLint("Range")
    fun getUserData(email : String): User {
        val db = this.readableDatabase
        val columns = arrayOf(COLUMN_ID, COLUMN_EMAIL, COLUMN_PASSWORD, COLUMN_BIO)
        var cursor = db.query(USER_DATA, columns, COLUMN_EMAIL, arrayOf(email), null, null, null)
        lateinit var userData : User
        cursor.use {
            while (cursor.moveToNext()) {
                val email = cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL))
                val password = cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD))
                val bio = cursor.getString(cursor.getColumnIndex(COLUMN_BIO))
                userData = User(email, password, bio as Boolean)
            }
        }
        return userData
    }
}