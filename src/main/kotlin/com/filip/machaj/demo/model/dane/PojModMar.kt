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
        var obywatel_id : Long
        var model_id : Long
        var marka_id : Long
        var kiedy_utworzono : Date
        var kiedy_zmodyfikowano : Date
}