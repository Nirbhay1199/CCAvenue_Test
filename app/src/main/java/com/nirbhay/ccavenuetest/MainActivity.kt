package com.nirbhay.ccavenuetest

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.nirbhay.ccavenuetest.Utlity.AvenuesParams
import com.nirbhay.ccavenuetest.Utlity.Constants
import com.nirbhay.ccavenuetest.Utlity.ServiceUtility
import com.nirbhay.ccavenuetest.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var order_Id: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.pay.setOnClickListener {

            val amount = binding.amount.text

            val vAccessCode = ServiceUtility.chkNull(Constants.access_code).toString().trim()
            val vMerchantId = ServiceUtility.chkNull(Constants.merchantId).toString().trim()
            val vCurrency = ServiceUtility.chkNull(Constants.currency).toString().trim()
            val vAmount = ServiceUtility.chkNull(amount).toString().trim()

            if (vAccessCode != "" && vMerchantId != "" && vCurrency != "" && vAmount != ""){

                val intent = Intent(this, WebViewActivity::class.java)
                intent.putExtra(AvenuesParams.ACCESS_CODE, ServiceUtility.chkNull(Constants.access_code).toString().trim())
                intent.putExtra(AvenuesParams.MERCHANT_ID, ServiceUtility.chkNull(Constants.merchantId).toString().trim())
                intent.putExtra(AvenuesParams.ORDER_ID, ServiceUtility.chkNull(order_Id).toString().trim())
                intent.putExtra(AvenuesParams.CURRENCY, ServiceUtility.chkNull(Constants.currency).toString().trim())
                intent.putExtra(AvenuesParams.AMOUNT, ServiceUtility.chkNull(amount).toString().trim())

                intent.putExtra(AvenuesParams.REDIRECT_URL, ServiceUtility.chkNull(Constants.redirectUrl).toString().trim())
                intent.putExtra(AvenuesParams.CANCEL_URL, ServiceUtility.chkNull(Constants.cancelUrl).toString().trim())
                intent.putExtra(AvenuesParams.RSA_KEY_URL, ServiceUtility.chkNull(Constants.rsaKeyUrl).toString().trim())

                startActivity(intent)

            }else{
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
            }


        }


    }

    override fun onStart() {
        super.onStart()
        val randomNum = ServiceUtility.randInt(0, 9999999)
        order_Id = randomNum.toString()

    }
}