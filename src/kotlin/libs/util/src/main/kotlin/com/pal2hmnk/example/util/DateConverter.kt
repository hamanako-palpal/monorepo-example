package com.pal2hmnk.example.util

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object DateConverter {
    private const val fmt = "yyyy-MM-dd"
    val strToLocalDate: (String) -> LocalDate = {
        LocalDate.parse(it, DateTimeFormatter.ofPattern(fmt))
    }
    val localDateTimeToStr: (LocalDateTime) -> String = {
        it.format(DateTimeFormatter.ofPattern(fmt))
    }
}
