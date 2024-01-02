package pt.isel.daw.gomoku.daw2223.ee

import org.springframework.stereotype.Component
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock


data class ErrorInfo(
    val receivedTimestamp : Long,
    val method : String,
    val path : String,
    val controllerName : String,
    val methodName : String
    )

@Component
class FaultyStorage {
    private val list : MutableList<ErrorInfo> = mutableListOf()
    private val monitor = ReentrantLock()

    fun add(errorInfo : ErrorInfo){
        monitor.withLock {
            list.add(errorInfo)
        }
    }

    fun get() : List<ErrorInfo> {
        monitor.withLock {
            return list
        }
    }
}
