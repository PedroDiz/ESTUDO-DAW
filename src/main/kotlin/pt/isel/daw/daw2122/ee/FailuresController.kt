package pt.isel.daw.daw2122.ee

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class FailuresController(
    private val failuresStorage : FailureStorage
) {
    companion object {
        const val FAILURES_PATH = "/failures"
        const val FAILURE_PATH = "/failure"
        const val ANOTHER_FAILURE_PATH = "/anotherfailure"
    }

    @GetMapping(FAILURES_PATH)
    fun failures() = failuresStorage.get()

    @GetMapping(FAILURE_PATH)
    fun failure() = ResponseEntity.status(500).body("failure")

    @GetMapping(ANOTHER_FAILURE_PATH)
    fun anotherfailure() = ResponseEntity.status(502).body("anotherfailure")


}