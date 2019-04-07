package com.github.otakusenpai.alohachat.base.channel

import com.github.otakusenpai.alohachat.base.helpers.findPublicIP
import com.github.otakusenpai.alohachat.base.message_parse.ChatMsg
import com.github.otakusenpai.alohachat.base.message_parse.Prefix
import java.util.*

abstract class Channel() {

    constructor(channelName: String, port: Int) : this() {
        this.channelPrefix = Prefix(channelName + "@" + findPublicIP() + ":" + port.toString())
        this.channelName = channelName
    }

    fun matchChannels(other: Channel): Boolean = this.channelPrefix == other.channelPrefix

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Channel

        if (messageList != other.messageList) return false
        if (dmORgroup != other.dmORgroup) return false
        if (channelName != other.channelName) return false
        if (channelPrefix != other.channelPrefix) return false

        return true
    }

    override fun hashCode(): Int {
        var result = messageList.hashCode()
        result = 31 * result + (dmORgroup?.hashCode() ?: 0)
        result = 31 * result + channelName.hashCode()
        result = 31 * result + channelPrefix.hashCode()
        return result
    }

    var messageList: Queue<ChatMsg> = LinkedList()

    var dmORgroup: Boolean? = null
    lateinit var channelName: String
    var channelPrefix = Prefix()
}