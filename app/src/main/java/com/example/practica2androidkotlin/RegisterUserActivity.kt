package com.example.practica2androidkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.practica2androidkotlin.databinding.ActivityRegisterUserBinding

class RegisterUserActivity : AppCompatActivity() {

    //Variables

    private lateinit var binding: ActivityRegisterUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}