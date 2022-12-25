package com.pal2hmnk.example.shared.presentations

import com.pal2hmnk.example.shared.exceptions.DomainException
import com.pal2hmnk.example.shared.utils.compose

class UseCaseRunner<T, U, V, W>(
    transformer: (T) -> U,
    useCase: (U) -> V,
    converter: (V & Any) -> W & Any,
    exceptionHandler: () -> W & Any,
) {
    private val composedRunner = transformer.compose(useCase)

    val run: (T) -> W & Any = {
        try {
            val executed = composedRunner
                .invoke(it)
                ?: throw DomainException()
            converter.invoke(executed)
        } catch (e: Exception) {
            println(e)
            exceptionHandler()
        }
    }
}
