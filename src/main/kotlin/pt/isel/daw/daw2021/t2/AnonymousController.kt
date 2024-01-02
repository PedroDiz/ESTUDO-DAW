package pt.isel.daw.daw2021.t2

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class AnonymousController(
    private val storage : MyStorage
) {

    companion object {
        const val ANONYMOUS_PATH = "/anonymous"
        const val DONOTHING_PATH = "/donothing"
    }

    @GetMapping(ANONYMOUS_PATH)
    fun anonymous() = storage.get()

    @GetMapping(DONOTHING_PATH)
    fun doNothing() : String {
        return "blyat"
    }
}