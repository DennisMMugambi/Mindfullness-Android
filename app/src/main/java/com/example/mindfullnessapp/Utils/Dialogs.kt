package com.ekenya.rnd.android.common.Utils

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.mindfullnessapp.R
import com.example.mindfullnessapp.databinding.AppCustomDialogBinding
import com.example.mindfullnessapp.databinding.LoadingAnimationDialogBinding


class Dialogs {

    var listener: (()->Unit)? = null
    lateinit var customDialog : AlertDialog

    fun showDialog(context : Context?, dialog_title : Int, dialog_description : Int, dialog_btn_text : Int, animation : Int) {
        val dialogBinding: AppCustomDialogBinding? =
            DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.app_custom_dialog,
                null,
                false
            )

        customDialog = AlertDialog.Builder(context, 0).create()


        customDialog.apply {
            setView(dialogBinding?.root)
            setCancelable(true)
        }.show()

        dialogBinding?.title?.setText(dialog_title)
        dialogBinding?.description?.setText(dialog_description)
        dialogBinding?.dialogBtn?.setText(dialog_btn_text)
        dialogBinding?.animationView?.setAnimation(animation)

        dialogBinding?.dialogBtn?.setOnClickListener {
            customDialog.dismiss()

            listener?.invoke()
        }
    }

    fun showLoadingDialog(context: Context?) {
        val dialogBinding: LoadingAnimationDialogBinding? =
            DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.loading_animation_dialog,
                null,
                false
            )

        customDialog = AlertDialog.Builder(context, 0).create()


        customDialog.apply {
            setView(dialogBinding?.root)
            setCancelable(true)
        }.show()

        }

    fun dismissDialog() {
        customDialog.dismiss()
    }

}