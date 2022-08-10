package br.com.rodrigogurgel.springhystrixexample.configuration.interceptor

import java.util.UUID
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import org.slf4j.MDC
import org.springframework.web.servlet.HandlerInterceptor


class RequestIdInterceptor : HandlerInterceptor {
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val requestId = getRequestId(request)
        MDC.put("request_id", requestId)
        response.setHeader("X-Request-ID", requestId)
        return true
    }

    override fun afterCompletion(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any,
        ex: Exception?
    ) {
        MDC.clear()
    }

    private fun getRequestId(request: HttpServletRequest) =
        request.getHeader("X-Request-ID") ?: UUID.randomUUID().toString()
}