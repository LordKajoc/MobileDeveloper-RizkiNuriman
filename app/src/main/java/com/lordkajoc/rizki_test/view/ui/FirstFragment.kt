package com.lordkajoc.rizki_test.view.ui

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.lordkajoc.rizki_test.R
import com.lordkajoc.rizki_test.databinding.FragmentFirstBinding
import com.lordkajoc.rizki_test.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale


@AndroidEntryPoint
class FirstFragment : Fragment() {
    private lateinit var binding: FragmentFirstBinding
    private lateinit var userViewmodel: UserViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        userViewmodel = ViewModelProvider(this)[UserViewModel::class.java]
        binding = FragmentFirstBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnCheck.setOnClickListener {
            checkPalindrome()
        }
        binding.btnNext.setOnClickListener {
            saveName()
            findNavController().navigate(R.id.action_firstFragment_to_secondFragment)
        }
    }

    private fun saveName() {
        val username = binding.etName.text.toString()
        userViewmodel.simpanNama(username)
    }

    private fun checkPalindrome() {
        val textCheck = binding.etPalindrome.text.toString()
        val regex = Regex("[^A-Za-z0-9]")
        val cleanString = textCheck.replace(regex, "").lowercase(Locale.getDefault())
        val reversedString = cleanString.reversed()
        val output = if (cleanString == reversedString) {
            "isPalindrome."
        } else {
            "not palindrome."
        }
        val builder = AlertDialog.Builder(context)
        builder.setMessage(output)
        builder.setPositiveButton("OK", null)
        val dialog = builder.create()
        dialog.show()
    }
}