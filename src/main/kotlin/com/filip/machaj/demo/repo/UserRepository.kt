package com.filip.machaj.demo.repo


import com.filip.machaj.demo.model.user.User
import org.springframework.data.repository.CrudRepository

interface UserRepository:CrudRepository<User,Long> {
    fun findUserByEmail(email:String): User?
}
