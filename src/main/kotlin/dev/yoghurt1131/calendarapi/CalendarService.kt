package dev.yoghurt1131.calendarapi

import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.Month

interface CalendarService {

    fun getSchedule(days: Int): Schedule
}

@Profile("dev")
@Service
class GoogleCalendarService : CalendarService {

    override fun getSchedule(days: Int): Schedule {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

@Profile("default", "local")
@Service
class LocalCalendarService : CalendarService {

    override fun getSchedule(days: Int): Schedule {
        val from = LocalDateTime.of(2019, Month.MAY, 6, 12, 0)
        val to = LocalDateTime.of(2019, Month.MAY, 6, 18, 0)
        return Schedule(
                arrayOf(Event("plan1", from, to, false, User("taro"), null )).toList()
        )
    }
}