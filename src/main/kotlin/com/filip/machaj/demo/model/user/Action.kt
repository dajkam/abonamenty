package com.filip.machaj.demo.model.user

import java.util.*

data class Action(
        val id : Long,
        val kto : Long, // klucz obcy id użytlownika, który wykonał czynnośc
        val kiedy : Date = Date(),
        val co_wykonano : String, // wymyślić obsługę tego to nie ma być string, najlepiej typ wyliczeniowy
        val co_zmieniono : String ){// wymyślić obsługe tego to nie ma nyć string,wymyślić obsługe tego to nie ma nyć string najlepiej typ wyliczeniowy
}