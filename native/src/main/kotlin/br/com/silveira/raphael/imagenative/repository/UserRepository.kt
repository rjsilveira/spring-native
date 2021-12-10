package br.com.silveira.raphael.imagenative.repository

import br.com.silveira.raphael.imagenative.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long>
