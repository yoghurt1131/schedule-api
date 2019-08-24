package dev.yoghurt1131.calendarapi.service

import com.google.api.client.util.DateTime
import com.google.api.services.calendar.model.Event
import com.google.api.services.calendar.model.EventDateTime
import getFrom
import getTo
import getUser
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import isAllDay
import java.time.LocalDateTime


class ExtensionEventTest : StringSpec (){
    init {
        "Event.isAllDay() shourd return true" {
            dateEvent.isAllDay() shouldBe true
        }

        "Event.isAllDay() should return false" {
            dateTimeEvent.isAllDay() shouldBe false
        }

        "Event.getFrom() should return '2019-04-01T00:00:00'" {
            dateEvent.getFrom() shouldBe LocalDateTime.of(2019, 4, 1, 0, 0, 0)
        }

        "Event.getFrom() should return '2019-04-01T10:00:00'" {
            dateTimeEvent.getFrom() shouldBe LocalDateTime.of(2019, 4, 1, 10, 0, 0)
        }

        "Event.getTo() should return '2019-04-03T23:59:59'" {
            dateEvent.getTo() shouldBe LocalDateTime.of(2019, 4, 3, 23, 59, 59)
        }

        "Event.getTo() should return '2019-04-01T17:00:0'" {
            dateTimeEvent.getTo() shouldBe LocalDateTime.of(2019, 4, 1, 17, 0, 0)
        }

        "Event.getUser().name should return 'taro'" {
            // setup
            val event = Event().setCreator(Event.Creator().setDisplayName("taro").setEmail("mail@gmailcom"))
            event.getUser().name shouldBe "taro"
        }

        "Event.getUser().name should return 'mail'" {
            // setup
            val event = Event().setCreator(Event.Creator().setEmail("mail@gmailcom"))
            event.getUser().name shouldBe "mail"
        }

        "Event.getUser().name should return 'NaN" {
            // setup
            val event = Event().setCreator(Event.Creator())
            event.getUser().name shouldBe "NaN"
        }
    }

    // setup date event
    private val dateEvent = Event().apply {
        start = EventDateTime().apply {
            date = DateTime("2019-04-01")
        }
        end = EventDateTime().apply {
            date = DateTime("2019-04-03")
        }
    }

    // setup date time event
    private val dateTimeEvent = Event().apply {
        start = EventDateTime().apply {
            dateTime = DateTime("2019-04-01T10:00:00+09:00")
        }
        end = EventDateTime().apply {
            dateTime = DateTime("2019-04-01T17:00:00+09:00")
        }
    }

}
