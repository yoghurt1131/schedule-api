package dev.yoghurt1131.calendarapi

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.ServletException

// 疎通確認用Controller
@RestController
class PingController {

    @GetMapping("/ping")
    fun ping() = "Connected."

    @GetMapping("/pong")
    fun pong() {
        throw ServletException("hoge")
    }
}
