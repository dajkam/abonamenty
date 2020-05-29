package com.filip.machaj.demo.service

import com.filip.machaj.demo.dto.UserDTO
import com.filip.machaj.demo.dto.UserDetailsDTO
import com.filip.machaj.demo.dto.UserUpdateDTO
import com.filip.machaj.demo.model.user.*
import com.filip.machaj.demo.model.user.security.WebSecurityConfiguration
import com.filip.machaj.demo.repo.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder

@Service("User service")
 class UserService: UserDetailsService {
    @Transactional(readOnly = true)
    override fun loadUserByUsername(email: String): UserDetails {
        return repo.findUserByEmail(email) ?:
        throw RuntimeException("Konto o emailu $email nie istnieje lub zostało usunięte.")


    }

    val encoder = BCryptPasswordEncoder(12)

    @Autowired
    lateinit var repo: UserRepository




   // @Autowired
 //  lateinit var security: WebSecurityConfiguration



    fun getUser(): Iterable<User> = repo.findAll().map { it -> User() }



    fun findUserByUserName(email:String): User? {
        return repo.findUserByEmail(email) ?:
                throw RuntimeException("Konto o emailu $email nie istnieje lub zostało usunięte.")
    }
    fun saveAdmin(user:UserDTO):User{
        val admin = Admin()
        admin.imie = user.imie
        admin.nazwisko = user.nazwisko
        admin.email = user.email
        admin.haslo = encoder.encode(user.haslo)
        admin.role =  Role.ADMIN.poziom
        repo.save(admin)
       //  var auth: AuthenticationManagerBuilder = AuthenticationManagerBuilder(null)
      //  security.configureGlobal( auth)

        return admin


    }
    fun saveStraznik(user:UserDTO):User{
       val straznik = Straznik()
       straznik.imie = user.imie
       straznik.nazwisko = user.nazwisko
       straznik.email = user.email
       straznik.haslo = encoder.encode(user.haslo)
       straznik.role =  Role.STRAZNIK.poziom
        return repo.save(straznik)


    }

    fun saveUliczny(user:UserDTO):User{
        val uliczny = Uliczny()
       uliczny.imie = user.imie
       uliczny.nazwisko = user.nazwisko
       uliczny.email = user.email
       uliczny.haslo = encoder.encode(user.haslo)
       uliczny.role = Role.ULICZNY.poziom
        return repo.save(uliczny)


    }

    fun updateUser(toSave:UserUpdateDTO): User? {
        val user = repo.findUserByEmail(toSave.email)
        user?.let{
            if(!toSave.haslo.isEmpty()){
                it.haslo = encoder.encode(toSave.haslo)
            }
            it.email = toSave.new_email
            it.imie = toSave.imie
            it.nazwisko = toSave.nazwisko
            it.role = toSave.role
            it.czy_zarchiwizowany = toSave.czy_zarchiwizowany
          /*  it.aktywne = toSave.aktywne                                          // to jest zakomentowane bo funkcja przyjmuje UserDTO jako argument a on nie ma tych pól zaimplementowanych
            it.nie_wygasniete = toSave.nie_wygasniete
            it.nie_wygasniete_id_i_haslo = toSave.nie_wygasniete_id_i_haslo*/
            if (!toSave.equals(it))
                user.kiedy_zmodyfikowano = Date()
            return repo.save(user)


        }
        return null
    }
    fun getUsers() = repo.findAll().map { it ->
        UserDetailsDTO(
                it.id,
        it.imie,
        it.nazwisko,
        it.email,
        it.role,
        it.czy_zarchiwizowany,
        it.aktywne,
        it.nie_wygasniete,
        it.nie_zablokowane,
        it.nie_wygasniete_id_i_haslo,
        it.kiedy_utworzono,
        it.kiedy_zmodyfikowano
        )
    }
    fun deleteUser(id: Long) = repo.deleteById(id)

    fun downLoadUsers(): Iterable<User> = repo.downloadUsers() ////////// najważniejsza metoda z userController





}
