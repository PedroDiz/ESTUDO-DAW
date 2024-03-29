package pt.isel.daw

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import pt.isel.daw.daw2021.t2.MyInterceptor
import pt.isel.daw.daw2122.ee.FailureInterceptor
import pt.isel.daw.daw2122.t2.MethodInterceptor
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
    val faultyInterceptor : FaultyInterceptor,
    val methodInterceptor: MethodInterceptor,
    val failureInterceptor: FailureInterceptor
) : WebMvcConfigurer {

    override fun addInterceptors(registry: InterceptorRegistry) {
        //registry.addInterceptor(interceptor)
        //registry.addInterceptor(myHandlerInterceptor)
        //registry.addInterceptor(randomInterceptor)
        //registry.addInterceptor(faultyInterceptor)
        //registry.addInterceptor(methodInterceptor)
        registry.addInterceptor(failureInterceptor)
    }

}

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}
