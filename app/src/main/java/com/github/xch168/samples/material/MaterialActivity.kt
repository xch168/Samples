package com.github.xch168.samples.material

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.xch168.samples.R
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.shape.ShapeAppearanceModel

/**
 * @see [https://www.jianshu.com/p/96d96c86fff0]
 * @see [https://mp.weixin.qq.com/s/6b5hIMxqz2WEutcxRbRfhg]
 * @see [https://github.com/material-components/material-components-android/blob/487e321497884ccb9ab93fc7fb31d81bd353423c/docs/components/Slider.md]
 */
class MaterialActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_material)

        val circleImageView = findViewById<ShapeableImageView>(R.id.circleImageView)
        circleImageView.shapeAppearanceModel = ShapeAppearanceModel.builder()
            .setAllCornerSizes(ShapeAppearanceModel.PILL)
            .build()
    }

    companion object {
        fun open(context: Context) {
            val intent = Intent(context, MaterialActivity::class.java)
            context.startActivity(intent)
        }
    }
}