package com.github.blacknblue.alohachat.base.helpers

import com.github.blacknblue.alohachat.base.message_parse.Prefix

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

// Functions

fun matchPrefix(prefix1: Prefix, prefix2: Prefix): Boolean = (prefix1.nick == prefix2.nick && prefix1.ip == prefix2.ip &&
            prefix1.port == prefix2.port && prefix1.originalPrefix == prefix2.originalPrefix)

