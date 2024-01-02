package pt.isel.daw.gomoku

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@SpringBootApplication
class GomokuApplication {}

@Configuration
class PipelineConfigurer(
    val interceptor: MyInterceptor,
    val myHandlerInterceptor : MyHandlerInterceptor
) : WebMvcConfigurer {

    override fun addInterceptors(registry: InterceptorRegistry) {
        //registry.addInterceptor(interceptor)
        registry.addInterceptor(myHandlerInterceptor)
    }

}

fun main(args: Array<String>) {
    runApplication<GomokuApplication>(*args)
}
