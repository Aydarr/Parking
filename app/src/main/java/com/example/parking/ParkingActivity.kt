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
import kotlinx.android.synthetic.main.activity_parking.*
import java.util.*

class ParkingActivity : AppCompatActivity() {

    lateinit var am : AlarmManager
    lateinit var tp: TimePicker
    lateinit var update_text: TextView
    lateinit var con: Context
    lateinit var btnStart: Button
    lateinit var btnStop: Button
    lateinit var pi: PendingIntent
    var hour : Int = 0
    var min: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parking)

        this.con = this
        am = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        tp = findViewById(R.id.mytime) as TimePicker
        update_text= findViewById(R.id.update_text) as TextView
        btnStart = findViewById(R.id.set_alarm) as Button
        btnStop = findViewById(R.id.stop_park) as Button
        var calendar: Calendar = Calendar.getInstance()
        btnStart.setOnClickListener(object: View.OnClickListener {
            @SuppressLint("NewApi")
            override fun onClick(v: View?) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    calendar.set(Calendar.HOUR_OF_DAY,tp.hour)
                    calendar.set(Calendar.MINUTE,tp.minute)
                    calendar.set(Calendar.SECOND,0)
                    calendar.set(Calendar.MILLISECOND,0)
                    hour= tp.hour
                    min = tp.minute



                }

                else
                {
                    calendar.set(Calendar.HOUR_OF_DAY,tp.currentHour)
                    calendar.set(Calendar.MINUTE,tp.currentMinute)
                    calendar.set(Calendar.SECOND,0)
                    calendar.set(Calendar.MILLISECOND,0)
                    hour= tp.currentHour
                    min = tp.currentMinute
                }

                var hr_str: String = hour.toString()
                var min_str: String = min.toString()
                if(hour>12){
                    hr_str= (hour-12).toString()
                }
                if(min<10){
                    min_str= "0$min"
                }
                set_alarm_text("Парковка закончится в: $hr_str: $min_str" )
                //pi = PendingIntent.getBroadcast(this@ParkingActivity,0,PendingIntent.FLAG_UPDATE_CURRENT)
                am.setExact(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pi)



            }
        } )
        btnStop.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                set_alarm_text("Парковка прекращена")
                //pi = PendingIntent.getBroadcast(this@ParkingActivity,0,PendingIntent.FLAG_UPDATE_CURRENT)
                am.cancel(pi)
            }
        })
    }

    private fun set_alarm_text(s: String) {
        update_text.setText(s)

    }

}
