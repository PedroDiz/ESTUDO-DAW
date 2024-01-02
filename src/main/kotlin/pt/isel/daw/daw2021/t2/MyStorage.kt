package pt.isel.daw.daw2021.t2

import org.springframework.stereotype.Component
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

@Component
class MyStorage {
    private val map : MutableMap<String, Int> = mutableMapOf()
    private val monitor = ReentrantLock()

    fun update(uri : String) {
        monitor.withLock {
            val count = map[uri] ?: 0
            map[uri] = count + 1
        }
    }

    fun get() : Map<String, Int> {
        monitor.withLock {
            return map
        }
    }
}