package com.filip.machaj.demo.repo

import com.filip.machaj.demo.model.dane.Marka
import org.springframework.data.repository.CrudRepository

interface MarkaRepo:CrudRepository<Marka,Long> {
}