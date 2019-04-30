package com.filip.machaj.demo.model.user

data class User(
                val id : Long,
                val imie : String,
                val nazwisko : String,
                var uprawnienia : Char, // zbudować obsługe uprawnień
                var haslo : String, // zbudować osługe haseł
                var czy_zarchiwizowany : Boolean = false) {
}