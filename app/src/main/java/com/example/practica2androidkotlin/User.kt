package com.example.practica2androidkotlin

class User(
    val name: String,
    val lastName: String,
    val singleOrMarried: Boolean
): Information {
    lateinit var code: String

    //Mètodo para generar el código del usuario

    override fun generateCodeUser(part2: String): String {
        var part1 = ""

        //Asignar valores

        part1 += "${name.uppercase()[0]}" +
                "${lastName.uppercase()[lastName.length - 1]}-"
        part1 += if (singleOrMarried) /*Casado*/ "C-" else /*Soltero*/ "S-"
        return part1 + part2
    }

    override fun showInformation(): String {
        return """
            Nombre: $name
            Apellido: $lastName
            Estado civil: ${if (singleOrMarried) "Casado" else "Soltero"}
            Código: ${this.code}
            [Usuario registrado correctamente]
        """.trimIndent()
    }
}