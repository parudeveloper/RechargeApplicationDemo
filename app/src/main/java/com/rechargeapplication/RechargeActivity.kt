package com.rechargeapplication

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rechargeapplication.databinding.ActivityRechargeBinding

class RechargeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRechargeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRechargeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
    }

    private fun initViews() {

        with(binding) {
            val mobileNo = etMobileNO.text.toString()
            btnPay.setOnClickListener(){
                val rechargeAmount = etRechargeAmount.text.toString()
                val amount = rechargeAmount.toIntOrNull()
                if (amount != null) {
                    proceedRecharge(mobileNo,amount)
                }
                binding.etMobileNO.text?.clear()
                binding.etRechargeAmount.text?.clear()
            }
        }
    }

    private fun proceedRecharge(mobileNo : String,amount: Int) {
        val intent = Intent(this, PaymentActivity::class.java)
        intent.putExtra(Constants.AMOUNT_OF_RECHARGE, amount)
        intent.putExtra(Constants.MOBILE_NO, mobileNo)
        startActivityForResult(intent, Constants.STATUS_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == Constants.STATUS_CODE && resultCode == Activity.RESULT_OK) {
            val statusOfPayment = data?.getBooleanExtra(Constants.STATUS, false)
            statusOfPayment?.let { currentStatus ->
                if (currentStatus) {
                    //Success message
                    binding.paymentStatus.text = "Success"
                    binding.paymentStatus.setTextColor(Color.GREEN)
                } else {
                    //failure message
                    binding.paymentStatus.text = "Failed"
                    binding.paymentStatus.setTextColor(Color.RED)
                }
            }
        }
    }
}

