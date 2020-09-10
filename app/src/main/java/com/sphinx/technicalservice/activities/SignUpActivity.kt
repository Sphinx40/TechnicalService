package com.sphinx.technicalservice.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import br.com.sapereaude.maskedEditText.MaskedEditText
import com.sphinx.technicalservice.R
import com.sphinx.technicalservice.models.SignUp
import com.sphinx.technicalservice.presenters.ISignUpPresenter
import com.sphinx.technicalservice.presenters.SignUpPresenter
import com.sphinx.technicalservice.utils.helpers
import com.sphinx.technicalservice.views.ISignUpView
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity(), ISignUpView {

    private lateinit var signUpPresenter: ISignUpPresenter
    private lateinit var phoneNumber: MaskedEditText
    private lateinit var builder: AlertDialog.Builder
    private lateinit var dialogView: View
    private lateinit var dialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        // Init
        signUpPresenter = SignUpPresenter(this)
        phoneNumber = editPhoneNumber
        builder = AlertDialog.Builder(this)
        dialogView = layoutInflater.inflate(R.layout.progress_dialog, null)
        builder.setView(dialogView)
        builder.setCancelable(false)
        dialog = builder.create()


    }

    fun onSignUp(view: View) {
        dialog.show()

        signUpPresenter.signUp("8"+phoneNumber.rawText.toString())
    }

    override fun onSuccess(response: SignUp) {
        // Close the dialog which show a progress bar
        dialog.dismiss()

        // Hide error text and save token
        sign_up_error.visibility = View.INVISIBLE
        helpers.saveToken(applicationContext, response.token)

        // Go to the verify
        val intent = Intent(applicationContext, SmsCodeActivity::class.java)
        startActivity(intent)
    }

    override fun onError(message: String) {
        // Close the dialog which show a progress bar
        dialog.dismiss()

        // Initialize text and show
        sign_up_error.text = message
        sign_up_error.visibility = View.VISIBLE
    }
}