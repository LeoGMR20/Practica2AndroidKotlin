package com.example.practica2androidkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.practica2androidkotlin.databinding.ActivityRegisterUserBinding

class RegisterUserActivity : AppCompatActivity() {

    //Variables

    private lateinit var binding: ActivityRegisterUserBinding
    private lateinit var part2userCode: String

    //datos de los editText

    private lateinit var name: String
    private lateinit var lastName: String
    private var single = false
    private lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Capturamos el código del intent de la primera pantalla

        part2userCode = intent.getStringExtra("cod").toString()
        binding.btnRegister.setOnClickListener {
            if(binding.etName.text.isNotEmpty() && binding.etLastName.text.isNotEmpty()){
                try {
                    user = User((reciveDataUser() + part2userCode), name, lastName, single)
                    binding.tvUserData.visibility = View.VISIBLE
                    binding.tvUserData.text = user.showInformation()
                } catch (e: Exception) {
                    Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show()}
            } else Toast.makeText(this, "Ingrese todos los datos", Toast.LENGTH_SHORT).show()
        }
    }

    private fun reciveDataUser(): String {
        var l_part1UserCode = ""

        //Asignar valores

        l_part1UserCode += "${binding.etName.text.toString().uppercase().get(0)}" +
                "${binding.etLastName.text.toString().uppercase().get(0)}-"
        l_part1UserCode += if (binding.swSingle.isChecked) /*Casado*/ "C" else /*Soltero*/ "S"
        return l_part1UserCode
    }
}