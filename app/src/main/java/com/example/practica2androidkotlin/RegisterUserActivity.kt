package com.example.practica2androidkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.practica2androidkotlin.databinding.ActivityRegisterUserBinding

class RegisterUserActivity : AppCompatActivity() {

    //Variables

    private lateinit var binding: ActivityRegisterUserBinding
    private lateinit var userCode: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Capturamos el código del intent de la primera pantalla

        userCode = intent.getStringExtra("cod").toString()
        binding.btnRegister.setOnClickListener {  }
    }
}