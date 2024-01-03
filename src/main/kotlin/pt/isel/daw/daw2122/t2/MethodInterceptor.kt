package pt.isel.daw.daw2122.t2

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.ModelAndView

data class MethodTimed(val method : String, val startTime : Long)

@Component
class MethodInterceptor(
    private val methodStorage : MethodStorage
) : HandlerInterceptor {

    private val mapTime = mutableMapOf<Int, MethodTimed>()
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {

        if(!request.requestURI.startsWith("/status")) {
            return true
        }

        val parsedUri = request.requestURI.split("/")

        if(parsedUri.size != 3) {
            return true
        }

        val method = parsedUri[2]

        val startTime = System.currentTimeMillis()
        mapTime[Thread.currentThread().id.toInt()] = MethodTimed(method, startTime)
        return true
    }

    override fun postHandle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any,
        modelAndView: ModelAndView?
    ) {
        val endTime = System.currentTimeMillis()
        val methodTimed = mapTime[Thread.currentThread().id.toInt()] ?: return
        mapTime.remove(Thread.currentThread().id.toInt())
        methodStorage.add(methodTimed.method, endTime - methodTimed.startTime)
    }
}