package com.filip.machaj.demo.repo

import com.filip.machaj.demo.model.dane.Obywatel
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface ObywatelRepo : CrudRepository<Obywatel, Long>  {
    @Query("select * from obywatel where imie = ?1", nativeQuery = true)
    fun findByImie(imie:String): Iterable<Obywatel>

    @Query("select * from obywatel where id=( select max(id) from obywatel)", nativeQuery = true )
    fun findLast():Obywatel


    @Query("select * from obywatel where nazwisko like ?1", nativeQuery = true)
    fun findByNazwisko(nazwisko:String): Iterable<Obywatel>

}