package pt.isel.daw.daw2122.t2

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@Controller
class MethodController(
    private val methodStorage : MethodStorage
) {
    companion object {
        const val METHOD_PATH = "/status/{method}"
    }
    @GetMapping(METHOD_PATH)
    fun method(@PathVariable method: String) : ResponseEntity<*> {
        val times = methodStorage.get(method)
            ?: return ResponseEntity
            .status(404)
            .body("Not found")
        return ResponseEntity
            .status(200)
            .body("Minimum time: ${times.minimumTime} Maximum time: ${times.maximumTime}")
    }
}