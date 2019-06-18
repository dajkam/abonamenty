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
        var kiedy_zmodyfikowano : Date = Date(),

        // klucze obce
        /*@ManyToMany(cascade = [CascadeType.ALL])
        @JoinTable(
                name = "obywatel_pojazd",
                joinColumns = [JoinColumn(name = "obywatel_id", referencedColumnName = "id")],
                inverseJoinColumns = [JoinColumn(name = "pojazd_id", referencedColumnName = "id")]

        )
        var pojazdy: Set<Pojazd> = mutableSetOf<Pojazd>() /// spróbować stwożyc qquery testujące klucze obce*/

        @OneToOne(mappedBy = "obywatel")
        var abonament: Abonament ? = null


) {

        constructor() : this(
                -1 ,"", "",
                "", "",
                "",
                false,
                Date(),
                Date()







        )

     //   constructor(id: Long, pesel: String, nr_dowodu: String, imie: String, nazwisko: String, adres: String, czy_zarchiwizowany: Boolean, data_urodzenia: Date) : this()


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