package com.vimosanan.workloaderapplication.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.vimosanan.workloaderapplication.R
import com.vimosanan.workloaderapplication.ui.dashboard.DashboardActivity
import com.vimosanan.workloaderapplication.util.NetworkStatus
import com.vimosanan.workloaderapplication.util.Status
import kotlinx.android.synthetic.main.activity_activity.*
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.android.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    private val loginViewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnLogin.setOnClickListener{
            val userName = edtUsername.text.toString()
            val password = edtPassword.text.toString()

            if(userName != "" && password != ""){
                btnLogin.isEnabled = false

                if(NetworkStatus.isNetworkConnected(this)){
                    login(userName, password)
                } else {
                    btnLogin.isEnabled = true
                    showSnackBar("Switch On your Internet and try again!")
                }
            }
        }
    }

    private fun login(userName: String, password: String){
        loginViewModel.getAccessToken(userName, password).observe(this, Observer {
            when(it.status){
                Status.LOADING -> {
                    progressBar.visibility = View.VISIBLE
                }

                Status.SUCCESS -> {
                    if(it.data!!.isSuccessful) {
                        progressBar.visibility = View.GONE
                        startDashboard()
                    }
                }

                Status.ERROR -> {
                    btnLogin.isEnabled = true
                    progressBar.visibility = View.GONE
                    it.msg?.let { msg ->
                        showSnackBar(msg)
                    }
                }
            }
        })
    }

    private fun showSnackBar(str: String){
        Snackbar.make(constraintLoginMain, str, Snackbar.LENGTH_LONG).show()
    }

    private fun startDashboard(){
        startActivity(Intent(this, DashboardActivity::class.java)
            .apply {
                //if we need to pass anything
        })
        finish()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left)
    }
}
