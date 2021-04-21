package com.example.dama_app


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.kakao.sdk.user.UserApiClient
import java.net.HttpURLConnection
import java.net.URL


private const val TAG = "MyActivity"




class SecondActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val id = findViewById<TextView>(R.id.id)
        val nickname = findViewById<TextView>(R.id.nickname)
        val iv_image = findViewById<ImageView>(R.id.iv_image)
        val qr_btn = findViewById<ImageButton>(R.id.qr_button)





        UserApiClient.instance.me { user, error ->


            if (error != null) {
                Log.e(TAG, "사용자 정보 요청 실패", error)
            }
            else if (user != null) {
                Log.i(TAG, "사용자 정보 요청 성공" +
                        "\n회원번호: ${user.id}" +
                        "\n이메일: ${user.kakaoAccount?.email}" +
                        "\n닉네임: ${user.kakaoAccount?.profile?.nickname}" +
                        "\n프로필사진: ${user.kakaoAccount?.profile?.thumbnailImageUrl}")

                id.text = "회원번호: ${user.id}"
                nickname.text = "닉네임: ${user.kakaoAccount?.profile?.nickname}"

                val imageUrl: String = "${user.kakaoAccount?.profile?.thumbnailImageUrl}";
                 Glide.with(this).load(imageUrl).into(iv_image);

            }
        }

        qr_btn.setOnClickListener {
            val intent = Intent(this, qrcodepage::class.java)
            startActivity(intent)
        }
    }

}

