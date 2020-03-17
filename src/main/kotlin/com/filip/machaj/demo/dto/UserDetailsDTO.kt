package com.filip.machaj.demo.dto

import java.util.*

data class UserDetailsDTO( // w tej klasie nie ma i niepowinno być pola z hasłem
        val id:Long,
        var imie:String,
        var nazwisko:String,
        var email:String,
        var role:String,
        var czy_zarchiwizowany: Boolean,
        var aktywne:Boolean,
        var nie_wygasniete:Boolean,
        var nie_zablokowane:Boolean,
        var nie_wygasniete_id_i_haslo:Boolean,
        var kiedy_utworzono: Date,
        var kiedy_zmodyfikowano: Date
) {
}
