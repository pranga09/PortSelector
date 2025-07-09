package com.example.portselector

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val handler = Handler(Looper.getMainLooper())
    private val pollInterval: Long = 5000

    private val selectedPorts = mutableSetOf<String>()
    private var isFrench = false

    private lateinit var listView: ListView
    private lateinit var languageButton: Button
    private var ports: List<Port> = emptyList()

    private val pollRunnable = object : Runnable {
        override fun run() {
            loadPorts()
            handler.postDelayed(this, pollInterval)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById(R.id.port_list)
        languageButton = findViewById(R.id.lang_toggle)

        languageButton.setOnClickListener {
            isFrench = !isFrench
            updateList()
        }

        pollRunnable.run()
    }

    private fun loadPorts() {
        // Replace with real API in future
        ports = PortRepository.getPorts()
        updateList()
    }

    private fun updateList() {
        val adapter = PortAdapter(ports, isFrench, selectedPorts) { id ->
            if (selectedPorts.contains(id)) selectedPorts.remove(id)
            else selectedPorts.add(id)
        }
        listView.adapter = adapter
        languageButton.text = if (isFrench) "English" else "Français"
    }

    override fun onDestroy() {
        handler.removeCallbacks(pollRunnable)
        super.onDestroy()
    }
}
