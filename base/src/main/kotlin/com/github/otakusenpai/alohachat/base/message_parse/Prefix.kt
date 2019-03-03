package com.github.otakusenpai.alohachat.base.message_parse

// Nick@IP together is called Prefix

class Prefix() {
    constructor(data: String, channelMsg: Boolean) : this() {
        originalPrefix = data
        parse(data, channelMsg)
    }

    fun parse(data: String, channelMsg: Boolean) {
        if(!channelMsg) {
            var wordList = data.split('@')
            nick = wordList[0]
            ip = wordList[1]
        } else {
            nick = data
            ip = ""
        }
    }

    fun getData() : String = originalPrefix

    lateinit var nick: String    // Nick string is the name of the person sending it, eg: Neel
    lateinit var ip: String      // IP is ip of the person sending it
    private lateinit var originalPrefix: String
}