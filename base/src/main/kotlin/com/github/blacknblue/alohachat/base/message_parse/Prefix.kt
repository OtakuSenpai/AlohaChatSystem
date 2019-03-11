package com.github.blacknblue.alohachat.base.message_parse

// Nick@IP together is called Prefix

class Prefix() {
    constructor(data: String, channelMsg: Boolean) : this() {
        originalPrefix = data
        parse(data, channelMsg)
    }

    fun parse(data: String, channelMsg: Boolean) {
        var temp = data
        nick = temp.substring(0,temp.indexOf('@'))
        temp = temp.substring(temp.indexOf('@') + 1,temp.length)
        ip = temp.substring(0,temp.indexOf(':'))
        temp = temp.substring(temp.indexOf(':') + 1,temp.length)
        port = ip
    }

    fun getData() : String = originalPrefix

    lateinit var nick: String    // Nick string is the name of the person sending it, eg: Neel
    lateinit var ip: String      // IP is ip of the person sending it
    private lateinit var originalPrefix: String
}