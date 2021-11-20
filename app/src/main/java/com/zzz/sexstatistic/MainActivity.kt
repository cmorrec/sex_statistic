package com.zzz.sexstatistic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import android.content.Intent
import com.zzz.sexstatistic.view.auth.AuthActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Text(text = "Загрузка")
        }
        // TODO authorization
        if (true) {
            startActivity(
                Intent(applicationContext, AuthActivity::class.java)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
        } else {
            // TODO main activity
            startActivity(
                Intent(applicationContext, AuthActivity::class.java)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
        }
    }
}