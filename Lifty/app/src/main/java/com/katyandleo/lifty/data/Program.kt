package com.katyandleo.lifty.data

data class Program(
    val name: String,
    val weeks: Int,
    val days: Int,
    val workouts: List<Workout>?
): java.io.Serializable