package dev.yoghurt1131.calendarapi

import org.springframework.boot.context.properties.ConfigurationProperties


@ConfigurationProperties("google.calendar")
class CalendarProperties {

    lateinit var calendarId: String
    lateinit var clientId: String
    lateinit var authUri: String
    lateinit var tokenUri: String
    lateinit var clientSecret: String
    lateinit var redirectUris: List<String>

}

