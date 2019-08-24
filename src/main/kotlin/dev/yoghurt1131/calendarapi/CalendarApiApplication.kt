package dev.yoghurt1131.calendarapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(CalendarProperties::class, HeaderCheckProperties::class)
class CalendarApiApplication
fun main(args: Array<String>) {
	runApplication<CalendarApiApplication>(*args)
}
