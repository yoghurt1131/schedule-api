package dev.yoghurt1131.calendarapi.service

import com.google.api.client.util.DateTime
import com.google.api.services.calendar.Calendar
import com.google.api.services.calendar.model.Events
import dev.yoghurt1131.calendarapi.CalendarProperties
import getFrom
import getTo
import getUser
import isAllDay
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.Month
import java.time.ZoneOffset

interface CalendarService {

    fun getSchedule(days: Long): Schedule
}

@Profile("local", "dev")
@Service
class GoogleCalendarService(
    private val calendar: Calendar,
    private val properties: CalendarProperties
    ) : CalendarService {

    override fun getSchedule(days: Long): Schedule {
        val events: Events = calendar.events().list(properties.calendarId)
            .setTimeMin(DateTime(LocalDate.now().atStartOfDay().toInstant(ZoneOffset.ofHours(9)).toEpochMilli()))
            .setTimeMax(DateTime(LocalDate.now().plusDays(days).atStartOfDay().toInstant(ZoneOffset.ofHours(9)).toEpochMilli()))
            .setMaxResults(10)
            .setOrderBy("startTime")
            .setSingleEvents(true)
            .execute()
        val eventList: List<CalendarEvent> = events.items.map{
            CalendarEvent(it.summary, it.getFrom(), it.getTo(), it.isAllDay(), it.getUser(), null)
        }

        return Schedule(eventList)
    }
}

@Profile("default")
@Service
class LocalCalendarService : CalendarService {

    override fun getSchedule(days: Long): Schedule {
        val from = LocalDateTime.of(2019, Month.MAY, 6, 12, 0)
        val to = LocalDateTime.of(2019, Month.MAY, 6, 18, 0)
        return Schedule(
                arrayOf(CalendarEvent("plan1", from, to, false, User("taro"), null)).toList()
        )
    }
}


