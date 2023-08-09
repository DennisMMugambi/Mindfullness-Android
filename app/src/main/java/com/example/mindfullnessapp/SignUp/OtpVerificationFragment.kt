package com.example.mindfullnessapp.SignUp

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.mindfullnessapp.databinding.FragmentOtpVerificationBinding
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import java.util.concurrent.TimeUnit
import com.example.mindfullnessapp.R


class OtpVerificationFragment : Fragment() {

    private lateinit var binding : FragmentOtpVerificationBinding
    private lateinit var auth : FirebaseAuth

    private lateinit var OTP : String
    private lateinit var resendToken : PhoneAuthProvider.ForceResendingToken
    private lateinit var phoneNumber : String

    private lateinit var pickedOtp : String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentOtpVerificationBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnResendOTP.setOnClickListener {
            resendOTP()
        }

        setCountDownTimer()
        setupNavUp()
        getDataFromPreviousScreen()

    }

    private fun getDataFromPreviousScreen() {

        arguments.let {
            OTP = arguments?.getString("verificationId").toString()
            resendToken = arguments?.getParcelable("token")!!
            phoneNumber = arguments?.getString("phoneNumber").toString()
        }
    }

    private fun verifyOtp() {
        val credential : PhoneAuthCredential = PhoneAuthProvider.getCredential(
            OTP, pickedOtp
        )

        signInWithPhoneAuthCredential(credential)
    }


    private fun setupNavUp() {
        val navController = findNavController()
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        binding.customToolbar.setupWithNavController(navController, appBarConfiguration)
    }

    private fun resendOTP() {

        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phoneNumber)
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(requireActivity())
            .setCallbacks(callbacks)
            .setForceResendingToken(resendToken)
            .build()

        PhoneAuthProvider.verifyPhoneNumber(options)
    }


    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                   // Timber.d("signInWithCredential:success")

                    val user = task.result?.user
                } else {
                    // Sign in failed, display a message and update the UI
                    //Timber.d("signInWithCredential:failure $task.exception")
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        // The verification code entered was invalid
                    }
                    // Update UI
                }
            }
    }

    private val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        override fun onVerificationCompleted(credential: PhoneAuthCredential) {
            // This callback will be invoked in two situations:
            // 1 - Instant verification. In some cases the phone number can be instantly
            //     verified without needing to send or enter a verification code.
            // 2 - Auto-retrieval. On some devices Google Play services can automatically
            //     detect the incoming verification SMS and perform verification without
            //     user action.
           //Timber.d("onVerificationCompleted:$credential")
            signInWithPhoneAuthCredential(credential)
        }

        override fun onVerificationFailed(e: FirebaseException) {
            // This callback is invoked in an invalid request for verification is made,
            // for instance if the the phone number format is not valid.
           // Timber.d("onVerificationFailed$e")

            if (e is FirebaseAuthInvalidCredentialsException) {
                // Invalid request
            } else if (e is FirebaseTooManyRequestsException) {
                // The SMS quota for the project has been exceeded
            }

            // Show a message and update the UI
        }

        override fun onCodeSent(
            verificationId: String,
            token: PhoneAuthProvider.ForceResendingToken
        ) {
            OTP = verificationId
            resendToken = token
        }
    }

    private fun setCountDownTimer() {
        object : CountDownTimer(30000, 1000) {

            // Callback function, fired on regular interval
            @SuppressLint("SetTextI18n")
            override fun onTick(millisUntilFinished: Long) {
                binding.receivedOtp.setText(resources.getString(R.string.didnt_receive_otp)+ " (" + millisUntilFinished / 1000 + " )")
            }

            // Callback function, fired
            // when the time is up
            override fun onFinish() {
                binding.btnResendOTP.isEnabled = true
            }
        }.start()
    }

}