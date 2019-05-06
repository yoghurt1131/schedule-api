package dev.yoghurt1131.calendarapi

import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.Month
import java.util.*

interface CalendarService {

    fun getSchedule(days: Int): Schedule
}

@Service("googleCalendarService")
class GoogleCalendarService : CalendarService {

    override fun getSchedule(days: Int): Schedule {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

@Service("localCalendarService")
class LocalCalendarService : CalendarService {

    override fun getSchedule(days: Int): Schedule {
        val from = LocalDateTime.of(2019, Month.MAY, 6, 12, 0)
        val to = LocalDateTime.of(2019, Month.MAY, 6, 18, 0)
        return Schedule(
                arrayOf(Event("plan1", from, to, false, User("taro"), null )).toList()
        )
    }
}