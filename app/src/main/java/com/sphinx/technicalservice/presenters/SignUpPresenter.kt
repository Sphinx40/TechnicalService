package com.sphinx.technicalservice.presenters

import com.sphinx.technicalservice.models.PhoneNumber
import com.sphinx.technicalservice.models.SignUp
import com.sphinx.technicalservice.services.ServiceBuilder
import com.sphinx.technicalservice.views.ISignUpView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

interface ISignUpPresenter {
    fun signUp(phoneNumber: String)
}

class SignUpPresenter(internal var iSignUpView: ISignUpView): ISignUpPresenter {

    override fun signUp(phoneNumber: String) {
        if (validation(phoneNumber)) {
            val apiService = ServiceBuilder.create()
            val callSignUp = apiService.signup(PhoneNumber(phoneNumber.toLong()))

            callSignUp.enqueue(object : Callback<SignUp> {
                override fun onFailure(call: Call<SignUp>, t: Throwable) {
                    iSignUpView.onError(t.message.toString())
                }

                override fun onResponse(call: Call<SignUp>, response: Response<SignUp>) {
                    iSignUpView.onSuccess(response.body()!!)
                }
            })
        }

    }

    private fun validation(phoneNumber: String): Boolean {
        var message = ""

        if (phoneNumber.isEmpty() || phoneNumber.length != 11) {
            message = "Введите номер телефона"
        }

        if (message.isNotEmpty()) {
            iSignUpView.onError(message)
            return false
        }

        return true
    }
}