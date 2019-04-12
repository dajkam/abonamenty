package com.filip.machaj.demo.model

import java.util.*

data class Obywatel(
                     val id : Long,
                     val PESEL:String,
                     var nr_dowodu:String,
                     val imie:String,
                     val nazwisko:String,
                     val data_urodzenia:Date,
                     var adres : String,
                     var czy_zarchiwizowany : Boolean = false ) {
}