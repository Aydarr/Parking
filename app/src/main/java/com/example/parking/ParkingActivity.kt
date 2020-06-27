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
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.TimePicker
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_parking.*
import java.util.*

class ParkingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parking)

        set_alarm.setOnClickListener {
            var firstName:String
            var carId:String
            val extras = getIntent().extras
            if (extras != null) {
                firstName = extras.getString("firstName").toString()
                carId = extras.getString("carId").toString()
                val db= FirebaseFirestore.getInstance()
                val parkedCar = hashMapOf(
                    "name" to firstName,
                    "time" to mytime.hour.toString()+":"+mytime.minute.toString()
                )

                db.collection("parking").document(carId)
                    .set(parkedCar as Map<String, Any>)
                    .addOnSuccessListener { Log.d("Success", "DocumentSnapshot successfully written!") }
                    .addOnFailureListener { e -> Log.w("Fail", "Error writing document", e)
                        Toast.makeText(this,"Не удалось записать", Toast.LENGTH_LONG).show()
                    }
                textView3.text=mytime.hour.toString()+":"+mytime.minute.toString();
            }
        }
        stop_park.setOnClickListener {
            var firstName:String
            var carId:String
            val extras = getIntent().extras
            if (extras != null) {
                firstName = extras.getString("firstName").toString()
                carId = extras.getString("carId").toString()
                val db= FirebaseFirestore.getInstance()
                val parkedCar = hashMapOf(
                    "name" to firstName,
                    "time" to mytime.hour.toString()+":"+mytime.minute.toString()
                )

                db.collection("parking").document(carId)
                    .delete()
                    .addOnSuccessListener { Log.d("", "DocumentSnapshot successfully deleted!") }
                    .addOnFailureListener { e -> Log.w("", "Error deleting document", e) }

                textView3.text="";
            }
        }

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toback, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {

            R.id.toback -> {
                FirebaseAuth.getInstance().signOut()
                val intent = Intent(this, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            }
        }

        return super.onOptionsItemSelected(item)
    }
}
