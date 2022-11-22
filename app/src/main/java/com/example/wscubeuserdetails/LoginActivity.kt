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

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()

        val email = findViewById<EditText>(R.id.loginEmail)
        val password = findViewById<EditText>(R.id.loginPassword)
        val anim = findViewById<LottieAnimationView>(R.id.animationView)
        anim.setAnimation(R.raw.secure_login);
        anim.playAnimation()

        findViewById<TextView>(R.id.txtSignup).setOnClickListener {
            startActivity(Intent(this@LoginActivity,MainActivity::class.java))
        }


        findViewById<AppCompatButton>(R.id.btnLogin).setOnClickListener {

            auth.signInWithEmailAndPassword(email.text.toString(), password.text.toString()).addOnCompleteListener(this) {
                if (it.isSuccessful) {
                    startActivity(Intent(this@LoginActivity,HomeActivity::class.java))
                    Toast.makeText(this, "Successfully LoggedIn", Toast.LENGTH_SHORT).show()
                } else
                    Toast.makeText(this, "Log In failed ", Toast.LENGTH_SHORT).show()
            }
        }
    }
}