package com.vatixdev.randotos

import org.osmdroid.util.GeoPoint
import kotlin.random.Random

object PathGenerator {

    fun generatePath(start: GeoPoint, steps: Int, amplitude: Double): List<GeoPoint> {
        val path = mutableListOf<GeoPoint>()
        path.add(start)

        var current = start
        for (i in 0 until steps) {
            val latOffset = Random.nextDouble(-amplitude, amplitude)
            val lngOffset = Random.nextDouble(-amplitude, amplitude)
            current = GeoPoint(current.latitude + latOffset, current.longitude + lngOffset)
            path.add(current)
        }

        // retour au point de départ
        path.add(start)
        return path
    }
}
