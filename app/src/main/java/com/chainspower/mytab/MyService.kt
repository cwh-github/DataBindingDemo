package com.chainspower.mytab

import android.app.Service
import android.content.Intent
import android.os.CountDownTimer
import android.os.IBinder
import android.util.Log
import android.widget.Toast

class MyService : Service() {

    lateinit var countDownTimer: CountDownTimer

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        countDownTimer = object : CountDownTimer(60 * 1000, 5000) {
            override fun onFinish() {
                Toast.makeText(this@MyService, "Finish", Toast.LENGTH_SHORT).show()
            }

            override fun onTick(millisUntilFinished: Long) {
                Toast.makeText(this@MyService, "Tick", Toast.LENGTH_SHORT).show()
                Log.d("Service", "onTick")
            }
        }
        countDownTimer.start()

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        countDownTimer.cancel()
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }


}
