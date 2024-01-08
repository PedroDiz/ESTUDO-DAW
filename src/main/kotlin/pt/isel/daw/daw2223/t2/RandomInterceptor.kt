package pt.isel.daw.daw2223.t2

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.lang.Nullable
import org.springframework.stereotype.Component
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.ModelAndView

@Component
class RandomInterceptor : HandlerInterceptor {

    companion object {
        private val logger = LoggerFactory.getLogger(RandomInterceptor::class.java)
    }

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        request.setAttribute("startTime", System.currentTimeMillis())
        return true
    }

    override fun postHandle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any,
        @Nullable modelAndView: ModelAndView?
    ) {
        val uri = request.requestURI
        val httpMethod = request.method
        val status = response.status

        if(handler is HandlerMethod) {
            val handlerIdentifier = handler.shortLogMessage
            val threadId = Thread.currentThread().id.toInt()
            val currentTime = System.currentTimeMillis()
            val startTime = request.getAttribute("startTime") as Long
            val time = currentTime - startTime
                logger.info("Request to $uri with method $httpMethod and status $status handled by $handlerIdentifier in $time ms" +
                        " on thread $threadId")
            }
        }
    }