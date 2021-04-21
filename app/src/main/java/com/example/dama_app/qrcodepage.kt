@file:Suppress("DEPRECATION")

package com.example.dama_app

import android.graphics.Point
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.ImageView
import androidmads.library.qrgenearator.QRGContents
import androidmads.library.qrgenearator.QRGEncoder
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.WriterException
import com.kakao.sdk.user.UserApiClient
import com.kakao.sdk.user.model.User


private const val TAG = "MyActivity"

class qrcodepage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qrcodepage)

        val qrImage = findViewById<ImageView>(R.id.qr_image)
        val manager = getSystemService(WINDOW_SERVICE) as WindowManager
        val display = manager.defaultDisplay
        val point = Point()
        display.getSize(point)
        val width: Int = point.x
        val height: Int = point.y
        var smallerDimension: Int = if (width < height) width else height
        smallerDimension = smallerDimension * 3 / 4


        UserApiClient.instance.me { user: User?, error ->

            val dama_num : String = Integer.toString(201801285);
            val kakao_usernum : String = "${user?.id}";
            val dama_usernum : String = dama_num + kakao_usernum;
            val qrgEncoder = QRGEncoder(dama_usernum, null, QRGContents.Type.TEXT, smallerDimension);

            try {
                // Getting QR-Code as Bitmap
                val bitmap = qrgEncoder.encodeAsBitmap()
                // Setting Bitmap to ImageView
                qrImage.setImageBitmap(bitmap)
            } catch (e: WriterException) {
                Log.v(TAG, e.toString())
            }
        }
    }
}