package com.vimosanan.workloaderapplication.ui.dashboard

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.vimosanan.workloaderapplication.R
import com.vimosanan.workloaderapplication.app.Constants
import com.vimosanan.workloaderapplication.model.Account
import com.vimosanan.workloaderapplication.ui.progress.ShowProgressActivity
import com.vimosanan.workloaderapplication.util.Status
import kotlinx.android.synthetic.main.activity_activity.*
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.android.viewmodel.ext.android.viewModel


class DashboardActivity : AppCompatActivity() {

    private val dashboardViewModel: DashboardViewModel by viewModel()

    private var isCLockedIn = false
    private var isClockedOut = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_activity)


        dashboardViewModel.loadData().observe(this, Observer {
            when(it.status) {
                Status.LOADING -> {
                    progressBarDashBoard.visibility = View.VISIBLE
                }

                Status.SUCCESS -> {
                    progressBarDashBoard.visibility = View.GONE

                    it.data?.let {response ->
                        if (response.isSuccessful){ //if response is success: 200
                            updateDashboardUI(response.body())
                        }
                    }
                }
            }
        })

        btnClockInOut.setOnClickListener{
            startProgressActivity()

        }

        dashboardViewModel.clockInTime.observe(this, Observer {
            if(it != null){
                txtClockInTime.text = it
            }
        })

        dashboardViewModel.clockOutTime.observe(this, Observer {
            if(it != null){
                txtClockOutTime.text = it
            }
        })
    }


    private fun updateDashboardUI(account: Account?){

        if(account != null){
            account.position?.name?.let {
                txtPositionTitle.text = it
            }

            account.client?.name?.let {
                txtClientName.text = it
            }

            account.wageAmount?.let {
                txtSalaryAmount.text = "Rp $it"
            }

            account.wageType?.let {
                txtType.text = it
            }

            account.location?.address?.streetOne?.let {
                txtAddress.text = it
            }

            account.manager?.name?.let{
                txtManagerName.text = it
            }

            account.manager?.contactNumber?.let{
                txtContactNumber.text = it
            }
        }else{
            //inform something went wrong
        }

    }

    private fun startProgressActivity(){
        startActivityForResult(Intent(this, ShowProgressActivity::class.java).apply{
            putExtra(Constants.IS_CLOCKED_IN, isCLockedIn)
        }, CANCEL_REQUEST)
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
    }

    private fun clockIn(){
        dashboardViewModel.clockIn().observe(this, Observer {
            when(it.status){
                Status.LOADING -> {
                    progressBarDashBoard.visibility = View.VISIBLE
                }

                Status.SUCCESS -> {
                    progressBarDashBoard.visibility = View.GONE

                    if(it.statusCode == 201) {
                        isCLockedIn = true
                        btnClockInOut.text = resources.getString(R.string.txt_lbl_clockOut)
                    } else if(it.statusCode == 400){
                        //already clocked in
                        showSnackBar("Already Clocked In, Click again to Clock Out first!")
                        isCLockedIn = true
                        btnClockInOut.text = resources.getString(R.string.txt_lbl_clockOut)
                    }
                }

                Status.ERROR -> {
                    showSnackBar("Something went wrong while connecting to remote!")
                }
            }
        })
    }

    private fun clockOut(){
        dashboardViewModel.clockOut().observe(this, Observer {
            when(it.status){
                Status.LOADING -> {
                    progressBarDashBoard.visibility = View.VISIBLE
                }

                Status.SUCCESS -> {
                    progressBarDashBoard.visibility = View.GONE

                    if(it.statusCode == 201) {
                        isClockedOut = true
                        btnClockInOut.visibility = View.GONE
                    } else if(it.statusCode == 400){
                        //already clocked out
                        showSnackBar("Clock In first!")
                    }
                }

                Status.ERROR -> {
                    showSnackBar("Something went wrong while connecting to remote!")
                }
            }
        })
    }

    private fun showSnackBar(str: String){
        Snackbar.make(constraintLayout, str, Snackbar.LENGTH_LONG).show()
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == CANCEL_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {
                if(!isCLockedIn) {
                    clockIn()
                }else{
                    clockOut()
                }

            } else if(resultCode == Activity.RESULT_CANCELED){
                //do nothing
            }
        }
    }

    companion object{
        val CANCEL_REQUEST get() = 450
    }
}
