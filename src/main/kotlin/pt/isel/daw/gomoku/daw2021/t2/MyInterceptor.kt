package pt.isel.daw.gomoku.daw2021.t2

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor

@Component
class MyInterceptor(
    private val storage : MyStorage
) : HandlerInterceptor {

    companion object {
        const val AUTHENTICATION_HEADER = "Authorization"
    }

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {

        if(request.cookies == null && request.getHeader(AUTHENTICATION_HEADER) == null) {
            storage.update(request.requestURI)
        }
        return true
    }
}