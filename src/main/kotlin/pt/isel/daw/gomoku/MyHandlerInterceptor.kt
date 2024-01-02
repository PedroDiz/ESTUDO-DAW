package pt.isel.daw.gomoku

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.HandlerInterceptor

@Component
class MyHandlerInterceptor(
    private val handlersStorage : HandlerStorage
) : HandlerInterceptor {

    companion object {
        val logger = LoggerFactory.getLogger(MyHandlerInterceptor::class.java)
    }

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val handlerMethod = handler as HandlerMethod
        val idParameter = handlerMethod.methodParameters
            .firstOrNull { it.parameterName == "id" } ?: return true

        val idValue = request.getAttribute(idParameter.parameterName) as String?
        logger.info("Adding handler with id $idValue and uri ${request.requestURI}")
        val id = idValue?.toIntOrNull() ?: return true
        logger.info("Adding handler with id $id and uri ${request.requestURI}")
        handlersStorage.add(id, request.requestURI)
        return true
    }
}