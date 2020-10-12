package com.dorukaneskiceri.dailyathon

class CurrencyModel( val title: String,
                     val description: String,
                     val changeRate: String) {

    constructor(): this(title = "", description = "", changeRate = "")
}