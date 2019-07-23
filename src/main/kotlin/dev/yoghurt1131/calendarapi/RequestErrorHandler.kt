package dev.yoghurt1131.calendarapi

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import javax.servlet.ServletException

@RestControllerAdvice
class RequestErrorHandler: ResponseEntityExceptionHandler() {

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    final fun handle(exception: Exception):ResponseEntity<String> {
        return ResponseEntity("Ops!", HttpStatus.OK)
    }
}