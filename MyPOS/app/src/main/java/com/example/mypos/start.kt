package com.example.mypos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth

class start : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var edtEmail: EditText
    private lateinit var edtPassword: EditText
    private lateinit var txtToRegister: TextView
    private lateinit var btnLogin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        auth = FirebaseAuth.getInstance()

        edtEmail = findViewById(R.id.edtEmail)
        edtPassword = findViewById(R.id.edtPassword)
        btnLogin = findViewById(R.id.btnLogin)
        txtToRegister = findViewById(R.id.txt_to_register)

        //點擊登入按鈕
        btnLogin.setOnClickListener {
            var email = edtEmail.text.toString()
            var password = edtPassword.text.toString()

            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success
                        val user = auth.currentUser
//                        updateUI(user)

                        //切換到Main頁面
                        val intent= Intent(this, MainActivity::class.java)
                        startActivity(intent)

                        Toast.makeText(this, "Login sucess.", Toast.LENGTH_SHORT).show()
                    } else {
                        //sign in fails
                        Toast.makeText(this, "Authentication failed.", Toast.LENGTH_SHORT).show()
//                        updateUI(null)
                    }
                }
        }
        //切換到Register頁面
        txtToRegister.setOnClickListener{
            val intent= Intent(this, register::class.java)
            startActivity(intent)
        }

        //檢查使用者目前是否已登入。
        val currentUser = auth.currentUser
        if (currentUser != null) {
            //可以省略登入步驟(暫定)
//            reload()
        }

    }
}