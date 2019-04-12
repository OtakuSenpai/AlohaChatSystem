package com.github.otakusenpai.alohachat.base.connection

import io.ktor.network.selector.ActorSelectorManager
import io.ktor.network.sockets.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import java.net.*
import java.nio.ByteBuffer
import java.nio.charset.StandardCharsets

class BasicConnection: Connection {

    constructor(Port: Int,Address: String): super() {
        runBlocking {
            try {
                port = Port
                address = Address
                socket = aSocket(ActorSelectorManager(Dispatchers.IO)).
                        tcp().connect(InetSocketAddress(address,port))
                input = socket.openReadChannel()
                output = socket.openWriteChannel(autoFlush = true)
                connected = true
            } catch (e: Throwable) {
                e.printStackTrace()
                socket.close()
                connected = false
            }
        }
    }

    override fun Connect() = runBlocking {
        if(!connected) {
            try {
                socket = aSocket(ActorSelectorManager(Dispatchers.IO)).
                        tcp().connect(InetSocketAddress(address,port))
                input = socket.openReadChannel()
                output = socket.openWriteChannel(autoFlush = true)
                connected = true
            } catch(e: Throwable) {
                e.printStackTrace()
                socket.close()
                connected = false
            }
        }
    }

    override suspend fun sendDataAsync(data: String)  {
        try {
            output.writeAvailable(ByteBuffer.wrap(data.toByteArray()))
            // println("Sending: ${data}")
        } catch (e: Throwable) {
            e.printStackTrace()
            socket.close()
            connected = false
        }
    }

    override fun sendData(data: String) {
        try {
            runBlocking {
                output.writeAvailable(ByteBuffer.wrap(data.toByteArray()))
                // println("Sending: ${data}")
            }
        } catch (e: Throwable) {
            e.printStackTrace()
            socket.close()
            connected = false
        }
    }

    @Deprecated("This function us deprecated, use receiveUTF8Data(): String?")
    override suspend fun receiveData(): String? {
        var data: String? = null
        try {
            // 512 here is the MAX size of a IRC message
            val bbToString = { bb: ByteBuffer ->
                try {
                    var decoder = StandardCharsets.US_ASCII.newDecoder()
                    var charBuffer = decoder.decode(bb)
                    data = charBuffer.toString()
                } catch(e: Throwable) {
                    throw e
                }
            }
            input.read{ byteBuffer: ByteBuffer -> bbToString(byteBuffer) }
            if(data == null) {
                throw Exception("Error: At receiveData(): String? in " +
                        "com.github.otakusenpai.aaghora.irc.BasicConnectionKt: Didn't receive data from connection!")
            }
            // println("Data size = ${data?.length}")
        } catch(e: Exception) {
            e.printStackTrace()
        }
        return data
    }

    override suspend fun receiveUTF8Data(): String? {
        var data: String? = null
        var cB = StringBuilder(10000)
        try {
            if(!input.readUTF8LineTo(cB,10000))
                throw Exception("Error: At receiveData(): String? in " +
                        "com.github.otakusenpai.aaghora.irc.BasicConnectionKt: Didn't receive data from connection!")
            else data = cB.toString()
        } catch (e: Exception) {
            e.printStackTrace()
            socket.close()
            connected = false
        }
        return data
    }
}