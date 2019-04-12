package com.filip.machaj.demo.model

import java.util.*

data class Abonament(
                        val id : Long,
                        var data_rozpoczecia :Date,
                        var data_zakonczenia : Date,
                        var sektor : Char, /// zmienić na enum
                        var uwagi : String,
                        var czy_zarchiwizowany : Boolean = false) {
}