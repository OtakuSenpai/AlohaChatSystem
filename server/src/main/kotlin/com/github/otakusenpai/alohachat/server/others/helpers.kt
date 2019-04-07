package com.github.otakusenpai.alohachat.server.others

import com.github.otakusenpai.alohachat.base.message_parse.ChatMsg

fun segragateInput(input: String?) : MutableList<ChatMsg> {
    var tempList = MutableList<ChatMsg>(0)  { ChatMsg() }

    if(input != null) {
        for(msg in input.split('\n')) {
            tempList.add(ChatMsg(msg))
        }
    } else throw Exception("Error in Server module : Input string is null!!!")
    return tempList
}

