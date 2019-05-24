package com.filip.machaj.demo.model.dane


import java.util.*
import javax.persistence.*

@Entity
@Table(name = "obywatel")
data class Obywatel(
          @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id : Long,
          @Column(columnDefinition = "varchar(36)")
        var PESEL:String,
        var nr_dowodu:String,
        var imie:String,
        var nazwisko:String,
        var adres : String,
        var kiedy_utworzono : Long? = System.currentTimeMillis(),
        var kiedy_zmodyfikowano : Long?= null,
          @Column(columnDefinition = "boolean")
          var czy_zarchiwizowany : Boolean = false,
          @Column(columnDefinition = "date")
          var data_urodzenia:Date?

        ) {

        constructor() : this(
                -1 ,"", "",
                "", "",
                "", -1,
                -1,
                false,
                Date(9)

        )





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