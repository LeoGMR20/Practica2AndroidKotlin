package com.example.practica2androidkotlin

class User(
    val cod: String,
    val name: String,
    val lastName: String,
    val single: Boolean
) {
    fun showInformation(): String{
        return """
            Nombre: $name
            Apellido: $lastName
            Estado civil: ${if (single) "Casado" else "Soltero"}
            Código: $cod
            [Usuario registrado correctamente]
        """.trimIndent()
    }
}