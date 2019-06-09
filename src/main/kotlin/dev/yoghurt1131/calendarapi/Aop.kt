package dev.yoghurt1131.calendarapi

import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component


@Aspect
@Component
class LoggingInterceptor {

    private final val logger = LoggerFactory.getLogger(this::class.java)

    @Before(value = "within(dev.yoghurt1131.calendarapi.CalendarController)")
    fun logging(joinPoint: JoinPoint) {
        logger.info("Called Endpoint. ${joinPoint.signature}")
    }

}