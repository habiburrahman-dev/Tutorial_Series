package dev.habiburrahman.tutorialseries.models

import java.io.Serializable

data class DialogDataModel(

    val typeDialog: String? = null

): Serializable {

    var negativeButton: String? = null
    var positiveButton: String? = null
    var dialogTitleValue: String? = null
    var dialogBodyValue: String? = null
    var dialogCaptionValue: String? = null
    var dialogIcon: Int? = null

}
