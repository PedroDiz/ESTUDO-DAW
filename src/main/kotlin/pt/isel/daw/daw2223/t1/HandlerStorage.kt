package pt.isel.daw.daw2223.t1

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock


data class HandlerInfo(val id : Int, val uri : String, val count : Int)


@Component
class HandlerStorage {

    companion object {
        val logger = LoggerFactory.getLogger(HandlerStorage::class.java)
    }

    private val list : MutableList<HandlerInfo> = mutableListOf()
    private val monitor = ReentrantLock()

    fun add(id : Int, uri : String){
        monitor.withLock {
            val handler = list.firstOrNull { it.uri == uri }
            if(handler != null) {
                list.remove(handler)
            }
            list.add(HandlerInfo(id, uri, (handler?.count ?: 0) + 1))
            logger.info("Added handler with id $id and uri $uri")
        }
    }

    fun get() : List<HandlerInfo> {
        monitor.withLock {
            return list
        }
    }

    fun getById(id : Int) : HandlerInfo? {
        monitor.withLock {
            logger.info("Getting handler with id $id")
            return list.firstOrNull { it.id == id }
        }
    }
}