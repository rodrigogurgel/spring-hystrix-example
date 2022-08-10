package br.com.rodrigogurgel.springhystrixexample

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
class SpringHystrixExampleApplication

fun main(args: Array<String>) {
	runApplication<SpringHystrixExampleApplication>(*args)
}
