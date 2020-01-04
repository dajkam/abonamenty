package com.filip.machaj.demo.dto

import com.fasterxml.jackson.annotation.JsonIdentityInfo
import com.fasterxml.jackson.annotation.JsonIdentityReference
import com.fasterxml.jackson.annotation.ObjectIdGenerators
import com.filip.machaj.demo.model.dane.Abonament
import com.filip.machaj.demo.model.dane.Obywatel
import com.filip.machaj.demo.model.dane.Pojazd
import java.time.LocalDate
import java.util.*

class AbonamentDTO(
        var data_rozpoczecia: LocalDate,
        var data_zakonczenia: LocalDate,
        var sektor:String,
        var uwagi:String,
        var czy_zarchiwizowany:Boolean,
        @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator::class, property = "id")
        @JsonIdentityReference(alwaysAsId = true)
        var pojazd:  Pojazd


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
            abonament.pojazd


    ){
        id = abonament.id
        kiedy_utworzono = abonament.kiedy_utworzono
        kiedy_zmodyfikowano = abonament.kiedy_zmodyfikowano
    }

}
