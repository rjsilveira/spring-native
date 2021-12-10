package br.com.silveira.raphael.jvm.repository

import br.com.silveira.raphael.jvm.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import java.math.BigInteger

interface UserRepository : JpaRepository<User, Long>
