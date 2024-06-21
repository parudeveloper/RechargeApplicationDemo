package com.rechargeapplication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.SyncStateContract
import androidx.appcompat.app.AppCompatActivity
import com.rechargeapplication.databinding.ActivityPayementBinding

class PaymentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPayementBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPayementBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }

    private fun initViews() {
        with(binding) {
            val mobileNO = intent.getStringExtra(Constants.MOBILE_NO)
            tvMobileNO.text = "Enter Mobile No is $mobileNO"

            // we are getting the recharge info from previous screen
            val rechargeAmount = intent.getIntExtra(Constants.AMOUNT_OF_RECHARGE, 0)
            //and setting that amount to textview
            amountOfRecharge.text = "Recharge request of $rechargeAmount"


            btnSuccess.setOnClickListener {
                setPaymentResult(paymentDone = true)
            }
            btnFailure.setOnClickListener {
                setPaymentResult(paymentDone = false)
            }
        }
    }

    private fun setPaymentResult(paymentDone: Boolean) {
        val intent = Intent()
        intent.putExtra(Constants.STATUS, paymentDone)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }
}