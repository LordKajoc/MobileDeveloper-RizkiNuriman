package com.lordkajoc.rizki_test.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.lordkajoc.rizki_test.R
import com.lordkajoc.rizki_test.databinding.FragmentSecondBinding
import com.lordkajoc.rizki_test.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SecondFragment : Fragment() {
    private lateinit var binding: FragmentSecondBinding
    private lateinit var userViewmodel: UserViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        userViewmodel = ViewModelProvider(this)[UserViewModel::class.java]
        binding = FragmentSecondBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getDataUserSingle()
        getNama()

        binding.btnPilihuser.setOnClickListener {
            findNavController().navigate(R.id.action_secondFragment_to_thirdFragment)
        }
        binding.btnBack.setOnClickListener {
            findNavController().navigate(R.id.action_secondFragment_to_firstFragment)
        }
    }

    private fun getNama() {
        val getnama = userViewmodel.getNama()
        binding.tvNamefs.text = getnama
    }

    private fun getDataUserSingle() {
        val idUser = arguments?.getInt("ID")
        if (idUser != null) {
            userViewmodel.getUserSingle(idUser)
            userViewmodel.livedatauserSingle.observe(viewLifecycleOwner) {
                if (it != null) {
                    binding.tvSelectedUser.text = "${it.data!!.firstName} ${it.data.lastName}"
                }
            }
        }

    }
}