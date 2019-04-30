package com.filip.machaj.demo.model.user

import java.sql.Timestamp

data class Action(
                    val id : Long,
                    val kiedy : Timestamp,
                    val co_wykonano : String, // wymyślić obsługę tego to nie ma być string, najlepiej typ wyliczeniowy
                    val co_zmieniono : String ){// wymyślić obsługe tego to nie ma nyć string,wymyślić obsługe tego to nie ma nyć string najlepiej typ wyliczeniowy
}