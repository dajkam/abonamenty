package com.filip.machaj.demo.repo

import com.fasterxml.jackson.annotation.JsonIdentityInfo
import com.fasterxml.jackson.annotation.JsonIdentityReference
import com.fasterxml.jackson.annotation.ObjectIdGenerators
import com.filip.machaj.demo.model.dane.Obywatel
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.transaction.annotation.Transactional

interface ObywatelRepo : CrudRepository<Obywatel, Long>  {
    @Query("select * from obywatel where imie = ?1", nativeQuery = true)
    fun findByImie(imie:String): Iterable<Obywatel>

    @Query("select * from obywatel where id=( select max(id) from obywatel)", nativeQuery = true )
    fun findLast():Obywatel


    @Query("select * from obywatel where nazwisko like ?1", nativeQuery = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator::class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    fun findByNazwisko(nazwisko:String): Iterable<Obywatel>

    @Modifying(clearAutomatically = true, flushAutomatically = true) // mandatory if native quaries are modying something in the dataBase
    @Transactional
    @Query("""
        update obywatel
        set czy_zarchiwizowany = true 
        where id = :id""", nativeQuery = true)
    fun archwizujObywatela(@Param("id") id:Long) // tego typu funkcje muszą być typu void.

    @Modifying(clearAutomatically = true, flushAutomatically = true) // mandatory if native quaries are modying something in the dataBase
    @Transactional
    @Query("""
        update obywatel
        set czy_zarchiwizowany = false 
        where id = :id""", nativeQuery = true)
    fun odnowObywatela(@Param("id") id:Long) // tego typu funkcje muszą być typu void.

    @Query("""
        select * from obywatel
        where 
        lower(cast(id as varchar(36))) like ?1 or lower(pesel) like ?1 or lower(nr_dowodu) like ?1
        or lower(imie) like ?1 or lower(nazwisko) like ?1 or lower(cast(data_urodzenia as varchar(36))) like ?1
        or lower(adres) like ?1
        
    """, nativeQuery = true)
    fun szukaj(fraza:String):Iterable<Obywatel>
    @Query("select * from obywatel where id = ?1", nativeQuery = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator::class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    fun getObywatelById(@Param("id")id:Long):Obywatel

}
