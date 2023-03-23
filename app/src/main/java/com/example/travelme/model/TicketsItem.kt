package com.example.travelme.model

import androidx.annotation.DrawableRes

//параметры билета
data class TicketsItem(
    val id: Int,
    @DrawableRes val iconAvia: Int,
    val departureCity: String,
    val arrivalCity: String,
    val departureDate: String,
    val flightTime: Int,
)
