package com.example.parking

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.activity_parking.*

class ParkingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parking)
        val firstName:String
        val carId:String
        val extras = intent.extras
        if (extras != null) {
            firstName = extras.getString("firstName").toString()
            carId=extras.getString("carID").toString()
        }

        set_alarm.setOnClickListener {

        }
        stop_park.setOnClickListener {
            
        }

    }
}
