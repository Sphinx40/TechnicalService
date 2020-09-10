package com.sphinx.technicalservice.services

import com.sphinx.technicalservice.models.PhoneNumber
import com.sphinx.technicalservice.models.SignUp
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST


interface TechnicalServiceApi {
    @POST("signup")
    fun signup(
        @Body phoneNumber: PhoneNumber
    ):Call<SignUp>
}