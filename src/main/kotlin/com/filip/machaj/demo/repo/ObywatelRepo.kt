package com.filip.machaj.demo.repo

import com.filip.machaj.demo.model.dane.Obywatel
import org.springframework.data.repository.CrudRepository

interface ObywatelRepo : CrudRepository<Obywatel, Long>  {
}