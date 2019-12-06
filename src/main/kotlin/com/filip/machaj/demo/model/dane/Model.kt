package com.filip.machaj.demo.model.dane

import com.fasterxml.jackson.annotation.*
import javax.persistence.*
import javax.validation.constraints.NotEmpty
import kotlin.jvm.Transient


@Entity
@Table(name = "model")
@JsonInclude(JsonInclude.Include.NON_NULL)

data class Model(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Int,
        @Column(columnDefinition = "varchar(36)")
        @NotEmpty
        var nazwa: String,



        @Transient
        @OneToMany(mappedBy = "model", cascade =  arrayOf(CascadeType.ALL))
        @JsonManagedReference(value = "mod-poj")
        @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator::class, property = "id")
        @JsonIdentityReference(alwaysAsId = true)
        var pojazdy:MutableList<Pojazd> = mutableListOf(),


        @ManyToOne(fetch = FetchType.EAGER, cascade =  arrayOf(CascadeType.ALL))
        @JoinColumn(name = "marka_id")
        @JsonBackReference(value = "mar-mod")
       // @JsonManagedReference(value = "mod-mar")
        @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator::class, property = "id")
        @JsonIdentityReference(alwaysAsId = true)
        var marka:Marka,

        var model_id: Int = id
        ) {

    constructor():this(
            -1,
            "",
            mutableListOf(),
            Marka()

    )

    operator fun invoke(model:Model):Model{
        return this
    }

}
