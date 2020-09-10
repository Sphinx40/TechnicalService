package com.sphinx.technicalservice.utils

import android.content.Context
import android.preference.PreferenceManager


object helpers {
    fun saveToken(context: Context, token: String) {
        Globals.token = token

        val pref = PreferenceManager.getDefaultSharedPreferences(context);
        val editor = pref.edit()

        editor
            .putString("token", token)
            .apply()
    }

    fun getToken(context: Context) {

        val pref = PreferenceManager.getDefaultSharedPreferences(context);
        pref.apply {
            val token = getString("token","")
            Globals.token = token.toString()
        }
    }
}