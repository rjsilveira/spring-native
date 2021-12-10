package br.com.silveira.raphael.jvm

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class JvmApplication

fun main(args: Array<String>) {
    runApplication<JvmApplication>(*args)
}
