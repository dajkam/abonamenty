package com.filip.machaj.demo.model.dane

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonManagedReference
import javax.persistence.*


@Entity
@Table(name = "marka")
@JsonInclude(JsonInclude.Include.NON_NULL)
data class Marka(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id : Long,
        @Column(columnDefinition = "varchar(36)")
        var nazwa : String,


        @OneToMany(mappedBy = "marka", cascade =  arrayOf(CascadeType.ALL))
        @JsonBackReference
        var modele :MutableList<Model> =  mutableListOf()
        ) {

    constructor():this(
            -1,
            ""

    )

    operator fun invoke(marka:Marka):Marka{
        return this
    }
}