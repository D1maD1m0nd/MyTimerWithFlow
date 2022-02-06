package com.example.mytimerwithflow

interface TimestampProvider {
    fun getMilliseconds(): Long
}