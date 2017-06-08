package io.github.budincsevity.OpenSubtitlesAPI

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class OpenSubtitlesApiApplication

fun main(args: Array<String>) {
    SpringApplication.run(OpenSubtitlesApiApplication::class.java, *args)
}
