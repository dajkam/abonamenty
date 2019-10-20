package com.filip.machaj.demo.dto

import com.fasterxml.jackson.annotation.JsonIdentityInfo
import com.fasterxml.jackson.annotation.JsonIdentityReference
import com.fasterxml.jackson.annotation.ObjectIdGenerators
import com.filip.machaj.demo.model.dane.Model
import com.filip.machaj.demo.model.dane.Obywatel
import com.filip.machaj.demo.model.dane.Pojazd
import java.time.LocalDate
import java.util.*

data class ObywatelDTO(


        var pesel:String,
        var nr_dowodu:String,
        var imie:String,
        var nazwisko:String,
        var adres: String,
        var czy_zarchiwizowany: Boolean,
        var data_urodzenia: LocalDate,
        @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator::class, property = "id")
        @JsonIdentityReference(alwaysAsId = true)
        var pojazdy: MutableList<Pojazd>

        ) {
   operator fun invoke(obywatel: Obywatel): ObywatelDTO { // return obywatelDTO(obywatel) z funcji upDateObywatel w ObywatelService nie działa bez funkcji invoke

       return this

    }


    var id : Long = 0
    var kiedy_utworzono : Date = Date()
    var kiedy_zmodyfikowano : Date = Date()

    constructor(obywatel: Obywatel) : this(
            obywatel.pesel,
            obywatel.nr_dowodu,
            obywatel.imie,
            obywatel.nazwisko,
            obywatel.adres,
            obywatel.czy_zarchiwizowany,
            obywatel.data_urodzenia,
            obywatel.pojazdy
            
    ){
        id = obywatel.id
        kiedy_utworzono = obywatel.kiedy_utworzono
        kiedy_zmodyfikowano = obywatel.kiedy_zmodyfikowano

    }


}