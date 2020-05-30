package com.filip.machaj.demo.controllers

import com.filip.machaj.demo.dto.UserDTO
import com.filip.machaj.demo.dto.UserUpdateDTO
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







    @GetMapping(value = ["/download"],
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )
    fun getUser() = service.getUser() /////////// ta metoda nie działa nie wiadomo dlaczego ale ostatnia metoda na samym dole szczególnie jązastępuje mówi że metoda get nie jest wspierana
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
            value = ["/update"],
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE),
            consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )
    fun updateUser(
            @RequestBody user: UserUpdateDTO
    ): User? = service.updateUser(user)

    @GetMapping(value = ["/get"],
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )
    fun getUsers() = service.downLoadUsers() /////////////////// najważniejsza netoda///////////////////////////////

    @GetMapping(value = ["/get/{email}"],
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )
    fun getUserByEmail(@PathVariable(name = "email") email:String): User? = service.findUserByUserName(email)

    @PostMapping(
            value = ["archiwizuj/{email}"],
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )
    fun archwizujUser(@PathVariable(name = "email")email:String) = service.archiwizujUser(email)

    @PostMapping(
            value = ["odnow/{email}"],
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )
    fun odnowUser(@PathVariable(name = "email")email: String) = service.odnowUser(email)
}


