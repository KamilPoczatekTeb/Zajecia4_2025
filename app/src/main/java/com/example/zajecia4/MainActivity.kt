package com.example.zajecia4

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(), SensorEventListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val saveButton = findViewById<Button>(R.id.saveButton)
        val myNameView = findViewById<TextView>(R.id.myNameView)
        val textView = findViewById<TextView>(R.id.textView)

        val myNameSharedPreferences = getSharedPreferences("MY_DATA", Context.MODE_PRIVATE)
        textView.text = myNameSharedPreferences.getString("MY_NAME", "Bezimienny")


        saveButton.setOnClickListener {
            myNameSharedPreferences.edit().putString("MY_NAME", myNameView.text.toString()).apply()
            textView.text = myNameView.text
        }


        val sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        val gyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)
        sensorManager.registerListener(this, gyroscope, SensorManager.SENSOR_DELAY_NORMAL)

    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
    }

    override fun onSensorChanged(sensorEvent: SensorEvent) {
        if (sensorEvent.sensor.type == Sensor.TYPE_GYROSCOPE) {
            val x = sensorEvent.values[0]
            val b = sensorEvent.values[1]
            val c = sensorEvent.values[2]

            println("=======")
            println(x)
            println(b)
            println(c)
        }


    }
}