package com.filip.machaj.demo.dto

import com.fasterxml.jackson.annotation.JsonIdentityInfo
import com.fasterxml.jackson.annotation.JsonIdentityReference
import com.fasterxml.jackson.annotation.ObjectIdGenerators
import com.filip.machaj.demo.model.dane.Abonament
import com.filip.machaj.demo.model.dane.Model
import com.filip.machaj.demo.model.dane.Obywatel
import com.filip.machaj.demo.model.dane.Pojazd
import java.util.*

data class PojazdDTO(

        var kolor:String?,
        var uwagi:String?,
        var nr_rejstracyjny_pojazdu:String,
        var rok_produkcji:Date,
        var czy_zarchiwizowany:Boolean,
        @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator::class, property = "id")
        @JsonIdentityReference(alwaysAsId = true)
        var model:Model,
        @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator::class, property = "id")
        @JsonIdentityReference(alwaysAsId = true)
        var obywatel:Obywatel,
        @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator::class, property = "id")
        @JsonIdentityReference(alwaysAsId = true)
        var abonamenty: MutableList<Abonament>
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
            pojazd.rok_produkcji,
            pojazd.czy_zarchiwizowany,
            pojazd.model,
            pojazd.obywatel,
            pojazd.abonamenty
    ){
        id = pojazd.id
        kiedy_utworzono = pojazd.kiedy_utworzono
        kiedy_zmodyfikowano = pojazd.kiedy_zmodyfikowano

    }




}