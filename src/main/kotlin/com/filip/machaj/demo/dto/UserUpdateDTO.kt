package com.filip.machaj.demo.dto

data class UserUpdateDTO(
        var  imie:String,
        var  nazwisko:String,
        var email:String,
        var  haslo:String,
        var new_email:String,
        var role:String,
        var czy_zarchiwizowany:Boolean
) {


}
