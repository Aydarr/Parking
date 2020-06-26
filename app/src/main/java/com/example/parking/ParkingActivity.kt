package com.example.parking

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.app.SearchManager
import android.app.UiAutomation
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CancellationSignal
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.TimePicker
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_parking.*
import java.util.*

class ParkingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parking)

        set_alarm.setOnClickListener {

        }
        stop_park.setOnClickListener {
            
        }

    }
}
