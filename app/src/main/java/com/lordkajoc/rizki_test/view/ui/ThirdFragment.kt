package com.lordkajoc.rizki_test.view.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.lordkajoc.rizki_test.R
import com.lordkajoc.rizki_test.databinding.FragmentThirdBinding
import com.lordkajoc.rizki_test.view.adapter.UserAdapter
import com.lordkajoc.rizki_test.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ThirdFragment : Fragment(), UserAdapter.ListUserClickInterface {
    lateinit var binding: FragmentThirdBinding
    private lateinit var movieadapter: UserAdapter
    private lateinit var userViewmodel: UserViewModel
    var page: Int = 1


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        userViewmodel = ViewModelProvider(this).get(UserViewModel::class.java)
        binding = FragmentThirdBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnBack.setOnClickListener {
            findNavController().navigate(R.id.action_thirdFragment_to_secondFragment)
        }
    }

    private fun getDataUser() {
        userViewmodel.getAllUser(page)
        userViewmodel.livedatauser.observe(viewLifecycleOwner) {
            if (!it.isNullOrEmpty()) {
                binding.swipeLayout.visibility = View.VISIBLE
                binding.layoutEmptydata.visibility = View.GONE
                movieadapter = UserAdapter(it, this)
                val layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                binding.rvUser.layoutManager = layoutManager
                binding.rvUser.adapter = movieadapter
            } else {
                binding.swipeLayout.visibility = View.GONE
                binding.layoutEmptydata.visibility = View.VISIBLE
                Toast.makeText(context, "No data available", Toast.LENGTH_SHORT).show()
                binding.btnResetpage.setOnClickListener {
                    page = 1
                    userViewmodel.getAllUser(page)
                }
            }

        }
        binding.swipeLayout.setOnRefreshListener {
            page++
            userViewmodel.getAllUser(page)
            binding.swipeLayout.isRefreshing = false
        }
    }

    override fun onItemClickUser(id: Int) {
        val bundle = Bundle()
        bundle.putInt("ID", id)
        userViewmodel.simpanIdUser(id)
        Log.d("idUser:", id.toString())
        findNavController().navigate(R.id.action_thirdFragment_to_secondFragment, bundle)
    }

    override fun onStart() {
        super.onStart()
        getDataUser()
    }
}