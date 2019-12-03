package com.filip.machaj.demo.model.dane

import com.fasterxml.jackson.annotation.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "abonament")
@JsonInclude(JsonInclude.Include.NON_NULL)// tutaj skonczyłem 5 VI 2019 16:18

data class Abonament(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long,
        @Column(columnDefinition = "date")
        var data_rozpoczecia:Date, // Zmienić na LocalDate
        var data_zakonczenia: Date,// Zmienić na LocalDate
        @Column(columnDefinition = "varchar(9)") /// jest 9 a nie 1 ze względu na to że szłowo "WSZYSTKIE" reprezentujące wszystkie sektory ma 9 liter czy można nazucić konkretnie 2 rozmiary?
        var sektor: String, /// zmienić na enum
        @Column(columnDefinition = "varchar(36)")
        var uwagi: String ,
        @Column(columnDefinition = "boolean")
        var czy_zarchiwizowany: Boolean = false,
        @CreationTimestamp
        var kiedy_utworzono: Date = Date(),
        @UpdateTimestamp
        var kiedy_zmodyfikowano: Date = Date(),


      //  @Transient
        @ManyToOne(fetch = FetchType.EAGER, cascade =  arrayOf(CascadeType.ALL)) // dodane cascade =  arrayOf(CascadeType.ALL) jako rozwiązanie pewnego względu jednak chyba nie jest wymagane.
        @JoinColumn(name = "pojazd_id")
        @JsonBackReference(value = "poj-ab")
        @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator::class, property = "id")
        @JsonIdentityReference(alwaysAsId = true)
        var pojazd : Pojazd







        ) {
    constructor():this(
            -1,
            Date(),
            Date(),
            "",
            "",
            false,
            Date(),
            Date(),
            Pojazd()

    )

  //  constructor(id: Long, data_rozpoczecia: Date, data_zakonczenia: Date, sektor: String, uwagi: String, czy_zarchiwizowany: Boolean, kiedy_utworzono: Obywatel?, kiedy_zmodyfikowano: Pojazd?) : this()

    operator fun invoke(abonament: Abonament):Abonament{
        return this
    }

}
