package com.github.otakusenpai.alohachat.base.helpers

import com.github.otakusenpai.alohachat.base.message_parse.Prefix
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.InetAddress
import java.net.URL

enum class Numerics(val type: Int) {
    None(-1),
    Join(0),
    Quit(1),
    PrivMsg(2),
    NickChange(3),
    Part(4);

    override fun toString(): String = this.type.toString()
}

fun retNumeric(numeric: Int) : Numerics {
    var num = when (numeric) {
        0 -> Numerics.Join
        1 -> Numerics.Quit
        2 -> Numerics.PrivMsg
        3 -> Numerics.NickChange
        4 -> Numerics.Part
        else -> throw Exception("Error in module Base: Unable to parse the numeric code!!!")
    }
    return num
}

data class MsgData(
    val numeric: Numerics = Numerics.None,
    val channelMsg: Boolean = false,
    val senderPrefix: Prefix = Prefix(),
    val receiverPrefix: Prefix = Prefix(),
    val data: String = String()
)

fun msgDataToStr(msgData: MsgData) = msgData.receiverPrefix.toString() + " " +
        msgData.numeric.toString() + " " + if(msgData.channelMsg) { "1" } else { "0" } +
        msgData.senderPrefix.toString()



// Functions

fun matchPrefix(prefix1: Prefix, prefix2: Prefix): Boolean = prefix1 == prefix2

fun findPublicIP() : String {
    var publicIP = ""
    try {
        var url_name : URL = URL("http://bot.whatismyipaddress.com")
        var sc = BufferedReader(InputStreamReader(url_name.openStream()))
        publicIP = sc.readLine().trim()
    } catch(e: Exception) {
        throw e
    }
    return publicIP
}

fun parseStrToMsgData(data: String): MsgData {
    var list = data.split("\\s{1,}".toRegex())
    var datalist = list.subList(4,list.size)
    var senderPrefix = Prefix()
    var receiverPrefix = Prefix()
    var numeric = Numerics.None
    var channelMsg = false
    var actualData = ""
    lateinit var msgdata: MsgData

    // Arghya@193.33.34.54:34000 0 1 Avraneel@98.54.33.22:34000 : Hello

    if(list.size > 4) {
        receiverPrefix = Prefix(list[0])
        numeric = retNumeric(list[1].toInt())
        channelMsg = (list[2].toInt() == 1)
        senderPrefix = Prefix(list[3])

        if(list[4] == ":") {
            for(i in 4 until list.size)
                actualData = actualData + " " + list[i]
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
    return msgdata
}

fun findLocalIP() : String {
    var localhost = InetAddress.getLocalHost()
    return localhost.hostAddress.trim()
}