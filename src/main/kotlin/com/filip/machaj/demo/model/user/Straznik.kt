package com.filip.machaj.demo.model.user

import java.util.*
import javax.persistence.DiscriminatorValue
import javax.persistence.Entity

@Entity
@DiscriminatorValue(value = "straznik")
class Straznik(
        id:Long,
        imie:String,
        nazwisko:String,
        email:String,
        haslo:String,
        role:String,
        czy_zarchiwizowany: Boolean ,
        aktywne:Boolean,
        nie_wygasniete:Boolean,
        nie_zablokowane:Boolean,
        nie_wygasniete_id_i_haslo:Boolean,
        kiedy_utworzono: Date ,
        kiedy_zmodyfikowano: Date

):User(
        id, imie, nazwisko, email, haslo, role, czy_zarchiwizowany, aktywne, nie_wygasniete, nie_zablokowane, nie_wygasniete_id_i_haslo, kiedy_utworzono, kiedy_zmodyfikowano
) {
    constructor():this(
            -1,
            "",
            "",
            "",
            "",
            "",
            false,
            true,
            true,
            true,
            true,
            Date(),
            Date()
    )

}
