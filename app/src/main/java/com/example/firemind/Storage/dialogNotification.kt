package com.example.firemind.Storage

import User

interface dialogNotification {
    fun onDismissDialog(b : Boolean)
    fun onCreateUser(user: User)
}