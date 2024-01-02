package pt.isel.daw

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import pt.isel.daw.daw2021.t2.MyInterceptor
import pt.isel.daw.daw2223.ee.FaultyInterceptor
import pt.isel.daw.daw2223.t1.MyHandlerInterceptor
import pt.isel.daw.daw2223.t2.RandomInterceptor

@SpringBootApplication
class Application {}

@Configuration
class PipelineConfigurer(
    val interceptor: MyInterceptor,
    val myHandlerInterceptor : MyHandlerInterceptor,
    val randomInterceptor : RandomInterceptor,
    val faultyInterceptor : FaultyInterceptor
) : WebMvcConfigurer {

    override fun addInterceptors(registry: InterceptorRegistry) {
        //registry.addInterceptor(interceptor)
        //registry.addInterceptor(myHandlerInterceptor)
        //registry.addInterceptor(randomInterceptor)
        registry.addInterceptor(faultyInterceptor)
    }

}

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}
