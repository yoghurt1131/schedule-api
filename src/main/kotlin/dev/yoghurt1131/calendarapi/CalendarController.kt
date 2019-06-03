package dev.yoghurt1131.calendarapi

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class CalendarController(private val calendarService: CalendarService) {


    @GetMapping("/schedule/daily")
    fun todaySchedule(): Schedule {
        val schedule = calendarService.getSchedule(1)
        return schedule
    }

    @GetMapping("/schedule/weekly")
    fun weeklySchedule(): Schedule {
        val schedule = calendarService.getSchedule(7)
        return schedule
    }
}