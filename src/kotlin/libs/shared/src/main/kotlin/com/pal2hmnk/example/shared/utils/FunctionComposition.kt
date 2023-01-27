package com.pal2hmnk.example.shared.utils

fun <T, U, V> compose(func1: (T) -> U, func2: (U) -> V): (T) -> V = { func2(func1(it)) }
fun <T, U> compose(supplier: () -> T, func: (T) -> U): () -> U = { func(supplier.invoke()) }

