package com.github.blacknblue.alohachat.base.message_parse

class ChatMsg {
    constructor(msg: String) {
        originalMsg = msg

    }

    fun parse(data: String) {
        var list = data.split("\\s{1,}".toRegex())
        var datalist = list.subList(4,list.size)
        var senderPrefix = Prefix()
        var receiverPrefix = Prefix()
        var numeric = Numerics.None
        var channelMsg = false
        var data = ""

        for(i in  datalist)
            data = "$i $data"


        if(list[2].compareTo("0") == 0) {
            receiverPrefix = Prefix(list[0], false)
            numeric = retNumeric(list[1].toInt())
            senderPrefix = Prefix(list[3], false)
        } else if(list[2].compareTo("0") != 0) {
            receiverPrefix = Prefix(list[0], true)
            numeric = retNumeric(list[1].toInt())
            senderPrefix = Prefix(list[3], true)
        }
        msgdata = MsgData(
            numeric,
            channelMsg,
            senderPrefix,
            receiverPrefix,
            data
        )
    }

    var msgdata = MsgData()
    lateinit var originalMsg: String
}

data class MsgData(
    val numeric: Numerics = Numerics.None,               // Type of message(Join = 0, Quit = 1, PrivMsg = 2, NickChange = 3
    val channelMsg: Boolean = false      // True for Channel message broadcast, then no senderIP
    val senderPrefix: Prefix = Prefix(),
    val receiverPrefix: Prefix = Prefix(),
    val data: String = String()          // Data is the string to be send
)



