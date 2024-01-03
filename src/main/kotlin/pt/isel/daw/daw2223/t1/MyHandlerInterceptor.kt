package pt.isel.daw.daw2223.t1

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Component
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.HandlerInterceptor

@Component
class MyHandlerInterceptor(
    private val handlersStorage : HandlerStorage
) : HandlerInterceptor {

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val handlerMethod = handler as HandlerMethod
        if(handlerMethod.methodParameters.firstOrNull { it.parameterName == "id" } == null && !request.requestURI.startsWith("/handler/")) {
            return true
        }

        handlersStorage.add(request.requestURI)
        return true
    }
}