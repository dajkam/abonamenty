package com.filip.machaj.demo.model.dane


import java.util.*
//@Entity
data class Obywatel(

                     val PESEL:String,
                     var nr_dowodu:String,
                     val imie:String,
                     val nazwisko:String,
                     val data_urodzenia:Date,
                     var adres : String,
                     var czy_zarchiwizowany : Boolean = false,
                 //  @Id @GeneratedValue(strategy = GenerationType.AUTO)
                     val id : Long
        ) {

}