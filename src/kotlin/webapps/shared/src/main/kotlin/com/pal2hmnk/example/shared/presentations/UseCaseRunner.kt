package com.pal2hmnk.example.shared.presentations

import com.pal2hmnk.example.util.compose

class UseCaseRunner<T, U, V, W>(
    transformer: (T) -> U,
    useCase: (U) -> V,
    converter: (V) -> W,
    exceptionHandler: () -> W
) {
    private val composite: (T) -> W = {
        try {
            transformer.compose(useCase).compose(converter).invoke(it)
        } catch (e: Exception) {
            println(e)
            exceptionHandler()
        }
    }

    fun run(input: T): W = composite.invoke(input)
}