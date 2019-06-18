package com.filip.machaj.demo.controllers

data class OBywatelFindByNazwisko(val nazwisko:String) {

    constructor():this("")// ten konstruktor musi być żeby sql query działało // dodaj komentaż w książce


}
