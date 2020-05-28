package com.filip.machaj.demo.model.user

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.*
import javax.persistence.*
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Entity
@Table(name = "user")
open class User (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
       open val id: Long,
        @Column(columnDefinition = "varchar(36)")
        @NotNull
        @NotBlank
        open var imie:String,
        @NotNull
        @NotBlank
        open  var nazwisko:String,
        @Column(unique = true, nullable = false) // , name = "username"
        @NotNull
        @Email
      open  var email: String,
        @NotNull
        @NotBlank
      //  @Column(name = "password")
        open  var haslo:String,
       open   var role:String,
        @Column(columnDefinition = "boolean")
       open   var czy_zarchiwizowany: Boolean = false,
        //@Column(name = "enabled")
        open  var aktywne:Boolean = true,
       open   var nie_wygasniete:Boolean = true,
       open   var nie_zablokowane:Boolean = true,
      open    var nie_wygasniete_id_i_haslo:Boolean = true,
        @CreationTimestamp
        open   var kiedy_utworzono: Date = Date(),
         @UpdateTimestamp
        open  var kiedy_zmodyfikowano: Date = Date()


): UserDetails {
    constructor():this(
            -1,
            "",
            "",
            "",
            "",
            "",
            false,
            true,
            true,
            true,
            true,
            Date(),
            Date()
    )
    override fun isEnabled(): Boolean = aktywne

    override fun getUsername(): String = email

    override fun isCredentialsNonExpired(): Boolean = nie_wygasniete_id_i_haslo

    override fun getPassword(): String = haslo

    override fun isAccountNonExpired(): Boolean = nie_wygasniete

    override fun isAccountNonLocked(): Boolean = nie_zablokowane

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
       val uprawnienia = mutableListOf<GrantedAuthority>()
        role.split(",").forEach{
            it -> uprawnienia.add(SimpleGrantedAuthority(it.trim()))
        }
        return uprawnienia
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is User) return false

        if (id != other.id) return false
        if (imie != other.imie) return false
        if (nazwisko != other.nazwisko) return false
        if (email != other.email) return false
        if (haslo != other.haslo) return false
        if (role != other.role) return false
        if (czy_zarchiwizowany != other.czy_zarchiwizowany) return false
        if (aktywne != other.aktywne) return false
        if (nie_wygasniete != other.nie_wygasniete) return false
        if (nie_zablokowane != other.nie_zablokowane) return false
        if (nie_wygasniete_id_i_haslo != other.nie_wygasniete_id_i_haslo) return false
        if (kiedy_utworzono != other.kiedy_utworzono) return false
        if (kiedy_zmodyfikowano != other.kiedy_zmodyfikowano) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + imie.hashCode()
        result = 31 * result + nazwisko.hashCode()
        result = 31 * result + email.hashCode()
        result = 31 * result + haslo.hashCode()
        result = 31 * result + role.hashCode()
        result = 31 * result + czy_zarchiwizowany.hashCode()
        result = 31 * result + aktywne.hashCode()
        result = 31 * result + nie_wygasniete.hashCode()
        result = 31 * result + nie_zablokowane.hashCode()
        result = 31 * result + nie_wygasniete_id_i_haslo.hashCode()
        result = 31 * result + kiedy_utworzono.hashCode()
        result = 31 * result + kiedy_zmodyfikowano.hashCode()
        return result
    }

}
