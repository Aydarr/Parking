package com.example.parking

import java.io.Serializable
import java.time.LocalDate

data class Person(
    val firstName: String,
    val lastName:String,
    var carId:String,
    val startDate: LocalDate
): Serializable {


}