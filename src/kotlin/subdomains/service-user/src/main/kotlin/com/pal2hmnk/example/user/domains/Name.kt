package com.pal2hmnk.example.user.domains

@JvmInline
value class Name(val value: String) {
    companion object {
        fun of(value: String): Name = Name(value)
    }
}
