package com.example.mytimerwithflow.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.mytimerwithflow.*
import com.example.mytimerwithflow.viewModel.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val vm: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.text_time)
        vm.liveData.observe (this){
            textView.text = it
        }

        findViewById<Button>(R.id.button_start).setOnClickListener {
            vm.start()
        }
        findViewById<Button>(R.id.button_pause).setOnClickListener {
            vm.pause()
        }
        findViewById<Button>(R.id.button_stop).setOnClickListener {
            vm.stop()
        }
    }
}