package com.filip.machaj.demo.model.dane

import com.fasterxml.jackson.annotation.JsonInclude
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
        var data_rozpoczecia:Date,
        var data_zakonczenia: Date,
        @Column(columnDefinition = "varchar(1)")
        var sektor: String, /// zmienić na enum
        @Column(columnDefinition = "varchar(36)")
        var uwagi: String,
        @Column(columnDefinition = "boolean")
        var czy_zarchiwizowany: Boolean = false,
        @CreationTimestamp
        var kiedy_utworzono: Date = Date(),
        @UpdateTimestamp
        var kiedy_zmodyfikowano: Date = Date(),

        @OneToOne(cascade = arrayOf(CascadeType.ALL))
            @JoinColumn(name = "obywatel_id")
        var obywatel: Obywatel ? = null,

        @OneToOne(cascade = arrayOf(CascadeType.ALL))
            @JoinColumn(name = "pojazd_id")
        var pojazd: Pojazd ? = null





        ) {
    constructor():this(
            -1,
            Date(),
            Date(),
            "",
            "",
            false,
            Date(),
            Date()
    )

  //  constructor(id: Long, data_rozpoczecia: Date, data_zakonczenia: Date, sektor: String, uwagi: String, czy_zarchiwizowany: Boolean, kiedy_utworzono: Obywatel?, kiedy_zmodyfikowano: Pojazd?) : this()

    operator fun invoke(abonament: Abonament):Abonament{
        return this
    }

}