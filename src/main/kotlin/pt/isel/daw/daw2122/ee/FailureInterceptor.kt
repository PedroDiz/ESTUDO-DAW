package pt.isel.daw.daw2122.ee

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Component
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.ModelAndView

@Component
class FailureInterceptor(
    private val failureStorage : FailureStorage
) : HandlerInterceptor{
    override fun postHandle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any,
        modelAndView: ModelAndView?
    ) {
        if(!response.status.toString().startsWith("5")) return

        val status = response.status
        val method = request.method
        val uri = request.requestURI
        val handlerMethod = handler as HandlerMethod
        val controllerName = handlerMethod.beanType.simpleName
        val methodName = handlerMethod.method.name

        failureStorage.add(failure(method, uri, status, controllerName, methodName))
    }
}