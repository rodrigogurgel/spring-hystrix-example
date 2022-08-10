package br.com.rodrigogurgel.springhystrixexample.configuration

import br.com.rodrigogurgel.springhystrixexample.configuration.interceptor.RequestIdInterceptor
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebMvcConfiguration : WebMvcConfigurer {
    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(RequestIdInterceptor())
    }
}