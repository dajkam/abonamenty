package com.filip.machaj.demo.model.dane

import com.fasterxml.jackson.annotation.*
import javax.persistence.*


@Entity
@Table(name = "model")
@JsonInclude(JsonInclude.Include.NON_NULL)

data class Model(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long,
        @Column(columnDefinition = "varchar(36)")
        var nazwa: String,



        @OneToMany(mappedBy = "model", cascade =  arrayOf(CascadeType.ALL))
        @JsonManagedReference
        @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator::class, property = "id")
        @JsonIdentityReference(alwaysAsId = true)
        var pojazdy:MutableList<Pojazd> = mutableListOf(),

        @ManyToOne(fetch = FetchType.EAGER, cascade =  arrayOf(CascadeType.ALL))
        @JoinColumn(name = "marka_id")
        @JsonBackReference
        @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator::class, property = "id")
        @JsonIdentityReference(alwaysAsId = true)
        var marka:Marka
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