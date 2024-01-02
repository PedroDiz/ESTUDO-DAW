package pt.isel.daw.daw2223.t1

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController


@RestController
class HandlersController(
    private val handlersStorage : HandlerStorage
) {

    companion object {
        const val HANDLERS_PATH = "/handlers"
        const val HANDLER = "/handler/{id}"
    }

    @GetMapping(HANDLERS_PATH)
    fun handlers() {
        handlersStorage.get()
    }

    @GetMapping(HANDLER)
    fun handler(@PathVariable id : Int) {
        handlersStorage.getById(id)
    }
}