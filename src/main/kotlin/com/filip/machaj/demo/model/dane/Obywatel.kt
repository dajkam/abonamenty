package com.filip.machaj.demo.model.dane


import com.fasterxml.jackson.annotation.JsonInclude
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "obywatel")
@JsonInclude(JsonInclude.Include.NON_NULL)
data class Obywatel(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id : Long,
        @Column(columnDefinition = "varchar(36)")
        var PESEL:String,
        var nr_dowodu:String,
        var imie:String,
        var nazwisko:String,
        var adres : String,
        @Column(columnDefinition = "boolean")
          var czy_zarchiwizowany : Boolean = false,
        @Column(columnDefinition = "date")
          var data_urodzenia:Date,
        @CreationTimestamp
        var kiedy_utworzono : Date = Date(),
        @UpdateTimestamp
        var kiedy_zmodyfikowano : Date = Date()

        ) {

        constructor() : this(
                -1 ,"", "",
                "", "",
                "",
                false,
                Date(),
                Date(),
                Date()


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

        operator fun invoke(obywatel: Obywatel): Obywatel{
                return this

        }

}