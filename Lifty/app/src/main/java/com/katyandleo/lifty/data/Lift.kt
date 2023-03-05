package com.katyandleo.lifty.data

data class Lift(
    val name: String,
    val reps: Int,
    val sets: Int,
    val rpe: Int,
    val weight: Float,
    val actualRpe: Int?,
    val actualWeight: Float?,
    val notes: String?
)
