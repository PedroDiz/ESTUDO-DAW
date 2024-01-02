package pt.isel.daw.daw2223.ee

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class FaultyController(
 private val faultyStorage : FaultyStorage
) {

    companion object {
        const val ERRORS_PATH = "/errors"
        const val FAULTY_PATH = "/faulty"
    }

    @GetMapping(ERRORS_PATH)
    fun errors() =
        faultyStorage
            .get()
            .filter { System.currentTimeMillis() - it.receivedTimestamp < 600_000 }

    @GetMapping(FAULTY_PATH)
    fun faulty() : ResponseEntity<*> {
        return ResponseEntity
            .status(500)
            .body("faulty")
    }
}