package com.filip.machaj.demo.repo


import com.fasterxml.jackson.annotation.JsonIdentityInfo
import com.fasterxml.jackson.annotation.JsonIdentityReference
import com.fasterxml.jackson.annotation.ObjectIdGenerators
import com.filip.machaj.demo.model.user.User
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface UserRepository:CrudRepository<User,Long> {

 /*  @Query("select * from user where email = ?1", nativeQuery = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator::class, property = "id") /////////// czemu poniższa funkcja działa bez tej części, która jest tutaj zakomentowana?
    @JsonIdentityReference(alwaysAsId = true)*/
    fun findUserByEmail(email:String): User?

    @Query("select * from user", nativeQuery = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator::class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)

    fun downloadUsers():Iterable<User>

}
