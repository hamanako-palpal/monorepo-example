package com.pal2hmnk.example.domain.shop.entities

@JvmInline
value class Name(val value: String) {
    companion object {
        fun of(name: String?) = Name(name!!)
    }
}
