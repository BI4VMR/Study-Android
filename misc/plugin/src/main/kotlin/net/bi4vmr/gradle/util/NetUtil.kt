package net.bi4vmr.gradle.util

import java.net.Socket

/**
 * 网络相关工具。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
object NetUtil {

    fun scanByTCP(ip: String, port: Int): Boolean {
        var socket: Socket? = null
        try {
            socket = Socket(ip, port)
            socket.soTimeout = 2000
            return true
        } catch (e: Exception) {
            System.err.println("TCP connect failed! IP:[$ip] Port:[$port]")
            System.err.println("TCP connect failed! Reason:[${e.message}]")
            return false
        } finally {
            socket?.close()
        }
    }
}
