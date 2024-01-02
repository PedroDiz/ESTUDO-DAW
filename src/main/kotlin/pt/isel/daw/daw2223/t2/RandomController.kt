package pt.isel.daw.daw2223.t2

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class RandomController  {

    companion object {
        const val RANDOM_PATH = "/random"
    }

    @GetMapping(RANDOM_PATH)
    fun random() = Math.random()
}