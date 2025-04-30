package desafio.itau.backend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import desafio.itau.backend.config.StatisticsProperties

@SpringBootApplication
@EnableConfigurationProperties(StatisticsProperties::class)
class BackendApplication

fun main(args: Array<String>) {
    runApplication<BackendApplication>(*args)
}