package pt.isel.daw.daw2122.t2

import org.springframework.stereotype.Component
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock


data class Times(val minimumTime : Long, val maximumTime : Long)
@Component
class MethodStorage {

    private val methods = mutableMapOf<String, Times>()
    private val monitor = ReentrantLock()

    fun add(method : String, time : Long) {
        monitor.withLock {
            val times = methods[method]
            if(times == null) {
                methods[method] = Times(time, time)
            } else {
                methods[method] = Times(
                    minimumTime = if(times.minimumTime < time) times.minimumTime else time,
                    maximumTime = if(times.maximumTime > time) times.maximumTime else time
                )
            }
        }
    }

    fun get(method : String) : Times? {
        monitor.withLock {
            return methods[method]
        }
    }
}
