package com.zj.android.workflow

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zj.workflow.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        println("Here3")
    }
}
