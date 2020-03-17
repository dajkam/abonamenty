package com.filip.machaj.demo.service

import com.filip.machaj.demo.dto.UserDTO
import com.filip.machaj.demo.dto.UserDetailsDTO
import com.filip.machaj.demo.model.user.*
import com.filip.machaj.demo.repo.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class UserService {
    @Autowired
    lateinit var repo: UserRepository

    val encoder = BCryptPasswordEncoder(11)

    fun findUserByUserName(email:String): User? {
        return repo.findUserByEmail(email) ?:
                throw RuntimeException("Konto o emailu $email nie istnieje lub zostało usunięte.")
    }
    fun SaveAdmin(user:UserDTO):User{
        val admin = Admin()
        admin.imie = user.imie
        admin.nazwisko = user.nazwisko
        admin.email = user.email
        admin.haslo = user.haslo
        admin.role = Role.ULICZNY.poziom + Role.STRAZNIK.poziom + Role.ADMIN.poziom
        return repo.save(admin)


    }
    fun SaveStraznik(user:UserDTO):User{
       val straznik = Straznik()
       straznik.imie = user.imie
       straznik.nazwisko = user.nazwisko
       straznik.email = user.email
       straznik.haslo = user.haslo
       straznik.role = Role.ULICZNY.poziom + Role.STRAZNIK.poziom + Role.ADMIN.poziom
        return repo.save(straznik)


    }

    fun SaveUliczny(user:UserDTO):User{
        val uliczny = Uliczny()
       uliczny.imie = user.imie
       uliczny.nazwisko = user.nazwisko
       uliczny.email = user.email
       uliczny.haslo = user.haslo
       uliczny.role = Role.ULICZNY.poziom + Role.STRAZNIK.poziom + Role.ADMIN.poziom
        return repo.save(uliczny)


    }

    fun updateUser(toSave:User): User? {
        val user = repo.findUserByEmail(toSave.email)
        user?.let{
            if(!toSave.haslo.isEmpty()){
                it.haslo = encoder.encode(toSave.haslo)
            }
            it.imie = toSave.imie
            it.nazwisko = toSave.nazwisko
            it.czy_zarchiwizowany = toSave.czy_zarchiwizowany
            it.aktywne = toSave.aktywne
            it.nie_wygasniete = toSave.nie_wygasniete
            it.nie_wygasniete_id_i_haslo = toSave.nie_wygasniete_id_i_haslo
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




}
