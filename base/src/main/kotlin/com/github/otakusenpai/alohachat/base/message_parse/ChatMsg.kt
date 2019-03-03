package com.github.otakusenpai.alohachat.base.message_parse

// ReceiverPrefix Numeric ChannelMsg SenderPrefix Data
// ReceiverPrefix Numeric ChannelMsg Channel Data

// NickReceiver@ReceiverIP Numeric 0 NickSender@SenderIP : Data
// Neel@109.189.234.10 0 0 Joy@167.103.87.10 : User Neel has joined!!!
// NickReceiver@ReceiverIP Numeric Channel : Data
// Neel@109.189.234.10 2 1 #Cars : Car X cost Rs 50000
// Channel == #nickReceiver-ServerIP
// Each channel broadcasts to all users in the channel. So if user A joins Channel "Cars", it will get hosted on
// a virtual environment inside the server, where each channel lets users join the channel. There will be a global
// list of users plus the number of channels they are in.


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
            receiverPrefix = Prefix(list[0],false)
            numeric = retNumeric(list[1].toInt())
            senderPrefix = Prefix(list[3], false)
        } else if(list[2].compareTo("0") != 0) {
            receiverPrefix = Prefix(list[0],true)
            numeric = retNumeric(list[1].toInt())
            senderPrefix = Prefix(list[3], true)
        }
        msgdata = MsgData(numeric,channelMsg,senderPrefix,receiverPrefix,data)
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

enum class Numerics(val type: Int) {
    None(-1),
    Join(0),
    Quit(1),
    PrivMsg(2),
    NickChange(3);

    override fun toString(): String = this.type.toString()
}

fun retNumeric(numeric: Int) : Numerics {
    var num = when (numeric) {
        0 -> Numerics.Join
        1 -> Numerics.Quit
        2 -> Numerics.PrivMsg
        3 -> Numerics.NickChange
        else -> throw Exception("Error in package Base: Unable to parse the numeric code!!!")
    }
    return num
}