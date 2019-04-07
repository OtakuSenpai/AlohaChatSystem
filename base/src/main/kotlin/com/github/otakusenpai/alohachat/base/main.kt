package com.github.otakusenpai.alohachat.base

import com.github.otakusenpai.alohachat.base.message_parse.ChatMsg

fun main() {
    var msg1 = "Arghya@98.19.67.45:34000 0 0 Avraneel@78.67.34.87:34000 : User Avraneel has joined!!!\n"
    var msg2 = "GroupName@ServerIP:Port 0 1 Avraneel@78.67.34.87:34000 : User Avraneel has joined!!!\n"
    var msg3 = "Arghya@98.19.67.45:34000 1 0 Avraneel@78.67.34.87:34000 : User Avraneel has quit!!!\n"
    var msg4 = "GroupName@ServerIP:Port 1 1 Avraneel@78.67.34.87:34000 : User Avraneel has quit!!!\n"
    var m1 = ChatMsg(msg1)
    m1.print()
    var m2 = ChatMsg(msg2)
    m2.print()
    var m3 = ChatMsg(msg3)
    m3.print()
    var m4 = ChatMsg(msg4)
    m4.print()
}