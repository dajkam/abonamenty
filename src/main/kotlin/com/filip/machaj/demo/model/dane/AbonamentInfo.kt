package com.filip.machaj.demo.model.dane

import java.time.LocalDate
import java.util.*

interface AbonamentInfo {
    val id: Long
    var data_rozpoczecia: LocalDate
    var data_zakonczenia: LocalDate
    var sektor: String
    var uwagi: String
    var nr_rejstracyjny_pojazdu: String
    var marka: String
    var model:String
    var imie: String
    var nazwisko: String
    var pesel: String
    var obywatel_id : Long
    var model_id : Long
    var marka_id : Long
    var pojazd_id : Long
    var kiedy_utworzono : Date
    var kiedy_zmodyfikowano : Date
    var czy_zarchiwizowany: Boolean



}
