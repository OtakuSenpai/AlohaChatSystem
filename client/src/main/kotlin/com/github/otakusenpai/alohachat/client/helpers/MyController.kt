package com.github.otakusenpai.alohachat.client.helpers

import com.github.otakusenpai.alohachat.client.AlohaClient
import tornadofx.Controller

class MyController : Controller() {
    val client = AlohaClient

    fun connect(nick: String, ip: String, port: String) {
        client.init(nick,ip,port)
    }
}