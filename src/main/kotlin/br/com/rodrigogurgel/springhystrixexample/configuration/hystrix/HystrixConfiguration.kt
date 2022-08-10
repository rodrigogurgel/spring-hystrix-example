package br.com.rodrigogurgel.springhystrixexample.configuration

import br.com.rodrigogurgel.springhystrixexample.client.PokemonClient
import br.com.rodrigogurgel.springhystrixexample.configuration.hystrix.interceptor.AuditInterceptor
import com.fasterxml.jackson.databind.ObjectMapper
import com.netflix.hystrix.strategy.HystrixPlugins
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategy
import feign.hystrix.HystrixFeign
import feign.jackson.JacksonDecoder
import feign.jackson.JacksonEncoder
import feign.okhttp.OkHttpClient
import java.util.concurrent.Callable
import javax.annotation.PostConstruct
import org.slf4j.MDC
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.cloud.openfeign.support.SpringMvcContract
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class HystrixConfiguration {
    @PostConstruct
    fun init() {
        HystrixPlugins.getInstance().registerConcurrencyStrategy(MdcHystrixConcurrencyStrategy())
    }

    @Bean
    fun okHttpclient(): OkHttpClient {
        val client = okhttp3.OkHttpClient.Builder().build()
        return OkHttpClient(client)
    }

    @Bean
    @Qualifier("pokemonClient")
    fun pokemonClient(okHttpClient: OkHttpClient, objectMapper: ObjectMapper): PokemonClient {
        return HystrixFeign.builder()
            .contract(SpringMvcContract())
            .encoder(JacksonEncoder(objectMapper))
            .decoder(JacksonDecoder(objectMapper))
            .requestInterceptor(AuditInterceptor())
            .target(PokemonClient::class.java, "https://pokeapi.co/api/v2/pokemon")
    }

    internal class MdcHystrixConcurrencyStrategy : HystrixConcurrencyStrategy() {
        override fun <T> wrapCallable(callable: Callable<T>): Callable<T> {
            return MdcAwareCallable(callable, MDC.getCopyOfContextMap())
        }
    }

    internal class MdcAwareCallable<T>(
        private val delegate: Callable<T>,
        private var contextMap: Map<String, String>?
    ) : Callable<T> {
        override fun call(): T {
            return try {
                MDC.setContextMap(contextMap)
                delegate.call()
            } finally {
                MDC.clear()
            }
        }

        init {
            this.contextMap = contextMap ?: mapOf()
        }
    }
}
