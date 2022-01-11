package dev.habiburrahman.tutorialseries.models

import java.io.Serializable

data class UserModel(

    var name: String? = null,
    var email: String? = null

): Serializable
