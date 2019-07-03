package com.filip.machaj.demo.dto

import com.filip.machaj.demo.model.dane.Pojazd
import java.util.*

data class PojazdDTO(

        var kolor:String?,
        var uwagi:String?,
        var nr_rejstracyjny_pojazdu:String,
        var rok_produkcji:Date,
        var czy_zarchiwizowany:Boolean
) {
    operator fun invoke(pojazd: Pojazd):PojazdDTO {
        return this
    }

    var id:Long = 0
    var kiedy_utworzono:Date = Date()
    var kiedy_zmodyfikowano:Date = Date()

    constructor(pojazd: Pojazd):this(

            pojazd.kolor,
            pojazd.uwagi,
            pojazd.nr_rejstracyjny_pojazdu,
            pojazd.rok_producji,
            pojazd.czy_zarchiwizowany
    ){
        id = pojazd.id
        kiedy_utworzono = pojazd.kiedy_utworzono
        kiedy_zmodyfikowano = pojazd.kiedy_zmodyfikowano
    }


}