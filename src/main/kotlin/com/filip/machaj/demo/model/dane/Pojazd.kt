package com.filip.machaj.demo.model.dane

import com.fasterxml.jackson.annotation.JsonInclude
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "pojazd")
@JsonInclude(JsonInclude.Include.NON_NULL)

data class Pojazd (
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id : Long,
        @Column(columnDefinition = "varchar(36)")
        var model : String,  /// dodać markę dla samochodu
        var kolor : String?,
        var uwagi : String?,
        var nr_rejstracyjny_pojazdu : String,
        @Column(columnDefinition = "date")
        var rok_producji : Date,
        @Column(columnDefinition = "boolean")
        var czy_zarchiwizowany : Boolean = false,
        @CreationTimestamp
        var kiedy_utworzono : Date = Date(),
        @UpdateTimestamp
        var kiedy_zmodyfikowano : Date = Date())
        {

    constructor():this(
            -1,"","","","",
            Date(),
            false,
            Date(),
            Date()
    )

    operator fun invoke(pojazd:Pojazd):Pojazd{
        return this
    }
}