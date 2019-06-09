package dev.yoghurt1131.calendarapi

import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component


@Aspect
@Component
class LoggingInterceptor {

    private val logger = LoggerFactory.getLogger(javaClass)

    @Before(value = "within(dev.yoghurt1131.calendarapi.CalendarController)")
    fun logging(joinPoint: JoinPoint) {
        logger.info("Called Endpoint. ${joinPoint.signature}")
    }

}