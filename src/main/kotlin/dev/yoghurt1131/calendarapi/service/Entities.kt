package dev.yoghurt1131.calendarapi.service

import java.time.LocalDateTime

data class CalendarEvent(
        var name: String,
        var from: LocalDateTime,
        var to: LocalDateTime,
        var isAllDay: Boolean,
        var createdBy: User,
        var place: String?
)

data class Schedule(
        var events: List<CalendarEvent>
)

data class User(
        var name: String
)
