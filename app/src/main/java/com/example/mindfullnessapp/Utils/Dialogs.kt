package com.ekenya.rnd.android.common.Utils

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.ekenya.rnd.android.common.R
import com.ekenya.rnd.android.common.databinding.AppCustomDialogBinding
import com.ekenya.rnd.android.common.databinding.BaptismAddedSuccessfullyBinding
import com.ekenya.rnd.android.common.databinding.LoadingAnimationDialogBinding
import com.ekenya.rnd.android.common.databinding.MarriageSuccessfulDialogBinding
import com.ekenya.rnd.android.common.databinding.NewtithedialogBinding
import com.ekenya.rnd.android.common.databinding.NoConnectivityDialogBinding
import com.ekenya.rnd.android.common.databinding.ProfileCompletedSuccessfullyBinding
import com.ekenya.rnd.android.common.databinding.SpecialSuccessfulDialogBinding
import com.ekenya.rnd.android.common.databinding.TitheSuccessfulDialogBinding
import kotlinx.android.synthetic.main.app_custom_dialog.*
import kotlinx.android.synthetic.main.app_custom_dialog.view.*

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

    fun showTitheItemDialog(context: Context?, month : String, mode : String, provider : String, number : String, amount : String) {
        val dialogBinding: NewtithedialogBinding? =
            DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.newtithedialog,
                null,
                false
            )

        customDialog = AlertDialog.Builder(context, 0).create()


        customDialog.apply {
            setView(dialogBinding?.root)
            setCancelable(false)
        }.show()

        dialogBinding?.amountValue?.setText(amount)
        dialogBinding?.mobileNumberValue?.setText(number)
        dialogBinding?.providerValue?.setText(provider)
        dialogBinding?.paymentModeValue?.setText(mode)
        dialogBinding?.monthRecord?.setText(month)

        dialogBinding?.dialogBtn?.setOnClickListener {
            customDialog.dismiss()

            listener?.invoke()
        }

    }

    fun showTitheSuccessDialog(context: Context?, animation: Int) {
        val dialogBinding: TitheSuccessfulDialogBinding? =
            DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.tithe_successful_dialog,
                null,
                false
            )

        customDialog = AlertDialog.Builder(context, 0).create()


        customDialog.apply {
            setView(dialogBinding?.root)
            setCancelable(false)
        }.show()

        dialogBinding?.cancelBtn?.setOnClickListener {
            customDialog.dismiss()

            listener?.invoke()
        }

        dialogBinding?.animationView?.setAnimation(animation)

        dialogBinding?.okayBtn?.setOnClickListener {
            customDialog.dismiss()

            listener?.invoke()
        }

    }


    fun showBaptismSuccessDialog(context: Context?, animation: Int) {
        val dialogBinding: BaptismAddedSuccessfullyBinding? =
            DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.baptism_added_successfully,
                null,
                false
            )

        customDialog = AlertDialog.Builder(context, 0).create()


        customDialog.apply {
            setView(dialogBinding?.root)
            setCancelable(false)
        }.show()

        dialogBinding?.cancelBtn?.setOnClickListener {
            customDialog.dismiss()

            listener?.invoke()
        }

        dialogBinding?.animationView?.setAnimation(animation)

        dialogBinding?.okayBtn?.setOnClickListener {
            customDialog.dismiss()

            listener?.invoke()
        }

    }




    fun showSpecialSuccessDialog(context: Context?, animation: Int) {
        val dialogBinding: SpecialSuccessfulDialogBinding? =
            DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.special_successful_dialog,
                null,
                false
            )

        customDialog = AlertDialog.Builder(context, 0).create()


        customDialog.apply {
            setView(dialogBinding?.root)
            setCancelable(false)
        }.show()

        dialogBinding?.cancelBtn?.setOnClickListener {
            customDialog.dismiss()

            listener?.invoke()
        }

        dialogBinding?.animationView?.setAnimation(animation)

        dialogBinding?.okayBtn?.setOnClickListener {
            customDialog.dismiss()

            listener?.invoke()
        }

    }

    fun showMarriageSuccessDialog(context: Context?, animation: Int) {
        val dialogBinding: MarriageSuccessfulDialogBinding? =
            DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.marriage_successful_dialog,
                null,
                false
            )

        customDialog = AlertDialog.Builder(context, 0).create()


        customDialog.apply {
            setView(dialogBinding?.root)
            setCancelable(false)
        }.show()

        dialogBinding?.cancelBtn?.setOnClickListener {
            customDialog.dismiss()

            listener?.invoke()
        }

        dialogBinding?.animationView?.setAnimation(animation)

        dialogBinding?.okayBtn?.setOnClickListener {
            customDialog.dismiss()

            listener?.invoke()
        }

    }



    fun dismissDialog() {
        customDialog.dismiss()
    }

    fun showProfileCompletedSuccessfully(context : Context?, animation : Int) {
        val dialogBinding: ProfileCompletedSuccessfullyBinding? =
            DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.profile_completed_successfully,
                null,
                false
            )

        customDialog = AlertDialog.Builder(context, 0).create()


        customDialog.apply {
            setView(dialogBinding?.root)
            setCancelable(true)
        }.show()

        dialogBinding?.animationView?.setAnimation(animation)

        dialogBinding?.dialogBtn?.setOnClickListener {
            customDialog.dismiss()

            listener?.invoke()
        }
    }

    fun showNoConnectivityDialog(context : Context?, animation : Int) {
        val dialogBinding: NoConnectivityDialogBinding? =
            DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.no_connectivity_dialog,
                null,
                false
            )

        customDialog = AlertDialog.Builder(context, 0).create()


        customDialog.apply {
            setView(dialogBinding?.root)
            setCancelable(true)
        }.show()

        dialogBinding?.animationView?.setAnimation(animation)

        dialogBinding?.dialogBtn?.setOnClickListener {
            customDialog.dismiss()

            listener?.invoke()
        }
    }


}