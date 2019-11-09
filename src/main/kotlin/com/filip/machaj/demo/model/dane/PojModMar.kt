package com.filip.machaj.demo.model.dane

import java.util.*

interface PojModMar{

        val id : Long
        var kolor : String?
        var uwagi : String?
        var nr_rejstracyjny_pojazdu : String
        var rok_produkcji : Date// Zmienić na LocalDate tylko rok
        var czy_zarchiwizowany : Boolean
        var marka : String
        var model : String
}