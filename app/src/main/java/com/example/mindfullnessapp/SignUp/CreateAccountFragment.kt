package com.example.mindfullnessapp.SignUp

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import com.example.mindfullnessapp.R
import com.example.mindfullnessapp.databinding.FragmentCreateAccountBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class CreateAccountFragment : Fragment() {


    private lateinit var binding : FragmentCreateAccountBinding

    private lateinit var auth : FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient

    private lateinit var phoneNumber : String

    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result ->
        if(result.resultCode == Activity.RESULT_OK){

            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            handleResult(task)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCreateAccountBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.login.setOnClickListener {
            findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)
        }

        setUpGoogleSignIn()
        signInWithPhoneNumber()
    }
    private fun signInWithGoogle() {
        val signInINtent = googleSignInClient.signInIntent
        launcher.launch(signInINtent)
    }

    private fun signInWithPhoneNumber() {
        auth = FirebaseAuth.getInstance()
        binding.btnSignUp.setOnClickListener{
            phoneNumber = binding.number.text?.trim().toString()

            if(phoneNumber.isNotEmpty() && phoneNumber.length == 10) {

                phoneNumber = "+254$phoneNumber"
                val options = PhoneAuthOptions.newBuilder(auth)
                    .setPhoneNumber(phoneNumber)
                    .setTimeout(60L, TimeUnit.SECONDS)
                    .setActivity(requireActivity())
                    .setCallbacks(callbacks)
                    .build()

                PhoneAuthProvider.verifyPhoneNumber(options)

            } else {
                binding.number.error = resources.getString(R.string.number_invalid)
            }

            findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)
        }

    }

    private fun setUpGoogleSignIn() {
        FirebaseApp.initializeApp(requireContext())
        auth = FirebaseAuth.getInstance()
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)

        binding.googleSignIn.setOnClickListener {
            signInWithGoogle()
        }
    }

    private fun handleResult(task: Task<GoogleSignInAccount>) {
        if(task.isSuccessful) {
            val account : GoogleSignInAccount? = task.result
            if(account != null) {
                updateUI(account)
            }
        } else {
            Snackbar.make(binding.root, task.exception.toString(), Snackbar.LENGTH_LONG).show()
        }
    }

    private fun updateUI(account : GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        auth.signInWithCredential(credential).addOnCompleteListener {
            if(it.isSuccessful) {
                //open home module
                findNavController().navigate(R.id.action_signUpFragment_to_homeFragment)
            } else {
                Snackbar.make(binding.root, it.exception.toString(), Snackbar.LENGTH_LONG).show()
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

            val bundle = Bundle()
            bundle.putString("verificationId", verificationId)
            bundle.putParcelable("token", token)
            bundle.putString("phoneNumber", phoneNumber)
            findNavController().navigate(R.id.action_signUpFragment_to_otpFragment, bundle)
        }
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
                   // Timber.d("signInWithCredential:failure $task.exception")
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        // The verification code entered was invalid
                    }
                    // Update UI
                }
            }
    }

    override fun onStart() {
        super.onStart()

        if(auth.currentUser != null){
            //showHomeModule()
            //navigate to home fragment
        }
    }
}