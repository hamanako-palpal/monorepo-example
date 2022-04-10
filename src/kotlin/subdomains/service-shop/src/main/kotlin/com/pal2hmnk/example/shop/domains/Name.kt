package com.pal2hmnk.example.shop.domains

@JvmInline
value class Name(val value: String) {
    companion object {
        fun of(name: String?) = Name(name!!)
    }
}
