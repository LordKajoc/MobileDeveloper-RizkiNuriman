package com.lordkajoc.rizki_test.viewmodel

import android.app.Application
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lordkajoc.rizki_test.model.user.DataGetUserResponse
import com.lordkajoc.rizki_test.model.user.DataGetUserResponseItem
import com.lordkajoc.rizki_test.model.user.DataGetUserSingleResponse
import com.lordkajoc.rizki_test.view.network.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val client: ApiService,
    private val sharedPreferences: SharedPreferences,
    application: Application
) : AndroidViewModel(application) {

    private val datauser: MutableLiveData<List<DataGetUserResponseItem>?> = MutableLiveData()
    val livedatauser: LiveData<List<DataGetUserResponseItem>?> get() = datauser

    fun getAllUser(page:Int) {
        client.getDataUser(page).enqueue(object : Callback<DataGetUserResponse> {
            override fun onResponse(
                call: Call<DataGetUserResponse>,
                response: Response<DataGetUserResponse>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        datauser.postValue(response.body()?.data)
                    }
                }
            }

            override fun onFailure(call: Call<DataGetUserResponse>, t: Throwable) {
                Log.d("Tag", t.message.toString())
            }
        })

    }

    private val datauserSingle: MutableLiveData<DataGetUserSingleResponse> = MutableLiveData()
    val livedatauserSingle: LiveData<DataGetUserSingleResponse> get() = datauserSingle

    fun getUserSingle(id: Int) {
        client.getDataUserSingle(id).enqueue(object : Callback<DataGetUserSingleResponse> {
            override fun onResponse(
                call: Call<DataGetUserSingleResponse>,
                response: Response<DataGetUserSingleResponse>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        datauserSingle.postValue(response.body())
                    }
                }
            }

            override fun onFailure(call: Call<DataGetUserSingleResponse>, t: Throwable) {
                Log.d("Tag", t.message.toString())
            }
        })

    }

    fun simpanIdUser(ID: Int) {
        val editor = sharedPreferences.edit()
        editor.putInt("ID", ID)
        editor.apply()
    }

    fun simpanNama(nama: String) {
        val editor = sharedPreferences.edit()
        editor.putString("NAMA", nama)
        editor.apply()
    }

    fun getNama(): String? {
        return sharedPreferences.getString("NAMA", "")
    }
}