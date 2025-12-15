package com.hamzaazman.knowme.domain.model

data class Person(
    val id: Int = 0,
    val firstName: String,
    val lastName: String,
    val notes: String = ""
)
