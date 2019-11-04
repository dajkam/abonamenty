package com.filip.machaj.demo.model.dane

import com.fasterxml.jackson.annotation.*
import javax.persistence.*
import kotlin.jvm.Transient


@Entity
@Table(name = "marka")
@JsonInclude(JsonInclude.Include.NON_NULL)
data class Marka(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id : Long,
        @Column(columnDefinition = "varchar(36)")
        var nazwa : String,

        @Transient
        @OneToMany(mappedBy = "marka", cascade =  arrayOf(CascadeType.ALL))
       // @JsonManagedReference(value = "mar-mod")
       // @JsonBackReference(value = "mod-mar")
        @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator::class, property = "id")
        @JsonIdentityReference(alwaysAsId = true)
        var modele :MutableList<Model> =  mutableListOf()
        ) {

    constructor():this(
            -1,
            "",
            mutableListOf()

    )

    operator fun invoke(marka:Marka):Marka{
        return this
    }
}