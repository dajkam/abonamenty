package com.filip.machaj.demo.user

import com.filip.machaj.demo.dto.UserDTO
import com.filip.machaj.demo.model.user.Admin
import com.filip.machaj.demo.model.user.Straznik
import com.filip.machaj.demo.model.user.User
import com.filip.machaj.demo.service.UserService
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.junit.Test
import org.junit.Assert

@RunWith(SpringRunner::class)
@SpringBootTest
class SecurityInitializationTest {

    @Autowired
    private lateinit var service:UserService

    private val admin_haslo = "123fil"
    private val straznik_haslo = "123tom"
    private val admin_email = "machajfil@gmail.com"
    private val straznik_email = "helix@interia.pl"

    @Test
    fun initAdmin(){
        try{
            val admin = service.findUserByUserName(admin_email)
            if(admin is Admin){
                println("Konto administratora o id: ${admin.id} już istnieje")
            }
            else{
                Assert.fail("To nie jest konto administratora")
            }
        } catch (e: RuntimeException){
            val toSave = UserDTO(
                  "Filip",
                    "Machaj",
                    admin_email,
                    admin_haslo

            )
            val saved = service.saveAdmin(toSave)
            println("Utworzono konto administratora o id: ${saved.id}")
        }
    }
    @Test
    fun initStraznik(){
        try{
            val straznik = service.findUserByUserName(straznik_email)
            if(straznik is Straznik) {
                println("Konto użytkownika o  id: ${straznik.id} już istnieje")
            }else{
                Assert.fail("To nie jest konto straznika")
            }
        }catch (e:RuntimeException){
            val toSave = UserDTO(
            "Tomasz",
            "Dusza",
            straznik_email,
            straznik_haslo
            )
            val saved = service.saveStraznik(toSave)
            println("Utworzono konto strażnika o id : ${saved.id}")

        }
    }

}
