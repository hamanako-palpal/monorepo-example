package com.pal2hmnk.example.util

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object DateConverter {
    private const val fmt = "yyyy-MM-dd"
    val strToLocalDateTime: (String) -> LocalDateTime = {
        LocalDateTime.parse(it, DateTimeFormatter.ofPattern(fmt))
    }
    val localDateTimeToStr: (LocalDateTime) -> String = {
        it.format(DateTimeFormatter.ofPattern(fmt))
    }
}
