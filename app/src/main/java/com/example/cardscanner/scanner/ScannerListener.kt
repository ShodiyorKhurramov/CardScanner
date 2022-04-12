
package com.example.cardscanner.scanner


interface ScannerListener {
    fun onDetected(detections: String)
    fun onStateChanged(var1: String, var2: Int)
}