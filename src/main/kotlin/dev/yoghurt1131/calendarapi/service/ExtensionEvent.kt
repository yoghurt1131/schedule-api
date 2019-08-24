import com.google.api.services.calendar.model.Event
import dev.yoghurt1131.calendarapi.service.User
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun Event.isAllDay(): Boolean {
    start.date?: run {
        return false
    }
    return start.date.isDateOnly
}

fun Event.getFrom(): LocalDateTime = if(isAllDay()) {
    LocalDate.parse(start.date.toStringRfc3339(), DateTimeFormatter.ofPattern("yyyy-MM-dd")).atStartOfDay()
} else {
    LocalDateTime.parse(start.dateTime.toStringRfc3339(), DateTimeFormatter.ISO_DATE_TIME)
}

fun Event.getTo(): LocalDateTime = if(isAllDay()) {
    LocalDate.parse(end.date.toStringRfc3339(), DateTimeFormatter.ofPattern("yyyy-MM-dd")).atTime(23, 59)
} else {
    LocalDateTime.parse(end.dateTime.toStringRfc3339(), DateTimeFormatter.ISO_DATE_TIME)
}

fun Event.getUser(): User {
    val name: String = (creator.displayName?: creator.email?: "NaN")
            .split(" ", "@")
            .first()
    return User(name)
}
