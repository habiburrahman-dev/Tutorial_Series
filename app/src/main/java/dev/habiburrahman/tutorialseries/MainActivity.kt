package dev.habiburrahman.tutorialseries

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import dev.habiburrahman.tutorialseries.activities.Target1Activity
import dev.habiburrahman.tutorialseries.databinding.ActivityMainBinding
import dev.habiburrahman.tutorialseries.models.DataContractModel
import dev.habiburrahman.tutorialseries.models.DialogDataModel
import dev.habiburrahman.tutorialseries.utils.Constant.CUSTOM_DIALOG_1
import dev.habiburrahman.tutorialseries.utils.Constant.CUSTOM_DIALOG_2
import dev.habiburrahman.tutorialseries.utils.Constant.CUSTOM_DIALOG_3
import dev.habiburrahman.tutorialseries.utils.handler.CustomDialogHandler

class MainActivity : AppCompatActivity(), CustomDialogHandler.OnObjectDialogClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var customDialogHandler: CustomDialogHandler

    private var targetActivity1Launcher = registerForActivityResult(Target1Activity.Contract()) {
        // do something
        when (it.isSuccess) {
            true -> {
                when (it.isFinish) {
                    true -> {/*
                        Toast.makeText(this, "Do Something", Toast.LENGTH_LONG)
                            .show()*/
                        binding.tvMain.text = "Do Something"
                    }
                    else -> {
                        // do something
                    }
                }
            }
            else -> {
                // do something
            }
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvMain.text = "This is First Launch"

        binding.mBtnMain.setOnClickListener {
            val dataContract = DataContractModel("target_activity_1")
            dataContract.user.name = "Jhon"
            dataContract.user.email = "example@mail.com"
            targetActivity1Launcher.launch(dataContract)
        }

        binding.mBtnMainCd1.setOnClickListener {
            showCustomDialog(
                DialogDataModel(
                    typeDialog = CUSTOM_DIALOG_1
                ).apply {
                    dialogTitleValue = "Login"
                    negativeButton = "Tutup"
                    positiveButton = "Login"
                }
            )
        }

        binding.mBtnMainCd2.setOnClickListener {
            showCustomDialog(
                DialogDataModel(
                    typeDialog = CUSTOM_DIALOG_2
                ).apply {
                    dialogTitleValue = "Perhatian"
                    dialogBodyValue = "Ini Notifikasinya"
                    dialogIcon = R.drawable.ic_outline_info_24
                    negativeButton = "Tutup"
                    positiveButton = "Login"
                }
            )
        }

        binding.mBtnMainCd3.setOnClickListener {
            showCustomDialog(
                DialogDataModel(
                    typeDialog = CUSTOM_DIALOG_3
                ).apply {
                    dialogTitleValue = "Title"
                    dialogCaptionValue = "Caption"
                }
            )
        }

    }

    private fun showCustomDialog(
        dialogDataSource: DialogDataModel,
    ) {
        customDialogHandler = CustomDialogHandler(
            listenerSource = this,
            dialogDataSource = dialogDataSource
        ).apply {
            isCancelable = when (dialogDataSource.typeDialog) {
                CUSTOM_DIALOG_3 -> true
                else -> false
            }
            show(supportFragmentManager, dialogDataSource.typeDialog)
        }
    }

    override fun onObjectDialogClickListener(viewSource: View, alertDialogSource: AlertDialog) {
        when (viewSource.id) {
            R.id.m_btn_negative -> alertDialogSource.dismiss()
            R.id.m_btn_positive_custom_dialog_1 -> {
                val name = alertDialogSource.findViewById<TextInputEditText>(R.id.et_name)?.text
                val phone =
                    alertDialogSource.findViewById<TextInputEditText>(R.id.et_contact_number)?.text
                Toast.makeText(this, "Nama: $name\nNo. Hp: $phone", Toast.LENGTH_LONG).show()
            }
        }
    }

}