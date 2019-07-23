package dev.yoghurt1131.calendarapi

import org.springframework.boot.web.servlet.error.ErrorController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ApiErrorController : ErrorController {
    @RequestMapping("/error")
    fun handleError(): String {
        return "Endpoint Not Found!"
    }

    override fun getErrorPath(): String {
        return "/error";
    }

}