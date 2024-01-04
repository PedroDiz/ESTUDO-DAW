package pt.isel.daw.daw2122.ee

import org.springframework.stereotype.Component
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock


data class failure(
    val method : String,
    val uri : String,
    val status : Int,
    val controllerName : String,
    val handlerName : String
    )

@Component
class FailureStorage {

    private val list : MutableList<failure> = mutableListOf()
    private val monitor = ReentrantLock()

    fun add(failure : failure) {
        monitor.withLock {
            list.add(failure)
        }
    }

    fun get() : List<failure> {
        monitor.withLock {
            return list.takeLast(10)
        }
    }

}
