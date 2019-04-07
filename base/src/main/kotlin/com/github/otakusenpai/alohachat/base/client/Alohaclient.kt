package com.github.otakusenpai.alohachat.base.client

import com.github.otakusenpai.alohachat.base.connection.BasicConnection
import com.github.otakusenpai.alohachat.base.helpers.findPublicIP
import com.github.otakusenpai.alohachat.base.message_parse.Prefix
import com.github.otakusenpai.alohachat.base.channel.Channel
import com.github.otakusenpai.alohachat.base.helpers.MsgData
import com.github.otakusenpai.alohachat.base.helpers.Numerics
import com.github.otakusenpai.alohachat.base.helpers.msgDataToStr
import com.github.otakusenpai.alohachat.base.message_parse.ChatMsg

class Alohaclient() {

    constructor(nick: String, ip: String, port: String) : this() {
        try {
            myIp = findPublicIP()
            this.prefix = Prefix("$nick@${this.myIp}:$port")
            serverIp = ip
            this.port = port
            conn = BasicConnection(ip.toInt(), port)
        } catch(e: Exception) {
            throw e
        }
    }

    suspend fun sendMsg(chatmsg: ChatMsg) {
        conn.sendDataAsync(chatmsg.toString())
    }

    suspend fun joinChannel(channel: Channel) {
        channels.add(channel)
        val msg = MsgData(Numerics.Join,channel.dmORgroup!!,prefix,channel.channelPrefix,
            "User ${prefix.nick} has joined !!! \n")
        conn.sendDataAsync(msgDataToStr(msg))
    }

    suspend fun partChannel(channel: Channel) {
        val msg = MsgData(Numerics.Part,channel.dmORgroup as Boolean,
            prefix,channel.channelPrefix,"User ${prefix.nick} has parted !!! \n")
        conn.sendDataAsync(msgDataToStr(msg))
        for(chan in 0 until channels.size) {
            if(channel.channelName == channels[chan].channelName)
                channels.removeAt(chan)
        }
    }

    suspend fun quitServer() {
        for(channel in channels) {
            val msg = MsgData(Numerics.Quit,channel.dmORgroup as Boolean,
                prefix,channel.channelPrefix,"User ${prefix.nick} has quit !!! \n")
            conn.sendDataAsync(msgDataToStr(msg))
        }
        channels.clear()
        conn.Disconnect()
    }

    suspend fun sendPrivMsg(data: String, channel: Channel) {
        val msg = MsgData(Numerics.PrivMsg, channel.dmORgroup as Boolean,
            prefix,channel.channelPrefix, data)
        conn.sendDataAsync(msgDataToStr(msg))
    }

    suspend fun sendNickChange(nick: String, channel: Channel) {
        val oldNick = prefix.nick
        prefix.nick = nick
        val msg = MsgData(Numerics.NickChange, channel.dmORgroup as Boolean,
            prefix,channel.channelPrefix, " User $oldNick has changed nick to $nick !!! \n")
        conn.sendDataAsync(msgDataToStr(msg))
    }

    suspend fun receiveMsg(msg: ChatMsg,channel: Channel) {
        for(chan in channels) {
            if(chan.equals(channel))
                chan.messageList.add(msg)
        }
    }

    lateinit var channels: MutableList<Channel>
    private lateinit var conn: BasicConnection
    private lateinit var serverIp: String
    lateinit var myIp: String
    lateinit var port: String
    lateinit var prefix: Prefix
}