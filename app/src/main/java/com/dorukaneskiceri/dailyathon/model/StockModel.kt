package com.dorukaneskiceri.dailyathon.model

class StockModel(val title: String, val description: String, val changeRate: String) {
    constructor(): this(title = "", description = "", changeRate = "")
}