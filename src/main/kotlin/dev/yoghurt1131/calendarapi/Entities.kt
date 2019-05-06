package dev.yoghurt1131.calendarapi

import java.time.LocalDateTime

data class Event(
        var name: String,
        var from: LocalDateTime,
        var to: LocalDateTime,
        var isAllDay: Boolean,
        var createdBy: User,
        var place: String?
)

data class Schedule(
        var events: List<Event>
)

data class User(
        var name: String
)
