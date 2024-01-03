package pt.isel.daw.daw2223.t1

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock


data class HandlerInfo(val uri : String, val count : Int)


@Component
class HandlerStorage {

    companion object {
        val logger = LoggerFactory.getLogger(HandlerStorage::class.java)
    }

    private val list : MutableList<HandlerInfo> = mutableListOf()
    private val monitor = ReentrantLock()

    fun add(uri : String){
        monitor.withLock {
            val handler = list.firstOrNull { it.uri == uri }
            if(handler != null) { list.remove(handler) }
            list.add(HandlerInfo(uri, (handler?.count ?: 0) + 1))
            logger.info("Added handler with and uri $uri and count ${(handler?.count ?: 0) + 1}")
        }
    }

    fun get() : List<HandlerInfo> {
        monitor.withLock {
            return list
        }
    }

    fun getById(id : Int) : HandlerInfo? {
        monitor.withLock {
            return list.firstOrNull { it.uri.endsWith("/$id") }
        }
    }

}