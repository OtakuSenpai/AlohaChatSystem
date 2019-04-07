package com.github.otakusenpai.alohachat.server

import com.github.otakusenpai.alohachat.base.client.Alohaclient
import com.github.otakusenpai.alohachat.base.connection.BasicConnection
import com.github.otakusenpai.alohachat.base.helpers.matchPrefix
import com.github.otakusenpai.alohachat.base.message_parse.ChatMsg
import com.github.otakusenpai.alohachat.base.channel.Channel
import com.github.otakusenpai.alohachat.server.others.segragateInput
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking

object AlohaServ {

    fun init(servName: String, servIP: String, servPort: String) {
        conn = BasicConnection(servPort.toInt(),servIP)
        serverPort = servPort
        serverIP = servIP
        serverName = servName
        running = true
    }

    suspend fun passMsgToChannels(msgList: MutableList<ChatMsg>) = coroutineScope {
        // Loop through the message list
        try {
            val one = async {
                for(msg in msgList) {
                    // Loop through the channel list
                    for(channels in channelList)
                    // If channel receiver prefix match msg receiver prefix, then enqueue in channel msg list
                        if(matchPrefix(msg.msgdata.receiverPrefix, channels.channelPrefix)) {
                            channels.messageList.add(msg)
                        }
                }
            }
            one.join()
        } catch(e: Exception) {
            throw e
        }
    }


    fun run() = runBlocking {
        var tempInput: String? = ""
        var msgList: MutableList<ChatMsg>
        try {
            while(running) {
                tempInput = conn.receiveUTF8Data()
                if(tempInput == null)
                    throw Exception("Error in Server module : Received null string!!!")
                else {
                    msgList = segragateInput(tempInput)
                    passMsgToChannels(msgList)
                }
            }
        } catch(e : Exception) {
            e.printStackTrace()
        }
    }

    suspend fun sendChannelMsgsToClients() = coroutineScope {
        val one = async {
            for(channel in channelList) {
                for(client in clientList) {
                    if()
                }
            }
        }
    }

    private lateinit var conn : BasicConnection
    private lateinit var channelList : MutableList<Channel>
    private lateinit var clientList : MutableList<Alohaclient>
    var serverPort = ""
    var serverIP = ""
    var serverName: String = ""
    var running = false
}