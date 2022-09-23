package com.example.practica2androidkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.practica2androidkotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //Variables

    private lateinit var binding: ActivityMainBinding
    private lateinit var handlerProcess: Handler
    private lateinit var prevCodUser: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Inicializar Handler

        handlerProcess = Handler(mainLooper)

        //Inicio del proceso con el click del botón

        binding.btnStartProcess.setOnClickListener {
            startCount()
            prevCodUser = generateCode()
        }
    }

    private fun startCount() {
        //Cambios en el textView tvProcess
        binding.tvProcess.text = "0"
        Thread{
            try {
                for(i in 1..10) {
                    Thread.sleep(1000)
                    handlerProcess.post{
                        binding.apply {
                            tvProcess.text = "$i"
                        }
                    }
                }
                binding.tvProcess.text = "LISTO"

            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }.start()
    }

    private fun generateCode():String {
        var codigo = ""
        Thread{
            try {
                for (number in generateRandomNumber()) codigo.plus(number.toString())
                codigo.plus("-")

            } catch (e: InterruptedException){
                e.printStackTrace()
            }
        }.start()
        return codigo
    }

    private fun generateRandomNumber(): MutableList<Int> {
        val listNumbers: MutableList<Int> = arrayListOf()
        var repeatNumber = (0..9).random()
        listNumbers.add(repeatNumber)
        do {
            listNumbers.add((0..9).random())
        } while (listNumbers.contains(repeatNumber))
        return listNumbers
    }
}