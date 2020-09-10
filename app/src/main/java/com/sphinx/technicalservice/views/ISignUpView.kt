package com.sphinx.technicalservice.views

import com.sphinx.technicalservice.models.SignUp

interface ISignUpView {
    fun onSuccess(response: SignUp)
    fun onError(message: String)
}