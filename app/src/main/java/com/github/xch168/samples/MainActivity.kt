package com.github.xch168.samples

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.github.xch168.samples.retrofitcoroutines.RetrofitCoroutinesActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun openRetrofitCoroutines(view: View) {
        RetrofitCoroutinesActivity.open(this)
    }
}
