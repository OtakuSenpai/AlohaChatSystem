package com.github.otakusenpai.alohachat.client

import com.github.otakusenpai.alohachat.base.client.Alohaclient
import com.github.otakusenpai.alohachat.base.connection.BasicConnection


object AlohaClient {

    fun init(nick: String, ip: String, port: String) {
        client = Alohaclient(nick,ip,port)
        conn = BasicConnection(port.toInt(),client.serverIp)
    }



    lateinit var conn: BasicConnection
    lateinit var client : Alohaclient
}