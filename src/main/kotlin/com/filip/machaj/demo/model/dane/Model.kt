package com.filip.machaj.demo.model.dane

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonManagedReference
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
        var pojazdy:MutableList<Pojazd> = mutableListOf(),

        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "marka_id")
        @JsonBackReference
        var marka:Marka ? = null
        ) {

    constructor():this(
            -1,
            ""

    )

    operator fun invoke(model:Model):Model{
        return this
    }

}