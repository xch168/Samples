package com.github.xch168.samples

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.github.xch168.samples.lifecycles.LifecycleDemoActivity
import com.github.xch168.samples.retrofitcoroutines.RetrofitCoroutinesActivity
import com.github.xch168.samples.room.RoomDemoActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun openRetrofitCoroutines(view: View) {
        RetrofitCoroutinesActivity.open(this)
    }

    fun openLifecyclePage(view: View) {
        LifecycleDemoActivity.open(this)
    }

    fun openRoomDemo(view: View) {
        RoomDemoActivity.open(this)
    }
}
