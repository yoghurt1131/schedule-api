package dev.yoghurt1131.calendarapi

import junit.framework.TestCase.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class CalendarApiApplicationTests {

	@Test
	fun contextLoads() {
		// Only check application has started.
		assertTrue(true)
	}

}
