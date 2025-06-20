package com.ryvk.androidarchitecturecomponents

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LiveDataViewModel: ViewModel() {

    private var _seconds = MutableLiveData<Int>()
    private var _finished = MutableLiveData<Boolean>()

    fun seconds() : LiveData<Int>{
        return _seconds
    }

    fun _finished () : LiveData<Boolean>{
        return _finished
    }

    fun startCountdown(){
        object : CountDownTimer(10000,100){
            override fun onTick(millisUntilFinished: Long) {
                val time = millisUntilFinished / 1000

                _seconds.value = time.toInt()
            }

            override fun onFinish() {
                _finished.value = true
            }

        }.start()
    }

}