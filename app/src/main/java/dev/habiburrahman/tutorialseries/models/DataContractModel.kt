package dev.habiburrahman.tutorialseries.models

import java.io.Serializable

data class DataContractModel(

    var targetActivity: String? = ""

): Serializable {

    var user: UserModel = UserModel()
}