package com.example.portselector

import java.util.Random

object PortRepository {
    private val random = Random()

    fun getPorts(): List<Port> {
        return listOf(
            Port("1", "Port A", "Port A (FR)", random.nextBoolean()),
            Port("2", "Port B", "Port B (FR)", random.nextBoolean()),
            Port("3", "Port C", "Port C (FR)", random.nextBoolean())
        )
    }
}
