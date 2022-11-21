package com.example.wscubeuserdetails

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import com.airbnb.lottie.LottieAnimationView
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {

    lateinit var activity: MainActivity
    lateinit var db:FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        activity =this

        db= FirebaseFirestore.getInstance()

        val edtName = findViewById<EditText>(R.id.edtName)
        val edtPhone = findViewById<EditText>(R.id.edtPhone)
        val edtEmail = findViewById<EditText>(R.id.edtEmail)
        val edtPassword = findViewById<EditText>(R.id.edtPassword)
        val btnAdd = findViewById<AppCompatButton>(R.id.btnSignUp)
        val anim = findViewById<LottieAnimationView>(R.id.animationView)
        anim.setAnimation(R.raw.signup);
        anim.playAnimation()

        btnAdd.setOnClickListener {

            if (edtName.text.toString().trim().equals("")){
                Toast.makeText(activity, "Please enter your name ", Toast.LENGTH_SHORT).show()
            }
            else if (edtPhone.text.toString().trim().equals("")){
                Toast.makeText(activity, "Please enter your password ", Toast.LENGTH_SHORT).show()
            }
            else if (edtPhone.text.toString().trim().length<10){
                Toast.makeText(activity, "Please enter your valid phone number", Toast.LENGTH_SHORT).show()
            }

            else if (edtEmail.text.toString().trim().equals("")){
                Toast.makeText(activity, "Please enter your email", Toast.LENGTH_SHORT).show()
            }
            else if (edtPassword.text.toString().trim().equals("")){
                Toast.makeText(activity, "Please enter your password", Toast.LENGTH_SHORT).show()
            }

            else
            {

                val userName = edtName.text.toString()
                val userPhone = edtPhone.text.toString()
                val userEmail = edtEmail.text.toString()
                val userPassword = edtPassword.text.toString()

                addDataToFirestore(userName, userPhone, userEmail,userPassword);

            }

        }

    }

    private fun addDataToFirestore(userName: String, userPhone: String, userEmail: String, userPassword: String) {

        val dbUsers = db.collection("Users")
        val data = Users(userName,userPhone,userEmail,userPassword)

        dbUsers.add(data)
            .addOnSuccessListener { documentReference ->
                Toast.makeText(activity, "Account Created", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(activity, "Failed to create account", Toast.LENGTH_SHORT).show()
            }
    }
}