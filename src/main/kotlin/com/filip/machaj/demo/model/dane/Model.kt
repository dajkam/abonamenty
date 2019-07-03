package com.filip.machaj.demo.model.dane

import com.fasterxml.jackson.annotation.JsonInclude
import javax.persistence.*


@Entity
@Table(name = "model")
@JsonInclude(JsonInclude.Include.NON_NULL)

data class Model(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long,
        @Column(columnDefinition = "varchar(36)")
        var nazwa: String,



        @OneToMany(mappedBy = "model", cascade =  arrayOf(CascadeType.ALL))
        var pojazdy:Set<Pojazd> = emptySet(),

        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "marka_id")
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