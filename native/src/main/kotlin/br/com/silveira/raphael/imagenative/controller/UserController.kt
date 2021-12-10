package br.com.silveira.raphael.imagenative.controller

import br.com.silveira.raphael.imagenative.entity.User
import br.com.silveira.raphael.imagenative.repository.UserRepository
import mu.KLogger
import mu.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserController(private val repository: UserRepository) {

    companion object {
        private val logger: KLogger = KotlinLogging.logger {}
    }

    @PostMapping
    fun create(@RequestBody user: User): User {
        logger.info { "c=UserController, m=create, user=$user" }
        return this.repository.save(user)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long) {
        logger.info { "c=UserController, m=delete, id=$id" }
        this.repository.deleteById(id)
    }

    @GetMapping("/{id}")
    fun retrieve(@PathVariable id: Long): ResponseEntity<User> {
        logger.info { "c=UserController, m=retrieve, id=$id" }
        val user = this.repository.findById(id)
        return if (user.isPresent) {
            logger.info { "c=UserController, m=retrieve, user_found=${user.get()}" }
            ResponseEntity(user.get(), HttpStatus.OK)
        } else {
            logger.info { "c=UserController, m=retrieve, user_not_found" }
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
}
