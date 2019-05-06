package dev.yoghurt1131.calendarapi

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class CalendarController(@Qualifier("localCalendarService") val calendarService: CalendarService) {


    @GetMapping("/schedule/today")
    fun todaySchedule(): Schedule {
        val schedule = calendarService.getSchedule(1)
        return schedule
    }
}