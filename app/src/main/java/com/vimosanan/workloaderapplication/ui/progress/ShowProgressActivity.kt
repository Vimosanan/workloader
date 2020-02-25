package com.vimosanan.workloaderapplication.ui.progress

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import com.vimosanan.workloaderapplication.R
import com.vimosanan.workloaderapplication.app.Constants
import kotlinx.android.synthetic.main.activity_progress.*


class ShowProgressActivity: AppCompatActivity() {
    private var isClockedIn = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_progress)

        isClockedIn = intent.getBooleanExtra(Constants.IS_CLOCKED_IN, false)

        setButtonText()
        showProgressBar(10_000, 10)

        txtCancel.setOnClickListener {
            setResult(Activity.RESULT_CANCELED, Intent())
            finish()
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }
    }

    private fun showProgressBar(length_in_milliseconds: Long, period_in_milliseconds: Long){
        object : CountDownTimer(length_in_milliseconds, period_in_milliseconds) {
            override fun onTick(millisUntilFinished_: Long) {
                progressBar.progress = (((length_in_milliseconds - millisUntilFinished_)/ length_in_milliseconds.toDouble())* 100).toInt()
            }

            override fun onFinish() {
                setResult(Activity.RESULT_OK, Intent())
                finish()
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
            }
        }.start()
    }

    private fun setButtonText(){
        if(!isClockedIn){
            txtClockInOut.text = resources.getString(R.string.txt_lbl_clockIn)
        } else {
            txtClockInOut.text = resources.getString(R.string.txt_lbl_clockedOut)
        }
    }
}