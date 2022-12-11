package com.pal2hmnk.example.shared.utils

import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Date

object DateConverter {
    private const val fmt = "yyyy-MM-dd"
    val strToLocalDateTime: (String) -> LocalDateTime = {
        LocalDateTime.parse(it, DateTimeFormatter.ofPattern(fmt))
    }
    val localDateTimeToStr: (LocalDateTime) -> String = {
        it.format(DateTimeFormatter.ofPattern(fmt))
    }
    val localDateTimeToDate: (LocalDateTime) -> Date = {
        val zone = ZoneId.systemDefault()
        val zonedDateTime: ZonedDateTime = ZonedDateTime.of(it, zone)
        val instant = zonedDateTime.toInstant()
        Date.from(instant)
    }
}
