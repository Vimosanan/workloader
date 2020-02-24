package com.vimosanan.workloaderapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.vimosanan.workloaderapplication.R
import com.vimosanan.workloaderapplication.model.Account
import kotlinx.android.synthetic.main.activity_activity.*
import org.koin.android.viewmodel.ext.android.viewModel

class DashboardActivity : AppCompatActivity() {

    private val dashboardViewModel: DashboardViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_activity)


        dashboardViewModel.loadData()


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
}
