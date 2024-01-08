package pt.isel.daw.daw2122.t2

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.ModelAndView

@Component
class MethodInterceptor(
    private val methodStorage : MethodStorage
) : HandlerInterceptor {
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {

        if(!request.requestURI.startsWith("/status")) {
            return true
        }

        val parsedUri = request.requestURI.split("/")

        if(parsedUri.size != 3) {
            return true
        }

        val method = parsedUri[2]
        request.setAttribute("method", method)

        val startTime = System.currentTimeMillis()
        request.setAttribute("startTime", startTime)

        return true
    }

    override fun postHandle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any,
        modelAndView: ModelAndView?
    ) {
        val endTime = System.currentTimeMillis()
        val startTime = request.getAttribute("startTime") as Long
        val method = request.getAttribute("method") as String
        methodStorage.add(method, endTime - startTime)
    }
}