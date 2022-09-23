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

    private lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Capturamos el código del intent de la primera pantalla

        part2userCode = intent.getStringExtra("cod").toString()
        binding.btnRegister.setOnClickListener {
            //Verificación de que los campos no estén vacíos
            //con esto aseguramos de que se registre el usuario si o si
            if(binding.etName.text.isNotEmpty() && binding.etLastName.text.isNotEmpty()){
                try {
                    user = User((genereteUser(part2userCode)),
                        binding.etName.text.toString(),
                        binding.etLastName.text.toString(),
                        binding.swSingle.isChecked)
                    binding.tvUserData.visibility = View.VISIBLE
                    binding.tvUserData.text = user.showInformation()
                } catch (e: Exception) {
                    Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show()}
            } else Toast.makeText(this, "Ingrese todos los datos", Toast.LENGTH_SHORT).show()
        }
    }

    //Mètodo para generar el código del usuario

    private fun genereteUser(p_part2UserCode: String): String {
        var part1UserCode = ""

        //Asignar valores

        part1UserCode += "${binding.etName.text.toString().uppercase()[0]}" +
                "${binding.etLastName.text.toString().uppercase()[0]}-"
        part1UserCode += if (binding.swSingle.isChecked) /*Casado*/ "C-" else /*Soltero*/ "S-"
        return part1UserCode + p_part2UserCode
    }
}