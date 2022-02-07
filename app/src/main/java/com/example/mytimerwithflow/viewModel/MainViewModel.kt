package com.example.mytimerwithflow.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mytimerwithflow.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class MainViewModel() : ViewModel() {
    val liveData : MutableLiveData<String> = MutableLiveData()
    private val timestampProvider = object : TimestampProvider {
        override fun getMilliseconds(): Long {
            return System.currentTimeMillis()
        }
    }
    private val stopwatchStateHolder: StopwatchStateHolder = StopwatchStateHolder(
                                                                                StopwatchStateCalculator(
                                                                                timestampProvider,
                                                                                ElapsedTimeCalculator(timestampProvider)
                                                                            ),
                                                                                ElapsedTimeCalculator(timestampProvider),
                                                                                TimestampMillisecondsFormatter()
                                                                            )
    fun start() {
        stopwatchStateHolder.start()
        startJob()
    }

    private fun startJob() {
        viewModelScope.launch {
            while (isActive) {
                liveData.value = stopwatchStateHolder.getStringTimeRepresentation()
                delay(10)
            }
        }
    }

    fun pause() {
        stopwatchStateHolder.pause()

    }

    fun stop() {
        stopwatchStateHolder.stop()
        clearValue()
    }

    private fun clearValue() {
        liveData.value = ""
    }
}