package graphlib.vertex

import kotlin.random.Random

data class SimpleVertex(val id: String = randomString(32)) : IVertex {
    override fun toString(): String = id
    companion object {
        private val symbols = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toList()
        fun randomString(len: Int) = 1.rangeTo(len).map { symbols[Random.nextInt(symbols.size)] }.joinToString("")
    }
}
