package com.example.cardscanner.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cardscanner.databinding.ActivityScanBinding
import com.example.cardscanner.scanner.Scanner
import com.example.cardscanner.scanner.ScannerListener
import com.example.cardscanner.utils.*


class ScanActivity : AppCompatActivity() {
    private lateinit var binding: ActivityScanBinding
    private var scanner: Scanner? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        try {
            initScanner()
        } catch (e: Exception) {
            closePage()
        }
    }

    private fun closePage() {
        val returnIntent = Intent()
        setResult(Activity.RESULT_CANCELED, returnIntent)
        finish()
    }

    private fun initScanner() {
        binding.myScanningView.startAnimation()
        binding.myScanningView.setOnDetectedListener(this, object : ScannerListener {

            override fun onDetected(detections: String) {
                val data= data(detections)

                    val returnIntent = Intent()
                    returnIntent.putExtra("data",data)
                    setResult(Activity.RESULT_OK, returnIntent)
                    finish()


//                val cardNumber =basicCard(detections)
//                    ?: (uzCard(detections) ?: (humoCard(detections) ?: (unionCard(detections)
//                        ?: (attoCard(detections) ?: ""))))
//                if (cardNumber.length > 5) {
//                    var cardExpire = expireDate(detections) ?: ""
//                    if (cardExpire.isEmpty()) cardExpire = expireDate(detections) ?: ""
//                    if (cardExpire.isNotEmpty()) {
//                        val returnIntent = Intent()
//                        returnIntent.putExtra("card_number", cardNumber.replace(" ", ""))
//                        returnIntent.putExtra("card_expire", cardExpire)
//                        returnIntent.putExtra("data",data)
//                        setResult(Activity.RESULT_OK, returnIntent)
//                        finish()
//                    }
//                }
            }

            override fun onStateChanged(var1: String, var2: Int) {

            }
        })
    }

    override fun onResume() {
        super.onResume()
        try {
            scanner?.scan()
        } catch (e: Exception) {
            closePage()
        }
    }

}