package com.filip.machaj.demo.user

import com.filip.machaj.demo.dto.UserDTO
import com.filip.machaj.demo.model.user.*
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

    private val admin_email = "lemmy"
    private val admin_haslo = "motorhead"
    private val straznik_email = "user"
    private val straznik_haslo = "du"
    private val uliczny_email = "user1"
    private val uliczny_hasło = "du1"

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
                    admin_haslo,
                    Role.ADMIN.poziom

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
            straznik_haslo,
            Role.STRAZNIK.poziom
            )
            val saved = service.saveStraznik(toSave)
            println("Utworzono konto strażnika o id : ${saved.id}")

        }
    }

    @Test
    fun initUliczny(){
        try{
            val uliczny = service.findUserByUserName(uliczny_email)
            if(uliczny is Uliczny) {
                println("Konto użytkownika o  id: ${uliczny.id} już istnieje")
            }else{
                Assert.fail("To nie jest konto ulicznego")
            }
        }catch (e:RuntimeException){
            val toSave = UserDTO(
                    "Maciej",
                    "Jarosz",
                    uliczny_email,
                    uliczny_hasło,
                    Role.ULICZNY.poziom
            )
            val saved = service.saveUliczny(toSave)
            println("Utworzono konto ulicznego o id : ${saved.id}")

        }
    }

}
