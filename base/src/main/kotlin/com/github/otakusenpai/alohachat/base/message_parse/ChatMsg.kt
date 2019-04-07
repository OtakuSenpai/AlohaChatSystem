package com.github.otakusenpai.alohachat.base.message_parse

import com.github.otakusenpai.alohachat.base.helpers.*

class ChatMsg() {

    constructor(msg: String) :  this() {
        originalMsg = msg
        msgdata = parseStrToMsgData(msg)
        receiverPrefix = msgdata.receiverPrefix
    }

    constructor(msgData: MsgData) : this() {
        originalMsg = msgData.toString()
        receiverPrefix = msgdata.receiverPrefix
    }

    override fun toString(): String = msgDataToStr(msgdata)

    fun print() {
        println("Receiver Address: ${msgdata.receiverPrefix.originalPrefix}")
        println("Sender Address: ${msgdata.senderPrefix.originalPrefix}")
        println("Msg TYPE: ${msgdata.numeric}")
        println("Chat or DM: ${msgdata.channelMsg}")
        println("Data: ${msgdata.data} ")
    }

    var msgdata = MsgData()
    lateinit var receiverPrefix: Prefix
    var originalMsg: String = ""
}



