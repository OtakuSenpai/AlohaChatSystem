package com.github.otakusenpai.alohachat.base.message_parse

// Nick@IP together is called Prefix

class Prefix() {
    constructor(data: String) : this() {
        originalPrefix = data
        parse(data)
    }

    fun parse(data: String) {
        var temp = data
        nick = temp.substring(0,temp.indexOf('@'))
        temp = temp.substring(temp.indexOf('@') + 1,temp.length)
        ip = temp.substring(0, temp.indexOf(':'))
        temp = temp.substring(temp.indexOf(':') + 1,temp.length)
        port  = temp
    }

    override fun toString() = "$nick@$ip:$port"

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Prefix

        if (nick != other.nick) return false
        if (ip != other.ip) return false
        if (port != other.port) return false
        if (originalPrefix != other.originalPrefix) return false

        return true
    }

    override fun hashCode(): Int {
        var result = nick.hashCode()
        result = 31 * result + ip.hashCode()
        result = 31 * result + port.hashCode()
        result = 31 * result + originalPrefix.hashCode()
        return result
    }

    lateinit var nick: String    // Nick string is the name of the person sending it, eg: Neel
    lateinit var ip: String      // IP is ip of the person sending it
    lateinit var port: String    // Port number
    lateinit var originalPrefix: String
}