package com.pal2hmnk.example.util

import java.time.LocalDate
import java.time.format.DateTimeFormatter

object DateConverter {
    private const val fmt = "yyyy-MM-dd"
    val toLocalDate: (String) -> LocalDate = {
        LocalDate.parse(it, DateTimeFormatter.ofPattern(fmt))
    }
    val toStr: (LocalDate) -> String = {
        it.format(DateTimeFormatter.ofPattern(fmt))
    }
}
