package com.example.appcc.fragment

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.appcc.R
import com.example.appcc.activity.MainActivity
import com.example.appcc.base.BaseFragment
import com.example.appcc.databinding.FragmentPrivacyPolicyBinding
import com.example.appcc.model.UserModel
import com.example.appcc.utils.Const
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PrivacyPolicyFragment : BaseFragment() {
    private lateinit var binding: FragmentPrivacyPolicyBinding

//    private val authViewModel: AuthViewModel by viewModels()

    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentPrivacyPolicyBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun bindView() {
        binding.loading.setVisibility(View.GONE)
        auth = FirebaseAuth.getInstance()
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)
        binding.apply {
            btnBack.setOnClickListener {
                onBackPressed()
            }
            btnGoogle.setOnClickListener {
                if (binding.ckCheck.isChecked){
                    binding.loading.setVisibility(View.VISIBLE)
                    sigInGoogle()
                }else{
                    Toast.makeText(requireContext(),getString(R.string.terms),Toast.LENGTH_SHORT).show()
                }

            }

        }

    }

    override fun observeData() {

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    fun onSetupView(): PrivacyPolicyFragment {
        return PrivacyPolicyFragment()
    }

    private fun onBackPressed() {
        activity?.let { act ->
            act.window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            (act as MainActivity).removeFragment(this)

        }
    }
    private val launcher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                handlesResult(task)
            }
        }
    private fun sigInGoogle() {
        val signInInten = googleSignInClient.signInIntent
        launcher.launch(signInInten)
    }
    private fun handlesResult(task: Task<GoogleSignInAccount>) {
        if (task.isSuccessful){
            val account : GoogleSignInAccount?=task.result
            if (account!=null){
                updateUI(account)
            }
        }else{

        }
    }
    @SuppressLint("SuspiciousIndentation")
    private fun updateUI(account: GoogleSignInAccount) {
        val credential= GoogleAuthProvider.getCredential(account.idToken,null)
        auth.signInWithCredential(credential).addOnCompleteListener{
            if (it.isSuccessful){
                binding.loading.setVisibility(View.VISIBLE)
                val reference1: DatabaseReference =FirebaseDatabase.getInstance()
                    .getReference(Const.USER).child(auth.uid.toString())
                //
                reference1.addValueEventListener(object :ValueEventListener{
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val name=snapshot.child("name").getValue(String::class.java)
                        val avatarData = snapshot.child("avarta").getValue(String::class.java)
                        if (name==null){
                            activity?.let {act->
                                val profile =ProfileUserFragment().onSetupView()
                                (act as MainActivity).replaceFragment(profile)
                                (act as MainActivity).removeFragment(this@PrivacyPolicyFragment)
                            }
                            val userModel=UserModel(auth.uid.toString(),account.displayName.toString(),"",null)
                            val reference: DatabaseReference=FirebaseDatabase.getInstance().getReference(Const.USER)
                            reference.child(auth.uid.toString()).setValue(userModel).addOnCompleteListener {
                                Log.d("TAG", "updateUI: "+"Thanh cong")
                            }
                        }else{
                            activity?.let {act->
                                val profile =ProfileUserFragment().onSetupView()
                                val bundle = Bundle()
                                bundle.putString("avarta", avatarData)
                                Log.d("avao", "onDataChange: "+avatarData)
                                profile.arguments = bundle
                                (act as MainActivity).replaceFragment(profile)
                                (act as MainActivity).removeFragment(this@PrivacyPolicyFragment)
                            }
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        TODO("Not yet implemented")
                    }

                })

            }else{
                binding.loading.setVisibility(View.GONE)
            }
        }
    }
    companion object {

    }
}