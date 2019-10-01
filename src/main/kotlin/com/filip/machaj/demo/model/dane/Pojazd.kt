package com.filip.machaj.demo.model.dane

import com.fasterxml.jackson.annotation.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.util.*
import javax.persistence.*

//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class)
@Entity
@Table(name = "pojazd")
@JsonInclude(JsonInclude.Include.NON_NULL)

data class Pojazd (
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id : Long,
        @Column(columnDefinition = "varchar(36)")
        var kolor : String?,
        var uwagi : String?,
        var nr_rejstracyjny_pojazdu : String,
        @Column(columnDefinition = "date")
        var rok_producji : Date, // Zmienić na LocalDate tylko rok
        @Column(columnDefinition = "boolean")
        var czy_zarchiwizowany : Boolean = false,

        @ManyToOne(fetch = FetchType.EAGER, cascade =  arrayOf(CascadeType.ALL)) // dodane cascade =  arrayOf(CascadeType.ALL) jako rozwiązanie pewnego względu jednak chyba nie jest wymagane.
        @JoinColumn(name = "model_id")
        @JsonBackReference
        @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator::class, property = "id")
        @JsonIdentityReference(alwaysAsId = true)
        var model : Model ? = null,
        @CreationTimestamp
        var kiedy_utworzono : Date = Date(),
        @UpdateTimestamp
        var kiedy_zmodyfikowano : Date = Date(),
        //@ManyToMany(cascade = [CascadeType.ALL])
       /* @JoinTable(
                name = "pojazd_obywatel",com/filip/machaj/demo/model/dane/Pojazd.kt:21
                joinColumns = [JoinColumn(name = "pojazd_id",referencedColumnName = "id")],
                inverseJoinColumns = [JoinColumn(name = "obywatel_id", referencedColumnName = "id")]
        )*/
       /* @ManyToMany(mappedBy = "pojazdy")
        val obywatel: Set<Obywatel> = mutableSetOf<Obywatel>()*/





        @OneToOne(mappedBy = "pojazd")
        @JsonManagedReference(value = "ab-po")
        @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator::class, property = "id")
        @JsonIdentityReference(alwaysAsId = true)
        var abonament: Abonament ? = null



)

        {

  /*  constructor():this(
            -1,
            "",
            "",
            "",
            Date(),
            false,
            Date(),
            Date()

    )*/


            constructor():this(
                    -1,
                    "",
                    "",
                    "",
                    Date(),
                    false,

                    Model(),
                    Date(),
                    Date()

            )



            //constructor(id: Long, kolor: String?, uwagi: String?, nr_rejstracyjny_pojazdu: String, rok_producji: Date, czy_zarchiwizowany: Boolean, kiedy_utworzono: Model) : this()

            //  constructor(id: Long, kolor: String?, uwagi: String?, nr_rejstracyjny_pojazdu: String, rok_producji: Date, czy_zarchiwizowany: Boolean, kiedy_utworzono: Model?) : this()

            //  constructor(id: Long, model: String, kolor: String?, uwagi: String?, nr_rejstracyjny_pojazdu: String, rok_produkcji: Date, czy_zarchiwizowany: Boolean) : this()

            operator fun invoke(pojazd:Pojazd):Pojazd{
        return this
    }
}