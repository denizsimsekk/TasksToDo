package com.denizsimsek.taskstodo.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.denizsimsek.taskstodo.R
import com.denizsimsek.taskstodo.databinding.FragmentLogInBinding
import com.denizsimsek.taskstodo.viewmodel.LogInViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LogInFragment : Fragment() {

    private val viewModel: LogInViewModel by viewModels()
    private var signedIn="0"
    private lateinit var binding: FragmentLogInBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLogInBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.logInButton.setOnClickListener {

            viewModel.signIn(binding.userName.text.toString(),binding.userPassword.text.toString())
                .toString()
            binding.logInButton.setCompoundDrawablesWithIntrinsicBounds(androidx.constraintlayout.widget.R.drawable.abc_ab_share_pack_mtrl_alpha, 0, 0, 0)

            /*println("viewmodelSonresi")
            println("signedIn: "+signedIn)
            if(signedIn=="1")
            {
                println("if ici")
                val myIntent = Intent(requireActivity(), ManagerFeedActivity::class.java)

                requireActivity().startActivity(myIntent)
                requireActivity().finish()
            }*/
        }
        //  observeLiveData()
    }

    fun observeLiveData()
    {
        viewModel.signedIn.observe(viewLifecycleOwner)
        {


        }
    }
}
