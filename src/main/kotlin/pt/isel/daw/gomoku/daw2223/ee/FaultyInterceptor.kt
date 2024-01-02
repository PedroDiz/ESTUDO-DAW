package pt.isel.daw.gomoku.daw2223.ee

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Component
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.ModelAndView
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

@Component
class FaultyInterceptor(
    private val faultyStorage : FaultyStorage
) : HandlerInterceptor {

    private val map = mutableMapOf<Int, Long>()
    private val monitor = ReentrantLock()

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        monitor.withLock {
            val threadId = Thread.currentThread().id.toInt()
            map[threadId] = System.currentTimeMillis()
            return true
        }
    }

    override fun postHandle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any,
        modelAndView: ModelAndView?
    ) {
        val uri = request.requestURI
        val httpMethod = request.method
        val status = response.status

        if(!status.toString().startsWith("5")) {
            return
        }

        val handlerMethod = handler as HandlerMethod
        val handlerName = handlerMethod.method.name
        val controllerName = handlerMethod.beanType.simpleName
        val threadId = Thread.currentThread().id.toInt()
        val initialTime = map[threadId]!!

        monitor.withLock {
            map.remove(threadId)
                faultyStorage.add(ErrorInfo(
                    receivedTimestamp = initialTime,
                    method = httpMethod,
                    path = uri,
                    controllerName = controllerName,
                    methodName = handlerName
                ))
        }

    }
}