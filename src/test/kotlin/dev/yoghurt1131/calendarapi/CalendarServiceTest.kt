package dev.yoghurt1131.calendarapi

import com.google.api.client.util.DateTime
import com.google.api.services.calendar.model.Event
import com.google.api.services.calendar.model.EventDateTime
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class CalendarServiceTest {

    @Test
    fun `When Event is dateOnly then return true`() {
        assertTrue(dateEvent.isAllDay())
    }

    @Test
    fun `When Event is not dateOnly then return false`() {
        assertFalse(dateTimeEvent.isAllDay())
    }

    @Test
    fun `Event can get 'from date' as expected`() {
        assertThat(dateEvent.getFrom()).isEqualTo("2019-04-01T00:00:00")
    }

    @Test
    fun `Event can get 'from date time' as expected`() {
        assertThat(dateTimeEvent.getFrom()).isEqualTo("2019-04-01T10:00:00")
    }

    @Test
    fun `Event can get 'to date' as expected`() {
        assertThat(dateEvent.getTo()).isEqualTo("2019-04-03T23:59:00")
    }

    @Test
    fun `Event can get 'to date time' as expected`() {
        assertThat(dateTimeEvent.getTo()).isEqualTo("2019-04-01T17:00:00")
    }

    @Test
    fun `event returns displayName if exists`() {
        // setup
        val event = Event().setCreator(Event.Creator().setDisplayName("taro").setEmail("mail@gmailcom"))
        assertThat(event.getUser().name).isEqualTo("taro")
    }

    @Test
    fun `event returns email if displayName does not exists`() {
        // setup
        val event = Event().setCreator(Event.Creator().setEmail("mail@gmailcom"))
        assertThat(event.getUser().name).isEqualTo("mail")
    }

    @Test
    fun `event returns 'NaN' if neither displayName nor email exists`() {
        // setup
        val event = Event().setCreator(Event.Creator())
        assertThat(event.getUser().name).isEqualTo("NaN")
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
