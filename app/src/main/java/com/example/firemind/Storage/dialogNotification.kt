package com.example.firemind.Storage

import UserCollection

interface dialogNotification {
    fun onDismissDialog(b : Boolean)
    fun onCreateUser(userCollection: UserCollection)
}