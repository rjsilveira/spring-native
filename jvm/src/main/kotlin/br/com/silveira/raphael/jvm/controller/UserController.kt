package br.com.silveira.raphael.jvm.controller

import br.com.silveira.raphael.jvm.entity.User
import br.com.silveira.raphael.jvm.repository.UserRepository
import mu.KLogger
import mu.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserController(private val repository: UserRepository, private val logger: KLogger = KotlinLogging.logger {}) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody user: User): User {
        this.logger.info { "c=UserController, m=create, user=$user" }
        return this.repository.save(user)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long) {
        this.logger.info { "c=UserController, m=delete, id=$id" }
        this.repository.deleteById(id)
    }

    @GetMapping("/{id}")
    fun retrieve(@PathVariable id: Long): ResponseEntity<User> {
        this.logger.info { "c=UserController, m=retrieve, id=$id" }
        val user = this.repository.findById(id)
        return if (user.isPresent) {
            this.logger.info { "c=UserController, m=retrieve, user_found=${user.get()}" }
            ResponseEntity(user.get(), HttpStatus.OK)
        } else {
            this.logger.info { "c=UserController, m=retrieve, user_not_found" }
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
}
