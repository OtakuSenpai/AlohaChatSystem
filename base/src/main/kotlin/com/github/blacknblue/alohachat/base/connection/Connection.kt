package com.github.blacknblue.alohachat.base.connection

import io.ktor.network.sockets.Socket
import kotlinx.coroutines.io.ByteReadChannel
import kotlinx.coroutines.io.ByteWriteChannel

abstract class Connection {

    abstract fun Connect()

    abstract suspend fun sendDataAsync(data: String)
    abstract fun sendData(data: String)
    abstract suspend fun receiveData(): String?
    abstract suspend fun receiveUTF8Data(): String?

    fun Disconnect() {
        output.close(null)
        socket.close()
        connected = false
    }

    open var connected: Boolean = false
    open lateinit var output : ByteWriteChannel
    open lateinit var input: ByteReadChannel
    open lateinit var socket: Socket
    open var port: Int = 6667
    open var address: String = ""
}
