package com.pal2hmnk.example.shared.presentations

import com.pal2hmnk.example.shared.exceptions.DomainException
import com.pal2hmnk.example.util.compose

class UseCaseRunner<T, U, V, W>(
    transformer: (T) -> U,
    useCase: (U) -> V,
    converter: (V) -> W,
    exceptionHandler: () -> W & Any,
) {
    val run: (T) -> W & Any = {
        try {
            transformer
                .compose(useCase)
                .compose(converter)
                .invoke(it)
                ?: throw DomainException()
        } catch (e: Exception) {
            println(e)
            exceptionHandler()
        }
    }
}
