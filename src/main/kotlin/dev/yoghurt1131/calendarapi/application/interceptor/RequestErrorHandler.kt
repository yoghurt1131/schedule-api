package dev.yoghurt1131.calendarapi.application.interceptor

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@RestControllerAdvice
class RequestErrorHandler: ResponseEntityExceptionHandler() {

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handle(exception: Exception):ResponseEntity<String> {
        val logger : Logger = LoggerFactory.getLogger(javaClass)
        logger.info("Exception: ${exception}")
        return ResponseEntity("Ops!", HttpStatus.OK)
    }
}