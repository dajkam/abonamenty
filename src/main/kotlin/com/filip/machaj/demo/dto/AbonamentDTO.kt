package com.filip.machaj.demo.dto

import com.filip.machaj.demo.model.dane.Abonament
import com.filip.machaj.demo.model.dane.Obywatel
import com.filip.machaj.demo.model.dane.Pojazd
import java.util.*

class AbonamentDTO(
        var data_rozpoczecia:Date,
        var data_zakonczenia:Date,
        var sektor:String,
        var uwagi:String,
        var czy_zarchiwizowany:Boolean,
        var obywatel: Obywatel?,
        var pojazd:  Pojazd?

) {
    operator fun invoke(abonament:Abonament):AbonamentDTO{
        return this
    }
    var id:Long = 0
    var kiedy_utworzono:Date = Date()
    var kiedy_zmodyfikowano:Date = Date()

    constructor(abonament: Abonament):this(
            abonament.data_rozpoczecia,
            abonament.data_zakonczenia,
            abonament.sektor,
            abonament.uwagi,
            abonament.czy_zarchiwizowany,
            abonament.obywatel,
            abonament.pojazd


    ){
        id = abonament.id
        kiedy_utworzono = abonament.kiedy_utworzono
        kiedy_zmodyfikowano = abonament.kiedy_zmodyfikowano
    }

}