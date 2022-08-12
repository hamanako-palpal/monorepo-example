package com.pal2hmnk.example.util

import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Date

object DateConverter {
    private const val fmt = "yyyy-MM-dd"
    val strToLocalDate: (String) -> LocalDate = {
        LocalDate.parse(it, DateTimeFormatter.ofPattern(fmt))
    }
    val localDateToStr: (LocalDate) -> String = {
        it.format(DateTimeFormatter.ofPattern(fmt))
    }
    val dateToLocalDate: (Date) -> LocalDate = {
        it.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
    }
}
