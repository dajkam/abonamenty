package com.filip.machaj.demo.repo


import com.fasterxml.jackson.annotation.JsonIdentityInfo
import com.fasterxml.jackson.annotation.JsonIdentityReference
import com.fasterxml.jackson.annotation.ObjectIdGenerators
import com.filip.machaj.demo.model.user.User
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface UserRepository:CrudRepository<User,Long> {
    fun findUserByEmail(email:String): User?

    @Query("select * from user", nativeQuery = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator::class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)

    fun downloadUsers():Iterable<User>

}
