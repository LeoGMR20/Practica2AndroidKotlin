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
                for(i in 2..10) {
                    Thread.sleep(1000)
                    handlerProcess.post{
                        binding.apply {
                            if (i != 10) tvProcess.text = "$i"
                            else tvProcess.text = "LISTO"
                            pbProgress.progress = i * 10
                        }
                    }
                }
                Thread.sleep(1500) //Para ver el mensaje de LISTO
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
        for (char in generateRandom(65,90)) codigo += char.toChar()
        codigo += "-"
        for (number in generateRandom(0,9)) codigo += number
        return codigo
    }

    private fun generateRandom(first: Int, last: Int): MutableList<Int> {
        val listNumbers: MutableList<Int> = arrayListOf()
        var repeatedNumber: Int
        listNumbers.add((first..last).random())
        do {
            repeatedNumber = (first..last).random()
        } while (listNumbers.contains(repeatedNumber))
        listNumbers.add(repeatedNumber)
        return listNumbers
    }

    //Creacion de un intent para pasar de pantalla

    private fun passScreen() {
        val intent = Intent(this,RegisterUserActivity::class.java)
        intent.apply { putExtra("cod", prevCodUser) }
        startActivity(intent)
    }
}