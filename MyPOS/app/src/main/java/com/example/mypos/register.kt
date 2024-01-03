package com.example.mypos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class register : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    private lateinit var edtEmail: EditText
    private lateinit var edtPassword: EditText
    private lateinit var edtConfirmPassword: EditText
    private lateinit var btnRegister: Button
    private lateinit var btnToStart: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        edtEmail = findViewById(R.id.edtEmail)
        edtPassword = findViewById(R.id.edtPassword)
        edtConfirmPassword = findViewById(R.id.edtConfirmPassword)
        btnRegister = findViewById(R.id.btnRegister)
        btnToStart = findViewById(R.id.btnToStart)

        // 註冊按鈕
        btnRegister.setOnClickListener {
            //取得最新的登入資訊
            var email = edtEmail.text.toString()
            var password = edtPassword.text.toString()
            var confirmPassword = edtConfirmPassword.text.toString()

            if (!email.isNullOrBlank() || !password.isNullOrBlank() || !confirmPassword.isNullOrBlank()) {
                if (password == confirmPassword) {
                    //註冊新用戶
                    auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this) { task ->
                            if (task.isSuccessful) {
                                // Sign in success
                                val user = auth.currentUser
//                                updateUI(user)
                            } else {
                                // sign in fails
                                Toast.makeText(this, "Authentication failed.", Toast.LENGTH_SHORT,).show()
//                                updateUI(user)
                            }
                        }
                }else{
                    // 密碼與確認密碼不符
                    Toast.makeText(this, "Password and Confirm Password don't match", Toast.LENGTH_SHORT).show()
                }
            } else {
                // 使用者輸入空白
                Toast.makeText(this, "You can't input blank words", Toast.LENGTH_SHORT).show()
            }
        }

        //切換到Start頁面
        btnToStart.setOnClickListener{
            val intent= Intent(this, start::class.java)
            startActivity(intent)
        }
    }
}