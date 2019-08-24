package dev.yoghurt1131.calendarapi.application.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential
import dev.yoghurt1131.calendarapi.service.CalendarEvent
import dev.yoghurt1131.calendarapi.service.CalendarService
import dev.yoghurt1131.calendarapi.service.Schedule
import dev.yoghurt1131.calendarapi.service.User
import io.kotlintest.matchers.exactly
import io.kotlintest.specs.AnnotationSpec
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import java.time.LocalDateTime

class CalendarControllerTest : AnnotationSpec() {

    @MockK
    lateinit var googleCredential: GoogleCredential

    @MockK
    lateinit var googleClientSecrets: GoogleClientSecrets

    @MockK
    lateinit var googleAuthorizationCodeFlow: GoogleAuthorizationCodeFlow

    @MockK
    lateinit var calendarService: CalendarService

    @InjectMockKs
    lateinit var target: CalendarController

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true)
    }

    @Test
    fun init() {
        target = CalendarController(googleCredential, googleClientSecrets, googleAuthorizationCodeFlow, calendarService)
        val mockMvc: MockMvc = MockMvcBuilders.standaloneSetup(target).build()
        val events = listOf(CalendarEvent(
                "dummy",
                LocalDateTime.of(2019, 1, 1, 12, 0, 0),
                LocalDateTime.of(2019, 1, 1, 18, 0, 0),
                false,
                User("taro"),
                "Tokyo"
        ))
        val schedule = Schedule(events)

        // setup mock
        every { calendarService.getSchedule(1L) } returns schedule

        // execute
        val result = mockMvc.perform(get("/schedule/daily")).andExpect(status().isOk()).andReturn()

        // verify
        verify(exactly = 1) { calendarService.getSchedule(1L) }
    }
}