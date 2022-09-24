package com.example.practica2androidkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.practica2androidkotlin.databinding.ActivityRegisterUserBinding

class RegisterUserActivity : AppCompatActivity() {

    //Variables

    private lateinit var binding: ActivityRegisterUserBinding

    //datos de los editText

    private lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegister.setOnClickListener {
            //Verificación de que los campos no estén vacíos
            //con esto aseguramos de que se registre el usuario si o si
            if(binding.etName.text.isNotEmpty() && binding.etLastName.text.isNotEmpty()){
                try {
                    binding.apply {
                        user = User(etName.text.toString(),
                            etLastName.text.toString(),
                            swSingle.isChecked)
                        //generación del código del usuario, mediante la función que viene
                        //desde una interfaz (aplicable a todo el sistema)
                        user.code = user.generateCodeUser(intent.getStringExtra("cod").toString())
                        tvUserData.visibility = View.VISIBLE
                        tvUserData.text = user.showInformation()
                    }
                } catch (e: Exception) {
                    Toast.makeText(this, "Ocurrió un error", Toast.LENGTH_LONG).show()}
            } else Toast.makeText(this, "Ingrese todos los datos", Toast.LENGTH_SHORT).show()
        }
    }
}