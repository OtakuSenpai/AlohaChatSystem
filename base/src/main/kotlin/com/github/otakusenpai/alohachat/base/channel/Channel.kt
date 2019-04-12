package com.github.otakusenpai.alohachat.base.channel

import com.github.otakusenpai.alohachat.base.client.Alohaclient
import com.github.otakusenpai.alohachat.base.helpers.findPublicIP
import com.github.otakusenpai.alohachat.base.helpers.matchPrefix
import com.github.otakusenpai.alohachat.base.message_parse.ChatMsg
import com.github.otakusenpai.alohachat.base.message_parse.Prefix
import java.util.*

class Channel() {

    constructor(channelName: String, port: Int) : this() {
        this.channelPrefix = Prefix(channelName + "@" + findPublicIP() + ":" + port.toString())
        this.channelName = channelName
    }

    fun matchChannels(other: Channel): Boolean = this.channelPrefix == other.channelPrefix

    suspend fun passMsgToClients() {
        for(msg in messageList) {
            if(matchPrefix(msg.msgdata.senderPrefix,channelPrefix)) {
                for(client in clientList) {
                    if(matchPrefix(client.prefix, msg.receiverPrefix))
                        client.receiveMsg(msg,this@Channel)
                }
            }
        }
    }

    fun onJoinClient(client: Alohaclient) = clientList.add(client)

    fun onQuitClient(client: Alohaclient) = clientList.remove(client)

    fun ifClientIsThere(prefix: Prefix): Boolean {
        var found = false
        for(client in clientList) {
            if(matchPrefix(prefix, client.prefix))
                found = true
        }
        return found
    }

    fun isEmpty() = clientList.isEmpty()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Channel) return false

        if (messageList != other.messageList) return false
        if (clientList != other.clientList) return false
        if (channelName != other.channelName) return false
        if (channelPrefix != other.channelPrefix) return false

        return true
    }

    override fun hashCode(): Int {
        var result = messageList.hashCode()
        result = 31 * result + clientList.hashCode()
        result = 31 * result + channelName.hashCode()
        result = 31 * result + channelPrefix.hashCode()
        return result
    }

    var messageList: Queue<ChatMsg> = LinkedList()
    var clientList = mutableListOf<Alohaclient>()
    lateinit var channelName: String
    var channelPrefix = Prefix()
}