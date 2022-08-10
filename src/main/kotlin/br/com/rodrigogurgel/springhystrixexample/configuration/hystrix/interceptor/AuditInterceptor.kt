package br.com.rodrigogurgel.springhystrixexample.configuration.hystrix.interceptor

import feign.RequestInterceptor
import feign.RequestTemplate
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class AuditInterceptor: RequestInterceptor {
    companion object {
        private val logger: Logger = LoggerFactory.getLogger(AuditInterceptor::class.java)
    }
    override fun apply(template: RequestTemplate?) {
        // TODO: 10/08/2022 Add audit implamantation 
    }
}