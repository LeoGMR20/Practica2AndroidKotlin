package com.example.practica2androidkotlin

import android.content.Intent
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
        }
    }

    private fun startCount() {
        //Cambios en el textView tvProcess
        binding.tvProcess.text = "1"
        Thread{
            try {
                for(i in 2..3) { //todo cambiarle a 10 seg al finalizar todo
                    Thread.sleep(1000)
                    handlerProcess.post{
                        if (i != 3) binding.tvProcess.text = "$i"
                        else binding.tvProcess.text = "LISTO"
                    }
                }
                Thread.sleep(3000) //Para ver el mensaje de LISTO
                prevCodUser = generateCode()
                //Luego de generar el código y que haya terminado el contador
                //pasamos de pantalla
                passScreen()
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }.start()
    }

    //Funciones para generar codigo aleatorio

    private fun generateCode(): String {
        var codigo = ""
        for (number in generateRandomNumber()) codigo += number
        codigo += "-"
        for (char in generateRandomCharacter()) codigo += char
        return codigo
    }

    private fun generateRandomNumber(): MutableList<Int> {
        val listNumbers: MutableList<Int> = arrayListOf()
        var repeatedNumber: Int
        listNumbers.add((0..9).random())
        do {
            repeatedNumber = (0..9).random()
        } while (listNumbers.contains(repeatedNumber))
        listNumbers.add(repeatedNumber)
        return listNumbers
    }

    private fun generateRandomCharacter(): MutableList<Char> {
        val listChars: MutableList<Char> = arrayListOf()
        var repeatedChar: Int
        listChars.add((65..90).random().toChar())
        do {
            repeatedChar = (65..90).random()
        }while (listChars.contains(repeatedChar.toChar()))
        listChars.add(repeatedChar.toChar())
        return listChars
    }

    //Creacion de un intent para pasar de pantalla

    private fun passScreen() {
        val intent = Intent(this,RegisterUserActivity::class.java)
        intent.apply { putExtra("cod", prevCodUser) }
        startActivity(intent)
    }
}