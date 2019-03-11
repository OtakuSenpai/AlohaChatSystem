package com.github.blacknblue.alohachat.base.message_parse

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