package com.github.otakusenpai.alohachat.base.channel

import com.github.otakusenpai.alohachat.base.message_parse.Prefix

class DMChannel(channelName: String,port: Int) : Channel(channelName, port) {

    constructor(channelName: String, port: Int, sendPrefix: Prefix) : this(channelName,port) {
        this.dmORgroup = true
        receiverPrefix = sendPrefix
    }

    lateinit var receiverPrefix: Prefix
}