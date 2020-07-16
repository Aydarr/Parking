package com.example.parking

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val extras = getIntent().extras
        button3.setOnClickListener {
            /*if (extras != null){
                var firstName = extras.getString("FIO")
                var time = extras.getString("time")
                textView2.text="Статус парковки: припаркован до"+time+"\n Номер телефона: +79273954718 \n Имя: "+firstName
            }*/
            textView2.text="Статус парковки: припаркован до 18:15 \n Номер телефона: +79273971265 \n Имя: Смирнов Сергей Павлович"
        }С123ВА72
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toback, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {

            R.id.toback -> {
                val intent = Intent(this, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            }
        }

        return super.onOptionsItemSelected(item)
    }

}
