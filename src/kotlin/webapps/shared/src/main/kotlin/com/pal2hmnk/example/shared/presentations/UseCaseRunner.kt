package com.pal2hmnk.example.shared.presentations

import com.pal2hmnk.example.shared.exceptions.DomainException
import com.pal2hmnk.example.shared.utils.compose

class UseCaseRunner<T, U, V>(
    val useCase: (T) -> U,
    val converter: (U & Any) -> V & Any,
    val exceptionHandler: () -> V & Any,
) {
    private lateinit var composedRunner: () -> U

    fun initial(initializer: () -> T): UseCaseRunner<T, U, V> {
        composedRunner = compose(initializer, useCase)
        return this
    }

    fun run(): V & Any =
        try {
            val executed = composedRunner.invoke() ?: throw DomainException()
            converter.invoke(executed)
        } catch (e: Exception) {
            println(e)
            exceptionHandler()
        }
}
