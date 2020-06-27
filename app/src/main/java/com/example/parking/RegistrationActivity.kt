package com.example.parking

import android.app.Activity
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_registration.*
import java.time.LocalDate

class RegistrationActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        val button: Button = findViewById(R.id.display_button)



        button.setOnClickListener {
            regaction()
        }


        val registr: TextView = findViewById(R.id.registredalready)
        registr.setOnClickListener {
            val enterintent = Intent(this, LoginActivity::class.java)
            startActivity(enterintent)
        }
    }
    @RequiresApi(Build.VERSION_CODES.O)
    private fun  regaction(){

        /*var patternCarNumber = Regex("^[А-Я|а-я]{1}[0-9]{3}[А-Я|а-я]{2}[17]{0,1}[0-9]{2}$")
        if(patternCarNumber.containsMatchIn(carId_edittext.toString()))
        {

        }
        else{Toast.makeText(this, "Проверьте правильность номера автомобиля", Toast.LENGTH_LONG).show()
            return}*/


        val firstNameEditText: EditText = findViewById(R.id.username_edittext)
        val emailEditText: EditText = findViewById(R.id.email_edittext)
        val carIdEditText: EditText = findViewById(R.id.carId_edittext)
        val calendar = LocalDate.now()
        val passwordreg: EditText = findViewById(R.id.password_editText)
        val firstUserName = firstNameEditText.getText().toString()
        val lastUserName = emailEditText.getText().toString()
        val ageUserName = carIdEditText.getText().toString()
        val passwordcheck = password_editText.getText().toString()
        val password_again= passwor_again.getText().toString()





        if(passwordcheck == password_again)
        {

        }
        else{
            Toast.makeText(this, "Пароли не совпадают", Toast.LENGTH_LONG).show()
            return

        }

        Log.d("RegistrationActivity", "EmAil is:" + lastUserName )
        Log.d("RegistrationActivity","PAssword is:  $passwordcheck")
        when {
            TextUtils.isEmpty(firstUserName) -> {
                firstNameEditText.setError("Заполните поле!")
                return

            }
            TextUtils.isEmpty(lastUserName) -> {
                emailEditText.setError("Заполните поле!")
                return
            }
            TextUtils.isEmpty(ageUserName) -> {
                carIdEditText.setError("Заполните поле")
                return
            }
            TextUtils.isEmpty(passwordcheck) -> {
                passwordreg.setError("Заполните поле")
                return
            }

            else -> {



            }

        }


        FirebaseAuth.getInstance().createUserWithEmailAndPassword(lastUserName, passwordcheck)
            .addOnCompleteListener {
                if (!it.isSuccessful) return@addOnCompleteListener

                // else if successful
                Log.d("Main ", "Successfully created user with uid: ${it.result!!.user?.uid}")
                val person: Person = Person(
                    firstNameEditText.text.toString(), emailEditText.text.toString(),
                    carIdEditText.text.toString(), calendar)
                val intent = Intent(this, MainActivity::class.java)
                //intent.putExtra("person", person)
                intent.putExtra("firstName",firstNameEditText.text.toString() )
                intent.putExtra("carId", carIdEditText.text.toString())
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            }
            .addOnFailureListener {
                Log.d("Main", "Регистарция не удалась: ${it.message}")
                //loading_view.visibility = View.GONE
                //already_have_account_text_view.visibility = View.VISIBLE
                Toast.makeText(this, "${it.message}", Toast.LENGTH_LONG).show()
            }



        //    saveUser()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==0 && resultCode== Activity.RESULT_OK && data!=null){
            //check selected photo
            Log.d("RegistrationQctivity", "Photo was selected!!!!" )
            var uri = data.data
            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, uri)
            val bitmapdarawable  = BitmapDrawable(bitmap)

        }
    }

}
