package com.filip.machaj.demo.model.dane


import com.fasterxml.jackson.annotation.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDate
import java.util.*
import javax.persistence.*
import kotlin.jvm.Transient


@Entity
@Table(name = "obywatel")
@JsonInclude(JsonInclude.Include.NON_NULL)
data class Obywatel(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id : Long,
        @Column(columnDefinition = "varchar(36)")
        var pesel:String,
        var nr_dowodu:String,
        var imie:String,
        var nazwisko:String,
        var adres : String,
        @Column(columnDefinition = "boolean")
          var czy_zarchiwizowany : Boolean = false,
        @Column(columnDefinition = "date")
          var data_urodzenia:LocalDate,
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

   /*     @OneToOne(mappedBy = "obywatel")
        @JsonManagedReference(value = "ab-ob")
        @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator::class, property = "id")
        @JsonIdentityReference(alwaysAsId = true)
        var abonament: Abonament*/
        @Transient
        @OneToMany(mappedBy = "obywatel", cascade =  arrayOf(CascadeType.ALL))
        @JsonManagedReference(value = "poj-ob")
        @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator::class, property = "id")
        @JsonIdentityReference(alwaysAsId = true)
        var pojazdy:MutableList<Pojazd> = mutableListOf() // sprawdzać w konstruktorze czy jest tylko 1 niezarchiwizowany



) {

        constructor() : this(
                -1 ,"", "",
                "", "",
                "",
                false,
                LocalDate.now(),
                Date(),
                Date(),
                mutableListOf()







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
