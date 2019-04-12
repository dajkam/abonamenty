package com.filip.machaj.demo.model

import java.util.*

data class Pojazd (
        val id : Long,
        val nr_rejstracyjny_pojazdu : String,
        val rok_producji : Date,
        val  model : String,
        var kolor : String?,
        var uwagi : String?,
        var czy_zarchiwizowany : Boolean = false) {
}