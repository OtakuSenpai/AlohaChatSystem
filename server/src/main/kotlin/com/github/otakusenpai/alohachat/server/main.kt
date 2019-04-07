package com.github.otakusenpai.alohachat.server

import com.github.otakusenpai.alohachat.base.helpers.findPublicIP

fun main() {
    val server = AlohaServ
    server.init("Test-Server",findPublicIP(),"34001")
    server.run()
}