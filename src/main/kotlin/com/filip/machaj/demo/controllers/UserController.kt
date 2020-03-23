package com.filip.machaj.demo.controllers

import com.filip.machaj.demo.dto.UserDTO
import com.filip.machaj.demo.model.user.User
import com.filip.machaj.demo.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user")
class UserController {
    @Autowired
    lateinit var service:UserService
    @GetMapping(
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )
    fun getUsers() = service.getUsers()
    @PutMapping(
            value = ["/admin"],
             produces = arrayOf(MediaType.APPLICATION_JSON_VALUE),
             consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )
    fun insertAdmin(
            @RequestBody user:UserDTO
    ) = service.saveAdmin(user)

    @PutMapping(
            value = ["/straznik"],
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE),
            consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )
    fun insertStraznik(
            @RequestBody user:UserDTO
    ) = service.saveStraznik(user)

    @PutMapping(
            value = ["/uliczny"],
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE),
            consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )
    fun insertUliczny(
            @RequestBody user:UserDTO
    ) = service.saveUliczny(user)

    @DeleteMapping(
            value = ["/{id}"],
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )
    fun deleteUser(
            @PathVariable(name = "id") id:Long
    ) = service.deleteUser(id)


    @PostMapping(
            // dlaczego tu nie ma ścieszki ? może datego że jest to jasne z JSONA o którego usera chodzi? i jeśli to jest jedyna metoda z post to dlatego też nie potzeba ścieżki
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE),
            consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )
    fun updateUser(
            @RequestBody user: User
    ): User? = service.updateUser(user)
}
