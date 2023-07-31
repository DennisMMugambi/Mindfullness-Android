package com.example.mindfullnessapp.Login

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.activity.result.contract.ActivityResultContracts
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.ekenya.rnd.android.common.Utils.Dialogs
import com.ekenya.rnd.android.common.Utils.Enums.IsSearching
import com.ekenya.rnd.android.common.Utils.Enums.Status
import com.ekenya.rnd.android.common.Utils.Utils
import com.example.mindfullnessapp.Common.Constants
import com.example.mindfullnessapp.R
import com.example.mindfullnessapp.databinding.FragmentLoginBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var parentView: ConstraintLayout

    private val mViewModel by viewModels<DashboardViewModel>()

    // ...
    private lateinit var auth : FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient

    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result ->
        if(result.resultCode == Activity.RESULT_OK){

            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            handleResult(task)
        }
    }


    val dialog = Dialogs()
    private val pref = Utils

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        //Override status bar color in theme for this fragment
        activity?.window?.statusBarColor =  resources.getColor(R.color.white)

        binding = FragmentLoginBinding.inflate(layoutInflater)
        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.signUp.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_singUpFragment)
        }


        initListeners()
        showErrorMessage()
        showProgressBar()

    }


    private fun signInWithGoogle() {
        val signInINtent = googleSignInClient.signInIntent
        launcher.launch(signInINtent)
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
                //showHomeModule()
                //Navigate to home
                findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
            } else {
                Snackbar.make(binding.root, it.exception.toString(), Snackbar.LENGTH_LONG).show()
            }
        }
    }


    private fun showErrorMessage() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            mViewModel.showMessageEvent.collect { event ->

                when (event) {
                    // Status.PININVALID -> binding.churchEt.error = resources.getString(R.string.pin_invalid)
                    Status.SUCCESSFUL -> navigateHome()//showHomeModule()
                    Status.UNSUCCESSFUL -> dismissLoadingDialog()
                    else -> {}
                }
            }
        }
    }

    private fun navigateHome() {

    }

    private fun setUserNameForSubsequentLogin() {
        val userName = pref.getPreferences(requireContext(), R.string.user_name.toString())
        Constants.USERNAME = userName
    }

    private fun showProgressBar() {
        lifecycleScope.launch {
            mViewModel._searchStatus.collect { status ->
                when (status) {
                    IsSearching.SEARCHING -> showLoadingDialog()
                    IsSearching.IDLE -> dismissLoadingDialog()
                    else -> {}
                }
            }
        }
    }

    private fun validateAndSend() {
        mViewModel.validateFields()
    }

    private fun initListeners() {
        /* binding.loginPin.addTextChangedListener {
             mViewModel.setPin(it.toString().trim())
         }

         binding.churchDropDown.addOnEditTextAttachedListener {
             mViewModel.setChurch(it.toString())
         }*/
    }

    private fun showLoadingDialog() {
        dialog.showLoadingDialog(requireContext())
    }

    private fun dismissLoadingDialog() {
        dialog.dismissDialog()
        Snackbar.make(parentView, R.string.login_error, Snackbar.LENGTH_LONG).show()
    }

}
