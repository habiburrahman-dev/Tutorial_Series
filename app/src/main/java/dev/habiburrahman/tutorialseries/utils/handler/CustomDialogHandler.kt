package dev.habiburrahman.tutorialseries.utils.handler

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDialogFragment
import coil.load
import com.google.android.material.button.MaterialButton
import dev.habiburrahman.tutorialseries.R
import dev.habiburrahman.tutorialseries.databinding.CustomDialog1Binding
import dev.habiburrahman.tutorialseries.databinding.CustomDialog2Binding
import dev.habiburrahman.tutorialseries.databinding.CustomDialog3Binding
import dev.habiburrahman.tutorialseries.models.DialogDataModel
import dev.habiburrahman.tutorialseries.utils.Constant.CUSTOM_DIALOG_1
import dev.habiburrahman.tutorialseries.utils.Constant.CUSTOM_DIALOG_2
import dev.habiburrahman.tutorialseries.utils.Constant.CUSTOM_DIALOG_3

class CustomDialogHandler(

    listenerSource: OnObjectDialogClickListener,
    dialogDataSource: DialogDataModel? = null

): AppCompatDialogFragment() {

    private val dialogData by lazy { dialogDataSource }
    private val listener by lazy { listenerSource }
    private lateinit var alertDialog: AlertDialog

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater by lazy { layoutInflater }
        val rootView by lazy {
            when(dialogData?.typeDialog){
                CUSTOM_DIALOG_1 -> showCustomDialog1(inflater)
                CUSTOM_DIALOG_2 -> showCustomDialog2(inflater)
                CUSTOM_DIALOG_3 -> showCustomDialog3(inflater)
                else -> null
            }
        }
        alertDialog = AlertDialog.Builder(requireContext())
            .create().apply {
                setView(rootView)
                window?.setBackgroundDrawableResource(R.color.transparent)
            }
        return when(dialogData){
            null -> super.onCreateDialog(savedInstanceState)
            else -> {
                alertDialog
            }
        }
    }

    override fun onPause() {
        super.onPause()
        this.dismissAllowingStateLoss()
    }

    private fun showCustomDialog1(inflaterSource: LayoutInflater): View {
        return CustomDialog1Binding.inflate(inflaterSource).apply {
            mtvTitleCustomDialog1.text = dialogData?.dialogTitleValue
            mBtnNegative.text = dialogData?.negativeButton
            mBtnPositiveCustomDialog1.text = dialogData?.positiveButton
            onButtonClick(
                negativeButtonSource = mBtnNegative,
                positiveButtonSource = mBtnPositiveCustomDialog1
            )
        }.root
    }

    private fun showCustomDialog2(inflaterValue: LayoutInflater): View {
        return CustomDialog2Binding.inflate(inflaterValue).apply {
            mtvTitleCustomDialog2.text = dialogData?.dialogTitleValue
            mtvBodyCustomDialog2.text = dialogData?.dialogBodyValue
            mBtnNegative.text = dialogData?.negativeButton
            mBtnPositiveCustomDialog2.text = dialogData?.positiveButton
            sivIconCustomDialog2.load(dialogData?.dialogIcon)
            onButtonClick(
                negativeButtonSource = mBtnNegative,
                positiveButtonSource = mBtnPositiveCustomDialog2
            )
        }.root
    }

    private fun showCustomDialog3(inflaterValue: LayoutInflater): View {
        return CustomDialog3Binding.inflate(inflaterValue).apply {
            mtvTitleCustomDialog3.text = dialogData?.dialogTitleValue
            mtvCaptionCustomDialog3.text = dialogData?.dialogCaptionValue
            mcvQrCodeCustomDialog3.setOnClickListener {
                listener.onObjectDialogClickListener(it, alertDialog)
            }
            mcvGoOnRideCustomDialog3.setOnClickListener {
                listener.onObjectDialogClickListener(it, alertDialog)
            }
        }.root
    }

    private fun onButtonClick(
        negativeButtonSource: MaterialButton,
        positiveButtonSource: MaterialButton
    ){
        negativeButtonSource.setOnClickListener {
            listener.onObjectDialogClickListener(it, alertDialog)
        }
        positiveButtonSource.setOnClickListener {
            listener.onObjectDialogClickListener(it, alertDialog)
        }
    }

    interface OnObjectDialogClickListener{
        fun onObjectDialogClickListener(viewSource: View, alertDialogSource: AlertDialog)
    }
}