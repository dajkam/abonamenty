package com.filip.machaj.demo.repo


import com.fasterxml.jackson.annotation.JsonIdentityInfo
import com.fasterxml.jackson.annotation.JsonIdentityReference
import com.fasterxml.jackson.annotation.ObjectIdGenerators
import com.filip.machaj.demo.model.user.User
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.transaction.annotation.Transactional

interface UserRepository:CrudRepository<User,Long> {

 /*  @Query("select * from user where email = ?1", nativeQuery = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator::class, property = "id") /////////// czemu poniższa funkcja działa bez tej części, która jest tutaj zakomentowana?
    @JsonIdentityReference(alwaysAsId = true)*/
    fun findUserByEmail(email:String): User?

    @Query("select * from user", nativeQuery = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator::class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    fun downloadUsers():Iterable<User>

   @Modifying(clearAutomatically = true, flushAutomatically = true) // mandatory if native quaries are modying something in the dataBase
   @Transactional
   @Query("""
        update user
        set czy_zarchiwizowany = true, 
         aktywne = false
        where email = :email""", nativeQuery = true)
   fun archwizujUser(@Param("email") email:String) // tego typu funkcje muszą być typu void.

   @Modifying(clearAutomatically = true, flushAutomatically = true) // mandatory if native quaries are modying something in the dataBase
   @Transactional
   @Query("""
        update user
        set czy_zarchiwizowany = false,
         aktywne = true
        where email = :email""", nativeQuery = true)
   fun odnowUser(@Param("email") email:String) // tego typu funkcje muszą być typu void.

   @Query("select * from user where email like ?1", nativeQuery = true)
   @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator::class, property = "id")
   @JsonIdentityReference(alwaysAsId = true)
   fun szukaj(fraza: String): Iterable<User>

    @Query("select 1 from user where email = ?1", nativeQuery = true)
    fun czy_istnieje_email(@Param("email")email:String):Int

}
