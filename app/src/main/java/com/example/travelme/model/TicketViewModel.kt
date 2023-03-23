package com.example.travelme.model

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.travelme.data.DataSource
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit


@RequiresApi(Build.VERSION_CODES.O)
class TicketViewModel : ViewModel() {

    //список городов
    val cityNames = DataSource.cityNames

    //текущий билет, необходим для правильного открытия при выборе билета
    private var _currentTicket: MutableLiveData<TicketsItem> = MutableLiveData<TicketsItem>()
    val currentTicket: LiveData<TicketsItem>
        get() = _currentTicket

    //массив данных билетов
    private var _ticketsData: ArrayList<TicketsItem> = ArrayList()
    val ticketsData: ArrayList<TicketsItem>
        get() = _ticketsData

    //индекс города вылета
    private val _departureCity = MutableLiveData(0)
    val departureCity: LiveData<Int> = _departureCity

    //индекс города прибытия
    private val _arrivalCity = MutableLiveData(1)
    val arrivalCity: LiveData<Int> = _arrivalCity

    init {
        //устаналиваем по умолчанию город вылета Москва город прибылтия Питер
        _departureCity.value = 0
        _arrivalCity.value = 1

        //получаем данные билетов
        _ticketsData = DataSource.getTicketsData()

        //выставляем по умолчанию первый билет
        _currentTicket.value = _ticketsData[0]
    }

    //выбираем город
    fun chooseCity(index: Int, departure: Boolean = true){
        if (departure) {
            _departureCity.value = index
            Log.i("город", "Город отправления ${cityNames[departureCity.value!!]}")
        }
        else{
            _arrivalCity.value = index
            Log.i("город", "Город прибытия ${cityNames[arrivalCity.value!!]}")
        }
    }


    //метод для получения фиксированной даты завтрашнего дня
    @RequiresApi(Build.VERSION_CODES.O)
    fun getData(): String {
        val tomorrow = LocalDate.now().plus(1, ChronoUnit.DAYS)
        return tomorrow.format(DateTimeFormatter.ofPattern("dd.MM.yyy"))
    }

    //обновляем текущий выбранный билет
    fun updateCurrentTicket(ticket: TicketsItem) {
        _currentTicket.value = ticket
    }
}