package com.filip.machaj.demo.model.dane


import java.util.*
import javax.validation.constraints.Null

//@Entity
data class Obywatel(
        //  @Id @GeneratedValue(strategy = GenerationType.AUTO)
        var id : Long,
        val PESEL:String,
        var nr_dowodu:String,
        var imie:String,
        var nazwisko:String,
        val data_urodzenia:Date,
        var adres : String,
        var czy_zarchiwizowany : Boolean = false,
        var kiedy_utworzono : Long? = null,
        var kiedy_zmodyfikowano : Long?= null

        ) {
        init {
                kiedy_utworzono = System.currentTimeMillis()

        }
        fun wieksza():Boolean{
                if (kiedy_utworzono!! <= this!!.kiedy_zmodyfikowano!!)
                {
                        println("Kiedy_utworzono jest mniejsza od kiedy zmodyfikowano")
                        return true
                }
                else

                { println("jest źle")
                        return false
                }

        }

}