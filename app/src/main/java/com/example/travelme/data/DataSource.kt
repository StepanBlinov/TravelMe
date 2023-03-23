package com.example.travelme.data

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.travelme.R
import com.example.travelme.model.TicketsItem
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

object DataSource {

    //статический список городов
    val cityNames = listOf<String>("Москва", "Санкт-Петербург", "Екатеринбург", "Саратов")

    //статический данные о билетах
    @RequiresApi(Build.VERSION_CODES.O)
    fun getTicketsData(): ArrayList<TicketsItem> {
        return arrayListOf(
            TicketsItem(
                1,
                R.drawable.pobeda_icon,
                cityNames[0],
                cityNames[1],
                getData(),
                2,
            ),
            TicketsItem(
                2,
                R.drawable.pobeda_icon,
                cityNames[0],
                cityNames[1],
                getData(),
                4,
            ),
            TicketsItem(
                3,
                R.drawable.pobeda_icon,
                cityNames[0],
                cityNames[1],
                getData(),
                9,
            ),
            TicketsItem(
                4,
                R.drawable.pobeda_icon,
                cityNames[0],
                cityNames[1],
                getData(),
                7,
            ),
            TicketsItem(
                5,
                R.drawable.pobeda_icon,
                cityNames[0],
                cityNames[1],
                getData(),
                6,
            ),
            TicketsItem(
                6,
                R.drawable.pobeda_icon,
                cityNames[0],
                cityNames[1],
                getData(),
                16,
            ),
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getData(): String {
        val tomorrow = LocalDate.now().plus(1, ChronoUnit.DAYS)
        return tomorrow.format(DateTimeFormatter.ofPattern("dd.MM.yyy"))
    }
}