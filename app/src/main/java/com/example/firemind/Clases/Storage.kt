package com.example.firemind.Clases

import com.google.firebase.database.PropertyName

class Storage {
    lateinit var id : String
    lateinit var name: String
    lateinit var description: String
    var stockMin: Int = 0
    var stockMax: Int = Int.MAX_VALUE
    var currentStock: Int = 0
    lateinit var type: String
    lateinit var user: String

    constructor(
        name: String,
        description: String,
        stockMin: Int,
        stockMax: Int,
        currentStock: Int,
        type: String
    ) {
        this.name = name
        this.description = description
        this.stockMin = stockMin
        this.stockMax = stockMax
        this.currentStock = currentStock
        this.type = type
    }

    constructor()

}