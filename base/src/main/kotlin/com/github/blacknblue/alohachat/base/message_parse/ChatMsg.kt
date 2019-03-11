package com.github.blacknblue.alohachat.base.message_parse

import com.github.blacknblue.alohachat.base.helpers.MsgData
import com.github.blacknblue.alohachat.base.helpers.Numerics
import com.github.blacknblue.alohachat.base.helpers.retNumeric

class ChatMsg() {

    constructor(msg: String) :  this() {
        originalMsg = msg
        parse(originalMsg)
    }

    fun parse(data: String) {
        var list = data.split("\\s{1,}".toRegex())
        var datalist = list.subList(4,list.size)
        var senderPrefix = Prefix()
        var receiverPrefix = Prefix()
        var numeric = Numerics.None
        var channelMsg = false
        var actualData = ""

        // Arghya@193.33.34.54:34000 0 1 Avraneel@98.54.33.22:34000 : Hello

        if(list.size > 4) {
            receiverPrefix = Prefix(list[0])
            numeric = retNumeric(list[1].toInt())
            channelMsg = (list[2].toInt() == 1)
            senderPrefix = Prefix(list[3])

            if(list[4] == ":") {
                for(i in 4 until list.size)
                    actualData += list[i]
            } else throw Exception("Error in Base module : Error in parsing message!!!")

            msgdata = MsgData(
                numeric,
                channelMsg,
                senderPrefix,
                receiverPrefix,
                actualData
            )
        }
        else throw Exception("Error in Base module : Error in parsing message!!!")
    }

    var msgdata = MsgData()
    var originalMsg: String = ""
}



