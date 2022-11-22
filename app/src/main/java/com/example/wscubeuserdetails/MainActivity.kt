package com.example.wscubeuserdetails

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import com.airbnb.lottie.LottieAnimationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    lateinit var activity: MainActivity
    lateinit var db:FirebaseFirestore
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        activity =this

        auth = Firebase.auth


       /* val edtName = findViewById<EditText>(R.id.edtName)
        val edtPhone = findViewById<EditText>(R.id.edtPhone)*/
        val edtEmail = findViewById<EditText>(R.id.edtEmail)
        val edtPassword = findViewById<EditText>(R.id.edtPassword)
        val btnAdd = findViewById<AppCompatButton>(R.id.btnSignUp)
        val anim = findViewById<LottieAnimationView>(R.id.animationView)
        anim.setAnimation(R.raw.signup);
        anim.playAnimation()

        findViewById<TextView>(R.id.txtLogin).setOnClickListener {
            startActivity(Intent(activity,LoginActivity::class.java))
        }

        btnAdd.setOnClickListener {

           /* if (edtName.text.toString().trim().equals("")){
                Toast.makeText(activity, "Please enter your name ", Toast.LENGTH_SHORT).show()
            }
            else if (edtPhone.text.toString().trim().equals("")){
                Toast.makeText(activity, "Please enter your password ", Toast.LENGTH_SHORT).show()
            }
            else if (edtPhone.text.toString().trim().length<10){
                Toast.makeText(activity, "Please enter your valid phone number", Toast.LENGTH_SHORT).show()
            }*/

            if (edtEmail.text.toString().trim().equals("")){
                Toast.makeText(activity, "Please enter your email", Toast.LENGTH_SHORT).show()
            }
            else if (edtPassword.text.toString().trim().equals("")){
                Toast.makeText(activity, "Please enter your password", Toast.LENGTH_SHORT).show()
            }

            else
            {
                val userEmail = edtEmail.text.toString()
                val userPassword = edtPassword.text.toString()

                auth.createUserWithEmailAndPassword(userEmail,userPassword).addOnCompleteListener(activity) {
                    if (it.isSuccessful) {
                        Toast.makeText(this, "Successfully Singed Up",Toast.LENGTH_SHORT).show()
                        startActivity(Intent(activity,HomeActivity::class.java))
                    } else {
                        Toast.makeText(this, "Singed Up Failed!", Toast.LENGTH_SHORT).show()
                    }
                }



            }

        }

    }

    /* private fun addDataToFirestore(userName: String, userPhone: String, userEmail: String, userPassword: String) {

         val dbUsers = db.collection("Users")
         val data = Users(userName,userPhone,userEmail,userPassword)

         dbUsers.add(data)
             .addOnSuccessListener { documentReference ->
                 Toast.makeText(activity, "Account Created", Toast.LENGTH_SHORT).show()
             }.addOnFailureListener {
                 Toast.makeText(activity, "Failed to create account", Toast.LENGTH_SHORT).show()
             }
     }*/
}